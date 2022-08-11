package iteso.libs.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import iteso.libs.frame.FrameInventory;

public class PanelInventory extends JFrame implements ActionListener {

	private final int PANEL_WIDTH = 300, PANEL_HEIGHT = 345;
	int idUser;

	JPanel jpPanel;
	JLabel jlOrderBy, jlFindWord, jlToFind;
	JButton jbReturn, jbReset, jbShowInventory;
	JComboBox jcbOrderBy, jcbFindWord;
	JRadioButton jrbAscending,jrbDescending;
	ButtonGroup BGbuttonsAscDesc;
	JTextField jtfFindWord;

	FrameInventory inventory = null;

	private PanelInventory(int idUser) {
		this.idUser = idUser;
		if (inventory != null) inventory.jfFrame.setVisible(false);		

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
		jlOrderBy = new JLabel("Order by");
		jlOrderBy.setFont(font2);
		jlOrderBy.setBounds(30, 20, 102, 45);
		jpPanel.add(jlOrderBy);
		
		jlFindWord = new JLabel("Find a product by name");
		jlFindWord.setFont(font2);
		jlFindWord.setBounds(30, 120, 300, 45);
		jpPanel.add(jlFindWord);
		
		jlToFind = new JLabel("Name:");
		jlToFind.setFont(font5);
		jlToFind.setBounds(25, 195, 300, 23);
		jpPanel.add(jlToFind);

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

		jbShowInventory = new JButton("Show Inventory");
		jbShowInventory.setFont(font3);
		jbShowInventory.setBounds(42, 270, 200, 20);
		jbShowInventory.addActionListener(this);
		jpPanel.add(jbShowInventory);

		// Create JComboBox
		String[] optionOrderBy = { "ID", "Name", "Count No.", "Unit Price", "Profit %" };
		jcbOrderBy = new JComboBox(optionOrderBy);
		jcbOrderBy.setFont(font5);
		jcbOrderBy.setBounds(25, 60, 100, 23);
		jcbOrderBy.addActionListener(this);
		jcbOrderBy.setVisible(true);
		jpPanel.add(jcbOrderBy);
		
		String[] optionFindWord = { "Start with", "Contains", "Ends with"};
		jcbFindWord = new JComboBox(optionFindWord);
		jcbFindWord.setFont(font5);
		jcbFindWord.setBounds(25, 160, 100, 23);
		jcbFindWord.addActionListener(this);
		jcbFindWord.setVisible(true);
		jpPanel.add(jcbFindWord);

		// Create ButtonGroup and JRadioButton
		BGbuttonsAscDesc = new ButtonGroup();
		
		jrbAscending = new JRadioButton("Ascending");
		jrbAscending.setSelected(true);
		jrbAscending.setFont(font5);
		jrbAscending.setBounds(150, 60, 225, 23);
		jrbAscending.addActionListener(this);
		BGbuttonsAscDesc.add(jrbAscending);
		jpPanel.add(jrbAscending);
		
		jrbDescending = new JRadioButton("Descending");
		jrbDescending.setFont(font5);
		jrbDescending.setBounds(150, 83, 225, 23);
		jrbDescending.addActionListener(this);
		BGbuttonsAscDesc.add(jrbDescending);
		jpPanel.add(jrbDescending);
		
		// JTextField
		jtfFindWord = new JTextField();
		jtfFindWord.setFont(font5);
		jtfFindWord.setBounds(78, 195, 180, 23);
		jpPanel.add(jtfFindWord);
	}

	static public void createPanelInventory(int accountType) {
		new PanelInventory(accountType);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbReturn) {
			// PanelMenu	
			PanelMenu.createPanelMenu(idUser);
			if (inventory != null) inventory.jfFrame.dispose();	
			dispose();
		}
		if (e.getSource() == jbShowInventory) {
			// ShowInventory
			boolean ascending = false;
			if (jrbAscending.isSelected()) ascending = true;	
			if (inventory != null) inventory.jfFrame.dispose();			
			inventory = new FrameInventory(jcbOrderBy.getSelectedIndex(), ascending, jcbFindWord.getSelectedIndex(), jtfFindWord.getText(), true);	
			inventory.jfFrame.setVisible(true);	
		}
		if (e.getSource() == jbReset) {
			// Reset
			jcbOrderBy.setSelectedIndex(0);
			jrbAscending.setSelected(true);
			jcbFindWord.setSelectedIndex(0);
			jtfFindWord.setText("");
		}



	}

}
