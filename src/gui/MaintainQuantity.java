package gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.Inventory;
import project.Database;
import project.DistributionCenter;
import project.Product;

public class MaintainQuantity extends JFrame {
	private JPanel panel;
	private JLabel prodLabel,dcLabel,quantityLabel;
	private JTextField dcName,prodId,quantity;
	private GridLayout gLayout;
	private MaintainQListener maintainqListener;
	private JButton m_quantity;
	
	protected Database database;
	protected int sessionid;
	private int productID;
	private String distributionName;
	private int quant;
	
	public MaintainQuantity(Database database, int id){
		super();
		gLayout = new GridLayout(5,2);
		this.setLayout(gLayout);
		JPanel panel;
		FlowLayout pLayout = new FlowLayout(FlowLayout.LEFT);
		
		this.database =database;
		this.sessionid = id;
		
		prodLabel = new JLabel("product id: ");
		dcLabel = new JLabel("distribution center Name: ");
		quantityLabel = new JLabel("change quantity to: ");
		dcName = new JTextField(20);
		prodId = new JTextField(20);
		quantity = new JTextField(20);
		
		m_quantity = new JButton("save change");
		maintainqListener = new MaintainQListener();
		m_quantity.addActionListener(maintainqListener);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(prodLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(prodId);
		this.add(panel);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(dcLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(dcName);
		this.add(panel);		
		panel = new JPanel();
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(quantityLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(quantity);
		this.add(panel);
		
		
		panel.add(m_quantity);
		this.add(panel);
		
	}
	
	
	private class MaintainQListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0){
			try{
			productID = Integer.parseInt(prodId.getText());
			distributionName = dcName.getText();
			quant = Integer.parseInt(quantity.getText());
			}catch(NumberFormatException e){
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(panel, "Check input number format", "Error", JOptionPane.ERROR_MESSAGE);
			}
			for(DistributionCenter dc : database.inv.ProductSet.get(productID).mapOfCenter.keySet())
				System.out.println("maintain Quantity DC: " + dc.toString());
			try {
				boolean b = database.updateQuantity(productID, distributionName, quant, sessionid);
				if(b)
					dispose();
				else
					JOptionPane.showMessageDialog(panel, "Check input", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (RuntimeException  e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(panel, "Check input", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
			
			
		}
	}
	

	

}
