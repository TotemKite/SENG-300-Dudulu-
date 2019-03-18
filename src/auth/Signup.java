package auth;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;

import roles.Administrator;
import roles.Author;
import roles.Reviewer;

import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Signup {

	private JFrame frame;
	private JTextField txtUserEmail;
	private JPasswordField txtPassword;
	private String registeredAuthorsPath = "accounts/authors.txt";
	private String registeredReviewersPath = "accounts/reviewers.txt";
	private String registeredAdministratorPath = "accounts/administrator.txt";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup window = new Signup();
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
	public Signup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.setBounds(100, 100, 478, 593);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JRadioButton rdbtnAdministrator = new JRadioButton("Administrator");
		rdbtnAdministrator.setBackground(Color.PINK);
		rdbtnAdministrator.setBounds(164, 252, 109, 23);
		frame.getContentPane().add(rdbtnAdministrator);
		
		JRadioButton rdbtnReviewer = new JRadioButton("Reviewer");
		rdbtnReviewer.setBackground(Color.PINK);
		rdbtnReviewer.setBounds(165, 297, 109, 23);
		frame.getContentPane().add(rdbtnReviewer);
		
		JRadioButton rdbtnAuthor = new JRadioButton("Author");
		rdbtnAuthor.setBackground(Color.PINK);
		rdbtnAuthor.setBounds(166, 344, 109, 23);
		frame.getContentPane().add(rdbtnAuthor);
		
		// Name the RadioButton
		rdbtnAdministrator.setActionCommand("administrator");
		rdbtnReviewer.setActionCommand("reviewer");
		rdbtnAuthor.setActionCommand("authors");
		
		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnAdministrator);
		group.add(rdbtnReviewer);
		group.add(rdbtnAuthor);
		
		txtUserEmail = new JTextField();
		txtUserEmail.setBounds(164, 125, 101, 20);
		frame.getContentPane().add(txtUserEmail);
		txtUserEmail.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(164, 190, 109, 20);
		frame.getContentPane().add(txtPassword);
		
		JLabel lbUserEmail = new JLabel("UserEmail");
		lbUserEmail.setBounds(51, 128, 104, 14);
		frame.getContentPane().add(lbUserEmail);
		
		JLabel lbPassword = new JLabel("Password");
		lbPassword.setBounds(55, 193, 68, 14);
		frame.getContentPane().add(lbPassword);
		
		JLabel lblNewLabel = new JLabel("Registration");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(166, 51, 141, 49);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnComfirm = new JButton("Comfirm");
		btnComfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String passwd = String.copyValueOf(txtPassword.getPassword());
				String acct = group.getSelection().getActionCommand();
				String user = txtUserEmail.getText();
				registerAccount(user, passwd, acct);
			}
		});
		btnComfirm.setBounds(73, 429, 120, 41);
		frame.getContentPane().add(btnComfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(215, 428, 134, 41);
		frame.getContentPane().add(btnCancel);
	}
	
	private void registerAccount(String username, String password, String accounttype) {
		if (accounttype == "administrator") {
			if (ifNoAdminExists(registeredAdministratorPath)) {
				storeNewAccountRecord(username, password, registeredAdministratorPath);
				Administrator.main(null);
			}
		}
		if (accounttype == "reviewer") {
			if (!isDuplicate(username, password, registeredReviewersPath)) {
				storeNewAccountRecord(username, password, registeredReviewersPath);
				Reviewer.main(null);
			}
		}
		if (accounttype == "authors") {
			if (!isDuplicate(username, password, registeredAuthorsPath)) {
				storeNewAccountRecord(username, password, registeredAuthorsPath);
				Author.main(null);
			}
		}
	}
	
	private boolean ifNoAdminExists(String path) {
		try {
			File accountFile = new File(path);
			FileReader fileReader = new FileReader (accountFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			if (bufferedReader.readLine() != null) {
				bufferedReader.close();
				return false;
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + path + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + path + "'");
		}
		return true;
	}
	
	private boolean isDuplicate(String user, String passwd, String path) {
		try {
			String line;
			File file = new File(path);
			FileReader reader = new FileReader(file);
			BufferedReader bufferin = new BufferedReader(reader);
			while ((line = bufferin.readLine()) != null) {
				String[] token = line.split(";");
				if (token[0].equals(user) && token[1].equals(passwd)) {
					bufferin.close();
					return false;
				}
			}
			bufferin.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + path + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + path + "'");
		}
		return true;
	}
	
	private void storeNewAccountRecord(String user, String pass, String path) {
		try {
			String record = "";
			File file = new File(path);
			record = record += user + ";" + pass;
			FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferout = new BufferedWriter(writer); 
            bufferout.write(record);
            bufferout.close(); 
        } catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + path + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + path + "'");
		}
	}
}
