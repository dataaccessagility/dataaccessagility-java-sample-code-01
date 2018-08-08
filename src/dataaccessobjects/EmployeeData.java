package dataaccessobjects;

import java.sql.*;
import models.*;

public class EmployeeData
{
	/**
	* Inserts a single record into database. The values inserted are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>
	* @param obj <code>{@link Employee}</code> object
	* @return Number of rows affected
	*/
	public static int addEmployee(Employee obj) throws Exception
	{
		int retVal;

		obj.validate();

		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("INSERT INTO HR_EMPLOYEE (EMP_DIVISION_ID, EMP_FULL_NAME, EMP_Gender, EMP_HIR_DATE, EMP_ID, EMP_LATEST_CI_DT, EMP_NO, EMP_PICTURE, EMP_SALARY, EMP_STATUS, EMP_TYPE)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)  ;    "))
		{
			ps.setInt(1, obj.getDivisionId());
			ps.setString(2, obj.getFullName());
			if (!obj.isNull(Employee.GENDER_BIT_FLAG)) ps.setInt(3, obj.getGender());
			else ps.setNull(3, Types.INTEGER);
			if (!obj.isNull(Employee.HIRINGDATE_BIT_FLAG)) ps.setDate(4, new Date(obj.getHiringDate().getTime()), java.util.Calendar.getInstance());
			else ps.setNull(4, Types.DATE);
			ps.setInt(5, obj.getId());
			ps.setTimestamp(6, new Timestamp(obj.getLatestCheckInDateTime().getTime()));
			ps.setString(7, obj.getNumber());
			if (!obj.isNull(Employee.PICTURE_BIT_FLAG)) ps.setBytes(8, obj.getPicture());
			else ps.setNull(8, Types.VARBINARY);
			ps.setBigDecimal(9, obj.getSalary());
			ps.setInt(10, obj.getStatus());
			ps.setInt(11, obj.getType());

			retVal = ps.executeUpdate();

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Inserts a single record into database. The values inserted are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>
	* @param txn A database transaction that will be associated with this data access operation
	* @param obj <code>{@link Employee}</code> object
	* @return Number of rows affected
	*/
	public static int addEmployee(IDataAccessTransaction txn, Employee obj) throws Exception
	{
		int retVal;

		obj.validate();

		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("INSERT INTO HR_EMPLOYEE (EMP_DIVISION_ID, EMP_FULL_NAME, EMP_Gender, EMP_HIR_DATE, EMP_ID, EMP_LATEST_CI_DT, EMP_NO, EMP_PICTURE, EMP_SALARY, EMP_STATUS, EMP_TYPE)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)  ;    "))
		{
			ps.setInt(1, obj.getDivisionId());
			ps.setString(2, obj.getFullName());
			if (!obj.isNull(Employee.GENDER_BIT_FLAG)) ps.setInt(3, obj.getGender());
			else ps.setNull(3, Types.INTEGER);
			if (!obj.isNull(Employee.HIRINGDATE_BIT_FLAG)) ps.setDate(4, new Date(obj.getHiringDate().getTime()), java.util.Calendar.getInstance());
			else ps.setNull(4, Types.DATE);
			ps.setInt(5, obj.getId());
			ps.setTimestamp(6, new Timestamp(obj.getLatestCheckInDateTime().getTime()));
			ps.setString(7, obj.getNumber());
			if (!obj.isNull(Employee.PICTURE_BIT_FLAG)) ps.setBytes(8, obj.getPicture());
			else ps.setNull(8, Types.VARBINARY);
			ps.setBigDecimal(9, obj.getSalary());
			ps.setInt(10, obj.getStatus());
			ps.setInt(11, obj.getType());

			retVal = ps.executeUpdate();

			return retVal;
		}
	}

	/**
	* Retrieves a single record from database. The values returned are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>
	* @param byId (filter parameter) A unique number used internally by the system. This number is not supposed to be known employees
	* @return Objects retrieved from database
	*/
	public static Employee getEmployeeById(int byId) throws Exception
	{
		Employee retVal;


		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("SELECT EMP_ID, EMP_NO, EMP_FULL_NAME, EMP_Gender, EMP_HIR_DATE, EMP_STATUS, EMP_TYPE, EMP_SALARY, EMP_DIVISION_ID, EMP_PICTURE, EMP_LATEST_CI_DT  FROM HR_EMPLOYEE  WHERE (HR_EMPLOYEE.EMP_ID = ?)  ;  "))
		{
			ps.setInt(1, byId);

			retVal = new Employee();

			if (!ps.execute()) return retVal;

			ResultSet rs = ps.getResultSet();
			if (rs.next())
			{
				retVal.setId(rs.getInt("EMP_ID"));
				retVal.setNumber(rs.getString("EMP_NO"));
				retVal.setFullName(rs.getString("EMP_FULL_NAME"));
				if (rs.getObject("EMP_Gender") != null) retVal.setGender(rs.getInt("EMP_Gender"));
				if (rs.getObject("EMP_HIR_DATE") != null) retVal.setHiringDate(rs.getDate("EMP_HIR_DATE", java.util.Calendar.getInstance()));
				retVal.setStatus(rs.getInt("EMP_STATUS"));
				retVal.setType(rs.getInt("EMP_TYPE"));
				retVal.setSalary(rs.getBigDecimal("EMP_SALARY"));
				retVal.setDivisionId(rs.getInt("EMP_DIVISION_ID"));
				if (rs.getObject("EMP_PICTURE") != null) retVal.setPicture(rs.getBytes("EMP_PICTURE"));
				retVal.setLatestCheckInDateTime(rs.getTimestamp("EMP_LATEST_CI_DT"));
			}
			else
			{
				retVal = null;
			}

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Retrieves a single record from database. The values returned are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>
	* @param txn A database transaction that will be associated with this data access operation
	* @param byId (filter parameter) A unique number used internally by the system. This number is not supposed to be known employees
	* @return Objects retrieved from database
	*/
	public static Employee getEmployeeById(IDataAccessTransaction txn, int byId) throws Exception
	{
		Employee retVal;


		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("SELECT EMP_ID, EMP_NO, EMP_FULL_NAME, EMP_Gender, EMP_HIR_DATE, EMP_STATUS, EMP_TYPE, EMP_SALARY, EMP_DIVISION_ID, EMP_PICTURE, EMP_LATEST_CI_DT  FROM HR_EMPLOYEE  WHERE (HR_EMPLOYEE.EMP_ID = ?)  ;  "))
		{
			ps.setInt(1, byId);

			retVal = new Employee();

			if (!ps.execute()) return retVal;

			ResultSet rs = ps.getResultSet();
			if (rs.next())
			{
				retVal.setId(rs.getInt("EMP_ID"));
				retVal.setNumber(rs.getString("EMP_NO"));
				retVal.setFullName(rs.getString("EMP_FULL_NAME"));
				if (rs.getObject("EMP_Gender") != null) retVal.setGender(rs.getInt("EMP_Gender"));
				if (rs.getObject("EMP_HIR_DATE") != null) retVal.setHiringDate(rs.getDate("EMP_HIR_DATE", java.util.Calendar.getInstance()));
				retVal.setStatus(rs.getInt("EMP_STATUS"));
				retVal.setType(rs.getInt("EMP_TYPE"));
				retVal.setSalary(rs.getBigDecimal("EMP_SALARY"));
				retVal.setDivisionId(rs.getInt("EMP_DIVISION_ID"));
				if (rs.getObject("EMP_PICTURE") != null) retVal.setPicture(rs.getBytes("EMP_PICTURE"));
				retVal.setLatestCheckInDateTime(rs.getTimestamp("EMP_LATEST_CI_DT"));
			}
			else
			{
				retVal = null;
			}

			return retVal;
		}
	}

