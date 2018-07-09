package dataaccessobjects;

public class TransactionManager
{
	public static IDataAccessTransaction createTransaction() throws Exception
	{
		return new DataAccessTransaction();
	}
}
