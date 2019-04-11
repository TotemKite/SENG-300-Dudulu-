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
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class Administrator {
	JTable unreadTable;
	JTable pendingTable;
	File[] pendingFiles;
	JTable reviewedTable;
	File[] reviewedFiles;
	JTable approvedTable;
	File[] approvedFiles;
	JTable deadlineTable;
	private int selected;
	private JFrame frame;
	private String unreadPath;
	private String pendingPath;
	private String reviewedPath;
	private String approvedPath;
	int[] assignedUnread;
	int[] assignedPending;
	int[] assignedReviewed;
//	int[] assignedApproved;

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
//		File unreadFolder = new File(this.unreadPath);
		assignedUnread = new int[(new File(this.unreadPath)).listFiles().length];
		for(int i = 0; i < assignedUnread.length; i++) {
			assignedUnread[i] = assignedUnread.length;
		}
		
		assignedPending = new int[(new File(this.pendingPath)).listFiles().length];
		for(int i = 0; i < assignedPending.length; i++) {
			assignedPending[i] = assignedPending.length;
		}
		
		assignedReviewed = new int[(new File(this.reviewedPath)).listFiles().length];
		for(int i = 0; i < assignedReviewed.length; i++) {
			assignedReviewed[i] = assignedReviewed.length;
		}
		
//		assignedApproved = new int[(new File(this.approvedPath)).listFiles().length];
//		for(int i = 0; i < assignedApproved.length; i++) {
//			assignedApproved[i] = assignedApproved.length;
//		}
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.setTitle("Administrator");
		frame.setResizable(false);
		frame.setBounds(200, 200, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenuItem logout = new JMenuItem("Logout");
//		JMenuItem refresh = new JMenuItem("Refresh");
		menuBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		menuBar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuBar.add(logout);
//		menuBar.add(refresh);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}		
		});
//		refresh.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				unreadTable = getTable(unreadPath);
//				pendingTable = getTable(pendingPath);
//			}		
//		});
		
		// Tabs Setup
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		// Unread Papers Tab
		JPanel unread = new JPanel();
		this.unreadTable = getTable(this.unreadPath);
		JButton btnGetUnassigned = new JButton("View Unread Papers");
		btnGetUnassigned.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGetUnassigned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = getSelected();
				assignedUnread[index] = index;
				for(int i = 0; i < assignedUnread.length; i++) {
					if(index > assignedUnread[i])
						index--;
				}
//				System.out.println("index::::::::::::"+index);
				try {
					viewUnread(index);
					frame.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		unread.add(btnGetUnassigned);
		unread.add(new JSeparator(JSeparator.HORIZONTAL));
		unread.add(this.unreadTable);
		tabbedPane.addTab("Unread", null, unread, null);
		// Pending Papers Tab
		JPanel pending = new JPanel();
		this.pendingTable = getTable(this.pendingPath);
		JButton btnGetPending = new JButton("View Pending Papers");
		btnGetPending.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGetPending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = getSelected();
				assignedPending[index] = index;
				for(int i = 0; i < assignedPending.length; i++) {
					if(index > assignedPending[i])
						index--;
				}
				try {
					viewPending(index);
					frame.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		pending.add(btnGetPending);
		pending.add(new JSeparator(JSeparator.HORIZONTAL));
		pending.add(this.pendingTable);
		tabbedPane.addTab("Pending", null, pending, null);
		
		// Reviewed Papers Tab
		JPanel reviewed = new JPanel();
		this.reviewedTable = getTable(this.reviewedPath);
		JButton btnGetReviewed = new JButton("View Reviewed Papers");
		btnGetReviewed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGetReviewed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = getSelected();
				assignedReviewed[index] = index;
				for(int i = 0; i < assignedReviewed.length; i++) {
					if(index > assignedReviewed[i])
						index--;
				}
				try {
					viewReviewed(index);
					frame.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		reviewed.add(btnGetReviewed);
		reviewed.add(new JSeparator(JSeparator.HORIZONTAL));
		reviewed.add(this.reviewedTable);
		tabbedPane.addTab("Reviewed", null, reviewed, null);
		
		// Approved Papers Tab
		JPanel approved = new JPanel();
		this.approvedTable = getTable(this.approvedPath);
		JButton btnGetApproved = new JButton("View Approved Papers");
		btnGetApproved.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGetApproved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = getSelected();
				try {
					viewApproved(index);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		approved.add(btnGetApproved);
		approved.add(this.approvedTable);
		tabbedPane.addTab("Approved", null, approved, null);
		
		JPanel deadlines = new JPanel();
		JButton btnSetDeadline = new JButton("Set/Change Deadlines for Review");
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
		File origin = new File(this.unreadPath);
		String[] originals = origin.list();
		File viewing = new File(origin, originals[index]);
		File destination = new File(this.pendingPath);
//		String[] dests = destination.list();
//		File saving = new File(destination, dests[index]);
		AdminViewer.renderFile(viewing.getPath(), destination.getPath());
	}
	
	public void viewPending(int index) throws IOException {
		File origin = new File(this.pendingPath);
		String[] originals = origin.list();
		File viewing = new File(origin, originals[index]);
		File destination = new File(this.reviewedPath);
//		String[] dests = destination.list();
//		File saving = new File(destination, dests[index]);
		AdminViewer.renderFile(viewing.getPath(), destination.getPath());
	}
	
	public void viewReviewed(int index) throws IOException {
		File origin = new File(this.reviewedPath);
		String[] originals = origin.list();
		File viewing = new File(origin, originals[index]);
		File destination = new File(this.approvedPath);
//		String[] dests = destination.list();
//		File saving = new File(destination, dests[index]);
		AdminViewer.renderFile(viewing.getPath(), destination.getPath());
	}
	
	public void viewApproved(int index) throws IOException {
		File dir = new File(this.approvedPath);
	    String[] filenames = dir.list();
	    File viewing = new File(dir, filenames[index]);
	    AdminViewer.renderFile(viewing.getPath(), viewing.getPath());
	}
	
	private JTable getTable(String path) {
		int choice = 0;
		File dir = new File(path);
		JTable table = new JTable(new CustomFileTable(dir));
		SelectionListener listener = new SelectionListener(table, choice);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(listener);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table.setRowSelectionAllowed(true);
		table.setAutoCreateRowSorter(true);
		table.setGridColor(Color.YELLOW);
		table.setBackground(Color.PINK);
		table.setRowHeight(25);
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
//				pick = evt.getLastIndex();
				pick = table.getSelectedRow();
				this.index = pick;
//				System.out.println("11111111111111111111111111pick: "+pick);
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
	            return f.canRead() ? "READ": "N/A";
	        case WRITABLE:
	            return f.canWrite() ? "WRITE" : "N/A";
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
            String.class,
            String.class,
            Date.class,
        };
        public final static String NIMBUS_LF = "Nimbus";
    }
}