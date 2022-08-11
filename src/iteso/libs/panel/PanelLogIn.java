package iteso.libs.panel;
import iteso.libs.Users;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class PanelLogIn extends JFrame implements ActionListener {

	private final int PANEL_WIDTH = 400, PANEL_HEIGHT = 300;

	JPanel jpPanel;
	JLabel jlTitle, jlUserName, jlPassword, jlError;
	JTextField jtfUserName;
	JButton jbLogIn;
	JCheckBox jcbShowPassword;
	JPasswordField jpfPassword;

	private PanelLogIn() {

		// Create JPanel
		jpPanel = new JPanel();
		jpPanel.setLayout(null);
		add(jpPanel);
		setTitle("PanelLogIn");
		setSize(PANEL_WIDTH, PANEL_HEIGHT);
		setVisible(true);		
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		// Create Font
		Font font1 = new Font("Arial", 1, 35);
		Font font2 = new Font("Arial", 1, 20);
		Font font3 = new Font("Arial", 1, 15);
		Font font4 = new Font("Arial", 0, 20);
		Font font5 = new Font("Arial", 0, 15);

		// Create JLabel
		jlTitle = new JLabel("Log In");
		jlTitle.setFont(font1);
		jlTitle.setBounds(149, 15, 102, 45);
		jpPanel.add(jlTitle);

		jlUserName = new JLabel("User Name:");
		jlUserName.setFont(font2);
		jlUserName.setBounds(20, 75, 110, 30);
		jpPanel.add(jlUserName);

		jlPassword = new JLabel("Password:");
		jlPassword.setFont(font2);
		jlPassword.setBounds(30, 115, 100, 30);
		jpPanel.add(jlPassword);

		jlError = new JLabel("Error");
		jlError.setFont(font3);
		jlError.setForeground(Color.red);
		jlError.setBounds(140, 185, 200, 30);
		jlError.setVisible(false);
		jpPanel.add(jlError);		

		// Create JButton
		jbLogIn = new JButton("Login");
		jbLogIn.setFont(font3);
		jbLogIn.setBounds(160, 230, 80, 20);
		jbLogIn.addActionListener(this);
		jpPanel.add(jbLogIn);

		// Create JTextField
		jtfUserName = new JTextField();
		jtfUserName.setFont(font4);
		jtfUserName.setBounds(135, 75, 225, 30);
		jpPanel.add(jtfUserName);

		// Create JPasswordField
		jpfPassword = new JPasswordField();
		jpfPassword.setFont(font4);
		jpfPassword.setEchoChar('*');	
		jpfPassword.setBounds(135, 115, 225, 30);
		jpPanel.add(jpfPassword);

		// Create JCheckBox
		jcbShowPassword = new JCheckBox("Show Password");
		jcbShowPassword.setFont(font5);
		jcbShowPassword.setBounds(135, 150, 225, 30);
		jcbShowPassword.addActionListener(this);
		jpPanel.add(jcbShowPassword);

	}

	static public void createPanelLogIn() {
		new PanelLogIn();
	}

	private void logIn() throws NumberFormatException, UnsupportedEncodingException {
		if (jtfUserName.getText().length() <= 0) {
			jlError.setText("Username is empty");
			jlError.setVisible(true);
		}
		else if (String.valueOf(jpfPassword.getPassword()).length() <= 0) {
			jlError.setText("Password is empty");
			jlError.setVisible(true);
		}
		else {
			int idUser = Users.loginValidation(jtfUserName.getText(), String.valueOf(jpfPassword.getPassword()));
			if (idUser == -1) {
				jlError.setText("Username does not exist");
				jlError.setVisible(true);
			}
			else if (idUser == -2) {
				jlError.setText("Incorrect password");
				jlError.setVisible(true);
			}
			else {
				PanelMenu.createPanelMenu(idUser);
				dispose();
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbLogIn) {
			try {
				logIn();
			} catch (NumberFormatException | UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		}

		if (jcbShowPassword.isSelected())
			jpfPassword.setEchoChar((char)0);
		else
			jpfPassword.setEchoChar('*');

	}

}
