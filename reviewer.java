import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class reviewer {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reviewer window = new reviewer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	  
	 */
	public reviewer() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 826, 636);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Hello Reviewer!");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 15));
		
		JButton btnNewButton = new JButton("Submit feedback");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JLabel lblFeedback = new JLabel("Feedback:");
		lblFeedback.setForeground(Color.BLUE);
		lblFeedback.setFont(new Font("Goudy Old Style", Font.PLAIN, 18));
		
		
		
		JTextArea textArea = new JTextArea();
		
		try {
			FileReader reader = new FileReader("Journal.txt");
			textArea.read(reader, "Journal.txt");
		}
		catch (IOException ioe) {
		    System.err.println(ioe);
		    System.exit(1);
		}
		
		
		
		JTextArea textArea_1 = new JTextArea();
		//JScrollPane sp = new JScrollPane(textArea_1); 
		//frame.getContentPane().add(sp);
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(173)
							.addComponent(lblFeedback)
							.addGap(18)
							.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(98)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 615, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(97, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(54)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFeedback)
								.addComponent(btnNewButton)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
