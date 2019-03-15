package reviewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import java.io.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import journals.*;

public class ViewJournals {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		journal.Journal jou = new journal.Journal("jou", "test.txt");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewJournals window = new ViewJournals();
					window.frame.setVisible(true);
//					JFrame frame = new JFrame();
//			        JPanel panel = new JPanel();
//			        for (int i = 0; i < 10; i++) {
//			            panel.add(new JButton("Hello-" + i));
//			        }
//			        JScrollPane scrollPane = new JScrollPane(panel);
//			        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//			        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
//			        scrollPane.setBounds(50, 30, 300, 50);
//			        JPanel contentPane = new JPanel(null);
//			        contentPane.setPreferredSize(new Dimension(500, 400));
//			        contentPane.add(scrollPane);
//			        frame.setContentPane(contentPane);
//			        frame.pack();
//			        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//			        frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewJournals() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		JTextArea txt = new JTextArea(30, 30);
		JScrollPane scroll = new JScrollPane(txt);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.setLayout(new BorderLayout());
        txt.setEditable(false);
//		panel.add(txt);
		panel.add(scroll);
		try {
			FileReader reader = new FileReader( "src/journals/test.txt" );
			BufferedReader br = new BufferedReader(reader);
			txt.read(br, null);
			br.close();
			txt.requestFocus();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(71, Short.MAX_VALUE))
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 401, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 201, Short.MAX_VALUE)
		);
//		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
		frame.pack();
		
	}
}
