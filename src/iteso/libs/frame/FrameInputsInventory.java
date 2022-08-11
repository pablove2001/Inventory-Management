package iteso.libs.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FrameInputsInventory extends JFrame implements ActionListener {

	private final int PANEL_WIDTH = 500, PANEL_HEIGHT = 475;

	JPanel jpPanel;
	public JFrame jfFrame;
	JTable jtTable;
	JScrollPane jspScrollPane;
	JButton jbReturn;

	public FrameInputsInventory(String [][] inputsInventory) {
		
		// Create JPanel
		jpPanel = new JPanel();
		
		// Create JFrame
		jfFrame = new JFrame();
		
		// Create JTable
		String[] header = {"Name", "Quantity"};
		jtTable = new JTable(inputsInventory, header){
			public boolean isCellEditable(int row, int column) { 
				return false;             
			}
		};
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for (int i = 0; i<2; i++) jtTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
		
		// Create JScrollPane
		jspScrollPane = new JScrollPane(jtTable);		
		jpPanel.add(jspScrollPane);
		jfFrame.add(jpPanel);
		jfFrame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
		jfFrame.setResizable(false);
		jfFrame.setVisible(true);
		jfFrame.setTitle("FrameInputsOutputs");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		jfFrame.setLocation(dim.width/2-jfFrame.getSize().width/2, dim.height/2-jfFrame.getSize().height/2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {}

}

