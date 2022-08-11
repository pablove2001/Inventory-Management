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

public class PanelProducts extends JFrame implements ActionListener {

	private final int PANEL_WIDTH = 300, PANEL_HEIGHT = 189;
	int idUser;

	JPanel jpPanel;
	JButton jbReturn, jbAddNewProduct, jbEditProduct, jbDeleteProduct;
	JLabel jlTitle;

	private PanelProducts(int idUser) {
		this.idUser = idUser;

		// Create JPanel
		jpPanel = new JPanel();
		jpPanel.setLayout(null);
		add(jpPanel);
		setTitle("PanelProducts");
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
		jlTitle = new JLabel("Products");
		jlTitle.setBounds(100, 20, 200, 45); 
		jlTitle.setFont(font2);
		jpPanel.add(jlTitle);

		// Create JButton
		jbReturn = new JButton("Return");
		jbReturn.setFont(font3);
		jbReturn.setBounds(0, 0, 100, 20);
		jbReturn.addActionListener(this);
		jpPanel.add(jbReturn);

		jbAddNewProduct = new JButton("Add a New Product");
		jbAddNewProduct.setFont(font3);
		jbAddNewProduct.setBounds(0, 60, PANEL_WIDTH-16, 30);
		jbAddNewProduct.addActionListener(this);
		jpPanel.add(jbAddNewProduct);

		jbDeleteProduct = new JButton("Delete a Product");
		jbDeleteProduct.setFont(font3);
		jbDeleteProduct.setBounds(0, 90, PANEL_WIDTH-16, 30);
		jbDeleteProduct.addActionListener(this);
		jpPanel.add(jbDeleteProduct);

		jbEditProduct = new JButton("Edit a Product");
		jbEditProduct.setFont(font3);
		jbEditProduct.setBounds(0, 120, PANEL_WIDTH-16, 30);
		jbEditProduct.addActionListener(this);
		jpPanel.add(jbEditProduct);
	}

	static public void createPanelProducts(int accountType) {
		new PanelProducts(accountType);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbReturn) {
			// PanelMenu	
			PanelMenu.createPanelMenu(idUser);
			dispose();
		}
		if (e.getSource() == jbAddNewProduct) {
			// PanelAddNewProduct
			PanelAddNewProduct.createPanelAddNewProduct(idUser);
			dispose();
		}		
		if (e.getSource() == jbDeleteProduct) {
			// PanelDeleteProduct
			PanelDeleteProduct.createPanelDeleteProduct(idUser);
			dispose();
		}
		if (e.getSource() == jbEditProduct) {
			// PanelAddNewProduct
			PanelEditProduct.createPanelEditProduct(idUser);
			dispose();
		}
	}

}
