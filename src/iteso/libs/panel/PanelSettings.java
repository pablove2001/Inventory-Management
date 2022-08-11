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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.io.UnsupportedEncodingException;

public class PanelSettings extends JFrame implements ActionListener {

	private final int PANEL_WIDTH = 450, PANEL_HEIGHT = 315;
	int idUser;

	JPanel jpPanel;
	JButton jbReturn, jbReset, jbAccept;
	JLabel jlTitle, jlUserName, jlName, jlOldPassword, jlNewPassword, jlConfirmPassword, jlStatus;
	JPasswordField jpfOldPassword, jpfNewPassword, jpfConfirmPassword;
	JCheckBox jcbShowOldPassword, jcbShowNewPassword, jcbShowConfirmPassword;

	private PanelSettings(int idUser) {
		this.idUser = idUser;

		// Create JPanel
		jpPanel = new JPanel();
		jpPanel.setLayout(null);
		add(jpPanel);
		setTitle("PanelSettings");
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
		jlTitle = new JLabel("Change Password");
		jlTitle.setFont(font2);
		jlTitle.setBounds(130, 30, 300, 23);
		jpPanel.add(jlTitle);

		jlUserName = new JLabel("User Name:");
		jlUserName.setFont(font5);
		jlUserName.setBounds(25, 60, 300, 23);
		jpPanel.add(jlUserName);

		jlName = new JLabel(Users.getUserName(idUser));
		jlName.setFont(font5);
		jlName.setBounds(170, 60, 300, 23);
		jpPanel.add(jlName);

		jlOldPassword = new JLabel("Old Password:");
		jlOldPassword.setFont(font5);
		jlOldPassword.setBounds(25, 90, 300, 23);
		jpPanel.add(jlOldPassword);

		jlNewPassword = new JLabel("New Password:");
		jlNewPassword.setFont(font5);
		jlNewPassword.setBounds(25, 120, 300, 23);
		jpPanel.add(jlNewPassword);

		jlConfirmPassword = new JLabel("Confirm Password:");
		jlConfirmPassword.setFont(font5);
		jlConfirmPassword.setBounds(25, 150, 300, 23);
		jpPanel.add(jlConfirmPassword);

		jlStatus = new JLabel("Status");
		jlStatus.setVisible(false);
		jlStatus.setFont(font5);
		jlStatus.setBounds(25, 190, 400, 23);
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
		jbAccept.setBounds(165, 240, 100, 20);
		jbAccept.addActionListener(this);
		jpPanel.add(jbAccept);

		// Create JPasswordField
		jpfOldPassword = new JPasswordField();
		jpfOldPassword.setFont(font5);
		jpfOldPassword.setEchoChar('*');	
		jpfOldPassword.setBounds(170, 90, 180, 23);
		jpPanel.add(jpfOldPassword);

		jpfNewPassword = new JPasswordField();
		jpfNewPassword.setFont(font5);
		jpfNewPassword.setEchoChar('*');	
		jpfNewPassword.setBounds(170, 120, 180, 23);
		jpPanel.add(jpfNewPassword);

		jpfConfirmPassword = new JPasswordField();
		jpfConfirmPassword.setFont(font5);
		jpfConfirmPassword.setEchoChar('*');	
		jpfConfirmPassword.setBounds(170, 150, 180, 23);
		jpPanel.add(jpfConfirmPassword);

		// Create JCheckBox (-_-)/(•_•)
		jcbShowOldPassword = new JCheckBox("(-_-)");
		jcbShowOldPassword.setFont(font5);
		jcbShowOldPassword.setBounds(355, 90, 180, 23);
		jcbShowOldPassword.addActionListener(this);
		jpPanel.add(jcbShowOldPassword);

		jcbShowNewPassword = new JCheckBox("(-_-)");
		jcbShowNewPassword.setFont(font5);
		jcbShowNewPassword.setBounds(355, 120, 180, 23);
		jcbShowNewPassword.addActionListener(this);
		jpPanel.add(jcbShowNewPassword);

		jcbShowConfirmPassword = new JCheckBox("(-_-)");
		jcbShowConfirmPassword.setFont(font5);
		jcbShowConfirmPassword.setBounds(355, 150, 180, 23);
		jcbShowConfirmPassword.addActionListener(this);
		jpPanel.add(jcbShowConfirmPassword);

	}

