package dataaccessobjects;

import java.sql.Connection;

class DataAccessTransaction implements IDataAccessTransaction
{
	private Connection _conn;
	private boolean _committedOrRolledBack;
	private boolean _disposed;

	private void setCommittedOrRolledBack(boolean v)
	{
		this._committedOrRolledBack = v;
	}

	private void setDisposed(boolean v)
	{
		this._disposed = v;
	}

	public Connection getConnection()
	{
		return this._conn;
	}

	public DataAccessTransaction() throws Exception 
	{
		this._conn = ConnectionProvider.getConnection();
		this.setCommittedOrRolledBack(false);
		this.setDisposed(false);

		this._conn.setAutoCommit(false);
	}

	@Override
	public void commit() throws Exception 
	{
		this._conn.commit();
		this.setCommittedOrRolledBack(true);
		this.close();
	}

	@Override
	public void rollback() throws Exception 
	{
		this._conn.rollback();
		this.setCommittedOrRolledBack(true);
		this.close();
	}

	@Override
	public void close() throws Exception 
	{
		if(this._disposed) return;
		if(!this._committedOrRolledBack) this._conn.rollback();

		this._conn.close();

		this.setDisposed(true);
	}
}
