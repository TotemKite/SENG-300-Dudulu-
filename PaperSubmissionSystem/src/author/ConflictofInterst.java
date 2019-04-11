package author;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JButton;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ConflictofInterst {

	private JFrame frame;
	private String ReviewersInfoPath = "resources/reviewers.txt";
	private boolean flag = false;
	private static String ConflictofinterstPath = "ci/";
	private static String username;
	/**
	 * Launch the application.
	 */
	public static void main(String name) {
		username = name;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConflictofInterst window = new ConflictofInterst();
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
	public ConflictofInterst() {
		initialize();
	}

	public static void savetotxt(JList d, String filename) {

		try {

			FileWriter filer = new FileWriter(ConflictofinterstPath + filename + ".txt");
			PrintWriter out = new PrintWriter(filer);
			for (int i = 0; i < d.getModel().getSize(); i++) {

				Object item = d.getModel().getElementAt(i);

				out.println(item);

			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
/*	private static void savetotxt(JList d,String filename) {
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(ConflictofinterstPath + filename + ".txt", true);
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
	}*/

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 500);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setBackground(Color.PINK);
		JList list = new JList();
		list.setBounds(44, 57, 149, 322);

		JList list_1 = new JList();
		list_1.setBounds(340, 57, 149, 322);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(list);
		frame.getContentPane().add(list_1);

		JButton btnGet = new JButton("Get");
		btnGet.setBounds(235, 105, 59, 25);
		DefaultListModel dlm = new DefaultListModel();
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String line = null;
				while (!flag) {
					try {
						// FileReader reads text files in the default encoding.
						FileReader fileReader = new FileReader(ReviewersInfoPath);

						// Always wrap FileReader in BufferedReader.
						BufferedReader bufferedReader = new BufferedReader(fileReader);

						while ((line = bufferedReader.readLine()) != null) {
							String[] token = line.split(";");
							dlm.addElement(token[0]);
						}
						bufferedReader.close();
					} catch (FileNotFoundException ex) {
						System.out.println("Unable to open file '" + ReviewersInfoPath + "'");
					} catch (IOException ex) {
						System.out.println("Error reading file '" + ReviewersInfoPath + "'");
					}
					list.setModel(dlm);
					flag = true;
				}
			}
		});

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(235, 201, 61, 25);
		DefaultListModel dlm2 = new DefaultListModel();
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlm2.addElement(list.getSelectedValue());
				dlm.removeElement(list.getSelectedValue());
				list_1.setModel(dlm2);
			}
		});
		frame.getContentPane().add(btnGet);
		frame.getContentPane().add(btnAdd);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename = username;
				savetotxt(list_1,filename);
				frame.dispose();
				
			}
		});
		btnNewButton.setBounds(214, 409, 117, 25);
		frame.getContentPane().add(btnNewButton);
		

	}
}
