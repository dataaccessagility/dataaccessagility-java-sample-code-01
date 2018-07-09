package dataaccessobjects;

import java.sql.*;
import models.*;

public class EmployeeData
{
	/**
	* Inserts a single record into database. The values inserted are <code>{@link Employee#id id}</code>, <code>{@link Employee#name name}</code>, <code>{@link Employee#baseSalary baseSalary}</code>, <code>{@link Employee#hiringDate hiringDate}</code>
	* @param obj <code>{@link Employee}</code> object
	* @return Number of rows affected
	*/
	public static int addEmployee(Employee obj) throws Exception
	{
		int retVal;

		obj.validate();

		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement("INSERT INTO HR_EMPLOYEE (EMPLOYEE_BASE_SALARY, EMPLOYEE_HIRING_DATE, EMPLOYEE_ID, EMPLOYEE_NAME)  VALUES (?, ?, ?, ?)  ;    "))
		{
			ps.setBigDecimal(1, obj.getBaseSalary());
			ps.setDate(2, new Date(obj.getHiringDate().getTime()));
			ps.setInt(3, obj.getId());
			ps.setString(4, obj.getName());

			retVal = ps.executeUpdate();

			return retVal;
		}
	}

	/**
	* Inserts a single record into database. The values inserted are <code>{@link Employee#id id}</code>, <code>{@link Employee#name name}</code>, <code>{@link Employee#baseSalary baseSalary}</code>, <code>{@link Employee#hiringDate hiringDate}</code>
	* @param txn A database transaction that will be associated with this data access operation
	* @param obj <code>{@link Employee}</code> object
	* @return Number of rows affected
	*/
	public static int addEmployee(IDataAccessTransaction txn, Employee obj) throws Exception
	{
		int retVal;

		obj.validate();

		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("INSERT INTO HR_EMPLOYEE (EMPLOYEE_BASE_SALARY, EMPLOYEE_HIRING_DATE, EMPLOYEE_ID, EMPLOYEE_NAME)  VALUES (?, ?, ?, ?)  ;    "))
		{

			ps.setBigDecimal(1, obj.getBaseSalary());
			ps.setDate(2, new Date(obj.getHiringDate().getTime()));
			ps.setInt(3, obj.getId());
			ps.setString(4, obj.getName());

			retVal = ps.executeUpdate();

			return retVal;
		}
	}

	/**
	* Retrieves a single record from database. The values returned are <code>{@link Employee#id id}</code>, <code>{@link Employee#name name}</code>, <code>{@link Employee#baseSalary baseSalary}</code>, <code>{@link Employee#hiringDate hiringDate}</code>
	* @param byId (filter parameter) No documentation is available
	* @return Objects retrieved from database
	*/
	public static Employee getEmployeeById(int byId) throws Exception
	{
		Employee retVal;


		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement("SELECT EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_BASE_SALARY, EMPLOYEE_HIRING_DATE  FROM HR_EMPLOYEE  WHERE (HR_EMPLOYEE.EMPLOYEE_ID = ?)  ;  "))
		{
			ps.setInt(1, byId);

			retVal = new Employee();

			if (!ps.execute()) return retVal;

			ResultSet rs = ps.getResultSet();
			if (rs.next())
			{
				retVal.setId(rs.getInt("EMPLOYEE_ID"));
				retVal.setName(rs.getString("EMPLOYEE_NAME"));
				retVal.setBaseSalary(rs.getBigDecimal("EMPLOYEE_BASE_SALARY"));
				retVal.setHiringDate(rs.getDate("EMPLOYEE_HIRING_DATE"));
			}
			else
			{
				retVal = null;
			}

			return retVal;
		}
	}

	/**
	* Retrieves multiple records from database. The values returned for each record are <code>{@link Employee#id id}</code>, <code>{@link Employee#name name}</code>, <code>{@link Employee#baseSalary baseSalary}</code>, <code>{@link Employee#hiringDate hiringDate}</code>
	* @param rowsOffset Line number from which to start retrieving objects. First object has line number of 0
	* @param rowsCount Maximum number of objects returned
	* @return Objects retrieved from database
	*/
	public static java.util.ArrayList<Employee> getAllEmployees(int rowsOffset, int rowsCount) throws Exception
	{
		java.util.ArrayList<Employee> retVal;


		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement("SELECT EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_BASE_SALARY, EMPLOYEE_HIRING_DATE  FROM HR_EMPLOYEE  ORDER BY EMPLOYEE_HIRING_DATE DESC  LIMIT ?, ?  ;  "))
		{
			ps.setInt(1, rowsOffset);
			ps.setInt(2, rowsCount);

			retVal = new java.util.ArrayList<Employee>();

			if (!ps.execute()) return retVal;

			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{
				Employee obj = new Employee();

				obj.setId(rs.getInt("EMPLOYEE_ID"));
				obj.setName(rs.getString("EMPLOYEE_NAME"));
				obj.setBaseSalary(rs.getBigDecimal("EMPLOYEE_BASE_SALARY"));
				obj.setHiringDate(rs.getDate("EMPLOYEE_HIRING_DATE"));

				retVal.add(obj);
			}

			return retVal;
		}
	}

	/**
	* Updates a single field in database. The value updated is <code>{@link Employee#baseSalary baseSalary}</code>
	* @param baseSalary No documentation is available
	* @param byId (filter parameter) No documentation is available
	* @return Number of rows affected
	*/
	public static int updateEmployeeBaseSalary(java.math.BigDecimal baseSalary, int byId) throws Exception
	{
		int retVal;


		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement("UPDATE HR_EMPLOYEE  SET HR_EMPLOYEE.EMPLOYEE_BASE_SALARY = ?  WHERE (HR_EMPLOYEE.EMPLOYEE_ID = ?)  ;  "))
		{
			ps.setBigDecimal(1, baseSalary);
			ps.setInt(2, byId);

			retVal = ps.executeUpdate();

			return retVal;
		}
	}

	/**
	* Updates a single field in database. The value updated is <code>{@link Employee#baseSalary baseSalary}</code>
	* @param txn A database transaction that will be associated with this data access operation
	* @param baseSalary No documentation is available
	* @param byId (filter parameter) No documentation is available
	* @return Number of rows affected
	*/
	public static int updateEmployeeBaseSalary(IDataAccessTransaction txn, java.math.BigDecimal baseSalary, int byId) throws Exception
	{
		int retVal;


		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("UPDATE HR_EMPLOYEE  SET HR_EMPLOYEE.EMPLOYEE_BASE_SALARY = ?  WHERE (HR_EMPLOYEE.EMPLOYEE_ID = ?)  ;  "))
		{

			ps.setBigDecimal(1, baseSalary);
			ps.setInt(2, byId);

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

		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement("SET @XML_DATA = ?; SET @XML_PATH = ?; SET @TAG_NAME = SUBSTRING_INDEX(@XML_PATH, '/', -1);  SET @TAG_BEGIN = CONCAT('<', @TAG_NAME, '>');  SET @TAG_END = CONCAT('</', @TAG_NAME, '>');  SET @TAG_LENGTH = LENGTH(@TAG_BEGIN);  SET @ROWS_COUNT = ExtractValue(@XML_DATA, CONCAT('COUNT(', @XML_PATH, ')'));    SET @START_POINTER = 0;  SET @END_POINTER = 1;    SET @START_POINTER = LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER);  SET @END_POINTER = LOCATE(@TAG_END, @XML_DATA, @START_POINTER);    INSERT INTO HR_EMPLOYEE (EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_BASE_SALARY, EMPLOYEE_HIRING_DATE, DIVISION_ID)    SELECT ExtractValue(Nodes.Node, '/A') AS A, ExtractValue(Nodes.Node, '/B') AS B, ExtractValue(Nodes.Node, '/C') AS C, STR_TO_DATE(ExtractValue(Nodes.Node, '/D'), ), ExtractValue(Nodes.Node, '/E') AS E  FROM (  SELECT SUBSTR(@XML_DATA, Positions.X + @TAG_LENGTH, Positions.Y - Positions.X - @TAG_LENGTH) as Node  FROM (SELECT @START_POINTER X, @END_POINTER Y UNION SELECT @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER) as X, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) as Y FROM   (  SELECT a.x FROM (SELECT 0 x UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a LEFT JOIN (SELECT 0 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b ON @ROWS_COUNT > 10  ) AS sequences  WHERE @START_POINTER > 0  ) AS Positions WHERE Positions.X > 0 AND Positions.Y > 0  ) as Nodes  ;    "))
		{
			ps.setString(1, Employee.ToXml(objs, Employee.ID_BIT_FLAG | Employee.NAME_BIT_FLAG | Employee.BASESALARY_BIT_FLAG | Employee.HIRINGDATE_BIT_FLAG | Employee.DIVISIONID_BIT_FLAG));
			ps.setString(2, Employee.COLLECTION_XML_DATA_PATH);

			retVal = ps.executeUpdate();

			return retVal;
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

		try (PreparedStatement ps = ((DataAccessTransaction)txn).getConnection().prepareStatement("SET @XML_DATA = ?; SET @XML_PATH = ?; SET @TAG_NAME = SUBSTRING_INDEX(@XML_PATH, '/', -1);  SET @TAG_BEGIN = CONCAT('<', @TAG_NAME, '>');  SET @TAG_END = CONCAT('</', @TAG_NAME, '>');  SET @TAG_LENGTH = LENGTH(@TAG_BEGIN);  SET @ROWS_COUNT = ExtractValue(@XML_DATA, CONCAT('COUNT(', @XML_PATH, ')'));    SET @START_POINTER = 0;  SET @END_POINTER = 1;    SET @START_POINTER = LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER);  SET @END_POINTER = LOCATE(@TAG_END, @XML_DATA, @START_POINTER);    INSERT INTO HR_EMPLOYEE (EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_BASE_SALARY, EMPLOYEE_HIRING_DATE, DIVISION_ID)    SELECT ExtractValue(Nodes.Node, '/A') AS A, ExtractValue(Nodes.Node, '/B') AS B, ExtractValue(Nodes.Node, '/C') AS C, STR_TO_DATE(ExtractValue(Nodes.Node, '/D'), ), ExtractValue(Nodes.Node, '/E') AS E  FROM (  SELECT SUBSTR(@XML_DATA, Positions.X + @TAG_LENGTH, Positions.Y - Positions.X - @TAG_LENGTH) as Node  FROM (SELECT @START_POINTER X, @END_POINTER Y UNION SELECT @START_POINTER := LOCATE(@TAG_BEGIN, @XML_DATA, @END_POINTER) as X, @END_POINTER := LOCATE(@TAG_END, @XML_DATA, @START_POINTER) as Y FROM   (  SELECT a.x FROM (SELECT 0 x UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a LEFT JOIN (SELECT 0 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b ON @ROWS_COUNT > 10  ) AS sequences  WHERE @START_POINTER > 0  ) AS Positions WHERE Positions.X > 0 AND Positions.Y > 0  ) as Nodes  ;    "))
		{

			ps.setString(1, Employee.ToXml(objs, Employee.ID_BIT_FLAG | Employee.NAME_BIT_FLAG | Employee.BASESALARY_BIT_FLAG | Employee.HIRINGDATE_BIT_FLAG | Employee.DIVISIONID_BIT_FLAG));
			ps.setString(2, Employee.COLLECTION_XML_DATA_PATH);

			retVal = ps.executeUpdate();

			return retVal;
		}
	}

}
