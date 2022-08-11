package iteso.libs.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import iteso.libs.frame.FrameInventory;
import iteso.libs.frame.FrameShowUsers;

public class PanelShowUsers extends JFrame implements ActionListener {

	private final int PANEL_WIDTH = 300, PANEL_HEIGHT = 185;
	int idUser;

	JPanel jpPanel;
	JLabel jlShowUsers;
	JButton jbReturn, jbReset, jbShowUsers;
	JComboBox jcbShow;

	FrameShowUsers users = null;

	private PanelShowUsers(int idUser) {
		this.idUser = idUser;
		if (users != null) users.jfFrame.setVisible(false);		

		// Create JPanel
		jpPanel = new JPanel();
		jpPanel.setLayout(null);
		add(jpPanel);
		setTitle("PanelInventory");
		setSize(PANEL_WIDTH, PANEL_HEIGHT);
		setLocation(250, 250);
		setVisible(true);		
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		// Create Font
		Font font2 = new Font("Arial", 1, 20);
		Font font3 = new Font("Arial", 1, 15);
		Font font5 = new Font("Arial", 0, 17);

		// Create JLabel
		jlShowUsers = new JLabel("Show Users");
		jlShowUsers.setFont(font2);
		jlShowUsers.setBounds(30, 20, 160, 45);
		jpPanel.add(jlShowUsers);

		// Create JButton
		jbReturn = new JButton("Return");
		jbReturn.setFont(font3);
		jbReturn.setBounds(0, 0, 100, 20);
		jbReturn.addActionListener(this);
		jpPanel.add(jbReturn);

		jbReset = new JButton("Reset");
		jbReset.setFont(font3);
		jbReset.setBounds(PANEL_WIDTH-116, 0, 100, 20);
		jbReset.addActionListener(this);
		jpPanel.add(jbReset);

		jbShowUsers = new JButton("Show Users");
		jbShowUsers.setFont(font3);
		jbShowUsers.setBounds(62, 110, 160, 20);
		jbShowUsers.addActionListener(this);
		jpPanel.add(jbShowUsers);

		// Create JComboBox
		String[] optionOrderBy = { "All", "SuperAdmins", "Admins", "Employee"};
		jcbShow = new JComboBox(optionOrderBy);
		jcbShow.setFont(font5);
		jcbShow.setBounds(25, 60, 130, 23);
		jcbShow.addActionListener(this);
		jcbShow.setVisible(true);
		jpPanel.add(jcbShow);

	}

	static public void createPanelShowUsers(int accountType) {
		new PanelShowUsers(accountType);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbReturn) {
			// PanelAccountsManager	
			PanelAccountsManager.createPanelAccountsManager(idUser);
			if (users != null) users.jfFrame.dispose();	
			dispose();
		}
		if (e.getSource() == jbShowUsers) {
			// ShowUsers
			String type;
			switch (jcbShow.getSelectedIndex()) {
			case 0: type = "all";
			break;
			case 1: type = "superAdmin";
			break;
			case 2: type = "admin";
			break;
			case 3: type = "employee";
			break;
			default: type = "all";
			}

			if (users != null) users.jfFrame.dispose();			
			users = new FrameShowUsers(type);	
			users.jfFrame.setVisible(true);	
		}
		if (e.getSource() == jbReset) {
			// Reset
			jcbShow.setSelectedIndex(0);
		}



	}

}
