package author;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

public class Author {

	private JFrame frame;
	private static String name;
	/**
	 * Launch the application.
	 */
	public static void main(String username) {
		name = username;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Author window = new Author();
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
	public Author() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Author");
		frame.getContentPane().setBackground(Color.PINK);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSubmit = new JButton("Go to Submit");
		btnSubmit.setBackground(Color.CYAN);
		btnSubmit.setFont(new Font("Dialog", Font.ITALIC, 16));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UploadFile uf = new UploadFile();
				uf.UploadScreen(name);
			}
		});
		btnSubmit.setBounds(42, 184, 151, 24);
		frame.getContentPane().add(btnSubmit);
		
		JLabel lblWelcomeToSubmit = new JLabel("Welcome to submit your paper here");
		lblWelcomeToSubmit.setForeground(Color.YELLOW);
		lblWelcomeToSubmit.setBackground(Color.WHITE);
		lblWelcomeToSubmit.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblWelcomeToSubmit.setBounds(42, 85, 361, 24);
		frame.getContentPane().add(lblWelcomeToSubmit);
		
		JButton btnViewFeedback = new JButton("View Feedback");
		btnViewFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReceiveFeedBack.main(name);
			}
		});
		btnViewFeedback.setFont(new Font("Dialog", Font.ITALIC, 16));
		btnViewFeedback.setBackground(Color.CYAN);
		btnViewFeedback.setBounds(232, 184, 151, 24);
		frame.getContentPane().add(btnViewFeedback);
	}
}
