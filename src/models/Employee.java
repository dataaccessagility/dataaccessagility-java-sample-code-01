package models;

/**
* Anyone who works for the company including part-time employees and interns
*/
public class Employee
{
	public static String COLLECTION_XML_DATA_PATH = "/Items/Employee";

	public static long ID_BIT_FLAG = 1;
	public static long NAME_BIT_FLAG = 2;
	public static long BASESALARY_BIT_FLAG = 4;
	public static long HIRINGDATE_BIT_FLAG = 8;
	public static long DIVISIONID_BIT_FLAG = 16;

	private long IS_NULL = Long.MAX_VALUE;

	private int id;
	private String name;
	private java.math.BigDecimal baseSalary;
	private java.util.Date hiringDate;
	private int divisionId;
	private Division division;

	/**
	* Sets the value of id
	* @param val No documentation is available
	*/
	public void setId(int val)
	{
		this.id = val;
		this.IS_NULL = this.IS_NULL & (Long.MAX_VALUE - ID_BIT_FLAG);
	}

	/**
	* Returns the value of id
	* @return No documentation is available
	*/
	public int getId()
	{
		return this.id;
	}

	/**
	* Sets the value of name
	* @param val No documentation is available
	*/
	public void setName(String val)
	{
		this.name = val;
		this.IS_NULL = this.IS_NULL & (Long.MAX_VALUE - NAME_BIT_FLAG);
	}

	/**
	* Returns the value of name
	* @return No documentation is available
	*/
	public String getName()
	{
		return this.name;
	}

	/**
	* Sets the value of baseSalary
	* @param val No documentation is available
	*/
	public void setBaseSalary(java.math.BigDecimal val)
	{
		this.baseSalary = val;
		this.IS_NULL = this.IS_NULL & (Long.MAX_VALUE - BASESALARY_BIT_FLAG);
	}

	/**
	* Returns the value of baseSalary
	* @return No documentation is available
	*/
	public java.math.BigDecimal getBaseSalary()
	{
		return this.baseSalary;
	}

	/**
	* Sets the value of hiringDate
	* @param val No documentation is available
	*/
	public void setHiringDate(java.util.Date val)
	{
		this.hiringDate = val;
		this.IS_NULL = this.IS_NULL & (Long.MAX_VALUE - HIRINGDATE_BIT_FLAG);
	}

	/**
	* Returns the value of hiringDate
	* @return No documentation is available
	*/
	public java.util.Date getHiringDate()
	{
		return this.hiringDate;
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
		if (((fields & NAME_BIT_FLAG) == NAME_BIT_FLAG) && isNull(NAME_BIT_FLAG)) throw new Exception("name cannot be null!");
		if (((fields & BASESALARY_BIT_FLAG) == BASESALARY_BIT_FLAG) && isNull(BASESALARY_BIT_FLAG)) throw new Exception("baseSalary cannot be null!");
		if (((fields & HIRINGDATE_BIT_FLAG) == HIRINGDATE_BIT_FLAG) && isNull(HIRINGDATE_BIT_FLAG)) throw new Exception("hiringDate cannot be null!");
		if (((fields & DIVISIONID_BIT_FLAG) == DIVISIONID_BIT_FLAG) && isNull(DIVISIONID_BIT_FLAG)) throw new Exception("divisionId cannot be null!");

		if (((fields & NAME_BIT_FLAG) == NAME_BIT_FLAG) && !isNull(NAME_BIT_FLAG) && getName().length() > 256) throw new Exception("name length cannot exceed 256");
	}

	public String ToXml(long fields)
	{
		StringBuilder objXmlBuilder = new StringBuilder();

		objXmlBuilder.append("<Employee>");
		if (((fields & ID_BIT_FLAG) == ID_BIT_FLAG)) objXmlBuilder.append(String.format("<A>%d</A>", this.getId()));
		if (((fields & NAME_BIT_FLAG) == NAME_BIT_FLAG)) objXmlBuilder.append(String.format("<B>%s</B>", this.getName()));
		if (((fields & BASESALARY_BIT_FLAG) == BASESALARY_BIT_FLAG)) objXmlBuilder.append(String.format("<C>%f</C>", this.getBaseSalary()));
		if (((fields & HIRINGDATE_BIT_FLAG) == HIRINGDATE_BIT_FLAG)) objXmlBuilder.append(String.format("<D>%tD %tT</D>", this.getHiringDate()));
		if (((fields & DIVISIONID_BIT_FLAG) == DIVISIONID_BIT_FLAG)) objXmlBuilder.append(String.format("<E>%d</E>", this.getDivisionId()));
		objXmlBuilder.append("</Employee>");

		return objXmlBuilder.toString();
	}

	public String ToXml()
	{
		StringBuilder objXmlBuilder = new StringBuilder();

		objXmlBuilder.append("<Employee>");
		objXmlBuilder.append(String.format("<A>%d</A>", this.getId()));
		objXmlBuilder.append(String.format("<B>%s</B>", this.getName()));
		objXmlBuilder.append(String.format("<C>%f</C>", this.getBaseSalary()));
		objXmlBuilder.append(String.format("<D>%tD %tT</D>", this.getHiringDate()));
		objXmlBuilder.append(String.format("<E>%d</E>", this.getDivisionId()));
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
