package dataaccessobjects;

import java.sql.Connection;

class DataAccessTransaction implements IDataAccessTransaction
{
	private Connection connection;
	private boolean committedOrRolledBack;
	private boolean disposed;

	private void setCommittedOrRolledBack(boolean v)
	{
		this.committedOrRolledBack = v;
	}

	private void setDisposed(boolean v)
	{
		this.disposed = v;
	}

	public Connection getConnection()
	{
		return this.connection;
	}

	public DataAccessTransaction() throws Exception 
	{
		this.connection = ConnectionProvider.getConnection();
		this.setCommittedOrRolledBack(false);
		this.setDisposed(false);

		this.connection.setAutoCommit(false);
	}

	@Override
	public void commit() throws Exception 
	{
		this.connection.commit();
		this.setCommittedOrRolledBack(true);
		this.close();
	}

	@Override
	public void rollback() throws Exception 
	{
		this.connection.rollback();
		this.setCommittedOrRolledBack(true);
		this.close();
	}

	@Override
	public void close() throws Exception 
	{
		if (this.disposed) return;
		if (!this.committedOrRolledBack) this.connection.rollback();

		ConnectionProvider.close(this.connection);

		this.setDisposed(true);
	}
}
