package jobClient.IndeedAPI;

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

public class IndeedAPI implements JobSearch {

	// API Request looks like this:
	// http://api.glassdoor.com/api/api.htm?
	// v=1&format=json&t.p=120&t.k=fz6JLNDfgVs&action=employers
	// &q=pharmaceuticals&userip=192.168.43.42&useragent=Mozilla/%2F4.0


	// <<--- Better query url like this --->>
	// http://api.glassdoor.com/api/api.htm?t.p=55571&t.k=dq1mX87IgI&userip=0.0.0.0
	// &format=json&v=1&action=jobs&countryId=1&city=san+jose
	// &employer=facebook&jobTitle=software+engineer+intern&p=1



	// Indeed API: http://api.indeed.com/ads/apisearch?

	private static String url = "http://api.indeed.com/ads/apisearch";
	private static String format = "json";
	// Publisher key in url
	private static String publisher = "4548195452860771";

	// The particular API call that you would like to make - see jobs, reviews, salaries,
	// etc. sub-sections for details
	//private static String action = "jobs";
	private static String useragent = "Mozilla/%2F4.0";
	private int totalPageCount = Integer.MIN_VALUE;
	private static String platformPrefix = "id";
	private static String companyRootUrl = "https://www.indeed.com";

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

		// eliminate space
		location = dropSpace(location);
		keyword = dropSpace(keyword);
		company = dropSpace(company);

		String ipAddress = getIPAddress(location);
		String currentPage = "1";
		// http://api.glassdoor.com/api/api.htm?t.p=55571&t.k=dq1mX87IgI&v=1&userip=0.0.0.0
		// &format=json&action=jobs&countryId=1&city=san+jose
		// &employer=facebook&jobTitle=software+engineer+intern&p=1
		//String query = String.format("t.p=%s&t.k=%s&v=1&userip=%s&format=%s&action=%s&countryId=1&city="
		//						+ "%s&employer=%s&jobTitle=%s&p=%s",
		//						partnerId, partnerKey, ipAddress, format, action, location,
		//						company, keyword, currentPage);
		String query = String.format("publisher=%s&v=2&userip=%s&format=%s&co=us&l="
						+ "%s&employer=%s&jobTitle=%s&p=%s",
				publisher, ipAddress, format, location,
				company, keyword, currentPage);

		String fullUrl = url + "?" + query;
		System.out.println("url = " + fullUrl);

