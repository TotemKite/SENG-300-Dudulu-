package administrator;

import java.lang.String;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
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

	public AdminViewer(String filepath, String destpath) throws IOException {
		this.pathToFile = filepath;
		this.pathtoDest = destpath;
		System.out.println(destpath);
		this.viewing = new File(filepath);
		if (this.pathToFile.contains("unread")) {
			File dir = new File("submissions/unread");
			String[] filenames = dir.list();
			int numFiles = filenames.length;
			this.instructions = new String[numFiles+1];
			this.instructions[0] = "Once you open the File System Dialog, enter one of the following filenames:\n";
			for (int i = 1; i < numFiles+1; i++) {
				int fidx = i-1;
				this.instructions[i] = "        " + filenames[fidx] + "\n";
			}
		} else if (this.pathToFile.contains("pending")) {
			File dir = new File("submissions/pending");
			String[] filenames = dir.list();
			int numFiles = filenames.length;
			this.instructions = new String[numFiles+1];
			this.instructions[0] = "Once you open the File System Dialog, enter one of the following filenames:\n";
			for (int i = 1; i < numFiles+1; i++) {
				int fidx = i-1;
				this.instructions[i] = "        " + filenames[fidx] + "\n";
			}
		} else if (this.pathToFile.contains("reviewed")) {
			File dir = new File("submissions/reviewed");
			String[] filenames = dir.list();
			int numFiles = filenames.length;
			this.instructions = new String[numFiles+1];
			this.instructions[0] = "Once you open the File System Dialog, enter one of the following filenames:\n";
			for (int i = 1; i < numFiles+1; i++) {
				int fidx = i-1;
				this.instructions[i] = "        " + filenames[fidx] + "\n";
			}
		} else if (this.pathToFile.contains("approved")) {
			File dir = new File("submissions/approved");
			String[] filenames = dir.list();
			int numFiles = filenames.length;
			this.instructions = new String[numFiles+1];
			this.instructions[0] = "Once you open the File System Dialog, enter one of the following filenames:\n";
			for (int i = 1; i < numFiles+1; i++) {
				int fidx = i-1;
				this.instructions[i] = "        " + filenames[fidx] + "\n";
			}
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
	        fc.setCurrentDirectory(new java.io.File(".txt"));
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
	          	if (!this.test.contentEquals(this.temp)) {
	          		String response = JOptionPane.showInputDialog(
      					null,
      					"Save Changes to File",
      					"New File Name: ",
      					JOptionPane.QUESTION_MESSAGE
  					);
	          		String newSavePath = this.pathtoDest + "/" + response;
	          		this.saving = new File(newSavePath);
	          		BufferedWriter replace = new BufferedWriter(new FileWriter(this.viewing));
	          		BufferedWriter output = new BufferedWriter(new FileWriter(this.saving));
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
