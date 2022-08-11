package iteso.libs.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import iteso.libs.frame.FrameRecord;
import iteso.libs.frame.FrameSearchRecord;
import iteso.libs.Record;

public class PanelRecord extends JFrame implements ActionListener {

	private final int PANEL_WIDTH = 300, PANEL_HEIGHT = 335;
	int idUser;

	JPanel jpPanel;
	JLabel jlRecord, jlSearch, jlId, jlStatus;
	JButton jbReturn, jbReset, jbShowRecords, jbSearch;
	JRadioButton jrbInput,jrbOutput, jrbAll;
	ButtonGroup bgActiveDeleted;
	JTextField jtfId;

	FrameRecord record = null;
	FrameSearchRecord searchRecord = null;

	private PanelRecord(int idUser) {
		this.idUser = idUser;
		if (record != null) record.jfFrame.setVisible(false);		

		// Create JPanel
		jpPanel = new JPanel();
		jpPanel.setLayout(null);
		add(jpPanel);
		setTitle("PanelRecord");
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
		jlRecord = new JLabel("Record");
		jlRecord.setFont(font2);
		jlRecord.setBounds(110, 20, 130, 45);
		jpPanel.add(jlRecord);

		jlSearch = new JLabel("Search Record");
		jlSearch.setFont(font2);
		jlSearch.setBounds(65, 180, 200, 45);
		jpPanel.add(jlSearch);

		jlId = new JLabel("ID:");
		jlId.setFont(font5);
		jlId.setBounds(30, 210, 130, 45);
		jpPanel.add(jlId);

		jlStatus = new JLabel("Status");
		jlStatus.setVisible(false);
		jlStatus.setForeground(Color.red);
		jlStatus.setFont(font5);
		jlStatus.setBounds(120, 210, 160, 45);
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

		jbShowRecords = new JButton("Show Records");
		jbShowRecords.setFont(font3);
		jbShowRecords.setBounds(42, 150, 200, 20);
		jbShowRecords.addActionListener(this);
		jpPanel.add(jbShowRecords);

		jbSearch = new JButton("Show");
		jbSearch.setFont(font3);
		jbSearch.setBounds(100, 260, 80, 20);
		jbSearch.addActionListener(this);
		jpPanel.add(jbSearch);

		// Create ButtonGroup and JRadioButton
		bgActiveDeleted = new ButtonGroup();

		jrbInput = new JRadioButton("Inputs");
		jrbInput.setFont(font5);
		jrbInput.setBounds(30, 83, 225, 23);
		jrbInput.addActionListener(this);
		bgActiveDeleted.add(jrbInput);
		jpPanel.add(jrbInput);

		jrbOutput = new JRadioButton("Outputs");
		jrbOutput.setFont(font5);
		jrbOutput.setBounds(30, 106, 225, 23);
		jrbOutput.addActionListener(this);
		bgActiveDeleted.add(jrbOutput);
		jpPanel.add(jrbOutput);

		jrbAll = new JRadioButton("All");
		jrbAll.setFont(font5);
		jrbAll.setSelected(true);
		jrbAll.setBounds(30, 60, 225, 23);
		jrbAll.addActionListener(this);
		bgActiveDeleted.add(jrbAll);
		jpPanel.add(jrbAll);

		// JTextField
		jtfId = new JTextField();
		jtfId.setText("");
		jtfId.setFont(font5);
		jtfId.setBounds(60, 222, 50, 23);
		jpPanel.add(jtfId);
	}

	static public void createPanelRecord(int idUser) {
		new PanelRecord(idUser);
	}

	private boolean validateId() {
		if (jtfId.getText().length() <= 0) {
			jlStatus.setVisible(true);
			jlStatus.setText("ID is empty");
			return false;
		}

		try {
			if (Integer.parseInt(jtfId.getText()) < 0) {
				jlStatus.setVisible(true);
				jlStatus.setText("Minimum ID: 1");
				return false;
			}
		}catch(Exception e) {
			jlStatus.setVisible(true);
			jlStatus.setText("ID is not a number");
			return false;
		}
		
		if(!Record.idExists(Integer.parseInt(jtfId.getText()))) {
			jlStatus.setVisible(true);
			jlStatus.setText("Id does not exist");
			return false;
		}
		
		return true;
	}
	
	private void resetPanel() {
		jrbAll.setSelected(true);
		jtfId.setText("");
		jlStatus.setVisible(false);
		if (record != null) record.jfFrame.dispose();	
		if (searchRecord != null) searchRecord.jfFrame.dispose();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbReturn) {
			// PanelMenu	
			PanelMenu.createPanelMenu(idUser);
			resetPanel();
			dispose();
		}
		if (e.getSource() == jbShowRecords) {
			// Show
			if (record != null) record.jfFrame.dispose();

			int show = 2;
			if (jrbInput.isSelected()) show = 0;
			if (jrbOutput.isSelected()) show = 1;

			record = new FrameRecord(show);	
			record.jfFrame.setVisible(true);	
		}
		if (e.getSource() == jbReset) {
			// Reset
			resetPanel();
		}
		
		if (e.getSource() == jbSearch) {
			if (validateId()) {
				if (searchRecord != null) searchRecord.jfFrame.dispose();
				searchRecord = new FrameSearchRecord(Integer.parseInt(jtfId.getText()));	
				searchRecord.jfFrame.setVisible(true);
				jlStatus.setVisible(false);
			}
		}
	}

}
