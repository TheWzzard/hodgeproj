/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package my.MasterofProjects;

/**
 *
 * @author John
 */

public class Project
{
	private int id;
	private String name;
	private boolean active;
	private String sdate;
	private String edate;
	
	/**
	Basic constructor for Task
	*/
	public Project(int id, String name, boolean active, String sdate, String edate)
	{
		this.id = id;
		this.name = name;
		this.active = active;
		this.sdate  = sdate;
		this.edate = edate;
	}
        
        public Project()
	{
		id = 0;
		name = "My Project";
		active = true;
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
	public boolean getActive()
	{
		return active;
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
	public void setActive(boolean active)
	{
		this.active = active;
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
		String act;
		if (active == true) act = "Active";
		else act = "Inactive";
		String myString = "Project: ";
		myString += Integer.toString(id);
		myString += " " + name + " ";
		myString += act;
		myString += " " + sdate + " ";
		myString += " " + edate + " ";
		return myString;
	}	
}