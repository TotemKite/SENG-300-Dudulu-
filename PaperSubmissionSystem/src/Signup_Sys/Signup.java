package Signup_Sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.awt.event.ActionEvent;

public class Signup {

	private JFrame frame;
	private JTextField txtUserEmail;
	private JPasswordField txtPassword;

	private String AuthorsInfoPath = "resources/authors.txt";
	private String ReviewersInfoPath = "resources/reviewers.txt";

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
	 * Create the OpenAndWrite Method for sign-up.
	 */
	private void OpenAndWrite(String UserAccount, String UserPassword, String path) {
		String account = UserAccount;
		String password = UserPassword;
		String filepath = path;
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(filepath, true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
			bw.write(account);
			bw.write(";");
			bw.write(password);
			bw.newLine();
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		JRadioButton rdbtnReviewer = new JRadioButton("Reviewer");
		rdbtnReviewer.setBackground(Color.PINK);
		rdbtnReviewer.setBounds(165, 297, 190, 23);
		frame.getContentPane().add(rdbtnReviewer);

		JRadioButton rdbtnAuthors = new JRadioButton("Author");
		rdbtnAuthors.setBackground(Color.PINK);
		rdbtnAuthors.setBounds(166, 344, 205, 23);
		frame.getContentPane().add(rdbtnAuthors);
		rdbtnReviewer.setActionCommand("reviewer");
		rdbtnAuthors.setActionCommand("authors");

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnReviewer);
		group.add(rdbtnAuthors);

		txtUserEmail = new JTextField();
		txtUserEmail.setBounds(164, 125, 120, 20);
		frame.getContentPane().add(txtUserEmail);
		txtUserEmail.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(164, 190, 120, 20);
		frame.getContentPane().add(txtPassword);

		JLabel lbUserEmail = new JLabel("UserEmail");
		lbUserEmail.setBounds(46, 128, 109, 23);
		frame.getContentPane().add(lbUserEmail);

		JLabel lbPassword = new JLabel("Password");
		lbPassword.setBounds(46, 192, 100, 17);
		frame.getContentPane().add(lbPassword);

		JLabel lblNewLabel = new JLabel("Registration");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(166, 51, 141, 49);
		frame.getContentPane().add(lblNewLabel);

		JButton btnComfirm = new JButton("Comfirm");
		btnComfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selection = group.getSelection().getActionCommand();
				if (selection == "reviewer") {
					OpenAndWrite(txtUserEmail.getText(), String.copyValueOf(txtPassword.getPassword()),
							ReviewersInfoPath);
				}
				if (selection == "authors") {
					OpenAndWrite(txtUserEmail.getText(), String.copyValueOf(txtPassword.getPassword()),
							AuthorsInfoPath);
				}
				frame.dispose();
			}

		});
		btnComfirm.setBounds(21, 428, 120, 41);
		frame.getContentPane().add(btnComfirm);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(297, 428, 134, 41);
		frame.getContentPane().add(btnCancel);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUserEmail.setText(null);
				txtPassword.setText(null);
				group.clearSelection();
			}
		});
		btnReset.setBounds(164, 428, 120, 41);
		frame.getContentPane().add(btnReset);
	}
}
