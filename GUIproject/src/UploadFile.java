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
	
	SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String datetime = tempDate.format(new java.util.Date());
	private String deadline = "";
	
	
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
		UploadF.setBounds(100, 100, 491, 332);
		UploadF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UploadF.getContentPane().setLayout(null);
		
		openPath = new JTextField();
		openPath.setBounds(107, 75, 245, 21);
		UploadF.getContentPane().add(openPath);
		openPath.setColumns(10);
		
		file = new JLabel("File");
		file.setFont(new Font("Calibri", Font.PLAIN, 17));
		file.setBounds(47, 79, 24, 15);
		UploadF.getContentPane().add(file);
		
		// You can upload either one file or one folder
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		openButton = new JButton("Open...");
		openButton.setFont(new Font("Calibri", Font.PLAIN, 17));
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
		
		openButton.setBounds(362, 74, 103, 23);
		UploadF.getContentPane().add(openButton);
		
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(datetime.compareTo(deadline) <= 0){
					readPath = openPath.getText();
					writePath = "D:"+'\\'+"A2.txt"+"\\";
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
		submitButton.setFont(new Font("Calibri", Font.PLAIN, 16));
		submitButton.setBounds(211, 244, 93, 23);
		UploadF.getContentPane().add(submitButton);
	}
		
	    public int uploadFile(String oldPath, String newPath) {
	        try {
	            int byteSum = 0;
	            int byteRead = 0;
	            File oldfile = new File(oldPath);
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                System.out.println(newPath);
                
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteRead = inStream.read(buffer)) != -1) {
                    byteSum += byteRead; // 字节数 文件大小
                    System.out.println(byteSum);
                    fs.write(buffer, 0, byteRead);
                }
                inStream.close();
                fs.close();
                return 0;
      
	        } catch (Exception e) {
	            System.out.println("复制单个文件操作出错");
	            e.printStackTrace();
	            return -2;
	        }
	    }
    
	    public String readTime(String timeFile){
	    	try {
	    		File filename = new File(timeFile);
	    		InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
	    		BufferedReader br = new BufferedReader(reader);
	    		String line = "";
	    		line = br.readLine();
	    		while(line != null){
	    			line = br.readLine();
	    		}
	    		deadline = line;
	    		br.close();
	    	} catch (Exception e){
	    		e.printStackTrace();
	    	}
			return deadline;
	    }
}