		try {
			HttpURLConnection connection = (HttpURLConnection)new URL(fullUrl).openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			System.out.println("Indeed connection reponse code = " + responseCode);
			if (responseCode != 200) {
				System.out.println("failed.");
				return result;
			}

			// read the 1st page
			readItems(connection, result);
			// if total page num is larger than 1, then continue send requests to get new page results
			if (this.totalPageCount > 1) {
				int currentPageCount = 2;
				while (currentPageCount <= this.totalPageCount) {
					query = String.format("publisher%s&v=2&userip=%s&format=%s&co=us&l="
									+ "%s&employer=%s&jobTitle=%s&p=%s",
							publisher, ipAddress, format, location,
							company, keyword, String.valueOf(currentPageCount));
					fullUrl = url + "?" + query;
					//System.out.println("url = " + fullUrl);
					connection = (HttpURLConnection)new URL(fullUrl).openConnection();
					connection.setRequestMethod("GET");

					responseCode = connection.getResponseCode();
					if (responseCode != 200) {
						System.out.println("Failed when connected to Indeed, already read "
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

			JSONObject obj = new JSONObject(response.toString());
//			if (!obj.isNull("response")) {
//				JSONObject resp = obj.getJSONObject("response");
//				if (!resp.isNull("totalNumberOfPages")) {
//					if (totalPageCount == Integer.MIN_VALUE) {
//						this.totalPageCount =  resp.getInt("totalNumberOfPages");
//					}
//				}
			//if (!resp.isNull("results")) {
			//obj.getInt("totalResults"

			if (!obj.isNull("totalResults")) {

				this.totalPageCount =  Math.min(40, obj.getInt("totalResults")) ;
			}

			if (!obj.isNull("results")) {
				JSONArray jobs = obj.getJSONArray("results");
				for (int i = 0; i < jobs.length(); i++) {
					JobBuilder builder = new JobBuilder();
					JSONObject job = jobs.getJSONObject(i);
					if (!job.isNull("jobkey")) {
						String jobId = this.platformPrefix + String.valueOf(job.getString("jobkey"));
						builder.setJobId(jobId);
					}
					builder.setPlatform("indeed");

					if (!job.isNull("jobtitle")) {
						builder.setJobTitle(job.getString("jobtitle"));
					}
					if (!job.isNull("company")) {
						String company = job.getString("company");
						builder.setCompany(company);
					}
					if (!job.isNull("jobViewUrl")) {
						String jobUrl = this.companyRootUrl + job.getString("url");
						builder.setUrl(jobUrl);
					}
					if (!job.isNull("city")) {
						builder.setLocation(job.getString("city"));
					}
					//if (!job.isNull("jobCategory")) {
					builder.setCategory("1");
					//}
					result.add(builder.build());
				}
				//}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}



}
package jobClient.IndeedAPI;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Item;
import entity.Item.ItemBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jobClient.JobSearch;

public class IndeedAPI implements JobSearch {

	// API Request looks like this: 
	// http://api.glassdoor.com/api/api.htm?
	// v=1&format=json&t.p=120&t.k=fz6JLNDfgVs&action=employers
	// &q=pharmaceuticals&userip=192.168.43.42&useragent=Mozilla/%2F4.0
	
	
	// <<--- Better query url like this --->>
	// http://api.glassdoor.com/api/api.htm?t.p=55571&t.k=dq1mX87IgI&userip=0.0.0.0
	// &format=json&v=1&action=jobs&countryId=1&city=san+jose
	// &employer=facebook&jobTitle=software+engineer+intern&p=1
	
	
	
	// Indeed API: http://api.indeed.com/ads/apisearch?
	
	private static String url = "http://api.indeed.com/ads/apisearch";
	private static String format = "json";
	// Publisher key in url
	private static String publisher = "4548195452860771"; 

	// The particular API call that you would like to make - see jobs, reviews, salaries,
	// etc. sub-sections for details
	//private static String action = "jobs"; 
	private static String useragent = "Mozilla/%2F4.0";
	private int totalPageCount = Integer.MIN_VALUE;
	private static String platformPrefix = "id_"; 
	private static String companyRootUrl = "https://www.indeed.com";
	
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
	public List<Item> search(String location, String keyword, String company) {
		
		// keyword -> title
		// location -> city
		// company -> employer
		
		List<Item> result = new ArrayList<>();
		
		// eliminate space
		location = dropSpace(location);
		keyword = dropSpace(keyword);
		company = dropSpace(company);
		
		String ipAddress = getIPAddress(location);
		String currentPage = "1";
		// http://api.glassdoor.com/api/api.htm?t.p=55571&t.k=dq1mX87IgI&v=1&userip=0.0.0.0
		// &format=json&action=jobs&countryId=1&city=san+jose
		// &employer=facebook&jobTitle=software+engineer+intern&p=1
		//String query = String.format("t.p=%s&t.k=%s&v=1&userip=%s&format=%s&action=%s&countryId=1&city="
		//						+ "%s&employer=%s&jobTitle=%s&p=%s",
		//						partnerId, partnerKey, ipAddress, format, action, location, 
		//						company, keyword, currentPage);
		String query = String.format("publisher=%s&v=2&userip=%s&format=%s&co=us&l="
				+ "%s&employer=%s&jobTitle=%s&p=%s",
				publisher, ipAddress, format, location, 
				company, keyword, currentPage);
		
		String fullUrl = url + "?" + query;
		System.out.println("url = " + fullUrl);
		
		try {
			HttpURLConnection connection = (HttpURLConnection)new URL(fullUrl).openConnection();
			connection.setRequestMethod("GET");
			
			int responseCode = connection.getResponseCode();
			System.out.println("Indeed connection reponse code = " + responseCode);
			if (responseCode != 200) {			
				System.out.println("failed.");
				return result;
			}
			
			// read the 1st page
			readItems(connection, result);
			// if total page num is larger than 1, then continue send requests to get new page results
			if (this.totalPageCount > 1) {
				int currentPageCount = 2;
				while (currentPageCount <= this.totalPageCount) {
					query = String.format("publisher%s&v=2&userip=%s&format=%s&co=us&l="
							+ "%s&employer=%s&jobTitle=%s&p=%s",
							publisher, ipAddress, format, location, 
							company, keyword, String.valueOf(currentPageCount));
					fullUrl = url + "?" + query;
					//System.out.println("url = " + fullUrl);
					connection = (HttpURLConnection)new URL(fullUrl).openConnection();
					connection.setRequestMethod("GET");
					
					responseCode = connection.getResponseCode();
					if (responseCode != 200) {			
						System.out.println("Failed when connected to Indeed, already read " 
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
	
	private void readItems(HttpURLConnection connection, List<Item> result) {
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			
			JSONObject obj = new JSONObject(response.toString());
//			if (!obj.isNull("response")) {
//				JSONObject resp = obj.getJSONObject("response");
//				if (!resp.isNull("totalNumberOfPages")) {
//					if (totalPageCount == Integer.MIN_VALUE) {
//						this.totalPageCount =  resp.getInt("totalNumberOfPages");
//					}
//				}
				//if (!resp.isNull("results")) {
			//obj.getInt("totalResults"
			
			    if (!obj.isNull("totalResults")) {
			    	
			    	this.totalPageCount =  Math.min(40, obj.getInt("totalResults")) ;
			    }
			    
				if (!obj.isNull("results")) {	
					JSONArray jobs = obj.getJSONArray("results");
					for (int i = 0; i < jobs.length(); i++) {
						ItemBuilder builder = new ItemBuilder();
						JSONObject job = jobs.getJSONObject(i);
						if (!job.isNull("jobkey")) {
							String jobId = this.platformPrefix + String.valueOf(job.getString("jobkey"));
							builder.setJobId(jobId);
						}
						builder.setPlatform("indeed");
						
						if (!job.isNull("jobtitle")) {
							builder.setTitle(job.getString("jobtitle"));
						}
						if (!job.isNull("company")) {
							String company = job.getString("company");
							builder.setCompany(company);
						}
						if (!job.isNull("jobViewUrl")) {
							String jobUrl = this.companyRootUrl + job.getString("url");
							builder.setUrl(jobUrl);
						}
						if (!job.isNull("city")) {
							builder.setLocation(job.getString("city"));
						}
						//if (!job.isNull("jobCategory")) {
							builder.setCategory("1");
						//}
						result.add(builder.build());
					}
 				//}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
