package entity;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Job {
	private String jobId;
	private String platform;
	private String jobTitle;
	private String company;
	private String url;
	private String location;
	private String category;
	private String description;
	
	
	
	private Job(JobBuilder builder) {
		this.jobId = builder.jobId;
		this.platform = builder.platform;
		this.jobTitle = builder.jobTitle;
		this.company = builder.company;
		this.url = builder.url;
		this.location = builder.location;
		this.category = builder.category;
		this.description = builder.description;
		
		
	}
	
	public String getJobId() {
		return jobId;
	}


	public String getPlatform() {
		return platform;
	}


	public String getJobTitle() {
		return jobTitle;
	}


	public String getCompany() {
		return company;
	}


	public String getUrl() {
		return url;
	}


	public String getLocation() {
		return location;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getDescription() {
		return description;
	}

	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("jobId", jobId);
			obj.put("platform", platform);
			obj.put("job_title", jobTitle);
			obj.put("company", company);
			obj.put("url", url);
			obj.put("location", location);
			obj.put("category", category);
			obj.put("description", description);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public static class JobBuilder{
		private String jobId;
		private String platform;
		private String jobTitle;
		private String company;
		private String url;
		private String location;
		private String category;
		private String description;

		public void setJobId(String jobId) {
			this.jobId = jobId;
		}

		public void setPlatform(String platform) {
			this.platform = platform;
		}

		public void setJobTitle(String job_title) {
			this.jobTitle = job_title;
		}

		public void setCompany(String company) {
			this.company = company;
		}
		
		public void setUrl(String url) {
			this.url = url;
		}
		
		public void setLocation(String location) {
			this.location = location;
		}
		
		public void setCategory(String category) {
			this.category = category;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}

		public Job build() {
			return new Job(this);
		}	
	}


}
