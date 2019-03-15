import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class Success {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void SuccessfulScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Success window = new Success();
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
	public Success() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Your paper has been uploaded successfully!");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel.setBounds(29, 101, 378, 40);
		frame.getContentPane().add(lblNewLabel);
	}

}
