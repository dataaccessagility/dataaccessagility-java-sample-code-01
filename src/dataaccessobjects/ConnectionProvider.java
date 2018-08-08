package dataaccessobjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConnectionProvider
{
	static class PooledConnection
	{
		private Connection connection;
		private long creationTime;
		private boolean isBusy;

		public Connection getConnection()
		{
			return connection;
		}

		public long getCreationTime()
		{
			return creationTime;
		}

		public boolean getIsBusy()
		{
			return isBusy;
		}

		public void setIsBusy(boolean val)
		{
			isBusy = val;
		}

		public PooledConnection(Connection connection, long creationTime)
		{
			this.connection = connection;
			this.creationTime = creationTime;
			this.isBusy = false;
		}
	}

	private static List<PooledConnection> connections;
	private static String connectionString;
	private static String username;
	private static String password;
	private static long lifetime;

	public static void setParameters(String connectionString, String username, String password, int maxPoolSize, long lifetime) throws SQLException
	{
		ConnectionProvider.connectionString = connectionString;
		ConnectionProvider.username = username;
		ConnectionProvider.password = password;
		ConnectionProvider.lifetime = lifetime;

		if (connections != null)
		{
			synchronized (connections)
			{
				for (int i = 0; i < connections.size(); i++)
				{
					if (!connections.get(i).getIsBusy() && !connections.get(i).getConnection().isClosed()) connections.get(i).getConnection().close();
				}
			}
		}

		connections = new ArrayList<>(maxPoolSize);

		synchronized (connections)
		{
			for (int i = 0; i < maxPoolSize; i++)
			{
				Connection conn = DriverManager.getConnection(ConnectionProvider.connectionString, ConnectionProvider.username, ConnectionProvider.password);
				connections.add(new PooledConnection(conn, (new Date()).getTime()));
			}
		}
	}

	public static void setParameters(String connectionString, String username, String password) throws SQLException
	{
		setParameters(connectionString, username, password, 10, 3600);
	}

	public static Connection getConnection() throws Exception
	{
		synchronized (connections)
		{
			for (int j = connections.size() - 1; j >= 0; j--)
			{
				PooledConnection pooledConnection = connections.get(j);
				if (pooledConnection.getIsBusy()) continue;
				pooledConnection.setIsBusy(true);
				return pooledConnection.getConnection();
			}

			throw new Exception("No more connections exist in the pool");
		}
	}

	public static void close(Connection connection) throws Exception
	{
		synchronized (connections)
		{
			for (int j = connections.size() - 1; j >= 0; j--)
			{
				PooledConnection pooledConnection = connections.get(j);

				if (pooledConnection.getConnection() == connection)
				{
					pooledConnection.setIsBusy(false);
					return;
				}
			}

			connection.close();
		}
	}
}
