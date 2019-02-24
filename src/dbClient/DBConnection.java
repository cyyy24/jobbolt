package dbClient;

import java.util.List;

import entity.Job;

public interface DBConnection {
	// close the connection
	public void close();
	
	// search jobs with location as lat and lon, keyword(position), company name
	public List<Job> searchItem(String location, String keyword, String company);
	
	// save one item to database
	public void saveItem(Job item);
	
}
