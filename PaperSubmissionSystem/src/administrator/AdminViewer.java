package administrator;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;


public class AdminViewer {
	private JFrame frame;
	File viewing;
	String pathToFile;
	private CardLayout cl = new CardLayout();
	private JPanel basePanel = new JPanel(new CardLayout());

	/**
	 * Launch the application.
	 */
	public static void renderFile(String filepath) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminViewer window = new AdminViewer(filepath);
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
	public AdminViewer(String filepath) throws IOException {
		this.pathToFile = filepath;
		this.viewing = new File(filepath);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 826, 636);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Administrator File Viewer");
		DefaultListModel<String> model = new DefaultListModel<>();
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(basePanel, GroupLayout.PREFERRED_SIZE, 812, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(basePanel, GroupLayout.PREFERRED_SIZE, 595, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		basePanel.setLayout(new CardLayout(0, 0));
//		JPanel wholePanel = readJournal("123");
		JPanel listPanel = new JPanel();
		basePanel.add(listPanel, "List Panel");
//		CardLayout cl = (CardLayout)(basePanel.getLayout());
		cl = (CardLayout)(basePanel.getLayout());
		
		JList list = new JList(model);
		File folder = new File("journal_author");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    System.out.println("File " + listOfFiles[i].getName());
		    model.addElement(listOfFiles[i].getName());
		  } else if (listOfFiles[i].isDirectory()) {
//		    System.out.println("Directory " + listOfFiles[i].getName());
		  }
		}
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				System.out.println(list.locationToIndex(e.getPoint()));
				JPanel panel_2 = readJournal(listOfFiles[list.locationToIndex(e.getPoint())].getName());
				basePanel.add(panel_2, "Journal Panel");
//				panel_2 = readJournal(listOfFiles[list.locationToIndex(e.getPoint())].getName());
				System.out.println(listOfFiles[list.locationToIndex(e.getPoint())].getName());
				cl.show(basePanel, "Journal Panel");
			}
		});
//		panel.add(list);
		JScrollPane scroll = new JScrollPane(list);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        listPanel.setLayout(new BorderLayout());
//        txt.setEditable(false);
//		panel.add(txt);
		listPanel.add(scroll);
		
		
		frame.getContentPane().setLayout(groupLayout);
	}
	
	public JPanel readJournal(String fileName) {
		JPanel wholePanel = new JPanel();
		JLabel lblNewLabel = new JLabel("Hello Administrator!");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 15));
		JTextArea textArea = new JTextArea();
		JPanel panel = new JPanel();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.setLayout(new BorderLayout());
        textArea.setEditable(false);
		panel.add(scroll);
		frame.add(panel);
		try {
			FileReader reader = new FileReader(this.viewing);
			BufferedReader br = new BufferedReader(reader);
			textArea.read(br, null);
			br.close();
			textArea.requestFocus();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		JButton backButton = new JButton("GO BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(basePanel, "List Panel");
			}
		});
		wholePanel.setLayout(new FlowLayout());
		wholePanel.add(panel);
		return wholePanel;
	}
}
