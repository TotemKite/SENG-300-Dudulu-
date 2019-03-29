package administrator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.SwingConstants;

public class Deadline {

	private JFrame frame;
	private JTextField date;
	private JTextField time;

	/**
	 * Launch the application.
	 */
	public static void setDeadline() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deadline window = new Deadline();
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
	public Deadline() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		date = new JTextField();
		date.setBounds(181, 66, 105, 30);
		frame.getContentPane().add(date);
		date.setColumns(10);
		
		time = new JTextField();
		time.setBounds(181, 133, 105, 30);
		frame.getContentPane().add(time);
		time.setColumns(10);
		
		JButton btnEnsure = new JButton("Ensure");
		btnEnsure.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEnsure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dt = date.getText();
				String tm = time.getText();
				
				String deadline = dt + " " + tm;
				
				try {
					FileOutputStream out = new FileOutputStream("deadline_info/deadlineforJournal1.txt");
					out.write(deadline.getBytes());
					out.close();
					File f = new File("resources/Deadline.txt");
					if(f.exists()) {
						JOptionPane.showMessageDialog(btnEnsure, "The deadline has been assigned successfully!");
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEnsure.setBounds(156, 204, 94, 32);
		frame.getContentPane().add(btnEnsure);
		
		JLabel lblYy = new JLabel("YYYY-MM-DD");
		lblYy.setHorizontalAlignment(SwingConstants.CENTER);
		lblYy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblYy.setBounds(44, 68, 127, 22);
		frame.getContentPane().add(lblYy);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTime.setBounds(63, 139, 83, 14);
		frame.getContentPane().add(lblTime);
	}
}
