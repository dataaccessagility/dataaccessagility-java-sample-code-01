package dataaccessobjects;

public interface IDataAccessTransaction extends AutoCloseable
{
	void commit() throws Exception;
	void rollback() throws Exception;
}
