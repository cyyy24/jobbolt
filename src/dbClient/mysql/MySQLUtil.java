package dbClient.mysql;

public class MySQLUtil {
	private static final String HOSTNAME = "localhost";
	private static final String PORT_NUMBER = "3306";
	public static final String DB_NAME = "yourjobs";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "822511";
	public static final String URL = "jdbc:mysql://"
			+ HOSTNAME + ":" + PORT_NUMBER + "/" + DB_NAME
			+ "?user=" + USERNAME + "&password=" + PASSWORD
			+ "&autoReconnect=true&serverTimezone=UTC";
}
