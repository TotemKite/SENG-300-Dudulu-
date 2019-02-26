package login_Sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Signup_Sys.Signup;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Login_S {

	private JFrame frame;
	private JPasswordField txtPassword;
	private JTextField txtUserEmail;

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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.setBounds(200, 200, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbUserEmail = new JLabel("UserEmail");
		lbUserEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbUserEmail.setBounds(71, 64, 66, 14);
		frame.getContentPane().add(lbUserEmail);
		
		JLabel lbPassword = new JLabel("Password");
		lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbPassword.setBounds(71, 119, 89, 23);
		frame.getContentPane().add(lbPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(212, 121, 86, 20);
		frame.getContentPane().add(txtPassword);
		
		txtUserEmail = new JTextField();
		txtUserEmail.setBounds(212, 62, 86, 20);
		frame.getContentPane().add(txtUserEmail);
		txtUserEmail.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login System");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(190, 14, 176, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String password = txtPassword.getText();
				String username = txtUserEmail.getText();
				
			}
		});
		btnLogin.setBounds(71, 211, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUserEmail.setText(null);
				txtPassword.setText(null);
			}
		});
		btnReset.setBounds(212, 211, 89, 23);
		frame.getContentPane().add(btnReset);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(21, 192, 426, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(21, 37, 432, 2);
		frame.getContentPane().add(separator_1);
		
		JButton btnSignup = new JButton("Sign up");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signup info = new Signup();
				Signup.main(null);
			}
		});
		btnSignup.setBounds(341, 211, 89, 23);
		frame.getContentPane().add(btnSignup);
	}
}
