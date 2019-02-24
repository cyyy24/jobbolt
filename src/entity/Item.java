package entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Item {
	private String jobId;
	private String platform;
	private String title;
	private String company;
	private String url;
	private String location;
	private String category;
	
	public static class ItemBuilder {
		private String jobId;
		private String platform;
		private String title;
		private String company;
		private String url;
		private String location;
		private String category;
		
		public void setJobId(String jobId) {
			this.jobId = jobId;
		}
		public void setPlatform(String platform) {
			this.platform = platform;
		}
		public void setTitle(String title) {
			this.title = title;
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
		
		public Item build() {
			return new Item(this);
		}
		
	}
	
	private Item(ItemBuilder builder) {
		this.jobId = builder.jobId;
		this.platform = builder.platform;
		this.title = builder.title;
		this.company = builder.company;
		this.url = builder.url;
		this.location = builder.location;
		this.category = builder.category;
		
	}
	
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("job_id", this.jobId)
				.put("platform", this.platform)
				.put("title", this.title)
				.put("company", this.company)
				.put("url", this.url)
				.put("location", this.location)
				.put("category", this.category);
		} catch(JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public String getJobId() {
		return this.jobId;
	}
	public String getPlatform() {
		return this.platform;
	}
	public String getTitle() {
		return this.title;
	}
	public String getCompany() {
		return this.company;
	}
	public String getUrl() {
		return this.url;
	}
	public String getLocation() {
		return this.location;
	}
	public String getCategory() {
		return this.category;
	}
	
}
