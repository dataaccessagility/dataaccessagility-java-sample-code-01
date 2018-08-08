package models;
/**
* Any person working for the company including part-time employees and interns
*/
public class Employee
{
	public static String COLLECTION_XML_DATA_PATH = "/Items/Employee";

	public static long ID_BIT_FLAG = 1;
	public static long NUMBER_BIT_FLAG = 2;
	public static long FULLNAME_BIT_FLAG = 4;
	public static long GENDER_BIT_FLAG = 8;
	public static long HIRINGDATE_BIT_FLAG = 16;
	public static long STATUS_BIT_FLAG = 32;
	public static long TYPE_BIT_FLAG = 64;
	public static long SALARY_BIT_FLAG = 128;
	public static long DIVISIONID_BIT_FLAG = 256;
	public static long PICTURE_BIT_FLAG = 512;
	public static long LATESTCHECKINDATETIME_BIT_FLAG = 1024;

	private long IS_NULL = Long.MAX_VALUE;

	/**
	* A unique number used internally by the system. This number is not supposed to be known employees
	*/
	private int id;
	/**
	* Employee's personnel number that is used by HR department
	*/
	private String number;
	/**
	* Employee's full name as appears on his Driving Licence or Passport. No prefix (E.g. Mr) should be stored in this field
	*/
	private String fullName;
	/**
	* 1 Indicates Male, and 2 Indicates Female
	*/
	private int gender;
	/**
	* Date of the 1st day of work
	*/
	private java.util.Date hiringDate;
	/**
	* Employee's status could be (1) Active, (2) Suspended, or (3) On Vacation. This field should be used to control access to company's internal systems
	*/
	private int status;
	/**
	* This field distinguishes between (1) full-time employees, (2) part-time employees, and (3) interns
	*/
	private int type;
	/**
	* Employee's base salary NOT including any bonuses or allowances. Net salary, including bonuses and allowances, is calculated based on some rules coded in the business logic layer
	*/
	private java.math.BigDecimal salary;
	/**
	* No documentation is available
	*/
	private int divisionId;
	/**
	* Employee's picture that appears on company id card
	*/
	private byte[] picture;
	/**
	* No documentation is available
	*/
	private java.util.Date latestCheckInDateTime;
	private Division division;

	/**
	* Sets the value of id
	* @param val A unique number used internally by the system. This number is not supposed to be known employees
	*/
	public void setId(int val)
	{
		this.id = val;
		this.IS_NULL = this.IS_NULL & (Long.MAX_VALUE - ID_BIT_FLAG);
	}

	/**
	* Returns the value of id
	* @return A unique number used internally by the system. This number is not supposed to be known employees
	*/
	public int getId()
	{
		return this.id;
	}

	/**
	* Sets the value of number
	* @param val Employee's personnel number that is used by HR department
	*/
	public void setNumber(String val)
	{
		this.number = val;
		this.IS_NULL = this.IS_NULL & (Long.MAX_VALUE - NUMBER_BIT_FLAG);
	}

	/**
	* Returns the value of number
	* @return Employee's personnel number that is used by HR department
	*/
	public String getNumber()
	{
		return this.number;
	}

	/**
	* Sets the value of fullName
	* @param val Employee's full name as appears on his Driving Licence or Passport. No prefix (E.g. Mr) should be stored in this field
	*/
	public void setFullName(String val)
	{
		this.fullName = val;
		this.IS_NULL = this.IS_NULL & (Long.MAX_VALUE - FULLNAME_BIT_FLAG);
	}

	/**
	* Returns the value of fullName
	* @return Employee's full name as appears on his Driving Licence or Passport. No prefix (E.g. Mr) should be stored in this field
	*/
	public String getFullName()
	{
		return this.fullName;
	}

	/**
	* Sets the value of gender
	* @param val 1 Indicates Male, and 2 Indicates Female
	*/
	public void setGender(int val)
	{
		this.gender = val;
		this.IS_NULL = this.IS_NULL & (Long.MAX_VALUE - GENDER_BIT_FLAG);
	}

	/**
	* Returns the value of gender
	* @return 1 Indicates Male, and 2 Indicates Female
	*/
	public int getGender()
	{
		return this.gender;
	}

	/**
	* Sets the value of hiringDate
	* @param val Date of the 1st day of work
	*/
	public void setHiringDate(java.util.Date val)
	{
		this.hiringDate = val;
		this.IS_NULL = this.IS_NULL & (Long.MAX_VALUE - HIRINGDATE_BIT_FLAG);
	}

	/**
	* Returns the value of hiringDate
	* @return Date of the 1st day of work
	*/
	public java.util.Date getHiringDate()
	{
		return this.hiringDate;
	}

	/**
	* Sets the value of status
	* @param val Employee's status could be (1) Active, (2) Suspended, or (3) On Vacation. This field should be used to control access to company's internal systems
	*/
	public void setStatus(int val)
	{
		this.status = val;
		this.IS_NULL = this.IS_NULL & (Long.MAX_VALUE - STATUS_BIT_FLAG);
	}

