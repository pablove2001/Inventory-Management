package iteso.libs.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelAccountsManager extends JFrame implements ActionListener {

	private final int PANEL_WIDTH = 300, PANEL_HEIGHT = 189;
	int idUser;

	JPanel jpPanel;
	JButton jbReturn, jbAddNewUser, jbDeliteUser, jbShowUsers;
	JLabel jlTitle;

	private PanelAccountsManager(int idUser) {
		this.idUser = idUser;

		// Create JPanel
		jpPanel = new JPanel();
		jpPanel.setLayout(null);
		add(jpPanel);
		setTitle("PanelAccountsManager");
		setSize(PANEL_WIDTH, PANEL_HEIGHT);
		setLocation(250, 250);
		setVisible(true);		
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		// Create Font
		Font font2 = new Font("Arial", 1, 20);
		Font font3 = new Font("Arial", 1, 15);

		// Create JLabel	
		jlTitle = new JLabel("Accounts Manager");
		jlTitle.setBounds(60, 20, 400, 45); 
		jlTitle.setFont(font2);
		jpPanel.add(jlTitle);

		// Create JButton
		jbReturn = new JButton("Return");
		jbReturn.setFont(font3);
		jbReturn.setBounds(0, 0, 100, 20);
		jbReturn.addActionListener(this);
		jpPanel.add(jbReturn);

		jbAddNewUser = new JButton("Add a New User");
		jbAddNewUser.setFont(font3);
		jbAddNewUser.setBounds(0, 60, PANEL_WIDTH-16, 30);
		jbAddNewUser.addActionListener(this);
		jpPanel.add(jbAddNewUser);

		jbDeliteUser = new JButton("Delete a User");
		jbDeliteUser.setFont(font3);
		jbDeliteUser.setBounds(0, 90, PANEL_WIDTH-16, 30);
		jbDeliteUser.addActionListener(this);
		jpPanel.add(jbDeliteUser);
		
		jbShowUsers = new JButton("Show Users");
		jbShowUsers.setFont(font3);
		jbShowUsers.setBounds(0, 120, PANEL_WIDTH-16, 30);
		jbShowUsers.addActionListener(this);
		jpPanel.add(jbShowUsers);
	}

	static public void createPanelAccountsManager(int accountType) {
		new PanelAccountsManager(accountType);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbReturn) {
			// PanelMenu	
			PanelMenu.createPanelMenu(idUser);
			dispose();
		}
		if (e.getSource() == jbAddNewUser) {
			// PanelAddNewUser
			PanelAddNewUser.createPanelAddNewUser(idUser);
			dispose();
		}		
		if (e.getSource() == jbDeliteUser) {
			// PanelDeliteUser
			PanelDeleteUser.createPanelDeleteUser(idUser);
			dispose();
		}
		if (e.getSource() == jbShowUsers) {
			// PanelShowUsers
			PanelShowUsers.createPanelShowUsers(idUser);
			dispose();
		}
	}

}
