package administrator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

public class GetFeedback {

	private JFrame frame;
	private JLabel lblMajorFeedback;
	private JLabel lblMinorFeedback;
	private static String username;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String name) {
		username = name;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetFeedback window = new GetFeedback();
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
	public GetFeedback() {
		initialize();
		readMajorFeedBack();
		readMinorFeedBack();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 500);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setBackground(Color.PINK);
		lblMajorFeedback = new JLabel("Major Feedback");
		lblMajorFeedback.setBounds(33, 21, 147, 26);

		lblMinorFeedback = new JLabel("Minor Feedback");
		lblMinorFeedback.setBounds(337, 21, 147, 26);

		JButton acceptButton = new JButton("ACCEPT");
		acceptButton.setBounds(200, 250, 100, 30);
//		acceptButton.setLocation(150, 150);
		frame.add(acceptButton);
		acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileOutputStream outputStream;
				try {
					String newFilePath = "submissions/approved/" + username + ".txt";
					String line = null;
					// FileReader reads text files in the default encoding.
					FileReader fileReader = new FileReader("submissions/reviewed/" + username + "_reviewed.txt");
					// Always wrap FileReader in BufferedReader.
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					BufferedWriter Writer = new BufferedWriter(new FileWriter(new File(newFilePath)));
					while ((line = bufferedReader.readLine()) != null) {
						Writer.write(line);
						Writer.newLine();
					}
					Writer.close();
					bufferedReader.close();
					File deleteFile = new File("submissions/reviewed/" + username + "_reviewed.txt");
					deleteFile.delete();
					frame.dispose();
				} catch (FileNotFoundException ex) {
//					System.out.println("Unable to open file '" + filePath + "'");
					ex.printStackTrace();
				} catch (IOException ex) {
//					System.out.println("Error reading file '" + filePath + "'");
				}
			}
		});
	}

	private void readMajorFeedBack() {
		JTextArea textArea = new JTextArea();
		JPanel panel = new JPanel();
		panel.setBounds(21, 50, 153, 340);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.setLayout(new BorderLayout());
		textArea.setEditable(false);
		panel.add(scroll);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblMajorFeedback);
		frame.getContentPane().add(lblMinorFeedback);
		frame.getContentPane().add(panel);
		try {
			FileReader reader = new FileReader("feedback/" + username + "_Major.txt");
			BufferedReader br = new BufferedReader(reader);
			textArea.read(br, null);
			br.close();
			textArea.requestFocus();
		} catch (Exception e) {
		}
	}

	private void readMinorFeedBack() {

		JTextArea textArea_1 = new JTextArea();
		JPanel panel1 = new JPanel();
		panel1.setBounds(331, 50, 153, 340);
		JScrollPane scroll1 = new JScrollPane(textArea_1);
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel1.setLayout(new BorderLayout());
		textArea_1.setEditable(false);
		panel1.add(scroll1);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblMajorFeedback);
		frame.getContentPane().add(lblMinorFeedback);
		frame.getContentPane().add(panel1);
		try {
			FileReader reader = new FileReader("feedback/" + username + "_Minor.txt");
			BufferedReader br = new BufferedReader(reader);
			textArea_1.read(br, null);
			br.close();
			textArea_1.requestFocus();
		} catch (Exception e) {
		}

	}
}
