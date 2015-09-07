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
import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.sql.Date;
public class ProjectTableModel extends AbstractTableModel
{
    private List<Project> projectList;
    private DatabaseIO myIO;
    private int selected = 0;
    private final String[] columnNames = new String[] {
            "Id", "Name", "Active", "Start Date", "End Date"
    };
    private final Class[] columnClass = new Class[] {
        Integer.class, String.class, Boolean.class, String.class, String.class
    };
 
    public ProjectTableModel(DatabaseIO myIO)
    {
        this.myIO = myIO;
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
        projectList = myIO.getProjects();
    	return projectList.size();
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        projectList = myIO.getProjects();
    	Project row = projectList.get(rowIndex);
        if (columnIndex == 0) {
            return row.getId();
        }
        else if (columnIndex == 1) {
            return row.getName();
        }
        else if (columnIndex == 2) {
            return row.getActive();
        }
        else if (columnIndex == 3) {
            return row.getSdate();
        }
        else if (columnIndex == 4) {
            return row.getEdate();
        }
        return null;
    }
     @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return columnIndex > 0;
    }
 
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        projectList = myIO.getProjects();
    	Project row = projectList.get(rowIndex);
        if (columnIndex == 1) {
            row.setName((String) aValue);
        }
        else if (columnIndex == 2) {
            row.setActive((Boolean) aValue);
        }
        else if (columnIndex == 3) {
            row.setSdate((String) aValue);
        }
        else if (columnIndex == 4) {
            row.setEdate((String) aValue);
        }
        myIO.updateProject(row);
        this.fireTableDataChanged();
    }
    public void setSelectedProject(int input)
    {
    	    selected = (Integer)this.getValueAt(input,0);
    }
    public int getSelectedProject()
    {
    	    return selected;
    }
}
