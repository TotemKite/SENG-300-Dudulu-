package reviewer;

import java.awt.EventQueue;

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


public class Reviewer {

	private JFrame frame;
	private String journalpathfornow ="src/test.txt" ;
	
	private CardLayout cl = new CardLayout();
	private JPanel basePanel = new JPanel(new CardLayout());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reviewer window = new Reviewer();
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
	public Reviewer() throws IOException {
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
		frame.setTitle("Reviewer Window");
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

		JTextArea textArea_1 = new JTextArea();
		
		JLabel lblNewLabel = new JLabel("Hello Reviewer!");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 15));

		JButton btnNewButton = new JButton("Submit feedback");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FeedbackUpload fb = new FeedbackUpload();
				fb.getFrame().setVisible(true);
				
				String fbs = textArea_1.getText();
				
				try {
					FileOutputStream out = new FileOutputStream("Feedback/John.txt");
					out.write(fbs.getBytes());
					out.close();
				} catch(FileNotFoundException e){
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		JLabel lblFeedback = new JLabel("Feedback:");
		lblFeedback.setForeground(Color.BLUE);
		lblFeedback.setFont(new Font("Goudy Old Style", Font.PLAIN, 18));



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
			FileReader reader = new FileReader( "journal_author/journal.txt" );
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

//		wholePanel.add(lblNewLabel);
//		wholePanel.add(btnNewButton);
//		wholePanel.add(panel);
		GroupLayout gl_panel = new GroupLayout(wholePanel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(173)
								.addComponent(lblFeedback)
								.addGap(18)
								.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(backButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(98)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 615, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(97, Short.MAX_VALUE))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(54)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblFeedback)
									.addComponent(btnNewButton)))
//							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(42)
								.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))))
			);
		wholePanel.setLayout(gl_panel);
		return wholePanel;
	}
}
