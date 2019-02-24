package jobClient;

import org.json.JSONException;
import org.json.JSONObject;

import entity.Job;

import java.util.List;

import org.json.JSONArray;


public interface JobSearch {
	
	public List<Job> search(String location, String keyword, String company);
	
}
