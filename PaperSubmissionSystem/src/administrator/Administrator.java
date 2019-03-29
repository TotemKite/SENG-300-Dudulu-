package administrator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Administrator {
	private JFrame frame;
	JTable unreadTable;
	JTable pendingTable;
	File[] pendingFiles;
	JTable reviewedTable;
	File[] reviewedFiles;
	JTable approvedTable;
	File[] approvedFiles;
	JTable deadlineTable;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrator window = new Administrator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Administrator() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.setTitle("Administrator");
		frame.setResizable(false);
		frame.setBounds(200, 200, 650, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenuItem logout = new JMenuItem("Logout");
		menuBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		menuBar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuBar.add(logout);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}		
		});
		
		// Tabs Setup
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		// Unread Papers Tab
		JPanel unread = new JPanel();
		JButton btnGetUnassigned = new JButton("Load Unread Papers");
		btnGetUnassigned.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGetUnassigned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTable unreadTable = getUnread();
				unreadTable.setVisible(true);
				unread.add(unreadTable);
			}
		});
		JButton btnSetPending = new JButton("Set Selected As Pending");
		btnSetPending.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSetPending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("Selected files will be transferred to Pending");
			}
		});
		unread.add(btnGetUnassigned);
		unread.add(btnSetPending);
		tabbedPane.addTab("Unassigned", null, unread, null);
		
		// Pending Papers Tab
		JPanel pending = new JPanel();
		JButton btnGetPending = new JButton("Load Papers Pending Review");
		btnGetPending.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGetPending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTable pendingTable = getPending();
				pendingTable.setVisible(true);
				pending.add(pendingTable);
			}
		});
		JButton btnSetReviewed = new JButton("Set Selected As Reviewed");
		btnSetReviewed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSetReviewed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("Selected files will be transferred to Reviewed");
			}
		});
		pending.add(btnGetPending);
		pending.add(btnSetReviewed);
		tabbedPane.addTab("Pending", null, pending, null);
		
		// Reviewed Papers Tab
		JPanel reviewed = new JPanel();
		JButton btnGetReviewed = new JButton("Load Reviewed Papers");
		btnGetReviewed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGetReviewed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTable reviewedTable = getReviewed();
				reviewedTable.setVisible(true);
				reviewed.add(reviewedTable);
			}
		});
		JButton btnSetApproved = new JButton("Set Selected As Approved");
		btnSetApproved.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSetApproved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("Selected files will be transferred to Approved");
			}
		});
		reviewed.add(btnGetReviewed);
		reviewed.add(btnSetApproved);
		tabbedPane.addTab("Reviewed", null, reviewed, null);
		
		// Approved Papers Tab
		JPanel approved = new JPanel();
		JButton btnGetApproved = new JButton("Get Approved Papers");
		btnGetApproved.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGetApproved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTable approvedTable = getApproved();
				approvedTable.setVisible(true);
				approved.add(approvedTable);
			}
		});
		approved.add(btnGetApproved);
		tabbedPane.addTab("Approved", null, approved, null);
		
		JPanel deadlines = new JPanel();
		JButton btnSetDeadline = new JButton("Set Deadlines");
		btnSetDeadline.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSetDeadline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Deadline.setDeadline();
			}
		});
		deadlines.add(btnSetDeadline);
		tabbedPane.addTab("Deadlines", null, deadlines, null);
	}
	
	private JTable getUnread() {
		File unreadPath = new File("submissions/unread");
		JTable unreadTable = new JTable(new CustomFileTable(unreadPath));
		unreadTable.setAutoCreateRowSorter(true);
		unreadTable.setGridColor(Color.YELLOW);
		unreadTable.setBackground(Color.CYAN);
		unreadTable.setVisible(true);
		return unreadTable;
	}
	
	private JTable getPending() {
		File pendingPath = new File("submissions/pending");
		JTable pendingTable = new JTable(new CustomFileTable(pendingPath));
		pendingTable.setAutoCreateRowSorter(true);
		pendingTable.setGridColor(Color.YELLOW);
		pendingTable.setBackground(Color.CYAN);
		pendingTable.setVisible(true);
		return pendingTable;
	}
	
	private JTable getReviewed() {
		File reviewedPath = new File("submissions/reviewed");
		JTable reviewedTable = new JTable(new CustomFileTable(reviewedPath));
		reviewedTable.setAutoCreateRowSorter(true);
		reviewedTable.setGridColor(Color.YELLOW);
		reviewedTable.setBackground(Color.CYAN);
		reviewedTable.setVisible(true);
		return reviewedTable;
	}
	
	private JTable getApproved() {
		File approvedPath = new File("submissions/approved");
		JTable approvedtable = new JTable(new CustomFileTable(approvedPath));
		approvedtable.setAutoCreateRowSorter(true);
		approvedtable.setGridColor(Color.YELLOW);
		approvedtable.setBackground(Color.CYAN);
		approvedtable.setVisible(true);
		return approvedtable;
	}
	
	class CustomFileTable extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		private File dir;
	    private String[] filenames;
	    public File[] selectedFiles;
	    private Class<?>[] columnClasses = Constants.COLUMN_CLASSES;
	    private String[] columnNames = new String[] {"Chosen", "FileName", "FileSize", "Author", "Readable?", "Writable?", "Last Modified"};
	 
	    public CustomFileTable(File dir) {
	        this.dir = dir;
	        this.filenames = dir.list();
	    }
	 
	    public int getColumnCount() {
	        return Constants.COLUMN_CLASSES.length;
	    }
	 
	    public int getRowCount() {
	        return filenames.length;
	    }
	 
	    public String getColumnName(int col) {
	        return columnNames[col];
	    }
	 
	    public Class<?> getColumnClass(int col) {
	        return columnClasses[col];
	    }
	 
	    // Returns the value of each cell
	    public Object getValueAt(int row, int col) {
	        File f = new File(dir, filenames[row]);
	        TableColumn tableColumn = TableColumn.fromIndex(col);
	        switch (tableColumn) {
	        case CHOSEN:
	        	JCheckBox rowcheckbox = new JCheckBox();
	        	rowcheckbox.setHorizontalAlignment(SwingConstants.CENTER);
	        	rowcheckbox.setBackground(Color.white);
	        	rowcheckbox.addActionListener(new ActionListener() {
	        		public void actionPerformed(ActionEvent arg0) {
	        			System.out.println("Checked");
	        			if (rowcheckbox.isSelected()) {
		        			rowcheckbox.setSelected(true);
		        			selectedFiles = selectThisFile(f);
	        			} else {
	        				rowcheckbox.setSelected(false);
	        				selectedFiles = unselectThisFile(f);
	        			}
	        		}
	        	});
	        	return rowcheckbox.isSelected() ? Boolean.TRUE : Boolean.FALSE;
	    	case NAME:
	    		return filenames[row];
	    	case SIZE:
	            return new Long(f.length());
	    	case AUTHOR:
	    		return "Author".concat("_" + row);
	        case READABLE:
	            return f.canRead() ? Boolean.TRUE : Boolean.FALSE;
	        case WRITABLE:
	            return f.canWrite() ? Boolean.TRUE : Boolean.FALSE;
	        case LAST_MODIFIED:
	            return new Date(f.lastModified());
	        default:
	            return null;
	        }
	    }
	    
	    public File[] selectThisFile(File f) {
	    	int currentLength = selectedFiles.length;
	    	int updatedLength = currentLength + 1;
	    	File[] tempfiles = new File[updatedLength];
	    	for (int i = 0; i < currentLength; i++) {
	    		tempfiles[i] = selectedFiles[i];
	    	}
	    	tempfiles[updatedLength-1] = f;
	    	return tempfiles;
	    }
	    
	    public File[] unselectThisFile(File f) {
	    	int currentLength = selectedFiles.length;
	    	int updatedLength = currentLength - 1;
	    	File[] tempfiles = new File[updatedLength];
	    	for (int i = 0; i < currentLength; i++) {
	    		if (f != selectedFiles[i])
	    			tempfiles[i] = selectedFiles[i];
	    	}
	    	return tempfiles;
	    }
	}
    	
    enum TableColumn {
    	CHOSEN(0, "Chosen"),
        NAME(1, "FileName"),
        SIZE(2, "FileSize"),
        AUTHOR(3, "Author"),
        READABLE(4, "Readable?"),
        WRITABLE(5, "Writable?"),
        LAST_MODIFIED(6, "Last Modified");
     
        private TableColumn(int index, String name) {
            this.index = index;
            this.name = name;
        }
     
        private int index;
        private String name;
     
        private static final Map<Integer, TableColumn> COLUMN_INDEX_NAME_MAP = new HashMap<>();
        private static final List<String> NAMES = new ArrayList<>();
     
        static {
            for (TableColumn c : TableColumn.values()) {
                COLUMN_INDEX_NAME_MAP.put(c.index, c);
                NAMES.add(c.name);
            }
        }
     
        public static TableColumn fromIndex(int colIndex) {
            TableColumn columnName = COLUMN_INDEX_NAME_MAP.get(colIndex);
            return (columnName != null) ? columnName : null;
        }
     
        public static String[] getNames() {
            return NAMES.toArray(new String[NAMES.size()]);
        }
     
    }
	
    static class Constants {
        public static final Class<?>[] COLUMN_CLASSES = new Class[] {
    		Boolean.class,
    		String.class,
            Long.class,
            String.class,
            Boolean.class,
            Boolean.class,
            Date.class,
        };
        public final static String NIMBUS_LF = "Nimbus";
    }
}
