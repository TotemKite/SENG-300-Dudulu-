package author;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Author {

	private JFrame frame;
	private static String name;
	private boolean submittFlag = false;
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
		lblWelcomeToSubmit.setBounds(12, 83, 410, 24);
		frame.getContentPane().add(lblWelcomeToSubmit);

		JButton btnViewFeedback = new JButton("View Feedback");
		btnViewFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReceiveFeedBack.main(name);
			}
		});
		btnViewFeedback.setFont(new Font("Dialog", Font.ITALIC, 16));
		btnViewFeedback.setBackground(Color.CYAN);
		btnViewFeedback.setBounds(252, 184, 151, 24);
		frame.getContentPane().add(btnViewFeedback);

		JButton btnV = new JButton("View Journal Status");
		btnV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File dir = new File("submissions/unread");
				File[] listOfFiles = dir.listFiles();
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						if (listOfFiles[i].getName().contains(name)) {
							JOptionPane.showMessageDialog(null,
									"Your Journal has been submitted, but not been assigned to a reviewer yet ");
							submittFlag =true;
						}
					}
				}
				File dir1 = new File("submissions/reviewed");
				File[] listOfFiles1 = dir1.listFiles();
				for (int i = 0; i < listOfFiles1.length; i++) {
					if (listOfFiles1[i].isFile()) {
						if (listOfFiles1[i].getName().contains(name)) {
							JOptionPane.showMessageDialog(null,
									"Your Journal is under reviewing");
							submittFlag =true;
						}
					}
				}
				File dir2 = new File("submissions/pending");
				File[] listOfFiles2 = dir2.listFiles();
				for (int i = 0; i < listOfFiles2.length; i++) {
					if (listOfFiles2[i].isFile()) {
						if (listOfFiles2[i].getName().contains(name)) {
							JOptionPane.showMessageDialog(null,
									"Your Journal has been reviewed, but not been approved yet");
							submittFlag =true;
						}
					}
				}
				File dir3 = new File("submissions/approved");
				File[] listOfFiles3 = dir3.listFiles();
				for (int i = 0; i < listOfFiles3.length; i++) {
					if (listOfFiles3[i].isFile()) {
						if (listOfFiles3[i].getName().contains(name)) {
							JOptionPane.showMessageDialog(null,
									"Your Journal has been approved, Congratulations!");
							submittFlag =true;
						}
					}
				}
				if(submittFlag == false) {
					JOptionPane.showMessageDialog(null,
							"You haven't submitted your journal yet");
				}
			}
		});
		btnV.setFont(new Font("Dialog", Font.ITALIC, 16));
		btnV.setBackground(Color.CYAN);
		btnV.setBounds(139, 219, 183, 29);
		frame.getContentPane().add(btnV);
	}
}
