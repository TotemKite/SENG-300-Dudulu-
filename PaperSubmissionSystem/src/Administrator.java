import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Administrator {
	private JFrame frame;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Administrator() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
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
		menuBar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		menuBar.add(logout);
		
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}		
		});
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JTextPane unassigned = new JTextPane();
		unassigned.setText("Journals that have yet to be assigned to a Reviewer...");
		tabbedPane.addTab("Unassigned", null, unassigned, null);
		
		JTextPane pending = new JTextPane();
		pending.setText("Journals that are being processed in some other way prior to review...");
		tabbedPane.addTab("Pending", null, pending, null);
		
		JTextPane assigned = new JTextPane();
		assigned.setText("Journals that have been assigned to a Reviewer...");
		tabbedPane.addTab("Assigned", null, assigned, null);
		
		JTextPane reviewed = new JTextPane();
		reviewed.setText("Journals that have been reviewed by a Reviewer...");
		tabbedPane.addTab("Reviewed", null, reviewed, null);
	}

}
