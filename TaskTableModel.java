//package my.MasterofProjects;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.sql.Date;
public class TaskTableModel extends AbstractTableModel
{
    private List<Task> taskList;
    int projectId;
    private int selected = 0;
    private ProjectTableModel myProjectTable;
    private DatabaseIO myIO;
    private final String[] columnNames = new String[]
    {
            "Name", "Complete", "Assigned to", "Activity", "Resource", "Notes", "Start Date", "End Date"
    };
    private final Class[] columnClass = new Class[]
    {
        String.class, Boolean.class, String.class, String.class, String.class, String.class, String.class, String.class
    };
 
    public TaskTableModel(ProjectTableModel myProjectTable, DatabaseIO myIO)
    {
    	this.myProjectTable = myProjectTable;
    	projectId = myProjectTable.getSelectedProject();
        this.myIO = myIO;
        this.fireTableDataChanged();
    }
     
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
 
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }
 
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
 
    @Override
    public int getRowCount()
    {
        projectId = myProjectTable.getSelectedProject();
    	taskList = myIO.getTasks(projectId);
    	return taskList.size();
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        projectId = myProjectTable.getSelectedProject();
    	taskList = myIO.getTasks(projectId);
    	Task row = taskList.get(rowIndex);
    	this.fireTableDataChanged();
        if (columnIndex == 0) {
            return row.getName();
        }
        else if (columnIndex == 1) {
            return row.getStatus();
        }
        else if (columnIndex == 2) {
            return row.getR1();
        }
        else if (columnIndex == 3) {
            return row.getR2();
        }
        else if (columnIndex == 4) {
            return row.getR3();
        }
        else if (columnIndex == 5) {
            return row.getR4();
        }
        else if (columnIndex == 6) {
            return row.getSdate();
        }
        else if (columnIndex == 7) {
            return row.getEdate();
        }
        else if (columnIndex == 8) {
            return row.getId();
        }
        return null;
    }
     @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }
 
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        projectId = myProjectTable.getSelectedProject();
    	taskList = myIO.getTasks(projectId);
    	Task row = taskList.get(rowIndex);
        if (columnIndex == 0) {
            row.setName((String) aValue);
        }
        else if (columnIndex == 1) {
            row.setStatus((Boolean) aValue);
        }
        else if (columnIndex == 2) {
            row.setR1((String) aValue);
        }
        else if (columnIndex == 3) {
            row.setR2((String) aValue);
        }
        else if (columnIndex == 4) {
            row.setR3((String) aValue);
        }
        else if (columnIndex == 5) {
            row.setR4((String) aValue);
        }
        else if (columnIndex == 6) {
            row.setSdate((String) aValue);
        }
        else if (columnIndex == 7) {
            row.setEdate((String) aValue);
        }
        myIO.updateTask(row);
        this.fireTableDataChanged();
    }
    public void setSelectedTask(int input)
    {
    	    selected = (Integer)this.getValueAt(input,8);
    }
    public int getSelectedTask()
    {
    	    return selected;
    }
}