package dbClient;

import java.util.List;

import entity.Item;

public interface DBConnection {
	// close the connection
	public void close();
	
	// search jobs with location as lat and lon, keyword(position), company name
	public List<Item> searchItem(String location, String keyword, String company);
	
	// save one item to database
	public void saveItem(Item item);
	
}