	static public void createPanelSettings(int accountType) {
		new PanelSettings(accountType);
	}

	private boolean validateOldPassword() throws UnsupportedEncodingException {
		jlStatus.setForeground(Color.red);
		
		if (String.valueOf(jpfOldPassword.getPassword()).length() <= 0) {
			jlStatus.setText("Old Password is empty");
			jlStatus.setVisible(true);
			return false;
		}
		
		if (!Users.validatePassword(idUser, String.valueOf(jpfOldPassword.getPassword()))) {
			jlStatus.setText("Incorrect Old Password");
			jlStatus.setVisible(true);
			return false;
		}
		
		return true;
	}

	private boolean validateNewPassword() {
		if (String.valueOf(jpfNewPassword.getPassword()).length() <= 0) {
			jlStatus.setText("New Password is empty");
			jlStatus.setVisible(true);
			return false;
		}
		
		if (String.valueOf(jpfNewPassword.getPassword()).length() < 8) {
			jlStatus.setText("In the New Password minimum 8 characters");
			jlStatus.setVisible(true);
			return false;
		}

		return true;
	}

	private boolean validateConfirmPassword() {
		if (String.valueOf(jpfConfirmPassword.getPassword()).length() <= 0) {
			jlStatus.setText("Confirm Password is empty");
			jlStatus.setVisible(true);
			return false;
		}
		
		if (!String.valueOf(jpfConfirmPassword.getPassword()).equals(String.valueOf(jpfNewPassword.getPassword()))) {
			jlStatus.setText("Confirm Password is not the same");
			jlStatus.setVisible(true);
			return false;
		}

		return true;
	}

	private void resetPanel() {
		jpfOldPassword.setText("");
		jpfNewPassword.setText("");
		jpfConfirmPassword.setText("");
		jlStatus.setVisible(false);
		jlStatus.setForeground(Color.red);

		jcbShowOldPassword.setSelected(false);
		jcbShowNewPassword.setSelected(false);
		jcbShowConfirmPassword.setSelected(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbReturn) {
			// PanelProducts
			PanelMenu.createPanelMenu(idUser);
			dispose();
		}
		if (e.getSource() == jbReset) {
			// Reset
			resetPanel();
		}		
		if (e.getSource() == jbAccept) {
			// Validate
			jlStatus.setForeground(Color.red);
			try {
				if (validateOldPassword())
					if(validateNewPassword())
						if(validateConfirmPassword()) {
							try {
								Users.changePassword(idUser, String.valueOf(jpfNewPassword.getPassword()));
							} catch (UnsupportedEncodingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}						
							
							resetPanel();
							jlStatus.setVisible(true);
							jlStatus.setForeground(new Color(0, 102, 0));
							jlStatus.setText("Correct input");
						}
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		if (jcbShowOldPassword.isSelected()) {
			jpfOldPassword.setEchoChar((char)0);
			jcbShowOldPassword.setText("(•_•)");
		}			
		else{
			jpfOldPassword.setEchoChar('*');
			jcbShowOldPassword.setText("(-_-)");
		}

		if (jcbShowNewPassword.isSelected()) {
			jpfNewPassword.setEchoChar((char)0);
			jcbShowNewPassword.setText("(•_•)");
		}
		else {
			jpfNewPassword.setEchoChar('*');
			jcbShowNewPassword.setText("(-_-)");
		}

		if (jcbShowConfirmPassword.isSelected()) {
			jpfConfirmPassword.setEchoChar((char)0);
			jcbShowConfirmPassword.setText("(•_•)");
		}
		else {
			jpfConfirmPassword.setEchoChar('*');
			jcbShowConfirmPassword.setText("(-_-)");
		}

	}

}
