package models;

/**
* No documentation is available
*/
public class Division
{
	public static String COLLECTION_XML_DATA_PATH = "/Items/Division";

	public static long ID_BIT_FLAG = 1;
	public static long NAME_BIT_FLAG = 2;
	public static long PARENTDIVISIONID_BIT_FLAG = 4;

	private long IS_NULL = Long.MAX_VALUE;

	private int id;
	private String name;
	private int parentDivisionId;
	private java.util.List<Employee> employees;
	private Division parentDivision;
	private java.util.List<Division> divisions;

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
	* Sets the value of parentDivisionId
	* @param val No documentation is available
	*/
	public void setParentDivisionId(int val)
	{
		this.parentDivisionId = val;
		this.IS_NULL = this.IS_NULL & (Long.MAX_VALUE - PARENTDIVISIONID_BIT_FLAG);
	}

	/**
	* Returns the value of parentDivisionId
	* @return No documentation is available
	*/
	public int getParentDivisionId()
	{
		return this.parentDivisionId;
	}

	/**
	* Sets the value of employees
	*/
	public void setEmployees(java.util.List<Employee> val)
	{
		this.employees = val;
	}

	/**
	* Returns the value of employees
	*/
	public java.util.List<Employee> getEmployees()
	{
		return this.employees;
	}

	/**
	* Sets the value of parentDivision
	*/
	public void setParentDivision(Division val)
	{
		this.parentDivision = val;
	}

	/**
	* Returns the value of parentDivision
	*/
	public Division getParentDivision()
	{
		return this.parentDivision;
	}

	/**
	* Sets the value of divisions
	*/
	public void setDivisions(java.util.List<Division> val)
	{
		this.divisions = val;
	}

	/**
	* Returns the value of divisions
	*/
	public java.util.List<Division> getDivisions()
	{
		return this.divisions;
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
		if (((fields & PARENTDIVISIONID_BIT_FLAG) == PARENTDIVISIONID_BIT_FLAG) && isNull(PARENTDIVISIONID_BIT_FLAG)) throw new Exception("parentDivisionId cannot be null!");

		if (((fields & NAME_BIT_FLAG) == NAME_BIT_FLAG) && !isNull(NAME_BIT_FLAG) && getName().length() > 128) throw new Exception("name length cannot exceed 128");
	}

	public String ToXml(long fields)
	{
		StringBuilder objXmlBuilder = new StringBuilder();

		objXmlBuilder.append("<Division>");
		if (((fields & ID_BIT_FLAG) == ID_BIT_FLAG)) objXmlBuilder.append(String.format("<A>%d</A>", this.getId()));
		if (((fields & NAME_BIT_FLAG) == NAME_BIT_FLAG)) objXmlBuilder.append(String.format("<B>%s</B>", this.getName()));
		if (((fields & PARENTDIVISIONID_BIT_FLAG) == PARENTDIVISIONID_BIT_FLAG)) objXmlBuilder.append(String.format("<C>%d</C>", this.getParentDivisionId()));
		objXmlBuilder.append("</Division>");

		return objXmlBuilder.toString();
	}

	public String ToXml()
	{
		StringBuilder objXmlBuilder = new StringBuilder();

		objXmlBuilder.append("<Division>");
		objXmlBuilder.append(String.format("<A>%d</A>", this.getId()));
		objXmlBuilder.append(String.format("<B>%s</B>", this.getName()));
		objXmlBuilder.append(String.format("<C>%d</C>", this.getParentDivisionId()));
		objXmlBuilder.append("</Division>");

		return objXmlBuilder.toString();
	}

	public static String ToXml(java.util.List<Division> items, long fields)
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

	public static String ToXml(java.util.List<Division> items)
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
