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
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Administrator {
	private int selected;
	private JFrame frame;
	private String unreadPath;
	private String pendingPath;
	private String reviewedPath;
	private String approvedPath;
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
		this.selected = 0;
		this.unreadTable = null;
		this.pendingTable = null;
		this.reviewedTable = null;
		this.approvedTable = null;
		this.unreadPath = "submissions/unread";
		this.pendingPath = "submissions/pending";
		this.reviewedPath = "submissions/reviewed";
		this.approvedPath = "submissions/approved";
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
		this.unreadTable = getTable(this.unreadPath);
		JButton btnGetUnassigned = new JButton("View Selected Paper");
		btnGetUnassigned.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGetUnassigned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = getSelected();
				try {
					viewUnread(index);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		JButton btnSetPending = new JButton("Set Selected As Pending");
		btnSetPending.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSetPending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = getSelected();
				System.out.println("Selected papers are now Pending");
			}
		});
		unread.add(btnGetUnassigned);
		unread.add(btnSetPending);
		unread.add(this.unreadTable);
		tabbedPane.addTab("Unassigned", null, unread, null);
		
		// Pending Papers Tab
		JPanel pending = new JPanel();
		this.pendingTable = getTable(this.pendingPath);
		JButton btnGetPending = new JButton("View Selected Paper");
		btnGetPending.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGetPending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = getSelected();
				try {
					viewPending(index);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		JButton btnSetReviewed = new JButton("Set Selected As Reviewed");
		btnSetReviewed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSetReviewed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = getSelected();
				System.out.println("Selected files have been Reviewed");
			}
		});
		pending.add(btnGetPending);
		pending.add(btnSetReviewed);
		pending.add(this.pendingTable);
		tabbedPane.addTab("Pending", null, pending, null);
		
		// Reviewed Papers Tab
		JPanel reviewed = new JPanel();
		this.reviewedTable = getTable(this.reviewedPath);
		JButton btnGetReviewed = new JButton("View Selected Paper");
		btnGetReviewed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGetReviewed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = getSelected();
				try {
					viewReviewed(index);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		JButton btnSetApproved = new JButton("Set Selected As Approved");
		btnSetApproved.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSetApproved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = getSelected();
				System.out.println("Selected files will be Approved");
			}
		});
		reviewed.add(btnGetReviewed);
		reviewed.add(btnSetApproved);
		reviewed.add(this.reviewedTable);
		tabbedPane.addTab("Reviewed", null, reviewed, null);
		
		// Approved Papers Tab
		JPanel approved = new JPanel();
		this.approvedTable = getTable(this.approvedPath);
		JButton btnGetApproved = new JButton("View Selected Paper");
		btnGetApproved.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGetApproved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = getSelected();
				try {
					viewApproved(index);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		approved.add(btnGetApproved);
		approved.add(this.approvedTable);
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
	
	public void setSelected(int pick) {
		this.selected = pick;
	}
	
	public int getSelected() {
		return this.selected;
	}
	
	public void viewUnread(int index) throws IOException {
		File dir = new File(this.unreadPath);
	    String[] filenames = dir.list();
	    File viewing = new File(dir, filenames[index]);
	    AdminViewer.renderFile(viewing.getPath());
	}
	
	public void viewPending(int index) throws IOException {
		File dir = new File(this.pendingPath);
	    String[] filenames = dir.list();
	    File viewing = new File(dir, filenames[index]);
	    AdminViewer.renderFile(viewing.getPath());
	}
	
	public void viewReviewed(int index) throws IOException {
		File dir = new File(this.reviewedPath);
	    String[] filenames = dir.list();
	    File viewing = new File(dir, filenames[index]);
	    AdminViewer.renderFile(viewing.getPath());
	}
	
	public void viewApproved(int index) throws IOException {
		File dir = new File(this.approvedPath);
	    String[] filenames = dir.list();
	    File viewing = new File(dir, filenames[index]);
	    AdminViewer.renderFile(viewing.getPath());
	}
	
	private JTable getTable(String path) {
		int choice = 0;
		File dir = new File(path);
		JTable table = new JTable(new CustomFileTable(dir));
		SelectionListener listener = new SelectionListener(table, choice);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(listener);
		table.setRowSelectionAllowed(true);
		table.setAutoCreateRowSorter(true);
		table.setGridColor(Color.YELLOW);
		table.setBackground(Color.CYAN);
		table.setVisible(true);
		return table;
	}
	
	class SelectionListener implements ListSelectionListener {
		JTable table;
		int index;
		
		SelectionListener(JTable table, int index) {
			super();
			this.table = table;
		}
		
		public void valueChanged(ListSelectionEvent evt) {
			int pick;
			if (evt.getValueIsAdjusting()) {
				return;
			}
			if (evt.getSource() == table.getSelectionModel()
					&& table.getRowSelectionAllowed()) {
				pick = evt.getLastIndex();
				this.index = pick;
				setSelected(pick);
			}
		}
		
		public int getChosen() {
			return this.index;
		}
	}
	
	class CustomFileTable extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		private File dir;
	    private String[] filenames;
	    public File[] selectedFiles;
	    private Class<?>[] columnClasses = Constants.COLUMN_CLASSES;
	    private String[] columnNames = new String[] {"FileName", "FileSize", "Author", "Readable?", "Writable?", "Last Modified"};
	 
	    public CustomFileTable(File dir) {
	    	super();
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
        NAME(0, "FileName"),
        SIZE(1, "FileSize"),
        AUTHOR(2, "Author"),
        READABLE(3, "Readable?"),
        WRITABLE(4, "Writable?"),
        LAST_MODIFIED(5, "Last Modified");
     
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
