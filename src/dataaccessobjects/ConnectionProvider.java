package dataaccessobjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider
{
	private static String _connectionString;
	private static String _username;
	private static String _password;

	public static void setConnectionString(String connectionString, String username, String password)
	{
		ConnectionProvider._connectionString = connectionString;
		ConnectionProvider._username = username;
		ConnectionProvider._password = password;
	}

	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(ConnectionProvider._connectionString, ConnectionProvider._username, ConnectionProvider._password);
	}
}
