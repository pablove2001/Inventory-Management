package iteso.libs.panel;
import iteso.libs.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.io.UnsupportedEncodingException;

public class PanelAddNewUser extends JFrame implements ActionListener {

	private final int PANEL_WIDTH = 390, PANEL_HEIGHT = 285;
	int idUser;

	JPanel jpPanel;
	JButton jbReturn, jbReset, jbAccept;
	JLabel jlTitle, jlUserName,  jlPassword, jlStatus;
	JTextField jtfUserName;
	JPasswordField jpfPassword;
	JComboBox jcbTypeUser;
	JCheckBox jcbShowPassword;

	private PanelAddNewUser(int idUser) {
		this.idUser = idUser;

		// Create JPanel
		jpPanel = new JPanel();
		jpPanel.setLayout(null);
		add(jpPanel);
		setTitle("PanelAddNewUser");
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
		jlTitle = new JLabel("Add a New User");
		jlTitle.setFont(font2);
		jlTitle.setBounds(110, 30, 300, 23);
		jpPanel.add(jlTitle);

		jlUserName = new JLabel("User Name:");
		jlUserName.setFont(font5);
		jlUserName.setBounds(25, 95, 300, 23);
		jpPanel.add(jlUserName);

		jlPassword = new JLabel("Password:");
		jlPassword.setFont(font5);
		jlPassword.setBounds(25, 125, 300, 23);
		jpPanel.add(jlPassword);

		jlStatus = new JLabel("Status");
		jlStatus.setVisible(false);
		jlStatus.setFont(font5);
		jlStatus.setBounds(25, 165, 400, 23);
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
		jbAccept.setBounds(165, 210, 100, 20);
		jbAccept.addActionListener(this);
		jpPanel.add(jbAccept);

		// Create JTextField
		jtfUserName = new JTextField();
		jtfUserName.setText("");
		jtfUserName.setFont(font5);
		jtfUserName.setBounds(120, 95, 180, 23);
		jpPanel.add(jtfUserName);

		// Create JPasswordField
		jpfPassword = new JPasswordField();
		jpfPassword.setFont(font5);
		jpfPassword.setEchoChar('*');	
		jpfPassword.setBounds(120, 125, 180, 23);
		jpPanel.add(jpfPassword);

		// Create JComboBox
		switch (Users.idToTypeUser(idUser)) {
		case 0: 
			String[] optionTypeUser0 = {"Employee", "Admin"};
			jcbTypeUser = new JComboBox(optionTypeUser0);
			break;
		case 1: 
			String[] optionTypeUser1 = {"Employee"};
			jcbTypeUser = new JComboBox(optionTypeUser1);
			break;
		case 2: 
			jbAccept.setVisible(false);
			break;
		}

		jcbTypeUser.setFont(font5);
		jcbTypeUser.setBounds(25, 65, 100, 23);
		jcbTypeUser.addActionListener(this);
		jcbTypeUser.setVisible(true);
		jpPanel.add(jcbTypeUser);

		// Create JCheckBox (-_-)/(•_•)
		jcbShowPassword = new JCheckBox("(-_-)");
		jcbShowPassword.setFont(font5);
		jcbShowPassword.setBounds(305, 125, 180, 23);
		jcbShowPassword.addActionListener(this);
		jpPanel.add(jcbShowPassword);

	}

	static public void createPanelAddNewUser(int accountType) {
		new PanelAddNewUser(accountType);
	}

	private boolean validateUserName() {
		jlStatus.setForeground(Color.red);

		if (jtfUserName.getText().length() <= 0) {
			jlStatus.setVisible(true);
			jlStatus.setText("User Name is empty");
			return false;
		}

		if (!Users.nameAvailability(jtfUserName.getText())) {
			jlStatus.setVisible(true);
			jlStatus.setText("User Name already exist");
			return false;
		}		

		return true;
	}

	private boolean validatePassword() {
		jlStatus.setForeground(Color.red);

		if (String.valueOf(jpfPassword.getPassword()).length() <= 0) {
			jlStatus.setText("Password is empty");
			jlStatus.setVisible(true);
			return false;
		}

		if (String.valueOf(jpfPassword.getPassword()).length() < 8) {
			jlStatus.setText("Minimum 8 characters in the Password");
			jlStatus.setVisible(true);
			return false;
		}

		return true;
	}

	private void resetPanel() {
		jcbTypeUser.setSelectedIndex(0);
		jtfUserName.setText("");
		jpfPassword.setText("");
		jlStatus.setVisible(false);
		jlStatus.setForeground(Color.red);

		jcbShowPassword.setSelected(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbReturn) {
			// PanelProducts
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
			if (validateUserName())
				if(validatePassword()) {
					String typeUser;
					if (jcbTypeUser.getSelectedIndex() == 0)
						typeUser = "employee";
					else typeUser = "admin";

					String[] str = {"", jtfUserName.getText(), String.valueOf(jpfPassword.getPassword()), typeUser};
					try {
						Users.addNewUser(str);
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}

					resetPanel();
					jlStatus.setVisible(true);
					jlStatus.setForeground(new Color(0, 102, 0));
					jlStatus.setText("Correct input");
				}

		}

		if (jcbShowPassword.isSelected()) {
			jpfPassword.setEchoChar((char)0);
			jcbShowPassword.setText("(•_•)");
		}
		else {
			jpfPassword.setEchoChar('*');
			jcbShowPassword.setText("(-_-)");
		}
	}

}
