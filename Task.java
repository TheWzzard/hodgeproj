//package my.MasterofProjects;

public class Task
{
	private int id;
	private int projid;

	private String name;
	private boolean status;
	private String r1;
	private String r2;
	private String r3;
	private String r4;
	private String sdate;
	private String edate;
	/**
	Basic constructor for Task
	*/
	public Task(int id, int projid, String name, boolean status, String r1, String r2, String r3, String r4, String sdate, String edate)
	{
		this.id = id;
		this.projid = projid;
		this.name = name;
		this.status =  status;
		this.r1 = r1;
		this.r2 = r2;
		this.r3 = r3;
		this.r4 = r4;
		this.sdate  = sdate;
		this.edate = edate;
	}
	public Task(int projid)
	{
		id = 0;
		this.projid = projid;
		name = "My Task";
		status = true;
		r1 = "";
		r2 = "";
		r3 = "";
		r4 = "";
		sdate  = "";
		edate = "";
	}
	
	//GETS
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public boolean getStatus()
	{
		return status;
	}
	public String getR1()
	{
		return r1;
	}
	public String getR2()
	{
		return r2;
	}
	public String getR3()
	{
		return r3;
	}
	public String getR4()
	{
		return r4;
	}
	public String getEdate()
	{
		return edate;
	}
	public String getSdate()
	{
		return sdate;
	}
	
	// SETS
	public void setName(String newName)
	{
		name = newName;
	}
	public void setStatus(boolean newStatus)
	{
		status = newStatus;
	}
	public void setR1(String r1)
	{
		this.r1 = r1;
	}
	public void setR2(String r2)
	{          
		this.r2 = r2;
	}
	public void setR3(String r3)
	{
		this.r3 = r3;
	}
	public void setR4(String r4)
	{
		this.r4 = r4;
	}
	public void setEdate(String edate)
	{
		this.edate = edate;
	}
	public void setSdate(String sdate)
	{
		this.sdate = sdate;
	}
	public String toString()
	{
		String myString = "Task: ";
		myString += Integer.toString(id);
		myString += " " + name + " ";
		myString += Boolean.toString(status) + " ";
		myString += r1 + " ";
		myString += r2 + " ";
		myString += r3 + " ";
		myString += r4 + " ";
		myString += " " + sdate + " ";
		myString += " " + edate + " ";
		return myString;
	}
	/**

	 * Returns the value of projid.

	 */

	public int getProjID() {

		return projid;

	}





	/**

	 * Sets the value of projid.

	 * @param projid The value to assign projid.

	 */

	public void setProjID(int projid) {

		this.projid = projid;

	}

	
}