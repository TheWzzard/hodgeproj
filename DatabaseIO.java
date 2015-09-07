//package my.MasterofProjects;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;

public class DatabaseIO
{
	String framework = "embedded";
	String protocol = "jdbc:derby:";
	Connection conn = null;
	ArrayList<Statement> statements = new ArrayList<>(); // list of Statements, PreparedStatements
	PreparedStatement queryTaskResourceList;
	PreparedStatement queryResource;
	PreparedStatement queryTaskList;
	PreparedStatement queryResourceList;
	PreparedStatement queryProjectList;
	PreparedStatement deleteProject;
	PreparedStatement deleteTask;
	PreparedStatement deleteResource;
	PreparedStatement deleteProjectTasks;
	PreparedStatement updateResource;
	PreparedStatement updateProject;
	PreparedStatement updateTask;
	PreparedStatement insertTask;
	PreparedStatement insertProject;
	PreparedStatement insertResource;
	PreparedStatement updateTaskR1;
	PreparedStatement updateTaskR2;
	PreparedStatement updateTaskR3;
	PreparedStatement updateTaskR4;
	
	Statement statement = null;
	ResultSet results = null;
	Scanner keyboard = new Scanner(System.in);
	String inputString;
	boolean finished = false;
	boolean initialized = false;
	boolean inputError = true;
	int inputInt = 0;
	int i = 0;
    public DatabaseIO()
    {
    	//CONSTRUCTOR
    	//Initializes DB and adds tables if missing
        try
        {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = "derbyDB"; // the name of the database
            conn = DriverManager.getConnection(protocol + dbName + ";create=true", props);
            System.out.println("Connected to and created database " + dbName);
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            statements.add(statement);
            
            DatabaseMetaData meta;
            ResultSet tables;
            meta = conn.getMetaData();
            tables = meta.getTables(conn.getCatalog(), null, "project", null);
            if (!tables.next())
            {  
		    statement.execute("create table project "+
		    "("+
			    "projid INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"+
			    "projname varchar(40) NOT NULL,"+
			    "active boolean,"+
			    "sdate varchar(40),"+
			    "edate varchar(40),"+
			    "PRIMARY KEY (projid)"+
		    ")");
	    }
	    tables.close();
	    tables = meta.getTables(conn.getCatalog(), null, "task", null);
            if (!tables.next())
            {
		    statement.execute("create table task "+
		    "("+
			    "taskid INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"+
			    "projid integer NOT NULL,"+
			    "taskname varchar(40) NOT NULL,"+
			    "status boolean,"+
			    "r1 varchar(40),"+
			    "r2 varchar(40),"+
			    "r3 varchar(40),"+
			    "r4 varchar(40),"+
			    "sdate varchar(40),"+
			    "edate varchar(40),"+
			    "PRIMARY KEY (taskid)"+
		    ")");
	    }
            tables.close();
            
            
            /*
            tables = meta.getTables(conn.getCatalog(), null, "resource", null);
            if (!tables.next())
            {
		    statement.execute("create table resource "+
		    "("+
			    "resourceid INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"+
			    "resourcename varchar(40) NOT NULL,"+
			    "value double,"+
			    "PRIMARY KEY (resourceid)"+
		    ")");
	    }
            tables.close();
            */

            
            //INSERTS
            insertTask = conn.prepareStatement(
            	    "insert into task (projid,taskname,status,r1,r2,r3,r4,sdate,edate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statements.add(insertTask);
            
            insertProject = conn.prepareStatement(
            	    "insert into project (projname,active,sdate,edate) values (?, ?, ?, ?)");
            statements.add(insertProject);
            /*
            insertResource = conn.prepareStatement(
            	    "insert into resource (resourcename,value) values (?, ?)");
            statements.add(insertResource);
            
            //RESOURCES
            updateResource = conn.prepareStatement(
                        "update resource set resourcename=?, value=? where resourceid=?");
            statements.add(updateResource);
            
            //all resources
            queryResourceList = conn.prepareStatement(
                        "SELECT * FROM resource ORDER BY resourceid");
            statements.add(queryResourceList);
            //one resource
            queryResource = conn.prepareStatement(
                        "SELECT * FROM resource where resourceid=?");
            statements.add(queryResource);
            */
            
            //UPDATES
            //PROJECT
            updateProject = conn.prepareStatement(
                        "update project set projname=?, active=?, sdate=?, edate=? where projid=?");
            statements.add(updateProject);
            
            //TASKS
            updateTask = conn.prepareStatement(
                        "update task set taskname=?, status=?, r1=?, r2=?, r3=?, r4=?, sdate=?, edate=? where taskid=?");
            statements.add(updateTask);
            updateTaskR1 = conn.prepareStatement(
                        "update task set r1=? where r1=?");
            statements.add(updateTaskR1);
            updateTaskR2 = conn.prepareStatement(
                        "update task set r2=? where r2=?");
            statements.add(updateTaskR2);
            updateTaskR3 = conn.prepareStatement(
                        "update task set r3=? where r3=?");
            statements.add(updateTaskR3);
            updateTaskR4 = conn.prepareStatement(
                        "update task set r4=? where r4=?");
            statements.add(updateTaskR4);
            
            
            
            
            
            //DELETIONS
            //PROJECTS
            deleteProject = conn.prepareStatement(
                        "delete from project where projid=?");
            statements.add(deleteProject);
            
            deleteTask = conn.prepareStatement(
                        "delete from task where taskid=?");
            statements.add(deleteTask);
            
            deleteResource = conn.prepareStatement(
                        "delete from resource where resourceid=?");
            statements.add(deleteResource);
            
            deleteProjectTasks = conn.prepareStatement(
                        "delete from task where projid=?");
            statements.add(deleteProjectTasks);
            
            
            //QUERIES
            //all projects
            queryProjectList = conn.prepareStatement(
                        "SELECT * FROM project ORDER BY projid");
            statements.add(queryProjectList);
            
            
            
            
            
            //all tasks in a project
            queryTaskList = conn.prepareStatement(
                        "SELECT taskid, taskname, status, r1, r2, r3, r4, sdate, edate FROM task where projid=? ORDER BY taskid");
            statements.add(queryTaskList);
            
            
        }
        catch (SQLException sqle)
        {
            printSQLException(sqle);
        }
    }
            
    public void cleanup()
    {
	    try
	    {
		    conn.commit();
	    	    // the shutdown=true attribute shuts down Derby
		    DriverManager.getConnection("jdbc:derby:;shutdown=true");
	
		    // To shut down a specific database only, but keep the
		    // engine running (for example for connecting to other
		    // databases), specify a database in the connection URL:
		    //DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
	    }
	
		
	    catch (SQLException sqle)
	    {
		   printSQLException(sqle);
	    }
	    finally
	    {
		    // release all open resources to avoid unnecessary memory usage
		    // ResultSet
		    try
		    {
			if (results != null)
			{
			    results.close();
			    results = null;
			}
		    }
		    catch (SQLException sqle)
		    {
			printSQLException(sqle);
		    }
		
		    // Statements and PreparedStatements
		    int i = 0;
		    while (!statements.isEmpty())
		    {
			// PreparedStatement extend Statement
			Statement st = statements.remove(i);
			try
			{
			    if (st != null)
			    {
				st.close();
				st = null;
			    }
			}
			catch (SQLException sqle)
			{
			    printSQLException(sqle);
			}
		    }
			
		    //Connection
		    try
		    {
			if (conn != null)
			{
			    conn.close();
			    conn = null;
			}
		    }
		    catch (SQLException sqle)
		    {
			printSQLException(sqle);
		    }
	    }
    }

    private static void printSQLException(SQLException e)
    {
        // Unwraps the entire exception chain to unveil the real cause of the
        // Exception.
        while (e != null)
        {
            System.err.println("\n----- SQLException -----");
	    System.err.println("  SQL State:  " + e.getSQLState());
	    System.err.println("  Error Code: " + e.getErrorCode());
	    System.err.println("  Message:    " + e.getMessage());
	    // for stack traces, refer to derby.log or uncomment this:
	    //e.printStackTrace(System.err);
	  
	    e = e.getNextException();
        }
    }
    
    public void insertProject(Project myProject)
    {
    	    try
    	    {
		    insertProject.setString(1, myProject.getName());
		    insertProject.setBoolean(2, myProject.getActive());
		    insertProject.setString(3, myProject.getSdate());
		    insertProject.setString(4, myProject.getEdate());
		    insertProject.executeUpdate();
	    }
	    catch (SQLException sqle)
	    {
		printSQLException(sqle);
	    }
    }
    
    public void insertTask(Task myTask)
    {
    	    try
    	    {
		    insertTask.setInt(1, myTask.getProjID());
    	    	    insertTask.setString(2, myTask.getName());
		    insertTask.setBoolean(3, myTask.getStatus());
		    insertTask.setString(4, myTask.getR1());
		    insertTask.setString(5, myTask.getR2());
		    insertTask.setString(6, myTask.getR3());
		    insertTask.setString(7, myTask.getR4());
		    insertTask.setString(8, myTask.getSdate());
		    insertTask.setString(9, myTask.getEdate());
		    insertTask.executeUpdate();
	    }
	    catch (SQLException sqle)
	    {
		printSQLException(sqle);
	    }
    }
    /*
    public void insertResource(Resource myResource)
    {
    	    try
    	    {
		    insertResource.setString(1, myResource.getName());
		    insertResource.setDouble(2, myResource.getValue());
		    insertResource.executeUpdate();
	    }
	    catch (SQLException sqle)
	    {
		printSQLException(sqle);
	    }
    }
    */
    public void deleteProject(Project myProject)
    {
    	    try
    	    {
		    deleteProjectTasks.setInt(1, myProject.getId());
		    deleteProjectTasks.executeUpdate();
    	    	    deleteProject.setInt(1, myProject.getId());
		    deleteProject.executeUpdate();
	    }
	    catch (SQLException sqle)
	    {
		printSQLException(sqle);
	    }
    }
    public void deleteProject(int myProject)
    {
    	    try
    	    {
		    deleteProjectTasks.setInt(1, myProject);
		    deleteProjectTasks.executeUpdate();
    	    	    deleteProject.setInt(1, myProject);
		    deleteProject.executeUpdate();
	    }
	    catch (SQLException sqle)
	    {
		printSQLException(sqle);
	    }
    }
    
    public void deleteTask(Task myTask)
    {
    	    try
    	    {
		    deleteTask.setInt(1, myTask.getId());
		    deleteTask.executeUpdate();
	    }
	    catch (SQLException sqle)
	    {
		printSQLException(sqle);
	    }
    }
    
    public void deleteTask(int myTask)
    {
    	    try
    	    {
		    deleteTask.setInt(1, myTask);
		    deleteTask.executeUpdate();
	    }
	    catch (SQLException sqle)
	    {
		printSQLException(sqle);
	    }
    }
    /*
    public void deleteResource(Resource myResource)
    {
    	    try
    	    {
		    updateTaskR1.setInt(1,0);
		    updateTaskR1.setInt(2, myResource.getId());
    	    	    updateTaskR2.setInt(1,0);
		    updateTaskR2.setInt(2, myResource.getId());
		    updateTaskR3.setInt(1,0);
		    updateTaskR3.setInt(2, myResource.getId());
		    updateTaskR4.setInt(1,0);
		    updateTaskR4.setInt(2, myResource.getId());
		    updateTaskR1.executeUpdate();
		    updateTaskR2.executeUpdate();
		    updateTaskR3.executeUpdate();
		    updateTaskR4.executeUpdate();
    	    	    deleteResource.setInt(1, myResource.getId());
		    deleteResource.executeUpdate();
	    }
	    catch (SQLException sqle)
	    {
		printSQLException(sqle);
	    }
    }
    public void deleteResource(int myResource)
    {
    	    try
    	    {
		    updateTaskR1.setInt(1,0);
		    updateTaskR1.setInt(2, myResource);
    	    	    updateTaskR2.setInt(1,0);
		    updateTaskR2.setInt(2, myResource);
		    updateTaskR3.setInt(1,0);
		    updateTaskR3.setInt(2, myResource);
		    updateTaskR4.setInt(1,0);
		    updateTaskR4.setInt(2, myResource);
		    updateTaskR1.executeUpdate();
		    updateTaskR2.executeUpdate();
		    updateTaskR3.executeUpdate();
		    updateTaskR4.executeUpdate();
    	    	    deleteResource.setInt(1, myResource);
		    deleteResource.executeUpdate();
	    }
	    catch (SQLException sqle)
	    {
		printSQLException(sqle);
	    }
    }
    */
    public void updateProject(Project myProject)
    {
    	    try
    	    {
		    updateProject.setInt(5, myProject.getId());
		    updateProject.setString(1, myProject.getName());
		    updateProject.setBoolean(2, myProject.getActive());
		    updateProject.setString(3, myProject.getSdate());
		    updateProject.setString(4, myProject.getEdate());
		    updateProject.executeUpdate();
	    }
	    catch (SQLException sqle)
	    {
		printSQLException(sqle);
	    }
    }
    
    public void updateTask(Task myTask)
    {
    	    try
    	    {
		    updateTask.setInt(9, myTask.getId());
		    updateTask.setString(1, myTask.getName());
		    updateTask.setBoolean(2, myTask.getStatus());
		    updateTask.setString(3, myTask.getR1());
		    updateTask.setString(4, myTask.getR2());
		    updateTask.setString(5, myTask.getR3());
		    updateTask.setString(6, myTask.getR4());
		    updateTask.setString(7, myTask.getSdate());
		    updateTask.setString(8, myTask.getEdate());
		    updateTask.executeUpdate();
    	    }
	    catch (SQLException sqle)
	    {
		printSQLException(sqle);
	    }
    }
    /*
    public void updateResource(Resource myResource)
    {
    	    try
    	    {
		    updateResource.setInt(3, myResource.getId());
		    updateResource.setString(1, myResource.getName());
		    updateResource.setDouble(2, myResource.getValue());
		    updateResource.executeUpdate();
	    }
	    catch (SQLException sqle)
	    {
		printSQLException(sqle);
	    }
    }
    */
    public ArrayList<Project> getProjects()
    {
    	    ArrayList<Project> projectList = new ArrayList<>();
    	    ArrayList<Object> myList = new ArrayList<Object>(resultsToList(queryProjectList));
	    int count = myList.size() / 5;
	    int i = 0;
	    int id;
	    String name;
	    boolean active;
	    String sdate;
	    String edate;
	    for(int j = 0; j < count; j++)
	    {
		    id = (int)myList.get(i++);
		    name = (String)myList.get(i++);
		    active = (boolean)myList.get(i++);
		    sdate = (String)myList.get(i++);
		    edate = (String)myList.get(i++);
		    projectList.add(new Project(id,name,active,sdate,edate));
	    }
	    return projectList;
    }
    /*
    public ArrayList<Resource> getResources()
    {
    	    ArrayList<Resource> resourceList = new ArrayList<>();
    	    ArrayList<Object> myList = new ArrayList<Object>(resultsToList(queryResourceList));
	    int count = myList.size() / 3;
	    int i = 0;
	    int id;
	    String name;
	    double value;
	    //empty for no resource
	    id = 0;
	    name = "None";
	    value = 0;
	    resourceList.add(new Resource(id,name,value));
	    for(int j = 0; j < count; j++)
	    {
		    id = (int)myList.get(i++);
		    name = (String)myList.get(i++);
		    value = (double)myList.get(i++);
		    resourceList.add(new Resource(id,name,value));
	    }
	    return resourceList;
    }
    
    public Resource getResource(int resourceId)
    {
    	    try
    	    {
    	    	    queryResource.setInt(1, resourceId);
    	    }
    	    catch (SQLException sqle)
	    {
		printSQLException(sqle);
	    }
    	    ArrayList<Object> myList = new ArrayList<Object>(resultsToList(queryResource));;
	    int id;
	    String name;
	    double value;
	    int i = 0;
	    id = (int)myList.get(i++);
	    name = (String)myList.get(i++);
	    value = (double)myList.get(i++);
	    Resource resource = new Resource(id,name,value);
	    return resource;
    }
    */
    public ArrayList<Task> getTasks(int projectID)
    {
    	    ArrayList<Task> taskList = new ArrayList<>();
    	    try
    	    {
    	    	    queryTaskList.setInt(1, projectID);
    	    }
    	    catch (SQLException sqle)
	    {
		printSQLException(sqle);
	    }
	    ArrayList<Object> myList = new ArrayList<Object>(resultsToList(queryTaskList));
	    int count = myList.size() / 10;
	    int i = 0;
	    int id;
	    String name;
	    boolean status;
	    String r1;
	    String r2;
	    String r3;
	    String r4;
	    String sdate;
	    String edate;
	    if (myList.size() != 0)
	    {
		    for(int j = 0; j < count; j++)
		    {
			    id = (int)myList.get(i++);
			    name = (String)myList.get(i++);
			    status = (boolean)myList.get(i++);
			    r1 = (String)myList.get(i++);
			    r2 = (String)myList.get(i++);
			    r3 = (String)myList.get(i++);
			    r4 = (String)myList.get(i++);
			    sdate = (String)myList.get(i++);
			    edate = (String)myList.get(i++);
			    taskList.add(new Task(id,projectID,name,status,r1,r2,r3,r4,sdate,edate));
		    }
	    }    
	    return taskList;
    }
    
    private ArrayList<Object> resultsToList(PreparedStatement query)
    {
    	    ArrayList<Object> myList = new ArrayList<Object>();
    	    try
    	    {
		ResultSet rs = null;
		rs = query.executeQuery();
		ResultSetMetaData meta = rs.getMetaData();
		int numberOfColumns = meta.getColumnCount();
		while (rs.next())
		{
			for(int i = 1; i <= numberOfColumns; i++)
			    myList.add(rs.getObject(i));
		}
		rs.close();
		
	    }
	    catch (SQLException sqle)
	    {
		printSQLException(sqle);
	    }
	    return myList;
    }
}
