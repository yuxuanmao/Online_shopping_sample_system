package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import project.Database;
import project.Product;



public class Admin extends JFrame {
	private JMenuBar bar;
	private CagListener caglistener;
	private ProListener prolistener;
	private CenListener cenlistener;
	private QuanListener quanlistener;
	private ShipListener shiplistener;	
	private SaleListener salelistener;
	private LogoutListener logoutlistener;
	private StoreListener storelistener;
	private CatListener catlistener;
	private DCListener dclistener;
	private CityListener citylistener;
	private JMenu InventoryMenu, SalesMenu,logoutMenu;
    private JMenuItem cagItem, productItem, centerItem,quantityItem,shipItem;
    private JMenuItem saleItem,logoutItem;
    private JButton showproducts, showCategory, showDC, showCity;
    private JPanel panel, panel2;
    private JLabel label;
    

    private boolean loggedOut;
    protected int sessionid;
    protected Database database;
    private GridLayout gLayout;
    
    
    
	public Admin(int sessionid,Database database) throws IOException {
		super();
		this.database = database;
		this.sessionid = sessionid;
		
		gLayout = new GridLayout(5,2);
		this.setLayout(gLayout);
		JPanel panel;
		//FlowLayout pLayout = new FlowLayout(FlowLayout.CENTER);
		panel = new JPanel(new GridBagLayout());
		panel2 = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		panel2.setBackground(Color.DARK_GRAY);
		
		label = new JLabel("Hello");
		panel.add(label);
		
		
		bar = new JMenuBar();
		setJMenuBar(bar);
		InventoryMenu = new JMenu("Make Change to Inventory");
		bar.add(InventoryMenu);
		SalesMenu = new JMenu("Sales Reports");
		bar.add(SalesMenu);
		logoutMenu = new JMenu("Log out");
		bar.add(logoutMenu);
		
		
		cagItem = new JMenuItem("add Category");
		cagItem.setMnemonic('c');
		InventoryMenu.add(cagItem);
		caglistener = new CagListener();
		cagItem.addActionListener(caglistener);
		
		productItem = new JMenuItem("add Product");
		productItem.setMnemonic('p');
		InventoryMenu.add(productItem);
		prolistener = new ProListener();
		productItem.addActionListener(prolistener);
		
		centerItem = new JMenuItem("add Distribution Center");
		centerItem.setMnemonic('e');
		InventoryMenu.add(centerItem);
		cenlistener = new CenListener();
		centerItem.addActionListener(cenlistener);
		
		quantityItem = new JMenuItem("add Quantity to specified WareHouse");
		quantityItem.setMnemonic('q');
		InventoryMenu.add(quantityItem);
		quanlistener = new QuanListener();
		quantityItem.addActionListener(quanlistener);
		
		shipItem = new JMenuItem("add Route Between Cities");
		shipItem.setMnemonic('s');
		InventoryMenu.add(shipItem);
		shiplistener = new ShipListener();
		shipItem.addActionListener(shiplistener);
		
		saleItem = new JMenuItem("Sales Reports");
		saleItem.setMnemonic('a');
		SalesMenu.add(saleItem);
		salelistener = new SaleListener();
		saleItem.addActionListener(salelistener);

		logoutItem = new JMenuItem("Log Out");
		logoutItem.setMnemonic(KeyEvent.VK_T);
		logoutItem.setEnabled(true);
		logoutMenu.add(logoutItem);
		logoutlistener = new LogoutListener();
		logoutItem.addActionListener(logoutlistener);
		loggedOut = false;
		
		c.gridx = 0;
		c.gridx = 0;
		showproducts = new JButton("showproducts");
		panel2.add(showproducts);
		storelistener = new StoreListener();
		showproducts.addActionListener(storelistener);
		
		showCategory = new JButton("Show Category");
		panel2.add(showCategory);
		catlistener = new CatListener();
		showCategory.addActionListener(catlistener);
		
		showDC = new JButton("Show Distrution Center");
		panel2.add(showDC);
		dclistener = new DCListener();
		showDC.addActionListener(dclistener);
		
		showCity = new JButton("Show City");
		panel2.add(showCity);
		citylistener = new CityListener();
		showCity.addActionListener(citylistener);
		
		this.add(panel2, BorderLayout.SOUTH);
		this.add(panel, BorderLayout.CENTER);
		
	}
	
	private class CityListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent a){
			ShowCity sci = new ShowCity(database, sessionid);
			sci.setSize(800,800);
			sci.setVisible(true);
			dispose();
		}
	}
	
	private class DCListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent a){
			ShowDC dc = new ShowDC(database,sessionid);
			dc.setSize(800,800);
			dc.setVisible(true);
			dispose();
		}
	}
	
	private class CatListener implements ActionListener{
		@Override 
		public void actionPerformed(ActionEvent a){
			System.out.println(database.inv.CategoryList.size());
			showCategory sc = new showCategory(database, sessionid);
			sc.setSize(800,800);
			sc.setVisible(true);
			dispose();
		}
	}
	
	
	
	
	public boolean hasLoggedOut() {
		return loggedOut;
	}
	
	private class CagListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0){
			AddCategory addcag = new AddCategory(database, sessionid);
			
			addcag.setSize(600, 400);
			addcag.setVisible(true);
		}
	}
	
	private class ProListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0){
			AddProduct addpro = new AddProduct(database, sessionid);
			
			addpro.setSize(600, 400);
			addpro.setVisible(true);
		}
	}
	
	private class CenListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0){
			AddCenter addcen = new AddCenter(database, sessionid);
			addcen.setSize(600, 400);
			addcen.setVisible(true);
		}
	}
	
	private class QuanListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0){
			try{
			MaintainQuantity mq = new MaintainQuantity(database, sessionid);
			
			mq.setSize(600, 400);
			mq.setVisible(true);
			}catch(RuntimeException e){
				JOptionPane.showMessageDialog(panel, "Check input", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ShipListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0){
			MaintainShipping ms = new MaintainShipping(database, sessionid);
			
			ms.setSize(600, 400);
			ms.setVisible(true);
			
		}
	}	
	
	private class SaleListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0){
			SalesReportPage srp = new SalesReportPage(database, sessionid);
			
			srp.setSize(600, 400);
			srp.setVisible(true);
		}
	}	
	
	private class LogoutListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0){
				logoutItem.setEnabled(false);
				loggedOut = true;
				Store store;
				try {
					database.logOut(sessionid);
					store = new Store(database);
					store.a = null;
					store.setSize(800, 800);
					store.setVisible(true);
					dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(panel, "Try again", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	
	private class StoreListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0){
				Store store;
				try {
					store = new Store(database, sessionid);
					store.setSize(800, 800);
					store.setVisible(true);
					dispose();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(panel, "Check all the information again", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}

			}

	}
	}



	