	/**
	* Retrieves a single record from database. The values returned are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>. The returned objects are ordered by <code>{@link Employee#number number}</code> ascending
	* @param byNumber (filter parameter) Employee's personnel number that is used by HR department
	* @return Objects retrieved from database
	*/
	public static Employee getEmployeeByNumber(String byNumber) throws Exception
	{
		Employee retVal;


		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("SELECT EMP_ID, EMP_NO, EMP_FULL_NAME, EMP_Gender, EMP_HIR_DATE, EMP_STATUS, EMP_TYPE, EMP_SALARY, EMP_DIVISION_ID, EMP_PICTURE, EMP_LATEST_CI_DT  FROM HR_EMPLOYEE  WHERE (HR_EMPLOYEE.EMP_NO = ?)  ORDER BY EMP_NO ASC  ;  "))
		{
			ps.setString(1, byNumber);

			retVal = new Employee();

			if (!ps.execute()) return retVal;

			ResultSet rs = ps.getResultSet();
			if (rs.next())
			{
				retVal.setId(rs.getInt("EMP_ID"));
				retVal.setNumber(rs.getString("EMP_NO"));
				retVal.setFullName(rs.getString("EMP_FULL_NAME"));
				if (rs.getObject("EMP_Gender") != null) retVal.setGender(rs.getInt("EMP_Gender"));
				if (rs.getObject("EMP_HIR_DATE") != null) retVal.setHiringDate(rs.getDate("EMP_HIR_DATE", java.util.Calendar.getInstance()));
				retVal.setStatus(rs.getInt("EMP_STATUS"));
				retVal.setType(rs.getInt("EMP_TYPE"));
				retVal.setSalary(rs.getBigDecimal("EMP_SALARY"));
				retVal.setDivisionId(rs.getInt("EMP_DIVISION_ID"));
				if (rs.getObject("EMP_PICTURE") != null) retVal.setPicture(rs.getBytes("EMP_PICTURE"));
				retVal.setLatestCheckInDateTime(rs.getTimestamp("EMP_LATEST_CI_DT"));
			}
			else
			{
				retVal = null;
			}

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Retrieves a single record from database. The values returned are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>. The returned objects are ordered by <code>{@link Employee#number number}</code> ascending
	* @param txn A database transaction that will be associated with this data access operation
	* @param byNumber (filter parameter) Employee's personnel number that is used by HR department
	* @return Objects retrieved from database
	*/
	public static Employee getEmployeeByNumber(IDataAccessTransaction txn, String byNumber) throws Exception
	{
		Employee retVal;


		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("SELECT EMP_ID, EMP_NO, EMP_FULL_NAME, EMP_Gender, EMP_HIR_DATE, EMP_STATUS, EMP_TYPE, EMP_SALARY, EMP_DIVISION_ID, EMP_PICTURE, EMP_LATEST_CI_DT  FROM HR_EMPLOYEE  WHERE (HR_EMPLOYEE.EMP_NO = ?)  ORDER BY EMP_NO ASC  ;  "))
		{
			ps.setString(1, byNumber);

			retVal = new Employee();

			if (!ps.execute()) return retVal;

			ResultSet rs = ps.getResultSet();
			if (rs.next())
			{
				retVal.setId(rs.getInt("EMP_ID"));
				retVal.setNumber(rs.getString("EMP_NO"));
				retVal.setFullName(rs.getString("EMP_FULL_NAME"));
				if (rs.getObject("EMP_Gender") != null) retVal.setGender(rs.getInt("EMP_Gender"));
				if (rs.getObject("EMP_HIR_DATE") != null) retVal.setHiringDate(rs.getDate("EMP_HIR_DATE", java.util.Calendar.getInstance()));
				retVal.setStatus(rs.getInt("EMP_STATUS"));
				retVal.setType(rs.getInt("EMP_TYPE"));
				retVal.setSalary(rs.getBigDecimal("EMP_SALARY"));
				retVal.setDivisionId(rs.getInt("EMP_DIVISION_ID"));
				if (rs.getObject("EMP_PICTURE") != null) retVal.setPicture(rs.getBytes("EMP_PICTURE"));
				retVal.setLatestCheckInDateTime(rs.getTimestamp("EMP_LATEST_CI_DT"));
			}
			else
			{
				retVal = null;
			}

			return retVal;
		}
	}

	/**
	* Updates a single record in database. The values updated are <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>
	* @param obj <code>{@link Employee}</code> object
	* @param byId (filter parameter) A unique number used internally by the system. This number is not supposed to be known employees
	* @return Number of rows affected
	*/
	public static int updateEmployeeById(Employee obj, int byId) throws Exception
	{
		int retVal;

		obj.validate(Employee.FULLNAME_BIT_FLAG | Employee.GENDER_BIT_FLAG | Employee.HIRINGDATE_BIT_FLAG | Employee.STATUS_BIT_FLAG | Employee.TYPE_BIT_FLAG | Employee.SALARY_BIT_FLAG | Employee.DIVISIONID_BIT_FLAG | Employee.PICTURE_BIT_FLAG | Employee.LATESTCHECKINDATETIME_BIT_FLAG);

		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("UPDATE HR_EMPLOYEE  SET HR_EMPLOYEE.EMP_DIVISION_ID = ?, HR_EMPLOYEE.EMP_FULL_NAME = ?, HR_EMPLOYEE.EMP_Gender = ?, HR_EMPLOYEE.EMP_HIR_DATE = ?, HR_EMPLOYEE.EMP_LATEST_CI_DT = ?, HR_EMPLOYEE.EMP_PICTURE = ?, HR_EMPLOYEE.EMP_SALARY = ?, HR_EMPLOYEE.EMP_STATUS = ?, HR_EMPLOYEE.EMP_TYPE = ?  WHERE (HR_EMPLOYEE.EMP_ID = ?)  ;  "))
		{
			ps.setInt(1, obj.getDivisionId());
			ps.setString(2, obj.getFullName());
			if (!obj.isNull(Employee.GENDER_BIT_FLAG)) ps.setInt(3, obj.getGender());
			else ps.setNull(3, Types.INTEGER);
			if (!obj.isNull(Employee.HIRINGDATE_BIT_FLAG)) ps.setDate(4, new Date(obj.getHiringDate().getTime()), java.util.Calendar.getInstance());
			else ps.setNull(4, Types.DATE);
			ps.setTimestamp(5, new Timestamp(obj.getLatestCheckInDateTime().getTime()));
			if (!obj.isNull(Employee.PICTURE_BIT_FLAG)) ps.setBytes(6, obj.getPicture());
			else ps.setNull(6, Types.VARBINARY);
			ps.setBigDecimal(7, obj.getSalary());
			ps.setInt(8, obj.getStatus());
			ps.setInt(9, obj.getType());
			ps.setInt(10, byId);

			retVal = ps.executeUpdate();

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Updates a single record in database. The values updated are <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>
	* @param txn A database transaction that will be associated with this data access operation
	* @param obj <code>{@link Employee}</code> object
	* @param byId (filter parameter) A unique number used internally by the system. This number is not supposed to be known employees
	* @return Number of rows affected
	*/
	public static int updateEmployeeById(IDataAccessTransaction txn, Employee obj, int byId) throws Exception
	{
		int retVal;

		obj.validate(Employee.FULLNAME_BIT_FLAG | Employee.GENDER_BIT_FLAG | Employee.HIRINGDATE_BIT_FLAG | Employee.STATUS_BIT_FLAG | Employee.TYPE_BIT_FLAG | Employee.SALARY_BIT_FLAG | Employee.DIVISIONID_BIT_FLAG | Employee.PICTURE_BIT_FLAG | Employee.LATESTCHECKINDATETIME_BIT_FLAG);

		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("UPDATE HR_EMPLOYEE  SET HR_EMPLOYEE.EMP_DIVISION_ID = ?, HR_EMPLOYEE.EMP_FULL_NAME = ?, HR_EMPLOYEE.EMP_Gender = ?, HR_EMPLOYEE.EMP_HIR_DATE = ?, HR_EMPLOYEE.EMP_LATEST_CI_DT = ?, HR_EMPLOYEE.EMP_PICTURE = ?, HR_EMPLOYEE.EMP_SALARY = ?, HR_EMPLOYEE.EMP_STATUS = ?, HR_EMPLOYEE.EMP_TYPE = ?  WHERE (HR_EMPLOYEE.EMP_ID = ?)  ;  "))
		{
			ps.setInt(1, obj.getDivisionId());
			ps.setString(2, obj.getFullName());
			if (!obj.isNull(Employee.GENDER_BIT_FLAG)) ps.setInt(3, obj.getGender());
			else ps.setNull(3, Types.INTEGER);
			if (!obj.isNull(Employee.HIRINGDATE_BIT_FLAG)) ps.setDate(4, new Date(obj.getHiringDate().getTime()), java.util.Calendar.getInstance());
			else ps.setNull(4, Types.DATE);
			ps.setTimestamp(5, new Timestamp(obj.getLatestCheckInDateTime().getTime()));
			if (!obj.isNull(Employee.PICTURE_BIT_FLAG)) ps.setBytes(6, obj.getPicture());
			else ps.setNull(6, Types.VARBINARY);
			ps.setBigDecimal(7, obj.getSalary());
			ps.setInt(8, obj.getStatus());
			ps.setInt(9, obj.getType());
			ps.setInt(10, byId);

			retVal = ps.executeUpdate();

			return retVal;
		}
	}

	/**
	* Updates a single record in database. The values updated are <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>
	* @param obj <code>{@link Employee}</code> object
	* @param byNumber (filter parameter) Employee's personnel number that is used by HR department
	* @return Number of rows affected
	*/
	public static int updateEmployeeByNumber(Employee obj, String byNumber) throws Exception
	{
		int retVal;

		obj.validate(Employee.FULLNAME_BIT_FLAG | Employee.GENDER_BIT_FLAG | Employee.HIRINGDATE_BIT_FLAG | Employee.STATUS_BIT_FLAG | Employee.TYPE_BIT_FLAG | Employee.SALARY_BIT_FLAG | Employee.DIVISIONID_BIT_FLAG | Employee.PICTURE_BIT_FLAG | Employee.LATESTCHECKINDATETIME_BIT_FLAG);

		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("UPDATE HR_EMPLOYEE  SET HR_EMPLOYEE.EMP_DIVISION_ID = ?, HR_EMPLOYEE.EMP_FULL_NAME = ?, HR_EMPLOYEE.EMP_Gender = ?, HR_EMPLOYEE.EMP_HIR_DATE = ?, HR_EMPLOYEE.EMP_LATEST_CI_DT = ?, HR_EMPLOYEE.EMP_PICTURE = ?, HR_EMPLOYEE.EMP_SALARY = ?, HR_EMPLOYEE.EMP_STATUS = ?, HR_EMPLOYEE.EMP_TYPE = ?  WHERE (HR_EMPLOYEE.EMP_NO = ?)  ;  "))
		{
			ps.setInt(1, obj.getDivisionId());
			ps.setString(2, obj.getFullName());
			if (!obj.isNull(Employee.GENDER_BIT_FLAG)) ps.setInt(3, obj.getGender());
			else ps.setNull(3, Types.INTEGER);
			if (!obj.isNull(Employee.HIRINGDATE_BIT_FLAG)) ps.setDate(4, new Date(obj.getHiringDate().getTime()), java.util.Calendar.getInstance());
			else ps.setNull(4, Types.DATE);
			ps.setTimestamp(5, new Timestamp(obj.getLatestCheckInDateTime().getTime()));
			if (!obj.isNull(Employee.PICTURE_BIT_FLAG)) ps.setBytes(6, obj.getPicture());
			else ps.setNull(6, Types.VARBINARY);
			ps.setBigDecimal(7, obj.getSalary());
			ps.setInt(8, obj.getStatus());
			ps.setInt(9, obj.getType());
			ps.setString(10, byNumber);

			retVal = ps.executeUpdate();

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Updates a single record in database. The values updated are <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>
	* @param txn A database transaction that will be associated with this data access operation
	* @param obj <code>{@link Employee}</code> object
	* @param byNumber (filter parameter) Employee's personnel number that is used by HR department
	* @return Number of rows affected
	*/
	public static int updateEmployeeByNumber(IDataAccessTransaction txn, Employee obj, String byNumber) throws Exception
	{
		int retVal;

		obj.validate(Employee.FULLNAME_BIT_FLAG | Employee.GENDER_BIT_FLAG | Employee.HIRINGDATE_BIT_FLAG | Employee.STATUS_BIT_FLAG | Employee.TYPE_BIT_FLAG | Employee.SALARY_BIT_FLAG | Employee.DIVISIONID_BIT_FLAG | Employee.PICTURE_BIT_FLAG | Employee.LATESTCHECKINDATETIME_BIT_FLAG);

		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("UPDATE HR_EMPLOYEE  SET HR_EMPLOYEE.EMP_DIVISION_ID = ?, HR_EMPLOYEE.EMP_FULL_NAME = ?, HR_EMPLOYEE.EMP_Gender = ?, HR_EMPLOYEE.EMP_HIR_DATE = ?, HR_EMPLOYEE.EMP_LATEST_CI_DT = ?, HR_EMPLOYEE.EMP_PICTURE = ?, HR_EMPLOYEE.EMP_SALARY = ?, HR_EMPLOYEE.EMP_STATUS = ?, HR_EMPLOYEE.EMP_TYPE = ?  WHERE (HR_EMPLOYEE.EMP_NO = ?)  ;  "))
		{
			ps.setInt(1, obj.getDivisionId());
			ps.setString(2, obj.getFullName());
			if (!obj.isNull(Employee.GENDER_BIT_FLAG)) ps.setInt(3, obj.getGender());
			else ps.setNull(3, Types.INTEGER);
			if (!obj.isNull(Employee.HIRINGDATE_BIT_FLAG)) ps.setDate(4, new Date(obj.getHiringDate().getTime()), java.util.Calendar.getInstance());
			else ps.setNull(4, Types.DATE);
			ps.setTimestamp(5, new Timestamp(obj.getLatestCheckInDateTime().getTime()));
			if (!obj.isNull(Employee.PICTURE_BIT_FLAG)) ps.setBytes(6, obj.getPicture());
			else ps.setNull(6, Types.VARBINARY);
			ps.setBigDecimal(7, obj.getSalary());
			ps.setInt(8, obj.getStatus());
			ps.setInt(9, obj.getType());
			ps.setString(10, byNumber);

			retVal = ps.executeUpdate();

			return retVal;
		}
	}

	/**
	* Deletes a single record from database
	* @param byId (filter parameter) A unique number used internally by the system. This number is not supposed to be known employees
	* @return Number of rows affected
	*/
	public static int deleteEmployeeById(int byId) throws Exception
	{
		int retVal;


		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("DELETE HR_EMPLOYEE.* FROM HR_EMPLOYEE  WHERE (HR_EMPLOYEE.EMP_ID = ?)  ;  "))
		{
			ps.setInt(1, byId);

			retVal = ps.executeUpdate();

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Deletes a single record from database
	* @param txn A database transaction that will be associated with this data access operation
	* @param byId (filter parameter) A unique number used internally by the system. This number is not supposed to be known employees
	* @return Number of rows affected
	*/
	public static int deleteEmployeeById(IDataAccessTransaction txn, int byId) throws Exception
	{
		int retVal;


		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("DELETE HR_EMPLOYEE.* FROM HR_EMPLOYEE  WHERE (HR_EMPLOYEE.EMP_ID = ?)  ;  "))
		{
			ps.setInt(1, byId);

			retVal = ps.executeUpdate();

			return retVal;
		}
	}

	/**
	* Inserts a set of records into database. The values inserted for each record are 
	* @param objs A list of <code>{@link (Employee)}</code> objects
	* @return Number of rows affected
	*/
	public static int addEmployeesBulk(java.util.List<Employee> objs) throws Exception
	{
		int retVal;

		for (Employee obj : objs) obj.validate();

		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("SET @XML_DATA = ?; SET @XML_PATH = ?; SET @TAG_NAME = SUBSTRING_INDEX(@XML_PATH, '/', -1);  SET @TAG_BEGIN = CONCAT('<', @TAG_NAME, '>');  SET @TAG_END = CONCAT('</', @TAG_NAME, '>');  SET @TAG_LENGTH = LENGTH(@TAG_BEGIN);  SET @ROWS_COUNT = ExtractValue(@XML_DATA, CONCAT('COUNT(', @XML_PATH, ')'));    SET @START_POINTER = 0;  SET @END_POINTER = 1;    SET @START_POINTER = LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER);  SET @END_POINTER = LOCATE(@TAG_END, @XML_DATA, @START_POINTER);    INSERT INTO HR_EMPLOYEE (EMP_ID, EMP_NO, EMP_FULL_NAME, EMP_Gender, EMP_STATUS, EMP_TYPE, EMP_SALARY, EMP_DIVISION_ID, EMP_HIR_DATE, EMP_LATEST_CI_DT)    SELECT ExtractValue(Nodes.Node, '/A') AS A, ExtractValue(Nodes.Node, '/B') AS B, ExtractValue(Nodes.Node, '/C') AS C, ExtractValue(Nodes.Node, '/D') AS D, ExtractValue(Nodes.Node, '/F') AS F, ExtractValue(Nodes.Node, '/G') AS G, ExtractValue(Nodes.Node, '/H') AS H, ExtractValue(Nodes.Node, '/I') AS I, STR_TO_DATE(ExtractValue(Nodes.Node, '/E'), '%Y-%m-%d') AS E, STR_TO_DATE(ExtractValue(Nodes.Node, '/K'), '%Y-%m-%dT%H:%i:%S') AS K  FROM (  SELECT SUBSTR(@XML_DATA, Positions.X + @TAG_LENGTH, Positions.Y - Positions.X - @TAG_LENGTH) as Node  FROM (SELECT @START_POINTER X, @END_POINTER Y UNION SELECT @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER) as X, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) as Y FROM   (  SELECT a.x FROM (SELECT 0 x UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a LEFT JOIN (SELECT 0 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b ON @ROWS_COUNT > 10 LEFT JOIN (SELECT 0 UNION SELECT 1) c ON @ROWS_COUNT > 100 LEFT JOIN (SELECT 0 UNION SELECT 1) d ON @ROWS_COUNT > 200 LEFT JOIN (SELECT 0 UNION SELECT 1) e ON @ROWS_COUNT > 400 LEFT JOIN (SELECT 0 UNION SELECT 1) f ON @ROWS_COUNT > 800 LEFT JOIN (SELECT 0 UNION SELECT 1) g ON @ROWS_COUNT > 1600 LEFT JOIN (SELECT 0 UNION SELECT 1) h ON @ROWS_COUNT > 3200  ) AS sequences  WHERE @START_POINTER > 0  ) AS Positions WHERE Positions.X > 0 AND Positions.Y > 0  ) as Nodes  ;    "))
		{
			ps.setString(1, Employee.ToXml(objs, Employee.ID_BIT_FLAG | Employee.NUMBER_BIT_FLAG | Employee.FULLNAME_BIT_FLAG | Employee.GENDER_BIT_FLAG | Employee.STATUS_BIT_FLAG | Employee.TYPE_BIT_FLAG | Employee.SALARY_BIT_FLAG | Employee.DIVISIONID_BIT_FLAG | Employee.HIRINGDATE_BIT_FLAG | Employee.LATESTCHECKINDATETIME_BIT_FLAG));
			ps.setString(2, Employee.COLLECTION_XML_DATA_PATH);

			retVal = ps.executeUpdate();

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Inserts a set of records into database. The values inserted for each record are 
	* @param txn A database transaction that will be associated with this data access operation
	* @param objs A list of <code>{@link (Employee)}</code> objects
	* @return Number of rows affected
	*/
	public static int addEmployeesBulk(IDataAccessTransaction txn, java.util.List<Employee> objs) throws Exception
	{
		int retVal;

		for (Employee obj : objs) obj.validate();

		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("SET @XML_DATA = ?; SET @XML_PATH = ?; SET @TAG_NAME = SUBSTRING_INDEX(@XML_PATH, '/', -1);  SET @TAG_BEGIN = CONCAT('<', @TAG_NAME, '>');  SET @TAG_END = CONCAT('</', @TAG_NAME, '>');  SET @TAG_LENGTH = LENGTH(@TAG_BEGIN);  SET @ROWS_COUNT = ExtractValue(@XML_DATA, CONCAT('COUNT(', @XML_PATH, ')'));    SET @START_POINTER = 0;  SET @END_POINTER = 1;    SET @START_POINTER = LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER);  SET @END_POINTER = LOCATE(@TAG_END, @XML_DATA, @START_POINTER);    INSERT INTO HR_EMPLOYEE (EMP_ID, EMP_NO, EMP_FULL_NAME, EMP_Gender, EMP_STATUS, EMP_TYPE, EMP_SALARY, EMP_DIVISION_ID, EMP_HIR_DATE, EMP_LATEST_CI_DT)    SELECT ExtractValue(Nodes.Node, '/A') AS A, ExtractValue(Nodes.Node, '/B') AS B, ExtractValue(Nodes.Node, '/C') AS C, ExtractValue(Nodes.Node, '/D') AS D, ExtractValue(Nodes.Node, '/F') AS F, ExtractValue(Nodes.Node, '/G') AS G, ExtractValue(Nodes.Node, '/H') AS H, ExtractValue(Nodes.Node, '/I') AS I, STR_TO_DATE(ExtractValue(Nodes.Node, '/E'), '%Y-%m-%d') AS E, STR_TO_DATE(ExtractValue(Nodes.Node, '/K'), '%Y-%m-%dT%H:%i:%S') AS K  FROM (  SELECT SUBSTR(@XML_DATA, Positions.X + @TAG_LENGTH, Positions.Y - Positions.X - @TAG_LENGTH) as Node  FROM (SELECT @START_POINTER X, @END_POINTER Y UNION SELECT @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER) as X, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) as Y FROM   (  SELECT a.x FROM (SELECT 0 x UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a LEFT JOIN (SELECT 0 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b ON @ROWS_COUNT > 10 LEFT JOIN (SELECT 0 UNION SELECT 1) c ON @ROWS_COUNT > 100 LEFT JOIN (SELECT 0 UNION SELECT 1) d ON @ROWS_COUNT > 200 LEFT JOIN (SELECT 0 UNION SELECT 1) e ON @ROWS_COUNT > 400 LEFT JOIN (SELECT 0 UNION SELECT 1) f ON @ROWS_COUNT > 800 LEFT JOIN (SELECT 0 UNION SELECT 1) g ON @ROWS_COUNT > 1600 LEFT JOIN (SELECT 0 UNION SELECT 1) h ON @ROWS_COUNT > 3200  ) AS sequences  WHERE @START_POINTER > 0  ) AS Positions WHERE Positions.X > 0 AND Positions.Y > 0  ) as Nodes  ;    "))
		{
			ps.setString(1, Employee.ToXml(objs, Employee.ID_BIT_FLAG | Employee.NUMBER_BIT_FLAG | Employee.FULLNAME_BIT_FLAG | Employee.GENDER_BIT_FLAG | Employee.STATUS_BIT_FLAG | Employee.TYPE_BIT_FLAG | Employee.SALARY_BIT_FLAG | Employee.DIVISIONID_BIT_FLAG | Employee.HIRINGDATE_BIT_FLAG | Employee.LATESTCHECKINDATETIME_BIT_FLAG));
			ps.setString(2, Employee.COLLECTION_XML_DATA_PATH);

			retVal = ps.executeUpdate();

			return retVal;
		}
	}

	/**
	* Retrieves a set of records from database. The values returned for each record are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>. The returned objects are ordered by <code>{@link Employee#id id}</code> ascending
	* @param objs A list of <code>{@link Employee}</code> objects
	* @return Objects retrieved from database
	*/
	public static java.util.ArrayList<Employee> getEmployeesBulkById(java.util.List<Employee> objs) throws Exception
	{
		java.util.ArrayList<Employee> retVal;


		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("SELECT @XML_DATA := ?, @XML_PATH := ? FROM DUAL WHERE 1 < 0; SELECT @TAG_NAME := SUBSTRING_INDEX(@XML_PATH, '/', -1) AS TAG_NAME FROM DUAL WHERE 1 < 0;  SELECT @TAG_BEGIN := CONCAT('<', @TAG_NAME, '>') AS TAG_BEGIN, @TAG_END := CONCAT('</', @TAG_NAME, '>') AS TAG_END FROM DUAL WHERE 1 < 0;  SELECT @TAG_LENGTH := LENGTH(@TAG_BEGIN) AS TAG_LENGTH, @ROWS_COUNT := ExtractValue(@XML_DATA, CONCAT('COUNT(', @XML_PATH, ')')) AS ROWS_COUNT, @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, 1) AS START_POINTER, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) AS END_POINTER FROM DUAL;  SELECT HR_EMPLOYEE.EMP_ID, HR_EMPLOYEE.EMP_NO, HR_EMPLOYEE.EMP_FULL_NAME, HR_EMPLOYEE.EMP_Gender, HR_EMPLOYEE.EMP_HIR_DATE, HR_EMPLOYEE.EMP_STATUS, HR_EMPLOYEE.EMP_TYPE, HR_EMPLOYEE.EMP_SALARY, HR_EMPLOYEE.EMP_DIVISION_ID, HR_EMPLOYEE.EMP_PICTURE, HR_EMPLOYEE.EMP_LATEST_CI_DT  FROM HR_EMPLOYEE  INNER JOIN (SELECT ExtractValue(Nodes.Node, '/A') AS A  FROM (  SELECT SUBSTR(@XML_DATA, Positions.X + @TAG_LENGTH, Positions.Y - Positions.X - @TAG_LENGTH) as Node  FROM (SELECT @START_POINTER X, @END_POINTER Y UNION SELECT @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER) as X, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) as Y FROM   (  SELECT a.x FROM (SELECT 0 x UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a LEFT JOIN (SELECT 0 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b ON @ROWS_COUNT > 10 LEFT JOIN (SELECT 0 UNION SELECT 1) c ON @ROWS_COUNT > 100 LEFT JOIN (SELECT 0 UNION SELECT 1) d ON @ROWS_COUNT > 200 LEFT JOIN (SELECT 0 UNION SELECT 1) e ON @ROWS_COUNT > 400 LEFT JOIN (SELECT 0 UNION SELECT 1) f ON @ROWS_COUNT > 800 LEFT JOIN (SELECT 0 UNION SELECT 1) g ON @ROWS_COUNT > 1600 LEFT JOIN (SELECT 0 UNION SELECT 1) h ON @ROWS_COUNT > 3200  ) AS sequences  WHERE @START_POINTER > 0  ) AS Positions WHERE Positions.X > 0 AND Positions.Y > 0  ) as Nodes  ) AS TTable ON (HR_EMPLOYEE.EMP_ID = TTable.A)  ORDER BY EMP_ID ASC  ;  "))
		{
			ps.setString(1, Employee.ToXml(objs, Employee.ID_BIT_FLAG));
			ps.setString(2, Employee.COLLECTION_XML_DATA_PATH);

			retVal = new java.util.ArrayList<Employee>();

			if (!ps.execute()) return retVal;

			ResultSet rs = null;

			do
			{
				if (rs != null) rs.close();
				rs = ps.getResultSet();
			} while (ps.getMoreResults(Statement.KEEP_CURRENT_RESULT));

			while (rs.next())
			{
				Employee obj = new Employee();

				obj.setId(rs.getInt("EMP_ID"));
				obj.setNumber(rs.getString("EMP_NO"));
				obj.setFullName(rs.getString("EMP_FULL_NAME"));
				if (rs.getObject("EMP_Gender") != null) obj.setGender(rs.getInt("EMP_Gender"));
				if (rs.getObject("EMP_HIR_DATE") != null) obj.setHiringDate(rs.getDate("EMP_HIR_DATE"));
				obj.setStatus(rs.getInt("EMP_STATUS"));
				obj.setType(rs.getInt("EMP_TYPE"));
				obj.setSalary(rs.getBigDecimal("EMP_SALARY"));
				obj.setDivisionId(rs.getInt("EMP_DIVISION_ID"));
				if (rs.getObject("EMP_PICTURE") != null) obj.setPicture(rs.getBytes("EMP_PICTURE"));
				obj.setLatestCheckInDateTime(rs.getTimestamp("EMP_LATEST_CI_DT"));

				retVal.add(obj);
			}

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Retrieves a set of records from database. The values returned for each record are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>. The returned objects are ordered by <code>{@link Employee#id id}</code> ascending
	* @param txn A database transaction that will be associated with this data access operation
	* @param objs A list of <code>{@link Employee}</code> objects
	* @return Objects retrieved from database
	*/
	public static java.util.ArrayList<Employee> getEmployeesBulkById(IDataAccessTransaction txn, java.util.List<Employee> objs) throws Exception
	{
		java.util.ArrayList<Employee> retVal;


		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("SELECT @XML_DATA := ?, @XML_PATH := ? FROM DUAL WHERE 1 < 0; SELECT @TAG_NAME := SUBSTRING_INDEX(@XML_PATH, '/', -1) AS TAG_NAME FROM DUAL WHERE 1 < 0;  SELECT @TAG_BEGIN := CONCAT('<', @TAG_NAME, '>') AS TAG_BEGIN, @TAG_END := CONCAT('</', @TAG_NAME, '>') AS TAG_END FROM DUAL WHERE 1 < 0;  SELECT @TAG_LENGTH := LENGTH(@TAG_BEGIN) AS TAG_LENGTH, @ROWS_COUNT := ExtractValue(@XML_DATA, CONCAT('COUNT(', @XML_PATH, ')')) AS ROWS_COUNT, @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, 1) AS START_POINTER, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) AS END_POINTER FROM DUAL;  SELECT HR_EMPLOYEE.EMP_ID, HR_EMPLOYEE.EMP_NO, HR_EMPLOYEE.EMP_FULL_NAME, HR_EMPLOYEE.EMP_Gender, HR_EMPLOYEE.EMP_HIR_DATE, HR_EMPLOYEE.EMP_STATUS, HR_EMPLOYEE.EMP_TYPE, HR_EMPLOYEE.EMP_SALARY, HR_EMPLOYEE.EMP_DIVISION_ID, HR_EMPLOYEE.EMP_PICTURE, HR_EMPLOYEE.EMP_LATEST_CI_DT  FROM HR_EMPLOYEE  INNER JOIN (SELECT ExtractValue(Nodes.Node, '/A') AS A  FROM (  SELECT SUBSTR(@XML_DATA, Positions.X + @TAG_LENGTH, Positions.Y - Positions.X - @TAG_LENGTH) as Node  FROM (SELECT @START_POINTER X, @END_POINTER Y UNION SELECT @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER) as X, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) as Y FROM   (  SELECT a.x FROM (SELECT 0 x UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a LEFT JOIN (SELECT 0 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b ON @ROWS_COUNT > 10 LEFT JOIN (SELECT 0 UNION SELECT 1) c ON @ROWS_COUNT > 100 LEFT JOIN (SELECT 0 UNION SELECT 1) d ON @ROWS_COUNT > 200 LEFT JOIN (SELECT 0 UNION SELECT 1) e ON @ROWS_COUNT > 400 LEFT JOIN (SELECT 0 UNION SELECT 1) f ON @ROWS_COUNT > 800 LEFT JOIN (SELECT 0 UNION SELECT 1) g ON @ROWS_COUNT > 1600 LEFT JOIN (SELECT 0 UNION SELECT 1) h ON @ROWS_COUNT > 3200  ) AS sequences  WHERE @START_POINTER > 0  ) AS Positions WHERE Positions.X > 0 AND Positions.Y > 0  ) as Nodes  ) AS TTable ON (HR_EMPLOYEE.EMP_ID = TTable.A)  ORDER BY EMP_ID ASC  ;  "))
		{
			ps.setString(1, Employee.ToXml(objs, Employee.ID_BIT_FLAG));
			ps.setString(2, Employee.COLLECTION_XML_DATA_PATH);

			retVal = new java.util.ArrayList<Employee>();

			if (!ps.execute()) return retVal;

			ResultSet rs = null;

			do
			{
				if (rs != null) rs.close();
				rs = ps.getResultSet();
			} while (ps.getMoreResults(Statement.KEEP_CURRENT_RESULT));

			while (rs.next())
			{
				Employee obj = new Employee();

				obj.setId(rs.getInt("EMP_ID"));
				obj.setNumber(rs.getString("EMP_NO"));
				obj.setFullName(rs.getString("EMP_FULL_NAME"));
				if (rs.getObject("EMP_Gender") != null) obj.setGender(rs.getInt("EMP_Gender"));
				if (rs.getObject("EMP_HIR_DATE") != null) obj.setHiringDate(rs.getDate("EMP_HIR_DATE"));
				obj.setStatus(rs.getInt("EMP_STATUS"));
				obj.setType(rs.getInt("EMP_TYPE"));
				obj.setSalary(rs.getBigDecimal("EMP_SALARY"));
				obj.setDivisionId(rs.getInt("EMP_DIVISION_ID"));
				if (rs.getObject("EMP_PICTURE") != null) obj.setPicture(rs.getBytes("EMP_PICTURE"));
				obj.setLatestCheckInDateTime(rs.getTimestamp("EMP_LATEST_CI_DT"));

				retVal.add(obj);
			}

			return retVal;
		}
	}

	/**
	* Retrieves a set of records from database. The values returned for each record are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>. The returned objects are ordered by <code>{@link Employee#id id}</code> ascending
	* @param objs A list of <code>{@link Employee}</code> objects
	* @return Objects retrieved from database
	*/
	public static java.util.ArrayList<Employee> getEmployeesBulkByNumber(java.util.List<Employee> objs) throws Exception
	{
		java.util.ArrayList<Employee> retVal;


		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("SELECT @XML_DATA := ?, @XML_PATH := ? FROM DUAL WHERE 1 < 0; SELECT @TAG_NAME := SUBSTRING_INDEX(@XML_PATH, '/', -1) AS TAG_NAME FROM DUAL WHERE 1 < 0;  SELECT @TAG_BEGIN := CONCAT('<', @TAG_NAME, '>') AS TAG_BEGIN, @TAG_END := CONCAT('</', @TAG_NAME, '>') AS TAG_END FROM DUAL WHERE 1 < 0;  SELECT @TAG_LENGTH := LENGTH(@TAG_BEGIN) AS TAG_LENGTH, @ROWS_COUNT := ExtractValue(@XML_DATA, CONCAT('COUNT(', @XML_PATH, ')')) AS ROWS_COUNT, @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, 1) AS START_POINTER, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) AS END_POINTER FROM DUAL;  SELECT HR_EMPLOYEE.EMP_ID, HR_EMPLOYEE.EMP_NO, HR_EMPLOYEE.EMP_FULL_NAME, HR_EMPLOYEE.EMP_Gender, HR_EMPLOYEE.EMP_HIR_DATE, HR_EMPLOYEE.EMP_STATUS, HR_EMPLOYEE.EMP_TYPE, HR_EMPLOYEE.EMP_SALARY, HR_EMPLOYEE.EMP_DIVISION_ID, HR_EMPLOYEE.EMP_PICTURE, HR_EMPLOYEE.EMP_LATEST_CI_DT  FROM HR_EMPLOYEE  INNER JOIN (SELECT ExtractValue(Nodes.Node, '/B') AS B  FROM (  SELECT SUBSTR(@XML_DATA, Positions.X + @TAG_LENGTH, Positions.Y - Positions.X - @TAG_LENGTH) as Node  FROM (SELECT @START_POINTER X, @END_POINTER Y UNION SELECT @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER) as X, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) as Y FROM   (  SELECT a.x FROM (SELECT 0 x UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a LEFT JOIN (SELECT 0 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b ON @ROWS_COUNT > 10 LEFT JOIN (SELECT 0 UNION SELECT 1) c ON @ROWS_COUNT > 100 LEFT JOIN (SELECT 0 UNION SELECT 1) d ON @ROWS_COUNT > 200 LEFT JOIN (SELECT 0 UNION SELECT 1) e ON @ROWS_COUNT > 400 LEFT JOIN (SELECT 0 UNION SELECT 1) f ON @ROWS_COUNT > 800 LEFT JOIN (SELECT 0 UNION SELECT 1) g ON @ROWS_COUNT > 1600 LEFT JOIN (SELECT 0 UNION SELECT 1) h ON @ROWS_COUNT > 3200  ) AS sequences  WHERE @START_POINTER > 0  ) AS Positions WHERE Positions.X > 0 AND Positions.Y > 0  ) as Nodes  ) AS TTable ON (HR_EMPLOYEE.EMP_NO = TTable.B)  ORDER BY EMP_ID ASC  ;  "))
		{
			ps.setString(1, Employee.ToXml(objs, Employee.NUMBER_BIT_FLAG));
			ps.setString(2, Employee.COLLECTION_XML_DATA_PATH);

			retVal = new java.util.ArrayList<Employee>();

			if (!ps.execute()) return retVal;

			ResultSet rs = null;

			do
			{
				if (rs != null) rs.close();
				rs = ps.getResultSet();
			} while (ps.getMoreResults(Statement.KEEP_CURRENT_RESULT));

			while (rs.next())
			{
				Employee obj = new Employee();

				obj.setId(rs.getInt("EMP_ID"));
				obj.setNumber(rs.getString("EMP_NO"));
				obj.setFullName(rs.getString("EMP_FULL_NAME"));
				if (rs.getObject("EMP_Gender") != null) obj.setGender(rs.getInt("EMP_Gender"));
				if (rs.getObject("EMP_HIR_DATE") != null) obj.setHiringDate(rs.getDate("EMP_HIR_DATE"));
				obj.setStatus(rs.getInt("EMP_STATUS"));
				obj.setType(rs.getInt("EMP_TYPE"));
				obj.setSalary(rs.getBigDecimal("EMP_SALARY"));
				obj.setDivisionId(rs.getInt("EMP_DIVISION_ID"));
				if (rs.getObject("EMP_PICTURE") != null) obj.setPicture(rs.getBytes("EMP_PICTURE"));
				obj.setLatestCheckInDateTime(rs.getTimestamp("EMP_LATEST_CI_DT"));

				retVal.add(obj);
			}

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Retrieves a set of records from database. The values returned for each record are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>. The returned objects are ordered by <code>{@link Employee#id id}</code> ascending
	* @param txn A database transaction that will be associated with this data access operation
	* @param objs A list of <code>{@link Employee}</code> objects
	* @return Objects retrieved from database
	*/
	public static java.util.ArrayList<Employee> getEmployeesBulkByNumber(IDataAccessTransaction txn, java.util.List<Employee> objs) throws Exception
	{
		java.util.ArrayList<Employee> retVal;


		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("SELECT @XML_DATA := ?, @XML_PATH := ? FROM DUAL WHERE 1 < 0; SELECT @TAG_NAME := SUBSTRING_INDEX(@XML_PATH, '/', -1) AS TAG_NAME FROM DUAL WHERE 1 < 0;  SELECT @TAG_BEGIN := CONCAT('<', @TAG_NAME, '>') AS TAG_BEGIN, @TAG_END := CONCAT('</', @TAG_NAME, '>') AS TAG_END FROM DUAL WHERE 1 < 0;  SELECT @TAG_LENGTH := LENGTH(@TAG_BEGIN) AS TAG_LENGTH, @ROWS_COUNT := ExtractValue(@XML_DATA, CONCAT('COUNT(', @XML_PATH, ')')) AS ROWS_COUNT, @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, 1) AS START_POINTER, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) AS END_POINTER FROM DUAL;  SELECT HR_EMPLOYEE.EMP_ID, HR_EMPLOYEE.EMP_NO, HR_EMPLOYEE.EMP_FULL_NAME, HR_EMPLOYEE.EMP_Gender, HR_EMPLOYEE.EMP_HIR_DATE, HR_EMPLOYEE.EMP_STATUS, HR_EMPLOYEE.EMP_TYPE, HR_EMPLOYEE.EMP_SALARY, HR_EMPLOYEE.EMP_DIVISION_ID, HR_EMPLOYEE.EMP_PICTURE, HR_EMPLOYEE.EMP_LATEST_CI_DT  FROM HR_EMPLOYEE  INNER JOIN (SELECT ExtractValue(Nodes.Node, '/B') AS B  FROM (  SELECT SUBSTR(@XML_DATA, Positions.X + @TAG_LENGTH, Positions.Y - Positions.X - @TAG_LENGTH) as Node  FROM (SELECT @START_POINTER X, @END_POINTER Y UNION SELECT @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER) as X, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) as Y FROM   (  SELECT a.x FROM (SELECT 0 x UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a LEFT JOIN (SELECT 0 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b ON @ROWS_COUNT > 10 LEFT JOIN (SELECT 0 UNION SELECT 1) c ON @ROWS_COUNT > 100 LEFT JOIN (SELECT 0 UNION SELECT 1) d ON @ROWS_COUNT > 200 LEFT JOIN (SELECT 0 UNION SELECT 1) e ON @ROWS_COUNT > 400 LEFT JOIN (SELECT 0 UNION SELECT 1) f ON @ROWS_COUNT > 800 LEFT JOIN (SELECT 0 UNION SELECT 1) g ON @ROWS_COUNT > 1600 LEFT JOIN (SELECT 0 UNION SELECT 1) h ON @ROWS_COUNT > 3200  ) AS sequences  WHERE @START_POINTER > 0  ) AS Positions WHERE Positions.X > 0 AND Positions.Y > 0  ) as Nodes  ) AS TTable ON (HR_EMPLOYEE.EMP_NO = TTable.B)  ORDER BY EMP_ID ASC  ;  "))
		{
			ps.setString(1, Employee.ToXml(objs, Employee.NUMBER_BIT_FLAG));
			ps.setString(2, Employee.COLLECTION_XML_DATA_PATH);

			retVal = new java.util.ArrayList<Employee>();

			if (!ps.execute()) return retVal;

			ResultSet rs = null;

			do
			{
				if (rs != null) rs.close();
				rs = ps.getResultSet();
			} while (ps.getMoreResults(Statement.KEEP_CURRENT_RESULT));

			while (rs.next())
			{
				Employee obj = new Employee();

				obj.setId(rs.getInt("EMP_ID"));
				obj.setNumber(rs.getString("EMP_NO"));
				obj.setFullName(rs.getString("EMP_FULL_NAME"));
				if (rs.getObject("EMP_Gender") != null) obj.setGender(rs.getInt("EMP_Gender"));
				if (rs.getObject("EMP_HIR_DATE") != null) obj.setHiringDate(rs.getDate("EMP_HIR_DATE"));
				obj.setStatus(rs.getInt("EMP_STATUS"));
				obj.setType(rs.getInt("EMP_TYPE"));
				obj.setSalary(rs.getBigDecimal("EMP_SALARY"));
				obj.setDivisionId(rs.getInt("EMP_DIVISION_ID"));
				if (rs.getObject("EMP_PICTURE") != null) obj.setPicture(rs.getBytes("EMP_PICTURE"));
				obj.setLatestCheckInDateTime(rs.getTimestamp("EMP_LATEST_CI_DT"));

				retVal.add(obj);
			}

			return retVal;
		}
	}

	/**
	* Retrieves a set of records from database. The values returned for each record are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>. The returned objects are ordered by <code>{@link Employee#id id}</code> ascending
	* @param objs A list of <code>{@link Employee}</code> objects
	* @param rowsOffset Line number from which to start retrieving objects. First object has line number of 0
	* @param rowsCount Maximum number of objects returned
	* @return Objects retrieved from database
	*/
	public static java.util.ArrayList<Employee> getEmployeesBulkByIdPaginated(java.util.List<Employee> objs, int rowsOffset, int rowsCount) throws Exception
	{
		java.util.ArrayList<Employee> retVal;


		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("SELECT @XML_DATA := ?, @XML_PATH := ? FROM DUAL WHERE 1 < 0; SELECT @TAG_NAME := SUBSTRING_INDEX(@XML_PATH, '/', -1) AS TAG_NAME FROM DUAL WHERE 1 < 0;  SELECT @TAG_BEGIN := CONCAT('<', @TAG_NAME, '>') AS TAG_BEGIN, @TAG_END := CONCAT('</', @TAG_NAME, '>') AS TAG_END FROM DUAL WHERE 1 < 0;  SELECT @TAG_LENGTH := LENGTH(@TAG_BEGIN) AS TAG_LENGTH, @ROWS_COUNT := ExtractValue(@XML_DATA, CONCAT('COUNT(', @XML_PATH, ')')) AS ROWS_COUNT, @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, 1) AS START_POINTER, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) AS END_POINTER FROM DUAL;  SELECT HR_EMPLOYEE.EMP_ID, HR_EMPLOYEE.EMP_NO, HR_EMPLOYEE.EMP_FULL_NAME, HR_EMPLOYEE.EMP_Gender, HR_EMPLOYEE.EMP_HIR_DATE, HR_EMPLOYEE.EMP_STATUS, HR_EMPLOYEE.EMP_TYPE, HR_EMPLOYEE.EMP_SALARY, HR_EMPLOYEE.EMP_DIVISION_ID, HR_EMPLOYEE.EMP_PICTURE, HR_EMPLOYEE.EMP_LATEST_CI_DT  FROM HR_EMPLOYEE  INNER JOIN (SELECT ExtractValue(Nodes.Node, '/A') AS A  FROM (  SELECT SUBSTR(@XML_DATA, Positions.X + @TAG_LENGTH, Positions.Y - Positions.X - @TAG_LENGTH) as Node  FROM (SELECT @START_POINTER X, @END_POINTER Y UNION SELECT @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER) as X, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) as Y FROM   (  SELECT a.x FROM (SELECT 0 x UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a LEFT JOIN (SELECT 0 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b ON @ROWS_COUNT > 10 LEFT JOIN (SELECT 0 UNION SELECT 1) c ON @ROWS_COUNT > 100 LEFT JOIN (SELECT 0 UNION SELECT 1) d ON @ROWS_COUNT > 200 LEFT JOIN (SELECT 0 UNION SELECT 1) e ON @ROWS_COUNT > 400 LEFT JOIN (SELECT 0 UNION SELECT 1) f ON @ROWS_COUNT > 800 LEFT JOIN (SELECT 0 UNION SELECT 1) g ON @ROWS_COUNT > 1600 LEFT JOIN (SELECT 0 UNION SELECT 1) h ON @ROWS_COUNT > 3200  ) AS sequences  WHERE @START_POINTER > 0  ) AS Positions WHERE Positions.X > 0 AND Positions.Y > 0  ) as Nodes  ) AS TTable ON (HR_EMPLOYEE.EMP_ID = TTable.A)  ORDER BY EMP_ID ASC  LIMIT ?, ?  ;  "))
		{
			ps.setString(1, Employee.ToXml(objs, Employee.ID_BIT_FLAG));
			ps.setString(2, Employee.COLLECTION_XML_DATA_PATH);
			ps.setInt(3, rowsOffset);
			ps.setInt(4, rowsCount);

			retVal = new java.util.ArrayList<Employee>();

			if (!ps.execute()) return retVal;

			ResultSet rs = null;

			do
			{
				if (rs != null) rs.close();
				rs = ps.getResultSet();
			} while (ps.getMoreResults(Statement.KEEP_CURRENT_RESULT));

			while (rs.next())
			{
				Employee obj = new Employee();

				obj.setId(rs.getInt("EMP_ID"));
				obj.setNumber(rs.getString("EMP_NO"));
				obj.setFullName(rs.getString("EMP_FULL_NAME"));
				if (rs.getObject("EMP_Gender") != null) obj.setGender(rs.getInt("EMP_Gender"));
				if (rs.getObject("EMP_HIR_DATE") != null) obj.setHiringDate(rs.getDate("EMP_HIR_DATE"));
				obj.setStatus(rs.getInt("EMP_STATUS"));
				obj.setType(rs.getInt("EMP_TYPE"));
				obj.setSalary(rs.getBigDecimal("EMP_SALARY"));
				obj.setDivisionId(rs.getInt("EMP_DIVISION_ID"));
				if (rs.getObject("EMP_PICTURE") != null) obj.setPicture(rs.getBytes("EMP_PICTURE"));
				obj.setLatestCheckInDateTime(rs.getTimestamp("EMP_LATEST_CI_DT"));

				retVal.add(obj);
			}

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Retrieves a set of records from database. The values returned for each record are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>. The returned objects are ordered by <code>{@link Employee#id id}</code> ascending
	* @param txn A database transaction that will be associated with this data access operation
	* @param objs A list of <code>{@link Employee}</code> objects
	* @param rowsOffset Line number from which to start retrieving objects. First object has line number of 0
	* @param rowsCount Maximum number of objects returned
	* @return Objects retrieved from database
	*/
	public static java.util.ArrayList<Employee> getEmployeesBulkByIdPaginated(IDataAccessTransaction txn, java.util.List<Employee> objs, int rowsOffset, int rowsCount) throws Exception
	{
		java.util.ArrayList<Employee> retVal;


		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("SELECT @XML_DATA := ?, @XML_PATH := ? FROM DUAL WHERE 1 < 0; SELECT @TAG_NAME := SUBSTRING_INDEX(@XML_PATH, '/', -1) AS TAG_NAME FROM DUAL WHERE 1 < 0;  SELECT @TAG_BEGIN := CONCAT('<', @TAG_NAME, '>') AS TAG_BEGIN, @TAG_END := CONCAT('</', @TAG_NAME, '>') AS TAG_END FROM DUAL WHERE 1 < 0;  SELECT @TAG_LENGTH := LENGTH(@TAG_BEGIN) AS TAG_LENGTH, @ROWS_COUNT := ExtractValue(@XML_DATA, CONCAT('COUNT(', @XML_PATH, ')')) AS ROWS_COUNT, @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, 1) AS START_POINTER, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) AS END_POINTER FROM DUAL;  SELECT HR_EMPLOYEE.EMP_ID, HR_EMPLOYEE.EMP_NO, HR_EMPLOYEE.EMP_FULL_NAME, HR_EMPLOYEE.EMP_Gender, HR_EMPLOYEE.EMP_HIR_DATE, HR_EMPLOYEE.EMP_STATUS, HR_EMPLOYEE.EMP_TYPE, HR_EMPLOYEE.EMP_SALARY, HR_EMPLOYEE.EMP_DIVISION_ID, HR_EMPLOYEE.EMP_PICTURE, HR_EMPLOYEE.EMP_LATEST_CI_DT  FROM HR_EMPLOYEE  INNER JOIN (SELECT ExtractValue(Nodes.Node, '/A') AS A  FROM (  SELECT SUBSTR(@XML_DATA, Positions.X + @TAG_LENGTH, Positions.Y - Positions.X - @TAG_LENGTH) as Node  FROM (SELECT @START_POINTER X, @END_POINTER Y UNION SELECT @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER) as X, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) as Y FROM   (  SELECT a.x FROM (SELECT 0 x UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a LEFT JOIN (SELECT 0 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b ON @ROWS_COUNT > 10 LEFT JOIN (SELECT 0 UNION SELECT 1) c ON @ROWS_COUNT > 100 LEFT JOIN (SELECT 0 UNION SELECT 1) d ON @ROWS_COUNT > 200 LEFT JOIN (SELECT 0 UNION SELECT 1) e ON @ROWS_COUNT > 400 LEFT JOIN (SELECT 0 UNION SELECT 1) f ON @ROWS_COUNT > 800 LEFT JOIN (SELECT 0 UNION SELECT 1) g ON @ROWS_COUNT > 1600 LEFT JOIN (SELECT 0 UNION SELECT 1) h ON @ROWS_COUNT > 3200  ) AS sequences  WHERE @START_POINTER > 0  ) AS Positions WHERE Positions.X > 0 AND Positions.Y > 0  ) as Nodes  ) AS TTable ON (HR_EMPLOYEE.EMP_ID = TTable.A)  ORDER BY EMP_ID ASC  LIMIT ?, ?  ;  "))
		{
			ps.setString(1, Employee.ToXml(objs, Employee.ID_BIT_FLAG));
			ps.setString(2, Employee.COLLECTION_XML_DATA_PATH);
			ps.setInt(3, rowsOffset);
			ps.setInt(4, rowsCount);

			retVal = new java.util.ArrayList<Employee>();

			if (!ps.execute()) return retVal;

			ResultSet rs = null;

			do
			{
				if (rs != null) rs.close();
				rs = ps.getResultSet();
			} while (ps.getMoreResults(Statement.KEEP_CURRENT_RESULT));

			while (rs.next())
			{
				Employee obj = new Employee();

				obj.setId(rs.getInt("EMP_ID"));
				obj.setNumber(rs.getString("EMP_NO"));
				obj.setFullName(rs.getString("EMP_FULL_NAME"));
				if (rs.getObject("EMP_Gender") != null) obj.setGender(rs.getInt("EMP_Gender"));
				if (rs.getObject("EMP_HIR_DATE") != null) obj.setHiringDate(rs.getDate("EMP_HIR_DATE"));
				obj.setStatus(rs.getInt("EMP_STATUS"));
				obj.setType(rs.getInt("EMP_TYPE"));
				obj.setSalary(rs.getBigDecimal("EMP_SALARY"));
				obj.setDivisionId(rs.getInt("EMP_DIVISION_ID"));
				if (rs.getObject("EMP_PICTURE") != null) obj.setPicture(rs.getBytes("EMP_PICTURE"));
				obj.setLatestCheckInDateTime(rs.getTimestamp("EMP_LATEST_CI_DT"));

				retVal.add(obj);
			}

			return retVal;
		}
	}

	/**
	* Updates a set of records in database. The values updated for each record are 
	* @param objs A list of <code>{@link (Employee)}</code> objects
	* @return Number of rows affected
	*/
	public static int updateEmployeesBulkById(java.util.List<Employee> objs) throws Exception
	{
		int retVal;


		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("SET @XML_DATA = ?; SET @XML_PATH = ?; SET @TAG_NAME = SUBSTRING_INDEX(@XML_PATH, '/', -1);  SET @TAG_BEGIN = CONCAT('<', @TAG_NAME, '>');  SET @TAG_END = CONCAT('</', @TAG_NAME, '>');  SET @TAG_LENGTH = LENGTH(@TAG_BEGIN);  SET @ROWS_COUNT = ExtractValue(@XML_DATA, CONCAT('COUNT(', @XML_PATH, ')'));    SET @START_POINTER = 0;  SET @END_POINTER = 1;    SET @START_POINTER = LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER);  SET @END_POINTER = LOCATE(@TAG_END, @XML_DATA, @START_POINTER);    UPDATE HR_EMPLOYEE  INNER JOIN (SELECT ExtractValue(Nodes.Node, '/C') AS C, ExtractValue(Nodes.Node, '/D') AS D, ExtractValue(Nodes.Node, '/F') AS F, ExtractValue(Nodes.Node, '/G') AS G, ExtractValue(Nodes.Node, '/H') AS H, ExtractValue(Nodes.Node, '/I') AS I, STR_TO_DATE(ExtractValue(Nodes.Node, '/E'), '%Y-%m-%d') AS E, STR_TO_DATE(ExtractValue(Nodes.Node, '/K'), '%Y-%m-%dT%H:%i:%S') AS K, ExtractValue(Nodes.Node, '/A') AS A  FROM (  SELECT SUBSTR(@XML_DATA, Positions.X + @TAG_LENGTH, Positions.Y - Positions.X - @TAG_LENGTH) as Node  FROM (SELECT @START_POINTER X, @END_POINTER Y UNION SELECT @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER) as X, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) as Y FROM   (  SELECT a.x FROM (SELECT 0 x UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a LEFT JOIN (SELECT 0 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b ON @ROWS_COUNT > 10 LEFT JOIN (SELECT 0 UNION SELECT 1) c ON @ROWS_COUNT > 100 LEFT JOIN (SELECT 0 UNION SELECT 1) d ON @ROWS_COUNT > 200 LEFT JOIN (SELECT 0 UNION SELECT 1) e ON @ROWS_COUNT > 400 LEFT JOIN (SELECT 0 UNION SELECT 1) f ON @ROWS_COUNT > 800 LEFT JOIN (SELECT 0 UNION SELECT 1) g ON @ROWS_COUNT > 1600 LEFT JOIN (SELECT 0 UNION SELECT 1) h ON @ROWS_COUNT > 3200  ) AS sequences  WHERE @START_POINTER > 0  ) AS Positions WHERE Positions.X > 0 AND Positions.Y > 0  ) as Nodes  ) AS TTable ON (HR_EMPLOYEE.EMP_ID = TTable.A)  SET HR_EMPLOYEE.EMP_DIVISION_ID = TTable.I, HR_EMPLOYEE.EMP_FULL_NAME = TTable.C, HR_EMPLOYEE.EMP_Gender = TTable.D, HR_EMPLOYEE.EMP_HIR_DATE = TTable.E, HR_EMPLOYEE.EMP_LATEST_CI_DT = TTable.K, HR_EMPLOYEE.EMP_SALARY = TTable.H, HR_EMPLOYEE.EMP_STATUS = TTable.F, HR_EMPLOYEE.EMP_TYPE = TTable.G  ;  "))
		{
			ps.setString(1, Employee.ToXml(objs, Employee.FULLNAME_BIT_FLAG | Employee.GENDER_BIT_FLAG | Employee.STATUS_BIT_FLAG | Employee.TYPE_BIT_FLAG | Employee.SALARY_BIT_FLAG | Employee.DIVISIONID_BIT_FLAG | Employee.HIRINGDATE_BIT_FLAG | Employee.LATESTCHECKINDATETIME_BIT_FLAG | Employee.ID_BIT_FLAG));
			ps.setString(2, Employee.COLLECTION_XML_DATA_PATH);

			retVal = ps.executeUpdate();

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Updates a set of records in database. The values updated for each record are 
	* @param txn A database transaction that will be associated with this data access operation
	* @param objs A list of <code>{@link (Employee)}</code> objects
	* @return Number of rows affected
	*/
	public static int updateEmployeesBulkById(IDataAccessTransaction txn, java.util.List<Employee> objs) throws Exception
	{
		int retVal;


		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("SET @XML_DATA = ?; SET @XML_PATH = ?; SET @TAG_NAME = SUBSTRING_INDEX(@XML_PATH, '/', -1);  SET @TAG_BEGIN = CONCAT('<', @TAG_NAME, '>');  SET @TAG_END = CONCAT('</', @TAG_NAME, '>');  SET @TAG_LENGTH = LENGTH(@TAG_BEGIN);  SET @ROWS_COUNT = ExtractValue(@XML_DATA, CONCAT('COUNT(', @XML_PATH, ')'));    SET @START_POINTER = 0;  SET @END_POINTER = 1;    SET @START_POINTER = LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER);  SET @END_POINTER = LOCATE(@TAG_END, @XML_DATA, @START_POINTER);    UPDATE HR_EMPLOYEE  INNER JOIN (SELECT ExtractValue(Nodes.Node, '/C') AS C, ExtractValue(Nodes.Node, '/D') AS D, ExtractValue(Nodes.Node, '/F') AS F, ExtractValue(Nodes.Node, '/G') AS G, ExtractValue(Nodes.Node, '/H') AS H, ExtractValue(Nodes.Node, '/I') AS I, STR_TO_DATE(ExtractValue(Nodes.Node, '/E'), '%Y-%m-%d') AS E, STR_TO_DATE(ExtractValue(Nodes.Node, '/K'), '%Y-%m-%dT%H:%i:%S') AS K, ExtractValue(Nodes.Node, '/A') AS A  FROM (  SELECT SUBSTR(@XML_DATA, Positions.X + @TAG_LENGTH, Positions.Y - Positions.X - @TAG_LENGTH) as Node  FROM (SELECT @START_POINTER X, @END_POINTER Y UNION SELECT @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER) as X, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) as Y FROM   (  SELECT a.x FROM (SELECT 0 x UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a LEFT JOIN (SELECT 0 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b ON @ROWS_COUNT > 10 LEFT JOIN (SELECT 0 UNION SELECT 1) c ON @ROWS_COUNT > 100 LEFT JOIN (SELECT 0 UNION SELECT 1) d ON @ROWS_COUNT > 200 LEFT JOIN (SELECT 0 UNION SELECT 1) e ON @ROWS_COUNT > 400 LEFT JOIN (SELECT 0 UNION SELECT 1) f ON @ROWS_COUNT > 800 LEFT JOIN (SELECT 0 UNION SELECT 1) g ON @ROWS_COUNT > 1600 LEFT JOIN (SELECT 0 UNION SELECT 1) h ON @ROWS_COUNT > 3200  ) AS sequences  WHERE @START_POINTER > 0  ) AS Positions WHERE Positions.X > 0 AND Positions.Y > 0  ) as Nodes  ) AS TTable ON (HR_EMPLOYEE.EMP_ID = TTable.A)  SET HR_EMPLOYEE.EMP_DIVISION_ID = TTable.I, HR_EMPLOYEE.EMP_FULL_NAME = TTable.C, HR_EMPLOYEE.EMP_Gender = TTable.D, HR_EMPLOYEE.EMP_HIR_DATE = TTable.E, HR_EMPLOYEE.EMP_LATEST_CI_DT = TTable.K, HR_EMPLOYEE.EMP_SALARY = TTable.H, HR_EMPLOYEE.EMP_STATUS = TTable.F, HR_EMPLOYEE.EMP_TYPE = TTable.G  ;  "))
		{
			ps.setString(1, Employee.ToXml(objs, Employee.FULLNAME_BIT_FLAG | Employee.GENDER_BIT_FLAG | Employee.STATUS_BIT_FLAG | Employee.TYPE_BIT_FLAG | Employee.SALARY_BIT_FLAG | Employee.DIVISIONID_BIT_FLAG | Employee.HIRINGDATE_BIT_FLAG | Employee.LATESTCHECKINDATETIME_BIT_FLAG | Employee.ID_BIT_FLAG));
			ps.setString(2, Employee.COLLECTION_XML_DATA_PATH);

			retVal = ps.executeUpdate();

			return retVal;
		}
	}

	/**
	* Deletes a set of records from database
	* @param objs A list of <code>{@link Employee}</code> objects
	* @return Number of rows affected
	*/
	public static int deleteEmployeesBulkById(java.util.List<Employee> objs) throws Exception
	{
		int retVal;


		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("SET @XML_DATA = ?; SET @XML_PATH = ?; SET @TAG_NAME = SUBSTRING_INDEX(@XML_PATH, '/', -1);  SET @TAG_BEGIN = CONCAT('<', @TAG_NAME, '>');  SET @TAG_END = CONCAT('</', @TAG_NAME, '>');  SET @TAG_LENGTH = LENGTH(@TAG_BEGIN);  SET @ROWS_COUNT = ExtractValue(@XML_DATA, CONCAT('COUNT(', @XML_PATH, ')'));    SET @START_POINTER = 0;  SET @END_POINTER = 1;    SET @START_POINTER = LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER);  SET @END_POINTER = LOCATE(@TAG_END, @XML_DATA, @START_POINTER);    DELETE HR_EMPLOYEE.* FROM HR_EMPLOYEE  INNER JOIN (SELECT ExtractValue(Nodes.Node, '/A') AS A  FROM (  SELECT SUBSTR(@XML_DATA, Positions.X + @TAG_LENGTH, Positions.Y - Positions.X - @TAG_LENGTH) as Node  FROM (SELECT @START_POINTER X, @END_POINTER Y UNION SELECT @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER) as X, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) as Y FROM   (  SELECT a.x FROM (SELECT 0 x UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a LEFT JOIN (SELECT 0 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b ON @ROWS_COUNT > 10 LEFT JOIN (SELECT 0 UNION SELECT 1) c ON @ROWS_COUNT > 100 LEFT JOIN (SELECT 0 UNION SELECT 1) d ON @ROWS_COUNT > 200 LEFT JOIN (SELECT 0 UNION SELECT 1) e ON @ROWS_COUNT > 400 LEFT JOIN (SELECT 0 UNION SELECT 1) f ON @ROWS_COUNT > 800 LEFT JOIN (SELECT 0 UNION SELECT 1) g ON @ROWS_COUNT > 1600 LEFT JOIN (SELECT 0 UNION SELECT 1) h ON @ROWS_COUNT > 3200  ) AS sequences  WHERE @START_POINTER > 0  ) AS Positions WHERE Positions.X > 0 AND Positions.Y > 0  ) as Nodes  ) AS TTable ON (HR_EMPLOYEE.EMP_ID = TTable.A)  ;  "))
		{
			ps.setString(1, Employee.ToXml(objs, Employee.ID_BIT_FLAG));
			ps.setString(2, Employee.COLLECTION_XML_DATA_PATH);

			retVal = ps.executeUpdate();

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Deletes a set of records from database
	* @param txn A database transaction that will be associated with this data access operation
	* @param objs A list of <code>{@link Employee}</code> objects
	* @return Number of rows affected
	*/
	public static int deleteEmployeesBulkById(IDataAccessTransaction txn, java.util.List<Employee> objs) throws Exception
	{
		int retVal;


		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("SET @XML_DATA = ?; SET @XML_PATH = ?; SET @TAG_NAME = SUBSTRING_INDEX(@XML_PATH, '/', -1);  SET @TAG_BEGIN = CONCAT('<', @TAG_NAME, '>');  SET @TAG_END = CONCAT('</', @TAG_NAME, '>');  SET @TAG_LENGTH = LENGTH(@TAG_BEGIN);  SET @ROWS_COUNT = ExtractValue(@XML_DATA, CONCAT('COUNT(', @XML_PATH, ')'));    SET @START_POINTER = 0;  SET @END_POINTER = 1;    SET @START_POINTER = LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER);  SET @END_POINTER = LOCATE(@TAG_END, @XML_DATA, @START_POINTER);    DELETE HR_EMPLOYEE.* FROM HR_EMPLOYEE  INNER JOIN (SELECT ExtractValue(Nodes.Node, '/A') AS A  FROM (  SELECT SUBSTR(@XML_DATA, Positions.X + @TAG_LENGTH, Positions.Y - Positions.X - @TAG_LENGTH) as Node  FROM (SELECT @START_POINTER X, @END_POINTER Y UNION SELECT @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER) as X, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) as Y FROM   (  SELECT a.x FROM (SELECT 0 x UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a LEFT JOIN (SELECT 0 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b ON @ROWS_COUNT > 10 LEFT JOIN (SELECT 0 UNION SELECT 1) c ON @ROWS_COUNT > 100 LEFT JOIN (SELECT 0 UNION SELECT 1) d ON @ROWS_COUNT > 200 LEFT JOIN (SELECT 0 UNION SELECT 1) e ON @ROWS_COUNT > 400 LEFT JOIN (SELECT 0 UNION SELECT 1) f ON @ROWS_COUNT > 800 LEFT JOIN (SELECT 0 UNION SELECT 1) g ON @ROWS_COUNT > 1600 LEFT JOIN (SELECT 0 UNION SELECT 1) h ON @ROWS_COUNT > 3200  ) AS sequences  WHERE @START_POINTER > 0  ) AS Positions WHERE Positions.X > 0 AND Positions.Y > 0  ) as Nodes  ) AS TTable ON (HR_EMPLOYEE.EMP_ID = TTable.A)  ;  "))
		{
			ps.setString(1, Employee.ToXml(objs, Employee.ID_BIT_FLAG));
			ps.setString(2, Employee.COLLECTION_XML_DATA_PATH);

			retVal = ps.executeUpdate();

			return retVal;
		}
	}

	/**
	* Retrieves a single record from database. The values returned are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>
	* @param byFullName (filter parameter) Employee's full name as appears on his Driving Licence or Passport. No prefix (E.g. Mr) should be stored in this field
	* @return Objects retrieved from database
	*/
	public static Employee getEmployeeByNameStartsWith(String byFullName) throws Exception
	{
		Employee retVal;


		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("SELECT EMP_ID, EMP_NO, EMP_FULL_NAME, EMP_Gender, EMP_HIR_DATE, EMP_STATUS, EMP_TYPE, EMP_SALARY, EMP_DIVISION_ID, EMP_PICTURE, EMP_LATEST_CI_DT  FROM HR_EMPLOYEE  WHERE (LOWER(HR_EMPLOYEE.EMP_FULL_NAME) LIKE CONCAT(LOWER(?), '%'))  ;  "))
		{
			ps.setString(1, byFullName);

			retVal = new Employee();

			if (!ps.execute()) return retVal;

			ResultSet rs = ps.getResultSet();
			if (rs.next())
			{
				retVal.setId(rs.getInt("EMP_ID"));
				retVal.setNumber(rs.getString("EMP_NO"));
				retVal.setFullName(rs.getString("EMP_FULL_NAME"));
				if (rs.getObject("EMP_Gender") != null) retVal.setGender(rs.getInt("EMP_Gender"));
				if (rs.getObject("EMP_HIR_DATE") != null) retVal.setHiringDate(rs.getDate("EMP_HIR_DATE", java.util.Calendar.getInstance()));
				retVal.setStatus(rs.getInt("EMP_STATUS"));
				retVal.setType(rs.getInt("EMP_TYPE"));
				retVal.setSalary(rs.getBigDecimal("EMP_SALARY"));
				retVal.setDivisionId(rs.getInt("EMP_DIVISION_ID"));
				if (rs.getObject("EMP_PICTURE") != null) retVal.setPicture(rs.getBytes("EMP_PICTURE"));
				retVal.setLatestCheckInDateTime(rs.getTimestamp("EMP_LATEST_CI_DT"));
			}
			else
			{
				retVal = null;
			}

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Retrieves a single record from database. The values returned are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>
	* @param byFullName (filter parameter) Employee's full name as appears on his Driving Licence or Passport. No prefix (E.g. Mr) should be stored in this field
	* @return Objects retrieved from database
	*/
	public static Employee getEmployeeByNameEndsWith(String byFullName) throws Exception
	{
		Employee retVal;


		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("SELECT EMP_ID, EMP_NO, EMP_FULL_NAME, EMP_Gender, EMP_HIR_DATE, EMP_STATUS, EMP_TYPE, EMP_SALARY, EMP_DIVISION_ID, EMP_PICTURE, EMP_LATEST_CI_DT  FROM HR_EMPLOYEE  WHERE (HR_EMPLOYEE.EMP_FULL_NAME LIKE CONCAT('%', ?))  ;  "))
		{
			ps.setString(1, byFullName);

			retVal = new Employee();

			if (!ps.execute()) return retVal;

			ResultSet rs = ps.getResultSet();
			if (rs.next())
			{
				retVal.setId(rs.getInt("EMP_ID"));
				retVal.setNumber(rs.getString("EMP_NO"));
				retVal.setFullName(rs.getString("EMP_FULL_NAME"));
				if (rs.getObject("EMP_Gender") != null) retVal.setGender(rs.getInt("EMP_Gender"));
				if (rs.getObject("EMP_HIR_DATE") != null) retVal.setHiringDate(rs.getDate("EMP_HIR_DATE", java.util.Calendar.getInstance()));
				retVal.setStatus(rs.getInt("EMP_STATUS"));
				retVal.setType(rs.getInt("EMP_TYPE"));
				retVal.setSalary(rs.getBigDecimal("EMP_SALARY"));
				retVal.setDivisionId(rs.getInt("EMP_DIVISION_ID"));
				if (rs.getObject("EMP_PICTURE") != null) retVal.setPicture(rs.getBytes("EMP_PICTURE"));
				retVal.setLatestCheckInDateTime(rs.getTimestamp("EMP_LATEST_CI_DT"));
			}
			else
			{
				retVal = null;
			}

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Retrieves a single record from database. The values returned are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>
	* @param byFullName (filter parameter) Employee's full name as appears on his Driving Licence or Passport. No prefix (E.g. Mr) should be stored in this field
	* @return Objects retrieved from database
	*/
	public static Employee getEmployeeByNameContains(String byFullName) throws Exception
	{
		Employee retVal;


		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("SELECT EMP_ID, EMP_NO, EMP_FULL_NAME, EMP_Gender, EMP_HIR_DATE, EMP_STATUS, EMP_TYPE, EMP_SALARY, EMP_DIVISION_ID, EMP_PICTURE, EMP_LATEST_CI_DT  FROM HR_EMPLOYEE  WHERE (HR_EMPLOYEE.EMP_FULL_NAME LIKE CONCAT('%', ?, '%'))  ;  "))
		{
			ps.setString(1, byFullName);

			retVal = new Employee();

			if (!ps.execute()) return retVal;

			ResultSet rs = ps.getResultSet();
			if (rs.next())
			{
				retVal.setId(rs.getInt("EMP_ID"));
				retVal.setNumber(rs.getString("EMP_NO"));
				retVal.setFullName(rs.getString("EMP_FULL_NAME"));
				if (rs.getObject("EMP_Gender") != null) retVal.setGender(rs.getInt("EMP_Gender"));
				if (rs.getObject("EMP_HIR_DATE") != null) retVal.setHiringDate(rs.getDate("EMP_HIR_DATE", java.util.Calendar.getInstance()));
				retVal.setStatus(rs.getInt("EMP_STATUS"));
				retVal.setType(rs.getInt("EMP_TYPE"));
				retVal.setSalary(rs.getBigDecimal("EMP_SALARY"));
				retVal.setDivisionId(rs.getInt("EMP_DIVISION_ID"));
				if (rs.getObject("EMP_PICTURE") != null) retVal.setPicture(rs.getBytes("EMP_PICTURE"));
				retVal.setLatestCheckInDateTime(rs.getTimestamp("EMP_LATEST_CI_DT"));
			}
			else
			{
				retVal = null;
			}

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Retrieves a single record from database. The values returned are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>
	* @return Objects retrieved from database
	*/
	public static Employee getEmployeeByNameContainsConstant() throws Exception
	{
		Employee retVal;


		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("SELECT EMP_ID, EMP_NO, EMP_FULL_NAME, EMP_Gender, EMP_HIR_DATE, EMP_STATUS, EMP_TYPE, EMP_SALARY, EMP_DIVISION_ID, EMP_PICTURE, EMP_LATEST_CI_DT  FROM HR_EMPLOYEE  WHERE (LOWER(HR_EMPLOYEE.EMP_FULL_NAME) LIKE CONCAT('%', LOWER('Jawad'), '%'))  ;  "))
		{

			retVal = new Employee();

			if (!ps.execute()) return retVal;

			ResultSet rs = ps.getResultSet();
			if (rs.next())
			{
				retVal.setId(rs.getInt("EMP_ID"));
				retVal.setNumber(rs.getString("EMP_NO"));
				retVal.setFullName(rs.getString("EMP_FULL_NAME"));
				if (rs.getObject("EMP_Gender") != null) retVal.setGender(rs.getInt("EMP_Gender"));
				if (rs.getObject("EMP_HIR_DATE") != null) retVal.setHiringDate(rs.getDate("EMP_HIR_DATE", java.util.Calendar.getInstance()));
				retVal.setStatus(rs.getInt("EMP_STATUS"));
				retVal.setType(rs.getInt("EMP_TYPE"));
				retVal.setSalary(rs.getBigDecimal("EMP_SALARY"));
				retVal.setDivisionId(rs.getInt("EMP_DIVISION_ID"));
				if (rs.getObject("EMP_PICTURE") != null) retVal.setPicture(rs.getBytes("EMP_PICTURE"));
				retVal.setLatestCheckInDateTime(rs.getTimestamp("EMP_LATEST_CI_DT"));
			}
			else
			{
				retVal = null;
			}

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Retrieves multiple records from database. The values returned for each record are <code>{@link Employee#id id}</code>, <code>{@link Employee#number number}</code>, <code>{@link Employee#fullName fullName}</code>, <code>{@link Employee#gender gender}</code>, <code>{@link Employee#hiringDate hiringDate}</code>, <code>{@link Employee#status status}</code>, <code>{@link Employee#type type}</code>, <code>{@link Employee#salary salary}</code>, <code>{@link Employee#divisionId divisionId}</code>, <code>{@link Employee#picture picture}</code>, <code>{@link Employee#latestCheckInDateTime latestCheckInDateTime}</code>
	* @param byFullName (filter parameter) Employee's full name as appears on his Driving Licence or Passport. No prefix (E.g. Mr) should be stored in this field
	* @param byGender (filter parameter) 1 Indicates Male, and 2 Indicates Female
	* @param byStatus (filter parameter) Employee's status could be (1) Active, (2) Suspended, or (3) On Vacation. This field should be used to control access to company's internal systems
	* @param byType (filter parameter) This field distinguishes between (1) full-time employees, (2) part-time employees, and (3) interns
	* @param byDivisionId (filter parameter) No documentation is available
	* @return Objects retrieved from database
	*/
	public static java.util.ArrayList<Employee> searchEmployees(String byFullName, int byGender, int byStatus, int byType, int byDivisionId) throws Exception
	{
		java.util.ArrayList<Employee> retVal;


		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("SELECT EMP_ID, EMP_NO, EMP_FULL_NAME, EMP_Gender, EMP_HIR_DATE, EMP_STATUS, EMP_TYPE, EMP_SALARY, EMP_DIVISION_ID, EMP_PICTURE, EMP_LATEST_CI_DT  FROM HR_EMPLOYEE  WHERE ((HR_EMPLOYEE.EMP_FULL_NAME LIKE CONCAT('%', ?, '%')) OR (? IS NULL)) AND ((HR_EMPLOYEE.EMP_Gender = ?) OR (? IS NULL)) AND ((HR_EMPLOYEE.EMP_STATUS = ?) OR (? IS NULL)) AND ((HR_EMPLOYEE.EMP_TYPE = ?) OR (? IS NULL)) AND ((HR_EMPLOYEE.EMP_DIVISION_ID = ?) OR (HR_EMPLOYEE.EMP_DIVISION_ID IS NULL) OR (? IS NULL))  ;  "))
		{
			if (byFullName != null) ps.setString(1, byFullName); else ps.setNull(1, Types.VARCHAR);
			if (byFullName != null) ps.setString(2, byFullName); else ps.setNull(2, Types.VARCHAR);
			if (byGender != 0) ps.setInt(3, byGender); else ps.setNull(3, Types.INTEGER);
			if (byGender != 0) ps.setInt(4, byGender); else ps.setNull(4, Types.INTEGER);
			if (byStatus != 0) ps.setInt(5, byStatus); else ps.setNull(5, Types.INTEGER);
			if (byStatus != 0) ps.setInt(6, byStatus); else ps.setNull(6, Types.INTEGER);
			if (byType != 0) ps.setInt(7, byType); else ps.setNull(7, Types.INTEGER);
			if (byType != 0) ps.setInt(8, byType); else ps.setNull(8, Types.INTEGER);
			if (byDivisionId != 0) ps.setInt(9, byDivisionId); else ps.setNull(9, Types.INTEGER);
			if (byDivisionId != 0) ps.setInt(10, byDivisionId); else ps.setNull(10, Types.INTEGER);

			retVal = new java.util.ArrayList<Employee>();

			if (!ps.execute()) return retVal;

			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{
				Employee obj = new Employee();

				obj.setId(rs.getInt("EMP_ID"));
				obj.setNumber(rs.getString("EMP_NO"));
				obj.setFullName(rs.getString("EMP_FULL_NAME"));
				if (rs.getObject("EMP_Gender") != null) obj.setGender(rs.getInt("EMP_Gender"));
				if (rs.getObject("EMP_HIR_DATE") != null) obj.setHiringDate(rs.getDate("EMP_HIR_DATE"));
				obj.setStatus(rs.getInt("EMP_STATUS"));
				obj.setType(rs.getInt("EMP_TYPE"));
				obj.setSalary(rs.getBigDecimal("EMP_SALARY"));
				obj.setDivisionId(rs.getInt("EMP_DIVISION_ID"));
				if (rs.getObject("EMP_PICTURE") != null) obj.setPicture(rs.getBytes("EMP_PICTURE"));
				obj.setLatestCheckInDateTime(rs.getTimestamp("EMP_LATEST_CI_DT"));

				retVal.add(obj);
			}

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Updates a single field in database. The value updated is <code>{@link Employee#salary salary}</code>
	* @param salary Employee's base salary NOT including any bonuses or allowances. Net salary, including bonuses and allowances, is calculated based on some rules coded in the business logic layer
	* @param byId (filter parameter) A unique number used internally by the system. This number is not supposed to be known employees
	* @return Number of rows affected
	*/
	public static int updateEmployeeSalaryById(java.math.BigDecimal salary, int byId) throws Exception
	{
		int retVal;


		Connection conn = ConnectionProvider.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("UPDATE HR_EMPLOYEE  SET HR_EMPLOYEE.EMP_SALARY = ?  WHERE (HR_EMPLOYEE.EMP_ID = ?)  ;  "))
		{
			ps.setBigDecimal(1, salary);
			ps.setInt(2, byId);

			retVal = ps.executeUpdate();

			return retVal;
		}
		finally
		{
			ConnectionProvider.close(conn);
		}
	}

	/**
	* Updates a single field in database. The value updated is <code>{@link Employee#salary salary}</code>
	* @param txn A database transaction that will be associated with this data access operation
	* @param salary Employee's base salary NOT including any bonuses or allowances. Net salary, including bonuses and allowances, is calculated based on some rules coded in the business logic layer
	* @param byId (filter parameter) A unique number used internally by the system. This number is not supposed to be known employees
	* @return Number of rows affected
	*/
	public static int updateEmployeeSalaryById(IDataAccessTransaction txn, java.math.BigDecimal salary, int byId) throws Exception
	{
		int retVal;


		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("UPDATE HR_EMPLOYEE  SET HR_EMPLOYEE.EMP_SALARY = ?  WHERE (HR_EMPLOYEE.EMP_ID = ?)  ;  "))
		{
			ps.setBigDecimal(1, salary);
			ps.setInt(2, byId);

			retVal = ps.executeUpdate();

			return retVal;
		}
	}

}
