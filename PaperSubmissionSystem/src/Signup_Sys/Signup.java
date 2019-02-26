package Signup_Sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Signup {

	private JFrame frame;
	private JTextField txtUserEmail;
	private JPasswordField txtPassword;

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
		
		JRadioButton rdbtnAuthors = new JRadioButton("Author");
		rdbtnAuthors.setBackground(Color.PINK);
		rdbtnAuthors.setBounds(166, 344, 109, 23);
		frame.getContentPane().add(rdbtnAuthors);
		
		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnAdministrator);
		group.add(rdbtnReviewer);
		group.add(rdbtnAuthors);
		
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
				group.getSelection();
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
}
