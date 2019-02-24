package dbClient.mysql;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

//import dbClient.mysql.MySQLUtil;

public class MySQLTableReset {
	public static void main(String[] args) {
		try {
			// connect to database
			System.out.println("Connecting to " + MySQLUtil.URL);
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			Connection conn = DriverManager.getConnection(MySQLUtil.URL);
			
			if (conn == null) {
				return;
			}
			
			Statement statement = conn.createStatement();
			
			// drop old tables
			String sql = "DROP TABLE IF EXISTS jobs";
			statement.executeUpdate(sql);
			
			// create tables
			sql = "CREATE TABLE jobs ("
					+ "job_id VARCHAR(255) NOT NULL,"
					+ "platform VARCHAR(255),"
					+ "title VARCHAR(255),"
					+ "company VARCHAR(255),"
					+ "url VARCHAR(255),"
					+ "location VARCHAR(255),"
					+ "category VARCHAR(255),"
					+ "PRIMARY KEY (job_id)"
					+ ")";
			statement.executeUpdate(sql);
			
			conn.close();
			System.out.println("database tables reset successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
