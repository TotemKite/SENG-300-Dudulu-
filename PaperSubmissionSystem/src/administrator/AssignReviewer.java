package administrator;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import java.awt.CardLayout;
import java.awt.Color;

public class AssignReviewer {

	private JFrame frame;
	private static String name;

	/**
	 * Launch the application.
	 */
	public static void assign(String fileName) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					name = fileName.substring(19, fileName.length()-4-7);
					String filePath = name + ".txt";
					System.out.println(filePath);
					AssignReviewer window = new AssignReviewer(filePath);
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
	public AssignReviewer(String filePath) {
		initialize(filePath);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String filePath) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setBackground(Color.PINK);
		DefaultListModel<String> model = new DefaultListModel<>();
		System.out.println("filepath:" + filePath);
		JPanel panel = new JPanel();
		
		JLabel lblListOfAll = new JLabel("List of all Reviwers");
		lblListOfAll.setForeground(UIManager.getColor("Button.select"));
		
//		 txt = new JTextArea(30, 30);
//		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblListOfAll)))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListOfAll)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
//		panel.setLayout();
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "List Panel");
				
		JList list = new JList(model);

		
		boolean flag = false;
		
		String line = null;
		while (!flag) {
			try {
				File folder = new File("ci");
				File[] listOfFiles = folder.listFiles();
				boolean conflict = false;

				for(File f:listOfFiles) {
					if(f.getName().equals(filePath)) {
						conflict = true;
						filePath = "ci/" + name + ".txt";
						break;
					}
				}
				if(!conflict) {
					filePath = "resources/reviewers.txt";
				}
				// FileReader reads text files in the default encoding.
				FileReader fileReader = new FileReader(filePath);

				// Always wrap FileReader in BufferedReader.
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				while ((line = bufferedReader.readLine()) != null) {
					if(conflict) {
						model.addElement(line);
					} else{
						String[] token = line.split(";");
						model.addElement(token[0]);
					}

				}
				bufferedReader.close();
			} catch (FileNotFoundException ex) {
				
			} catch (IOException ex) {
				System.out.println("Error reading file '" + filePath + "'");
			}
//			list.setModel(model);
			flag = true;
		}

		
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String reviewer = model.getElementAt(list.locationToIndex(e.getPoint()));
				String newFilePath = "submissions/pending/" + name+"_" +reviewer + "_pending.txt";
				try {
					String line = null;
					// FileReader reads text files in the default encoding.
					FileReader fileReader = new FileReader("submissions/unread/"+name+"_unread.txt");
					// Always wrap FileReader in BufferedReader.
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					BufferedWriter Writer = new BufferedWriter(new FileWriter(new File(newFilePath)));
					while ((line = bufferedReader.readLine()) != null) {
						Writer.write(line);
						Writer.newLine();
					}
					Writer.close();
					bufferedReader.close();
				} catch (FileNotFoundException ex) {
//					System.out.println("Unable to open file '" + filePath + "'");
					ex.printStackTrace();
				} catch (IOException ex) {
//					System.out.println("Error reading file '" + filePath + "'");
				}
				
				File deleteFile = new File("submissions/unread/"+name+"_unread.txt");
				deleteFile.delete();
				frame.dispose();
			}
		});
//		panel.add(list);
		JScrollPane scroll = new JScrollPane(list);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel_1.setLayout(new BorderLayout());
//        txt.setEditable(false);
//		panel.add(txt);
		panel_1.add(scroll);

		frame.getContentPane().setLayout(groupLayout);
		

	}
	
}