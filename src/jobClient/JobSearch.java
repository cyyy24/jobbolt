package jobClient;

import org.json.JSONException;
import org.json.JSONObject;

import entity.Item;

import java.util.List;

import org.json.JSONArray;


public interface JobSearch {
	
	public List<Item> search(String location, String keyword, String company);
	
}
