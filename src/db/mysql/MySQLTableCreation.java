package db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySQLTableCreation {
	//run this application to reset db schema
	public static void main(String[] args) {
		try {
			
			//connect to MySQL
			System.out.println("Connecting to " + MySQLDBUtil.URL);
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);
			if(conn == null) {
				return;
			}
			
			//2 drop tables in case they exist
			Statement statement = conn.createStatement();
			String sql = "DROP TABLE IF EXISTS categories";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS history";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS jobs";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS users";
			statement.executeUpdate(sql);
			
			//3 create tables
			sql = "CREATE TABLE jobs ("
					+ "job_id VARCHAR(255) NOT NULL,"
					+ "platform VARCHAR(255) NOT NULL,"
					+ "job_title VARCHAR(255),"
					+ "company VARCHAR(255),"
					+ "url VARCHAR(255),"
					+ "location VARCHAR(255),"
					+ "category VARCHAR(255),"
					+ "description VARCHAR(255),"
					//+ "query VARCHAR(255),"
					+ "PRIMARY KEY (job_id, platform)"
					+ ")";
			statement.executeUpdate(sql);
			
			sql = "CREATE TABLE users ("
					+ "user_id VARCHAR(255) NOT NULL,"
					+ "password VARCHAR(255) NOT NULL,"
					+ "email VARCHAR(255) NOT NULL,"
					+ "first_name VARCHAR(255),"
					+ "last_name VARCHAR(255),"
					+ "PRIMARY KEY (user_id)"
					+ ")";
			statement.executeUpdate(sql);
			
			sql = "CREATE TABLE categories ("
					+ "job_id VARCHAR(255) NOT NULL,"
					+ "job_title VARCHAR(255) NOT NULL,"
					+ "PRIMARY KEY (job_id, job_title),"
					+ "FOREIGN KEY (job_id) REFERENCES jobs(job_id)"
					+ ")";
			statement.executeUpdate(sql);
			
			sql = "CREATE TABLE history ("
					+ "user_id VARCHAR(255) NOT NULL,"
					+ "job_id VARCHAR(255) NOT NULL,"
					+ "saved BOOLEAN,"
					+ "status VARCHAR(255),"
					+ "last_favor_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
					+ "PRIMARY KEY (user_id, job_id),"
					+ "FOREIGN KEY (user_id) REFERENCES users(user_id),"
					+ "FOREIGN KEY (job_id) REFERENCES jobs(job_id)"
					+ ")";
			statement.executeUpdate(sql);
			
						
			
			System.out.println("Import done successfully");
			
			//for test, should be deleted later
			sql = "INSERT INTO users VALUES('1111', '1234', '123@gmail.com','John', 'Smith')";
			statement.executeUpdate(sql);
			
			sql = "INSERT INTO jobs VALUES('job1', 'google', 'java Developer', 'Google', 'http://jjjjkkkk', 'LA', 'c0', 'description')";
			statement.executeUpdate(sql);
			
			sql = "INSERT INTO history VALUES('1111', 'job1', true, 'Pending', CURRENT_TIMESTAMP)";
			statement.executeUpdate(sql);
			
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
