package jobClient.glassdoorAPI;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Job;
import entity.Job.JobBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jobClient.JobSearch;

public class GlassdoorAPI implements JobSearch {

	// API Request looks like this: 
	// http://api.glassdoor.com/api/api.htm?
	// v=1&format=json&t.p=120&t.k=fz6JLNDfgVs&action=employers
	// &q=pharmaceuticals&userip=192.168.43.42&useragent=Mozilla/%2F4.0
	
	
	// <<--- Better query url like this --->>
	// http://api.glassdoor.com/api/api.htm?t.p=55571&t.k=dq1mX87IgI&userip=0.0.0.0
	// &format=json&v=1&action=jobs&countryId=1&city=san+jose
	// &employer=facebook&jobTitle=software+engineer+intern&p=1
	
	private static String url = "http://api.glassdoor.com/api/api.htm";
	private static String format = "json";
	// Your partner id, as assigned by Glassdoor
	private static String partnerId = "55571"; 
	// Your partner key, as assigned by Glassdoor
	private static String partnerKey = "dq1mX87IgI"; 
	// The particular API call that you would like to make - see jobs, reviews, salaries,
	// etc. sub-sections for details
	private static String action = "jobs"; 
	private static String userAgent = "Mozilla/%2F4.0";
	private int totalPageCount = Integer.MIN_VALUE;
	private static String companyRootUrl = "https://www.glassdoor.com";
	private static String platformString = "glassdoor";
	
	private static int limitPageNum = 2;
	
	private String getIPAddress(String location) {
		String ip = "0.0.0.0"; // Los Angeles
		return ip;
	}
	
	private String dropSpace(String s) {
		if (s == null || s.length() == 0) {
			return new String("");
		}
		char[] array = s.toCharArray();
		int slow = 0;
		int fast = 0;
		while (fast < s.length()) {
			if (array[fast] != ' ') {
				array[slow++] = array[fast++];
			} else {
				if (slow != 0 && array[slow - 1] != '+') {
					array[slow++] = '+';
				}
				fast++;
			}
		}
		return new String(array, 0, slow);
	}
	
	@Override
	public List<Job> search(String location, String keyword, String company) {
		
		// keyword -> title
		// location -> city
		// company -> employer
		
		List<Job> result = new ArrayList<>();
		
		// keyword MUST be NOT NULL
		
		if (keyword == null || keyword.length() == 0) {
			System.out.println("keyword cannot be null!");
			return result;
		}
		
		// eliminate space
		location = dropSpace(location);
		keyword = dropSpace(keyword);
		company = dropSpace(company);
		
		String ipAddress = getIPAddress(location);
		String currentPage = "1";
		// http://api.glassdoor.com/api/api.htm?t.p=55571&t.k=dq1mX87IgI&v=1&userip=0.0.0.0
		// &format=json&action=jobs&countryId=1&city=san+jose
		// &employer=facebook&jobTitle=software+engineer+intern&p=1
//		http://api.glassdoor.com/api/api.htm?t.p=55571&t.k=dq1mX87IgI&v=1&userip=0.0.0.0&format=json&action=jobs&countryId=1&city=san+jose&employer=facebook&jobTitle=software+engineer+intern&p=1
		String query = String.format("t.p=%s&t.k=%s&v=1&userip=%s&format=%s&action=%s&countryId=1&p=%s",
								partnerId, partnerKey, ipAddress, format, action, currentPage 
								);
		String locationQuery = "";
		String keywordQuery = "";
		String companyQuery = "";
		if (location != null) {
			locationQuery = String.format("&city=%s", location);
		}
		if (keyword != null) {
			keywordQuery = String.format("&jobTitle=%s", keyword);
		}
		if (companyQuery != null) {
			companyQuery = String.format("&employer=%s", company);
		}
		query = query + locationQuery + keywordQuery + companyQuery;
		
		
		String fullUrl = url + "?" + query;
		System.out.println("url = " + fullUrl);
		
		try {
			HttpURLConnection connection = (HttpURLConnection)new URL(fullUrl).openConnection();
			connection.setRequestMethod("GET");
			
			int responseCode = connection.getResponseCode();
			System.out.println("Glassdoor connection reponse code = " + responseCode);
			if (responseCode != 200) {			
				System.out.println("failed.");
				return result;
			}
			
			// read the 1st page
			readItems(connection, result);
			
			// if total page num is larger than 1 and smaller than 20,
			// then continue send requests to get continuing page results, page2, page3, page4 ....
			if (this.totalPageCount > 1) {
				int currentPageCount = 2;
				while (currentPageCount <= this.totalPageCount && currentPageCount <= this.limitPageNum) {
					query = String.format("t.p=%s&t.k=%s&v=1&userip=%s&format=%s&action=%s&countryId=1&city="
							+ "%s&employer=%s&jobTitle=%s&p=%s",
							partnerId, partnerKey, ipAddress, format, action, location, 
							company, keyword, String.valueOf(currentPageCount));
					fullUrl = url + "?" + query;
					System.out.println("url = " + fullUrl);
					connection = (HttpURLConnection)new URL(fullUrl).openConnection();
					connection.setRequestMethod("GET");
					
					responseCode = connection.getResponseCode();
					if (responseCode != 200) {			
						System.out.println("Failed when connected to glassdoor, already read " 
								+ String.valueOf(currentPageCount) + " pages. ");
						return result;
					}
					readItems(connection, result);
					currentPageCount++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private void readItems(HttpURLConnection connection, List<Job> result) {
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			
			if (response.length() == 0) {
				System.out.println("response is empty");
				return;
			}
			
			JSONObject obj = new JSONObject(response.toString());
			if (!obj.isNull("response")) {
				JSONObject resp = obj.getJSONObject("response");
				if (!resp.isNull("totalNumberOfPages")) {
					if (totalPageCount == Integer.MIN_VALUE) {
						this.totalPageCount =  resp.getInt("totalNumberOfPages");
					}
				}
				if (!resp.isNull("jobListings")) {
					JSONArray jobs = resp.getJSONArray("jobListings");
					for (int i = 0; i < jobs.length(); i++) {
						JobBuilder builder = new JobBuilder();
						JSONObject job = jobs.getJSONObject(i);
						if (!job.isNull("jobListingId")) {
							String jobId = String.valueOf(job.getInt("jobListingId"));
							builder.setJobId(jobId);
						}
						
						builder.setPlatform(this.platformString);
						
						if (!job.isNull("jobTitle")) {
							builder.setJobTitle(job.getString("jobTitle"));
						}
						if (!job.isNull("employer") && !job.getJSONObject("employer").isNull("name")) {
							String company = job.getJSONObject("employer").getString("name");
							builder.setCompany(company);
						}
						if (!job.isNull("jobViewUrl")) {
							String jobUrl = this.companyRootUrl + job.getString("jobViewUrl");
							builder.setUrl(jobUrl);
						}
						if (!job.isNull("location")) {
							builder.setLocation(job.getString("location"));
						}
						if (!job.isNull("jobCategory")) {
							builder.setCategory("glassdoor category " + String.valueOf(job.getInt("jobCategory")));
						}
						if (!job.isNull("descriptionFragment")) {
							
							builder.setDescription(job.getString("descriptionFragment"));
						}
						result.add(builder.build());
					}
 				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
