package administrator;

import java.lang.String;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JTextArea;

import javafx.geometry.Orientation;


public class AdminViewer {
	String temp;
	String test;
	File saving;
	File viewing;
	JFrame frame;
	JTextArea ta;
	JFileChooser fc;
	JFrame fileframe;
	String pathToFile;
	String pathtoDest;
	JScrollPane scroller;
	String[] instructions;

	/**
	 * Launch the application.
	 */
	public static void renderFile(String filepath, String destpath) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminViewer window = new AdminViewer(filepath, destpath);
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
	public AdminViewer(String filepath, String destpath) throws IOException {
		this.pathToFile = filepath;
		this.pathtoDest = destpath;
		this.viewing = new File(filepath);
		this.saving = new File(destpath);
		if (this.pathToFile.contains("unread")) {
			this.instructions = new String[] {
	             "Once you open the File System Dialog \n",
	             "enter one of the following filenames:\n",
	             "        1. \'001-unread.txt\'\n",
	             "        2. \'002-unread.txt\'\n",
	             "        3. \'003-unread.txt\'\n",
	             "        4. \'004-unread.txt\'\n",
	             "        5. \'005-unread.txt\'\n",
	             "        6. \'006-unread.txt\'\n"
			};
		} else if (this.pathToFile.contains("pending")) {
			this.instructions = new String[] {
	             "Once you open the File System Dialog \n",
	             "enter one of the following filenames:\n",
	             "        1. \'001-pending.txt\'\n",
	             "        2. \'002-pending.txt\'\n",
	             "        3. \'003-pending.txt\'\n",
	             "        4. \'004-pending.txt\'\n",
	             "        5. \'005-pending.txt\'\n",
	             "        6. \'006-pending.txt\'\n"
			};
		} else if (this.pathToFile.contains("reviewed")) {
			this.instructions = new String[] {
	             "Once you open the File System Dialog \n",
	             "enter one of the following filenames:\n",
	             "        1. \'001-reviewed.txt\'\n",
	             "        2. \'002-reviewed.txt\'\n",
	             "        3. \'003-reviewed.txt\'\n",
	             "        4. \'004-reviewed.txt\'\n",
	             "        5. \'005-reviewed.txt\'\n",
	             "        6. \'006-reviewed.txt\'\n"
			};
		} else if (this.pathToFile.contains("approved")) {
			this.instructions = new String[] {
	             "Once you open the File System Dialog \n",
	             "enter one of the following filenames:\n",
	             "        1. \'001-approved.txt\'\n",
	             "        2. \'002-approved.txt\'\n",
	             "        3. \'003-approved.txt\'\n",
	             "        4. \'004-approved.txt\'\n",
	             "        5. \'005-approved.txt\'\n",
	             "        6. \'006-approved.txt\'\n"
			};
		} else {
			this.instructions = new String[] {
	             "Once you open the File System Dialog \n",
	             "navigate to your project directory \n",
	             "via the File System or entering the \n",
	             "path to the desired file for viewing."
			};
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(50, 50, 300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Administrator File Viewer");
		fc = new JFileChooser();
		fileframe = new JFrame();
		fileframe.setBounds(50, 50, 300, 600);
		fileframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ta = new JTextArea(20, 40);
		ta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scroller = new JScrollPane(ta);
		JButton readButton = new JButton("Open File");
		JButton saveButton = new JButton("Save File");
		JList<String> list = new JList<>(this.instructions);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(8);
		list.setAlignmentX(50);
		list.setBackground(Color.LIGHT_GRAY);
		list.setForeground(Color.BLACK);
	    readButton.addActionListener(ev -> {
	      int returnVal = fc.showOpenDialog(fileframe);
	      if (returnVal == JFileChooser.APPROVE_OPTION) {
	        fc.setCurrentDirectory(new java.io.File("txt"));
	        fc.setDialogTitle(this.pathToFile);
	        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        try {
	        	BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(this.viewing)));
	          	ta.read(input, this.viewing);
	          	this.temp = ta.getText();
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	      } else {
	        System.out.println("This reading session has ended");
	      }
	    });
	    saveButton.addActionListener(ev -> {
	        try {
	          	this.test = ta.getText();
	          	BufferedWriter replace = new BufferedWriter(new FileWriter(this.viewing));
	          	BufferedWriter output = new BufferedWriter(new FileWriter(this.saving));
	          	if (!this.test.contentEquals(this.temp)) {
	          		ta.write(output);
	          		ta.setText("");
	          		ta.write(replace);
	          	} else {
	          		System.out.println("No changes were made to the file.");
	          	}
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	    });
	    frame.getContentPane().add(list, BorderLayout.PAGE_START);
	    frame.getContentPane().add(scroller, BorderLayout.CENTER);
	    frame.getContentPane().add(readButton, BorderLayout.EAST);
	    frame.getContentPane().add(saveButton, BorderLayout.WEST);
	    frame.pack();
	    frame.setVisible(true);
	}
}