	/**
	* Returns the value of status
	* @return Employee's status could be (1) Active, (2) Suspended, or (3) On Vacation. This field should be used to control access to company's internal systems
	*/
	public int getStatus()
	{
		return this.status;
	}

	/**
	* Sets the value of type
	* @param val This field distinguishes between (1) full-time employees, (2) part-time employees, and (3) interns
	*/
	public void setType(int val)
	{
		this.type = val;
		this.IS_NULL = this.IS_NULL & (Long.MAX_VALUE - TYPE_BIT_FLAG);
	}

	/**
	* Returns the value of type
	* @return This field distinguishes between (1) full-time employees, (2) part-time employees, and (3) interns
	*/
	public int getType()
	{
		return this.type;
	}

	/**
	* Sets the value of salary
	* @param val Employee's base salary NOT including any bonuses or allowances. Net salary, including bonuses and allowances, is calculated based on some rules coded in the business logic layer
	*/
	public void setSalary(java.math.BigDecimal val)
	{
		this.salary = val;
		this.IS_NULL = this.IS_NULL & (Long.MAX_VALUE - SALARY_BIT_FLAG);
	}

	/**
	* Returns the value of salary
	* @return Employee's base salary NOT including any bonuses or allowances. Net salary, including bonuses and allowances, is calculated based on some rules coded in the business logic layer
	*/
	public java.math.BigDecimal getSalary()
	{
		return this.salary;
	}

	/**
	* Sets the value of divisionId
	* @param val No documentation is available
	*/
	public void setDivisionId(int val)
	{
		this.divisionId = val;
		this.IS_NULL = this.IS_NULL & (Long.MAX_VALUE - DIVISIONID_BIT_FLAG);
	}

	/**
	* Returns the value of divisionId
	* @return No documentation is available
	*/
	public int getDivisionId()
	{
		return this.divisionId;
	}

	/**
	* Sets the value of picture
	* @param val Employee's picture that appears on company id card
	*/
	public void setPicture(byte[] val)
	{
		this.picture = val;
		this.IS_NULL = this.IS_NULL & (Long.MAX_VALUE - PICTURE_BIT_FLAG);
	}

	/**
	* Returns the value of picture
	* @return Employee's picture that appears on company id card
	*/
	public byte[] getPicture()
	{
		return this.picture;
	}

	/**
	* Sets the value of latestCheckInDateTime
	* @param val No documentation is available
	*/
	public void setLatestCheckInDateTime(java.util.Date val)
	{
		this.latestCheckInDateTime = val;
		this.IS_NULL = this.IS_NULL & (Long.MAX_VALUE - LATESTCHECKINDATETIME_BIT_FLAG);
	}

	/**
	* Returns the value of latestCheckInDateTime
	* @return No documentation is available
	*/
	public java.util.Date getLatestCheckInDateTime()
	{
		return this.latestCheckInDateTime;
	}

	/**
	* Sets the value of division
	*/
	public void setDivision(Division val)
	{
		this.division = val;
	}

	/**
	* Returns the value of division
	*/
	public Division getDivision()
	{
		return this.division;
	}


	public boolean isNull(long field)
	{
		return ((field & this.IS_NULL) == field);
	}

	public void setNull(long field)
	{
		this.IS_NULL = this.IS_NULL | field;
	}

	public void validate() throws Exception
	{
		validate(Long.MAX_VALUE);
	}
	public void validate(long fields) throws Exception
	{
		if (((fields & ID_BIT_FLAG) == ID_BIT_FLAG) && isNull(ID_BIT_FLAG)) throw new Exception("id cannot be null!");
		if (((fields & NUMBER_BIT_FLAG) == NUMBER_BIT_FLAG) && isNull(NUMBER_BIT_FLAG)) throw new Exception("number cannot be null!");
		if (((fields & FULLNAME_BIT_FLAG) == FULLNAME_BIT_FLAG) && isNull(FULLNAME_BIT_FLAG)) throw new Exception("fullName cannot be null!");
		if (((fields & STATUS_BIT_FLAG) == STATUS_BIT_FLAG) && isNull(STATUS_BIT_FLAG)) throw new Exception("status cannot be null!");
		if (((fields & TYPE_BIT_FLAG) == TYPE_BIT_FLAG) && isNull(TYPE_BIT_FLAG)) throw new Exception("type cannot be null!");
		if (((fields & SALARY_BIT_FLAG) == SALARY_BIT_FLAG) && isNull(SALARY_BIT_FLAG)) throw new Exception("salary cannot be null!");
		if (((fields & DIVISIONID_BIT_FLAG) == DIVISIONID_BIT_FLAG) && isNull(DIVISIONID_BIT_FLAG)) throw new Exception("divisionId cannot be null!");
		if (((fields & LATESTCHECKINDATETIME_BIT_FLAG) == LATESTCHECKINDATETIME_BIT_FLAG) && isNull(LATESTCHECKINDATETIME_BIT_FLAG)) throw new Exception("latestCheckInDateTime cannot be null!");

		if (((fields & NUMBER_BIT_FLAG) == NUMBER_BIT_FLAG) && !isNull(NUMBER_BIT_FLAG) && getNumber().length() > 16) throw new Exception("number length cannot exceed 16");
		if (((fields & FULLNAME_BIT_FLAG) == FULLNAME_BIT_FLAG) && !isNull(FULLNAME_BIT_FLAG) && getFullName().length() > 128) throw new Exception("fullName length cannot exceed 128");
	}

