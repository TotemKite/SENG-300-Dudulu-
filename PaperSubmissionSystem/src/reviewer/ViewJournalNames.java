package reviewer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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

public class ViewJournalNames {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewJournalNames window = new ViewJournalNames();
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
	public ViewJournalNames() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.PINK);
		DefaultListModel<String> model = new DefaultListModel<>();
		
		JPanel panel = new JPanel(new CardLayout());
		
		JLabel lblListOfAll = new JLabel("List of all Journals");
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
		panel.setLayout(new CardLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "List Panel");
		
		CardLayout cl = (CardLayout)(panel.getLayout());
		
		JList list = new JList(model);
		File folder = new File("src/journals");
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
				panel.add(panel_2, "Journal Panel");
//				panel_2 = readJournal(listOfFiles[list.locationToIndex(e.getPoint())].getName());
				System.out.println(listOfFiles[list.locationToIndex(e.getPoint())].getName());
				cl.show(panel, "Journal Panel");
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
	
	public JPanel readJournal(String name) {
		JPanel panel = new JPanel();
		JTextArea txt = new JTextArea(30, 30);
		JScrollPane scroll = new JScrollPane(txt);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.setLayout(new BorderLayout());
        txt.setEditable(false);
//		panel.add(txt);
		panel.add(scroll);
		try {
			FileReader reader = new FileReader( "src/journals/" + name);
			BufferedReader br = new BufferedReader(reader);
			txt.read(br, null);
			br.close();
			txt.requestFocus();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return panel;
	}
}
