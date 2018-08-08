package dataaccessobjects;

import java.sql.*;
import models.*;

public class DivisionData
{
	/**
	* Inserts a single record into database. The values inserted are <code>{@link Division#id id}</code>, <code>{@link Division#parentId parentId}</code>, <code>{@link Division#name name}</code>
	* @param obj <code>{@link Division}</code> object
	* @return Number of rows affected
	*/
	public static int addDivision(Division obj) throws Exception
	{
		int retVal;

		obj.validate();

		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("INSERT INTO HR_DIVISION (DIVISION_ID, DIVISION_NAME, DIVISION_PARENT_ID)  VALUES (?, ?, ?)  ;    "))
		{
			ps.setInt(1, obj.getId());
			ps.setString(2, obj.getName());
			if (!obj.isNull(Division.PARENTID_BIT_FLAG)) ps.setInt(3, obj.getParentId());
			else ps.setNull(3, Types.INTEGER);

			retVal = ps.executeUpdate();

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Inserts a single record into database. The values inserted are <code>{@link Division#id id}</code>, <code>{@link Division#parentId parentId}</code>, <code>{@link Division#name name}</code>
	* @param txn A database transaction that will be associated with this data access operation
	* @param obj <code>{@link Division}</code> object
	* @return Number of rows affected
	*/
	public static int addDivision(IDataAccessTransaction txn, Division obj) throws Exception
	{
		int retVal;

		obj.validate();

		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("INSERT INTO HR_DIVISION (DIVISION_ID, DIVISION_NAME, DIVISION_PARENT_ID)  VALUES (?, ?, ?)  ;    "))
		{
			ps.setInt(1, obj.getId());
			ps.setString(2, obj.getName());
			if (!obj.isNull(Division.PARENTID_BIT_FLAG)) ps.setInt(3, obj.getParentId());
			else ps.setNull(3, Types.INTEGER);

			retVal = ps.executeUpdate();

			return retVal;
		}
	}

}
