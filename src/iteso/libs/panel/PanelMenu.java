package iteso.libs.panel;

import iteso.libs.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelMenu extends JFrame implements ActionListener {

	private final int PANEL_WIDTH = 300, PANEL_HEIGHT = 249;
	int idUser;

	JPanel jpPanel;
	JButton jbReturn, jbSettings, jbInputsInventory, jbOutputsInventory, jbProducts, jbInventory, jbRecord, jbAccountManager;
	JLabel jlTitle;

	private PanelMenu(int idUser) {
		this.idUser = idUser;

		// Create JPanel
		jpPanel = new JPanel();
		jpPanel.setLayout(null);
		add(jpPanel);
		setTitle("PanelMenu");
		setSize(PANEL_WIDTH, PANEL_HEIGHT);
		setLocation(250, 250);
		setVisible(true);		
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		// Create Font
		Font font2 = new Font("Arial", 1, 20);
		Font font3 = new Font("Arial", 1, 15);

		// Create JButton
		jbReturn = new JButton("Return");
		jbReturn.setFont(font3);
		jbReturn.setBounds(0, 0, 100, 20);
		jbReturn.addActionListener(this);
		jpPanel.add(jbReturn);

		jbSettings = new JButton("Settings");
		jbSettings.setFont(font3);
		jbSettings.setBounds(PANEL_WIDTH-110-16, 0, 110, 20);
		jbSettings.addActionListener(this);
		jpPanel.add(jbSettings);

		jbInputsInventory = new JButton("Inputs Inventory");
		jbInputsInventory.setFont(font3);
		jbInputsInventory.setBounds(0, 60, PANEL_WIDTH-16, 30);
		jbInputsInventory.addActionListener(this);
		jpPanel.add(jbInputsInventory);

		jbOutputsInventory = new JButton("Outputs Inventory");
		jbOutputsInventory.setFont(font3);
		jbOutputsInventory.setBounds(0, 90, PANEL_WIDTH-16, 30);
		jbOutputsInventory.addActionListener(this);
		jpPanel.add(jbOutputsInventory);

		jbProducts = new JButton("Products");
		jbProducts.setFont(font3);
		jbProducts.setBounds(0, 120, PANEL_WIDTH-16, 30);
		jbProducts.addActionListener(this);
		jpPanel.add(jbProducts);

		jbInventory = new JButton("Inventory");
		jbInventory.setFont(font3);
		jbInventory.setBounds(0, 150, PANEL_WIDTH-16, 30);
		jbInventory.addActionListener(this);
		jpPanel.add(jbInventory);

		jbRecord = new JButton("Record");
		jbRecord.setFont(font3);
		jbRecord.setBounds(0, 180, PANEL_WIDTH-16, 30);
		jbRecord.addActionListener(this);
		jpPanel.add(jbRecord);

		//jbAccountManager
		if (Users.idToTypeUser(idUser) != 2) {
			jbAccountManager = new JButton("Accounts Manager");
			jbAccountManager.setFont(font3);
			jbAccountManager.setBounds(0, 210, PANEL_WIDTH-16, 30);
			jbAccountManager.addActionListener(this);
			jpPanel.add(jbAccountManager);
			setSize(PANEL_WIDTH, PANEL_HEIGHT+30);			
		}

		// Create JLabel
		switch (Users.idToTypeUser(idUser)) {
		case 0: 
			jlTitle = new JLabel("SuperAdmin menu"); 
			jlTitle.setBounds(56, 20, 200, 45); 
			break;
		case 1: 
			jlTitle = new JLabel("Admin menu");
			jlTitle.setBounds(85, 20, 200, 45); 
			break;
		case 2: 
			jlTitle = new JLabel("Employee menu"); 
			jlTitle.setBounds(70, 20, 200, 45); 
			break;
		}		
		jlTitle.setFont(font2);
		jpPanel.add(jlTitle);
	}

	static public void createPanelMenu(int accountType) {
		new PanelMenu(accountType);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbReturn) {
			// PanelLogIn	
			PanelLogIn.createPanelLogIn();
			dispose();
		}
		if (e.getSource() == jbSettings) {
			// PanelSettings
			PanelSettings.createPanelSettings(idUser);
			dispose();
		}
		if (e.getSource() == jbInputsInventory) {
			// PanelInventoryManagement
			PanelInputsInventory.createPanelInputsInventory(idUser);
			dispose();
		}
		if (e.getSource() == jbOutputsInventory) {
			// PanelInventoryManagement
			PanelOutputsInventory.createPanelOutputsInventory(idUser);
			dispose();
		}
		if (e.getSource() == jbProducts) {
			// PanelProducts	
			PanelProducts.createPanelProducts(idUser);
			dispose();
		}
		if (e.getSource() == jbInventory) {
			// PanelInventory
			PanelInventory.createPanelInventory(idUser);
			dispose();
		}
		if (e.getSource() == jbRecord) {
			// PanelRecord
			PanelRecord.createPanelRecord(idUser);
			dispose();
		}
		if (e.getSource() == jbAccountManager) {
			// PanelAccountManager
			PanelAccountsManager.createPanelAccountsManager(idUser);
			dispose();
		}


	}
}

