package iteso.libs.panel;
import iteso.libs.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelDeleteUser extends JFrame implements ActionListener {

	private final int PANEL_WIDTH = 300, PANEL_HEIGHT = 225;
	int idUser;

	JPanel jpPanel;
	JButton jbReturn, jbReset, jbAccept;
	JLabel jlTitle, jlUserName, jlStatus;
	JTextField jtfUserName;

	private PanelDeleteUser(int idUser) {
		this.idUser = idUser;

		// Create JPanel
		jpPanel = new JPanel();
		jpPanel.setLayout(null);
		add(jpPanel);
		setTitle("PanelDeleteUser");
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
		jlTitle = new JLabel("Delete a User");
		jlTitle.setFont(font2);
		jlTitle.setBounds(30, 30, 300, 23);
		jpPanel.add(jlTitle);

		jlUserName = new JLabel("User Name:");
		jlUserName.setFont(font5);
		jlUserName.setBounds(25, 60, 300, 23);
		jpPanel.add(jlUserName);

		jlStatus = new JLabel("Status");
		jlStatus.setVisible(false);
		jlStatus.setFont(font5);
		jlStatus.setBounds(25, 100, 300, 23);
		jpPanel.add(jlStatus);

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

		jbAccept = new JButton("Accept");
		jbAccept.setFont(font3);
		jbAccept.setBounds(70, 150, 100, 20);
		jbAccept.addActionListener(this);
		jpPanel.add(jbAccept);

		// JTextField
		jtfUserName = new JTextField();
		jtfUserName.setText("");
		jtfUserName.setFont(font5);
		jtfUserName.setBounds(120, 60, 140, 23);
		jpPanel.add(jtfUserName);
	}

	static public void createPanelDeleteUser(int accountType) {
		new PanelDeleteUser(accountType);
	}

	private boolean validateUserName() {
		if (jtfUserName.getText().length() <= 0) {
			jlStatus.setVisible(true);
			jlStatus.setText("User Name is empty");
			return false;
		}

		switch (Users.userCanBeDeleted(idUser, jtfUserName.getText())) {
		case 0: return true;
		case 1:
			jlStatus.setVisible(true);
			jlStatus.setText("User Name does not exists");
			return false;
		case 2:
			jlStatus.setVisible(true);
			jlStatus.setText("You do not have permission");
			return false;
		
		}
		return true;
	}

	private void resetPanel() {
		jtfUserName.setText("");
		jlStatus.setVisible(false);
		jlStatus.setForeground(Color.red);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbReturn) {
			// PanelAccountsManager	
			PanelAccountsManager.createPanelAccountsManager(idUser);
			dispose();
		}
		if (e.getSource() == jbReset) {
			// Reset
			resetPanel();
		}		
		if (e.getSource() == jbAccept) {
			// Validate
			jlStatus.setForeground(Color.red);
			if (validateUserName()) {
				Users.deleteUser(jtfUserName.getText());

				resetPanel();
				jlStatus.setVisible(true);
				jlStatus.setForeground(new Color(0, 102, 0));
				jlStatus.setText("Correct input");
			}
		}

	}

}

