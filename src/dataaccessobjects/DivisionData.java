package dataaccessobjects;

import java.sql.*;
import models.*;

public class DivisionData
{
	/**
	* Inserts a single record into database. The values inserted are <code>{@link Division#id id}</code>, <code>{@link Division#name name}</code>, <code>{@link Division#parentDivisionId parentDivisionId}</code>
	* @param obj <code>{@link Division}</code> object
	* @return Number of rows affected
	*/
	public static int addDivision(Division obj) throws Exception
	{
		int retVal;

		obj.validate();

		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement("INSERT INTO HR_Division (DIVISION_ID, DIVISION_NAME, PARENT_DIVISION_ID)  VALUES (?, ?, ?)  ;    "))
		{
			ps.setInt(1, obj.getId());
			ps.setString(2, obj.getName());
			ps.setInt(3, obj.getParentDivisionId());

			retVal = ps.executeUpdate();

			return retVal;
		}
	}

	/**
	* Inserts a single record into database. The values inserted are <code>{@link Division#id id}</code>, <code>{@link Division#name name}</code>, <code>{@link Division#parentDivisionId parentDivisionId}</code>
	* @param txn A database transaction that will be associated with this data access operation
	* @param obj <code>{@link Division}</code> object
	* @return Number of rows affected
	*/
	public static int addDivision(IDataAccessTransaction txn, Division obj) throws Exception
	{
		int retVal;

		obj.validate();

		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("INSERT INTO HR_Division (DIVISION_ID, DIVISION_NAME, PARENT_DIVISION_ID)  VALUES (?, ?, ?)  ;    "))
		{

			ps.setInt(1, obj.getId());
			ps.setString(2, obj.getName());
			ps.setInt(3, obj.getParentDivisionId());

			retVal = ps.executeUpdate();

			return retVal;
		}
	}

	/**
	* Retrieves multiple records from database. The values returned for each record are <code>{@link Division#id id}</code>, <code>{@link Division#name name}</code>, <code>{@link Division#parentDivisionId parentDivisionId}</code>
	* @return Objects retrieved from database
	*/
	public static java.util.ArrayList<Division> getAllDivisions() throws Exception
	{
		java.util.ArrayList<Division> retVal;


		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement("SELECT DIVISION_ID, DIVISION_NAME, PARENT_DIVISION_ID  FROM HR_Division  ORDER BY DIVISION_NAME ASC  ;  "))
		{

			retVal = new java.util.ArrayList<Division>();

			if (!ps.execute()) return retVal;

			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{
				Division obj = new Division();

				obj.setId(rs.getInt("DIVISION_ID"));
				obj.setName(rs.getString("DIVISION_NAME"));
				obj.setParentDivisionId(rs.getInt("PARENT_DIVISION_ID"));

				retVal.add(obj);
			}

			return retVal;
		}
	}

	/**
	* Retrieves a single record from database. The values returned are <code>{@link Division#id id}</code>, <code>{@link Division#name name}</code>, <code>{@link Division#parentDivisionId parentDivisionId}</code>
	* @param byId (filter parameter) No documentation is available
	* @return Objects retrieved from database
	*/
	public static Division getDivisionById(int byId) throws Exception
	{
		Division retVal;


		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement("SELECT DIVISION_ID, DIVISION_NAME, PARENT_DIVISION_ID  FROM HR_Division  WHERE (HR_Division.DIVISION_ID = ?)  ;  "))
		{
			ps.setInt(1, byId);

			retVal = new Division();

			if (!ps.execute()) return retVal;

			ResultSet rs = ps.getResultSet();
			if (rs.next())
			{
				retVal.setId(rs.getInt("DIVISION_ID"));
				retVal.setName(rs.getString("DIVISION_NAME"));
				retVal.setParentDivisionId(rs.getInt("PARENT_DIVISION_ID"));
			}
			else
			{
				retVal = null;
			}

			return retVal;
		}
	}

}