	public String ToXml(long fields)
	{
		java.text.SimpleDateFormat formatterTime = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		formatterTime.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

		StringBuilder objXmlBuilder = new StringBuilder();

		objXmlBuilder.append("<Employee>");
		if (((fields & ID_BIT_FLAG) == ID_BIT_FLAG)) objXmlBuilder.append(String.format("<A>%d</A>", getId()));
		if (((fields & NUMBER_BIT_FLAG) == NUMBER_BIT_FLAG)) objXmlBuilder.append(String.format("<B>%s</B>", getNumber()));
		if (((fields & FULLNAME_BIT_FLAG) == FULLNAME_BIT_FLAG)) objXmlBuilder.append(String.format("<C>%s</C>", getFullName()));
		if (((fields & GENDER_BIT_FLAG) == GENDER_BIT_FLAG)) objXmlBuilder.append(String.format("<D>%d</D>", getGender()));
		if (((fields & HIRINGDATE_BIT_FLAG) == HIRINGDATE_BIT_FLAG)) objXmlBuilder.append(String.format("<E>%s</E>", (new java.text.SimpleDateFormat("yyyy-MM-dd")).format(getHiringDate())));
		if (((fields & STATUS_BIT_FLAG) == STATUS_BIT_FLAG)) objXmlBuilder.append(String.format("<F>%d</F>", getStatus()));
		if (((fields & TYPE_BIT_FLAG) == TYPE_BIT_FLAG)) objXmlBuilder.append(String.format("<G>%d</G>", getType()));
		if (((fields & SALARY_BIT_FLAG) == SALARY_BIT_FLAG)) objXmlBuilder.append(String.format("<H>%f</H>", getSalary()));
		if (((fields & DIVISIONID_BIT_FLAG) == DIVISIONID_BIT_FLAG)) objXmlBuilder.append(String.format("<I>%d</I>", getDivisionId()));
		if (((fields & PICTURE_BIT_FLAG) == PICTURE_BIT_FLAG)) objXmlBuilder.append(String.format("<J>Byte[]</J>", getPicture()));
		if (((fields & LATESTCHECKINDATETIME_BIT_FLAG) == LATESTCHECKINDATETIME_BIT_FLAG)) objXmlBuilder.append(String.format("<K>%s</K>", formatterTime.format(getLatestCheckInDateTime())));
		objXmlBuilder.append("</Employee>");

		return objXmlBuilder.toString();
	}

	public String ToXml()
	{
		java.text.SimpleDateFormat formatterTime = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		formatterTime.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

		StringBuilder objXmlBuilder = new StringBuilder();

		objXmlBuilder.append("<Employee>");
		objXmlBuilder.append(String.format("<A>%d</A>", getId()));
		objXmlBuilder.append(String.format("<B>%s</B>", getNumber()));
		objXmlBuilder.append(String.format("<C>%s</C>", getFullName()));
		objXmlBuilder.append(String.format("<D>%d</D>", getGender()));
		objXmlBuilder.append(String.format("<E>%s</E>", (new java.text.SimpleDateFormat("yyyy-MM-dd")).format(getHiringDate())));
		objXmlBuilder.append(String.format("<F>%d</F>", getStatus()));
		objXmlBuilder.append(String.format("<G>%d</G>", getType()));
		objXmlBuilder.append(String.format("<H>%f</H>", getSalary()));
		objXmlBuilder.append(String.format("<I>%d</I>", getDivisionId()));
		objXmlBuilder.append(String.format("<J>Byte[]</J>", getPicture()));
		objXmlBuilder.append(String.format("<K>%s</K>", formatterTime.format(getLatestCheckInDateTime())));
		objXmlBuilder.append("</Employee>");

		return objXmlBuilder.toString();
	}

	public static String ToXml(java.util.List<Employee> items, long fields)
	{
		StringBuilder objXmlBuilder = new StringBuilder();

		objXmlBuilder.append("<Items>");
		for (int i = 0; i < items.size(); i++)
		{
			objXmlBuilder.append(items.get(i).ToXml(fields));
		}
		objXmlBuilder.append("</Items>");

		return objXmlBuilder.toString();
	}

	public static String ToXml(java.util.List<Employee> items)
	{
		StringBuilder objXmlBuilder = new StringBuilder();

		objXmlBuilder.append("<Items>");
		for (int i = 0; i < items.size(); i++)
		{
			objXmlBuilder.append(items.get(i).ToXml());
		}
		objXmlBuilder.append("</Items>");

		return objXmlBuilder.toString();
	}

}
