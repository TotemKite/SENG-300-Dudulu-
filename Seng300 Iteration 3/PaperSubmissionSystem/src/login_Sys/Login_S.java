package login_Sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import administrator.Administrator;
import author.Author;
import reviewer.Reviewer;
import signup_Sys.Signup;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JRadioButton;

public class Login_S {

	private JFrame frame;
	private JPasswordField txtPassword;
	private JTextField txtUserEmail;
	private String AuthorsInfoPath = "resources/authors.txt";
	private String ReviewersInfoPath = "resources/reviewers.txt";
	private String AdministratorInfoPath = "resources/administrator.txt";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_S window = new Login_S();
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
	public Login_S() {
		initialize();
	}
	/**
	 * Create the boolean checker function for log-in.
	 */
	private boolean checker(String useremail, String userpassword, String accountpath) {
		String line = null;
		boolean Vaild = false;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(accountpath);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				String[] token = line.split(";");
				if (token[0].equals(useremail) && token[1].equals(userpassword)) {
					Vaild = true;
					return Vaild;
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + accountpath + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + accountpath + "'");
		}
		return Vaild;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.setBounds(200, 200, 500, 342);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lbUserEmail = new JLabel("UserEmail:");
		lbUserEmail.setBounds(71, 62, 69, 17);
		lbUserEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbUserEmail);

		JLabel lbPassword = new JLabel("Password:");
		lbPassword.setBounds(71, 121, 89, 23);
		lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbPassword);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(212, 121, 130, 20);
		frame.getContentPane().add(txtPassword);

		txtUserEmail = new JTextField();
		txtUserEmail.setBounds(212, 62, 130, 20);
		frame.getContentPane().add(txtUserEmail);
		txtUserEmail.setColumns(10);

		JLabel lblNewLabel = new JLabel("Paper Submission System");
		lblNewLabel.setBounds(134, 12, 227, 18);
		lblNewLabel.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 17));
		frame.getContentPane().add(lblNewLabel);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(71, 237, 89, 23);
		frame.getContentPane().add(btnLogin);

		JRadioButton rdbtnadministrator = new JRadioButton("Administrator");
		rdbtnadministrator.setBounds(59, 161, 124, 26);
		rdbtnadministrator.setBackground(Color.PINK);
		rdbtnadministrator.setFont(new Font("Dialog", Font.PLAIN, 15));
		frame.getContentPane().add(rdbtnadministrator);

		JRadioButton rdbtnReviewer = new JRadioButton("Reviewer");
		rdbtnReviewer.setBounds(210, 161, 92, 26);
		rdbtnReviewer.setFont(new Font("Dialog", Font.PLAIN, 15));
		rdbtnReviewer.setBackground(Color.PINK);
		frame.getContentPane().add(rdbtnReviewer);

		JRadioButton rdbtnAuthor = new JRadioButton("Author");
		rdbtnAuthor.setBounds(357, 161, 74, 26);
		rdbtnAuthor.setFont(new Font("Dialog", Font.PLAIN, 15));
		rdbtnAuthor.setBackground(Color.PINK);
		frame.getContentPane().add(rdbtnAuthor);

		// Name the RadioButton
		rdbtnadministrator.setActionCommand("administrator");
		rdbtnReviewer.setActionCommand("reviewer");
		rdbtnAuthor.setActionCommand("authors");

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnadministrator);
		group.add(rdbtnReviewer);
		group.add(rdbtnAuthor);
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(201, 237, 89, 23);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUserEmail.setText(null);
				txtPassword.setText(null);
				group.clearSelection();
			}
		});
		frame.getContentPane().add(btnReset);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(21, 37, 432, 2);
		frame.getContentPane().add(separator_1);

		JButton btnSignup = new JButton("Sign up");
		btnSignup.setBounds(328, 237, 89, 23);
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signup.main(null);
			}
		});
		frame.getContentPane().add(btnSignup);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String password = String.copyValueOf(txtPassword.getPassword());
				String username = txtUserEmail.getText();
				String selection = group.getSelection().getActionCommand();
				if (selection == "reviewer") {
					if (checker(username, password, ReviewersInfoPath) == true) {
						Reviewer.main(null);
						
					}
				}
				if (selection == "authors") {
					if (checker(username, password, AuthorsInfoPath) == true) {
						Author.main(null);
					}
				}
				if (selection == "administrator") {
					if (checker(username, password, AdministratorInfoPath) == true) {
						Administrator.main(null);
					}
				}
			}
		});

	}
}
