package dbClient;

import dbClient.DBConnection;

public class DBConnectionFactory {
	
	private static final String DEFAULT_DB = "mysql";
	
	public static DBConnection getConnection(String db) {
		if (db == "mysql") {
			//return MySQLConnection();
		}
		return null;
	}
	
	public static DBConnection getConnection() {
		return null;
	}
	
}
