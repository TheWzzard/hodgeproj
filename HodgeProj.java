

import java.sql.Date;
import java.util.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ListSelectionModel;

/**
 *
 * @author John
 */
public class HodgeProj extends javax.swing.JFrame {
    int selectedProject = 0;
    ProjectTableModel projectModel;
    DatabaseIO myIO = new DatabaseIO();
    TaskTableModel taskModel;
    /**
     * Creates new form HodgeProj
     */
    public HodgeProj() {
        projectModel = new ProjectTableModel(myIO);
        taskModel = new TaskTableModel(projectModel,myIO);
        initComponents();
    }

    @SuppressWarnings("unchecked")                         
    private void initComponents() {

        taskToolBar = new javax.swing.JToolBar();
        newTaskButton = new javax.swing.JButton();
        deleteTaskButton = new javax.swing.JButton();
        projectScrollPane = new javax.swing.JScrollPane();
        projectTable = new javax.swing.JTable();
        taskScrollPane = new javax.swing.JScrollPane();
        taskTable = new javax.swing.JTable();
        projectToolBar = new javax.swing.JToolBar();
        newProjButton = new javax.swing.JButton();
        deleteProjectButton = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hodge Proj");
        setName("Hodge Proj"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        taskToolBar.setFloatable(false);
        taskToolBar.setRollover(true);

        newTaskButton.setText("New Task");
        newTaskButton.setFocusable(false);
        newTaskButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newTaskButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newTaskButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newTaskButtonMouseClicked(evt);
            }
        });
        taskToolBar.add(newTaskButton);

        deleteTaskButton.setText("Delete Task");
        deleteTaskButton.setFocusable(false);
        deleteTaskButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteTaskButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteTaskButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteTaskButtonMouseClicked(evt);
            }
        });
        taskToolBar.add(deleteTaskButton);

        projectTable.setModel(projectModel);
        projectTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        projectTable.getTableHeader().setReorderingAllowed(false);
        projectScrollPane.setViewportView(projectTable);
        projectTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        projectTable.getSelectionModel().addListSelectionListener(new ProjectListener());
    
        taskTable.setModel(taskModel);
        taskTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        taskTable.getTableHeader().setReorderingAllowed(false);
        taskScrollPane.setViewportView(taskTable);
        taskTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        taskTable.getSelectionModel().addListSelectionListener(new TaskListener());
        
        projectToolBar.setFloatable(false);
        projectToolBar.setRollover(true);

        newProjButton.setText("New Project");
        newProjButton.setFocusable(false);
        newProjButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newProjButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newProjButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newProjButtonMouseClicked(evt);
            }
        });
        projectToolBar.add(newProjButton);

        deleteProjectButton.setText("Delete Project");
        deleteProjectButton.setFocusable(false);
        deleteProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteProjectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteProjectButtonMouseClicked(evt);
            }
        });
        projectToolBar.add(deleteProjectButton);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(taskScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(projectScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(projectToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(projectToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(projectScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                                              

    private void formWindowClosing(java.awt.event.WindowEvent evt) {                                   
        myIO.cleanup();
        System.exit(0);
    }                                  

    private void newProjButtonMouseClicked(java.awt.event.MouseEvent evt) {                                           
        myIO.insertProject(new Project());
        projectModel.fireTableDataChanged();
    }  
    
    private void deleteProjectButtonMouseClicked(java.awt.event.MouseEvent evt) {                                           
        myIO.deleteProject(projectModel.getSelectedProject());
        projectModel.fireTableDataChanged();
        System.out.print("Clicked");
    } 

    private void newTaskButtonMouseClicked(java.awt.event.MouseEvent evt) {                                           
        myIO.insertTask(new Task(projectModel.getSelectedProject()));
        taskModel.fireTableDataChanged();
    }
    
    private void deleteTaskButtonMouseClicked(java.awt.event.MouseEvent evt) {                                              
        myIO.deleteTask(taskModel.getSelectedTask());
        taskModel.fireTableDataChanged();
    }                                             
    
    private class ProjectListener implements ListSelectionListener
    {
	    @Override
	    public void valueChanged(ListSelectionEvent e)
	    {
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		int firstIndex = e.getFirstIndex();
		int lastIndex = e.getLastIndex();
		int minIndex = lsm.getMinSelectionIndex();
		    int maxIndex = lsm.getMaxSelectionIndex();
		    for (int i = minIndex; i <= maxIndex; i++)
		    {
			if (lsm.isSelectedIndex(i))
			{
			    System.out.print(i);
			    projectModel.setSelectedProject(i);
			    taskModel.fireTableDataChanged();
			}
		    
		}
	    }
    }
    
    private class TaskListener implements ListSelectionListener
    {
	    @Override
	    public void valueChanged(ListSelectionEvent e)
	    {
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		int firstIndex = e.getFirstIndex();
		int lastIndex = e.getLastIndex();
		int minIndex = lsm.getMinSelectionIndex();
		    int maxIndex = lsm.getMaxSelectionIndex();
		    for (int i = minIndex; i <= maxIndex; i++)
		    {
			if (lsm.isSelectedIndex(i))
			{
			    System.out.print(i);
			    taskModel.setSelectedTask(i);
			    taskModel.fireTableDataChanged();
			}
		    }
		
	    }
    }


    public static void main(String args[]) {
        // Set the Nimbus look and feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HodgeProj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HodgeProj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HodgeProj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HodgeProj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @SuppressWarnings("override")
            public void run()
            {
                new HodgeProj().setVisible(true); 
            }
        });
    }

    // Variables declaration                    
    private javax.swing.JButton deleteProjectButton;
    private javax.swing.JButton deleteTaskButton;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JButton newProjButton;
    private javax.swing.JButton newTaskButton;
    private javax.swing.JScrollPane projectScrollPane;
    private javax.swing.JTable projectTable;
    private javax.swing.JToolBar projectToolBar;
    private javax.swing.JScrollPane taskScrollPane;
    private javax.swing.JTable taskTable;
    private javax.swing.JToolBar taskToolBar;                     
}
