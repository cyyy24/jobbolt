package dbClient.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import dbClient.DBConnection;
import entity.Item;
import jobClient.glassdoorAPI.GlassdoorAPI;


public class MySQLConnection implements DBConnection {

	private Connection conn;
	
	public MySQLConnection() {
		try {
			System.out.println("Connecting to " + MySQLUtil.URL);
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			conn = DriverManager.getConnection(MySQLUtil.URL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		try {
			if (conn != null) {
				conn.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Item> searchItem(String location, String keyword, String company) {
		GlassdoorAPI ga = new GlassdoorAPI();
		List<Item> results = ga.search(location, keyword, company);
		for (Item item : results) {
			saveItem(item);
		}
		return results;
	}

	@Override
	public void saveItem(Item item) {
		if (conn == null) {
			System.err.println("DB connction failed");
			return;
		}
		try {
			String sql = "INSERT IGNORE INTO jobs VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  item.getJobId());
			ps.setString(2, item.getPlatform());
			ps.setString(3, item.getTitle());
			ps.setString(4, item.getCompany());
			ps.setString(5, item.getUrl());
			ps.setString(6,  item.getLocation());
			ps.setString(7, item.getCategory());
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
