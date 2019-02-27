package login_Sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Signup_Sys.Signup;

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
	private static boolean checker(String useremail, String userpassword, String accountpath) {
		String line = null;
		boolean Value = false;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(accountpath);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				// System.out.println("111");
				// create a token based on
				String[] token = line.split(";");
				// because you know first and second word of each line in
				// given file is id and password
				if (token[0].equals(useremail) && token[1].equals(userpassword)) {
					Value = true;
					return Value;
				}
			}
			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + accountpath + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + accountpath + "'");
			// Or we could just do this:
			ex.printStackTrace();
		}
		return Value;
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

		JLabel lbUserEmail = new JLabel("UserEmail");
		lbUserEmail.setBounds(71, 64, 66, 14);
		lbUserEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbUserEmail);

		JLabel lbPassword = new JLabel("Password");
		lbPassword.setBounds(71, 119, 89, 23);
		lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbPassword);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(212, 121, 86, 20);
		frame.getContentPane().add(txtPassword);

		txtUserEmail = new JTextField();
		txtUserEmail.setBounds(212, 62, 86, 20);
		frame.getContentPane().add(txtUserEmail);
		txtUserEmail.setColumns(10);

		JLabel lblNewLabel = new JLabel("Login System");
		lblNewLabel.setBounds(190, 14, 176, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblNewLabel);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(71, 211, 89, 23);
		frame.getContentPane().add(btnLogin);

		JRadioButton rdbtnadministrator = new JRadioButton("administrator");
		rdbtnadministrator.setBounds(71, 161, 97, 14);
		rdbtnadministrator.setBackground(Color.PINK);
		rdbtnadministrator.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(rdbtnadministrator);

		JRadioButton rdbtnReviewer = new JRadioButton("reviewer");
		rdbtnReviewer.setBounds(181, 161, 97, 14);
		rdbtnReviewer.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rdbtnReviewer.setBackground(Color.PINK);
		frame.getContentPane().add(rdbtnReviewer);

		JRadioButton rdbtnAuthor = new JRadioButton("author");
		rdbtnAuthor.setBounds(296, 161, 97, 14);
		rdbtnAuthor.setFont(new Font("Tahoma", Font.PLAIN, 10));
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
		btnReset.setBounds(212, 211, 89, 23);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUserEmail.setText(null);
				txtPassword.setText(null);
				group.clearSelection();
			}
		});
		frame.getContentPane().add(btnReset);

		JSeparator separator = new JSeparator();
		separator.setBounds(21, 192, 426, 2);
		frame.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(21, 37, 432, 2);
		frame.getContentPane().add(separator_1);

		JButton btnSignup = new JButton("Sign up");
		btnSignup.setBounds(341, 211, 89, 23);
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signup info = new Signup();
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
						System.out.println("123");
					}
				}
				if (selection == "authors") {
					if (checker(username, password, AuthorsInfoPath) == true) {
						System.out.println("456");
					}
				}
				if (selection == "administrator") {
					if (checker(username, password, AdministratorInfoPath) == true) {
						System.out.println("789");
					}
				}
			}
		});

	}
}
