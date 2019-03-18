package author;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class UploadFile {

	private JFrame UploadF;
	private JFileChooser chooser;
	private JTextField openPath;
	private JTextField savePath;
	private String writePath;
	private String readPath;
	private JLabel file;
	private JButton openButton;
	private JButton browseButton;
	private JButton submitButton;
	private String fileName = "";
	
	
	SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String datetime = tempDate.format(new java.util.Date());
	private String deadline = "";
	private String Deadlineinfo = "deadline_info/deadlineforJournal1.txt";
	private String journalpathfornow ="journal_author/" ;
	private JButton btnConflictOfInterest;
	/**
	 * Launch the application.
	 */
	public static void UploadScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UploadFile window = new UploadFile();
					window.UploadF.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UploadFile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		UploadF = new JFrame("Upload");
		UploadF.getContentPane().setBackground(Color.PINK);
		UploadF.setBounds(100, 100, 491, 332);
		UploadF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UploadF.getContentPane().setLayout(null);
		
		openPath = new JTextField();
		openPath.setBounds(87, 122, 245, 21);
		UploadF.getContentPane().add(openPath);
		openPath.setColumns(10);
		
		file = new JLabel("File");
		file.setFont(new Font("Dialog", Font.PLAIN, 18));
		file.setBounds(43, 108, 34, 45);
		UploadF.getContentPane().add(file);
		
		// You can upload either one file or one folder
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		openButton = new JButton("Open...");
		openButton.setFont(new Font("Dialog", Font.PLAIN, 18));
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = chooser.showOpenDialog(null);
				chooser.setDialogType(JFileChooser.OPEN_DIALOG);
				chooser.setMultiSelectionEnabled(false);
				chooser.setAcceptAllFileFilterUsed(false);
				if(index == JFileChooser.APPROVE_OPTION){
					openPath.setText(chooser.getSelectedFile().getAbsolutePath());
					readPath = openPath.getText();
					System.out.println(openPath.getText());
				}
			}
		});
		
		openButton.setBounds(361, 119, 103, 23);
		UploadF.getContentPane().add(openButton);
		
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				// Compare the current time to the deadline to determine whether the user can submit or not
				if(datetime.compareTo(readTime(Deadlineinfo)) <= 0){
					readPath = openPath.getText();
					
					File curFile = new File(readPath.trim());
					fileName = curFile.getName();
					
					writePath = journalpathfornow +fileName+"\\";
					if(uploadFile(readPath, writePath) == -1){
						JOptionPane.showMessageDialog(null, "The file is not existed!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					Success su = new Success();
					su.SuccessfulScreen();
				} else {
					Fail fa = new Fail();
					fa.FailScreen();
				}
			}
		});
		submitButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		submitButton.setBounds(66, 214, 107, 34);
		UploadF.getContentPane().add(submitButton);
		
		btnConflictOfInterest = new JButton("Conflict of Interest");
		btnConflictOfInterest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConflictofInterst.main(null);
			}
		});
		btnConflictOfInterest.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnConflictOfInterest.setBounds(244, 214, 219, 34);
		UploadF.getContentPane().add(btnConflictOfInterest);
	}
		
		// Upload the file to the destination
	    public int uploadFile(String oldPath, String newPath) {
	        try {
	            int byteSum = 0;
	            int byteRead = 0;
	            File oldfile = new File(oldPath);
                InputStream inStream = new FileInputStream(oldPath); // Read the file you want to upload
                System.out.println(newPath);
                
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteRead = inStream.read(buffer)) != -1) {
                    byteSum += byteRead; 
                    System.out.println(byteSum);
                    fs.write(buffer, 0, byteRead);
                }
                inStream.close();
                fs.close();
                return 0;
      
	        } catch (Exception e) {
	            System.out.println("There is something wrong with uploading file!");
	            e.printStackTrace();
	            return -2;
	        }
	    }
    
	    
	    // Read the file to get the deadline
	    public String readTime(String timeFile){
	    	try {
	    		File filename = new File(timeFile);
	    		InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
	    		BufferedReader br = new BufferedReader(reader);
	    		String line = "";
	    		line = br.readLine();
	    		deadline = line;
	    		br.close();
	    	} catch (Exception e){
	    		e.printStackTrace();
	    	}
			return deadline;
	    }
}
