
package gui;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.border.LineBorder;

import project.Administrator;
import project.Database;
import project.LinkedItem;
import project.Product;
import project.Project;
import project.Shopper;
import project.ShoppingCart;

public class Store extends JFrame{
	private int title_panel_x = 2000;
	private int title_panel_y = 40;
	private int product_panel_x = 1200;
	private int product_panel_y = 1000;
	private int item_x = 800;
	private int item_y = 200;
	private int scroll_x =1300;
	private int scroll_y = 650;
	private int increment = 260;
	
	private JPanel panel;
	private JPanel products;
	private JPanel viewPanel;
	
	private JButton price;
	private JButton name;
	private JButton quantity;
	private JButton category;
	private JButton search; 
	private JButton myPage;
	private Database database;
	private Container contentPane = getContentPane();
	private int numOfProduct = 0;
	private ButtonHandler handler = new ButtonHandler();
	private PageHandler pageHandler;
	private JTextField text;
	private SeacheHandler seachHandler;
	
	public static HashMap<Integer,JButton> addButtons = new HashMap<Integer, JButton>();
	public Shopper s = null;
	public Administrator a = null;
	
	private JMenuBar bar;
	private LoginListener listener;
	private RegistListener Rlistener;
	private JMenu loginMenu,logoutMenu;
    private JMenuItem loginItem, logoutItem, RegistItem;
    private JLabel welcome;
    public boolean loggedIn, loggedOut;
    public int id;
    //private List<JLabel> prodForSale;
	
    public Store(Database database) throws IOException{
    		super("Shopping Store");
    		
    		s = null;
    		a = null;
    		this.database = database;
    		setBounds(100, 100, 300, 250);
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	    setLayout(new FlowLayout());
    	    
    	    panel = new JPanel();
    	    panel.setPreferredSize(new Dimension(title_panel_x, title_panel_y));
    	    panel.setBackground(Color.YELLOW);
    	    
    	    JLabel label = new JLabel("Search ");
    	    text = new JTextField(30);
    	    search = new JButton("Search");
    	    seachHandler = new SeacheHandler();
    	    search.addActionListener(seachHandler);
    	    
    	    //bar
    	    loggedIn = false;
    	    loggedOut = true;
    	    
    	    bar = new JMenuBar();
    	    setJMenuBar(bar);
    	    loginMenu = new JMenu("Login/Registration");
    	    bar.add(loginMenu);
    	    logoutMenu = new JMenu("Logout");
    	    bar.add(logoutMenu);
    	    
    	    loginItem = new JMenuItem("Log In");
    	    loginItem.setMnemonic('I');
    	    loginMenu.add(loginItem);
    	    listener = new LoginListener();
    	    loginItem.addActionListener(listener);
    	    
    	    RegistItem = new JMenuItem("Register");
    	    RegistItem.setMnemonic('R');
    		loginMenu.add(RegistItem);
    		Rlistener = new RegistListener();
    		RegistItem.addActionListener(Rlistener);
    		

    		logoutItem = new JMenuItem("Log Out");
    		logoutItem.setMnemonic(KeyEvent.VK_T);
    		logoutItem.setEnabled(false);
    		logoutMenu.add(logoutItem);
    		
    		
    	    
    	    price= new JButton("Price");
    	    price.addActionListener(handler);
    	    name = new JButton("Name");
    	    name.addActionListener(handler);
    	    quantity = new JButton("Quantity");
    	    quantity.addActionListener(handler);
    	   // category = new JButton("Category");
    	    //category.addActionListener(handler);
    	    JLabel label2 = new JLabel("  Sorting ");
    	    
    	    panel.add(label);
    	    panel.add(text);
    	    panel.add(search);
    	    panel.add(label2);
    	    panel.add(price);
    	    panel.add(name);
    	    panel.add(quantity);
    	   // panel.add(category);
    	   
    	    contentPane.add(panel, BorderLayout.CENTER);
//    	    result = database.inv.sortList("Price");
//    	    showProduct(true);
    	    showProduct(false, null);
    	 }
    
    
	public Store(Database database, int sessionID) throws IOException{
		super("Shopping Store");
		this.database = database;
		this.id = sessionID;
		
		if(database.shoppers.containsKey(sessionID)){
			this.s = database.shoppers.get(sessionID);
	
		}else if(database.administrators.containsKey(sessionID)){
			this.a = database.administrators.get(sessionID);
			
		}
		
		setBounds(100, 100, 300, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new FlowLayout());
	    
	    panel = new JPanel();
	    panel.setPreferredSize(new Dimension(title_panel_x, title_panel_y));
	    panel.setBackground(Color.YELLOW);
	    
	    JLabel label = new JLabel("Search ");
	    text = new JTextField(30);
	    search = new JButton("Search");
	    seachHandler = new SeacheHandler();
	    search.addActionListener(seachHandler);
	    
	    price= new JButton("Price");
	    price.addActionListener(handler);
	    name = new JButton("Name");
	    name.addActionListener(handler);
	    quantity = new JButton("Quantity");
	    quantity.addActionListener(handler);
	   // category = new JButton("Category");
	   // category.addActionListener(handler);
	    JLabel label2 = new JLabel("  Sorting ");
	    myPage = new JButton("My Page");
	    pageHandler = new PageHandler();
	    myPage.addActionListener(pageHandler);
	    
	    panel.add(label);
	    panel.add(text);
	    panel.add(search);
	    panel.add(label2);
	    panel.add(price);
	    panel.add(name);
	    panel.add(quantity);
	   // panel.add(category);
	    panel.add(myPage);
	   
	    contentPane.add(panel, BorderLayout.CENTER);
//	    result = database.inv.sortList("Price");
//	    showProduct(true);
	    showProduct(false, null);
	

	}
	
	private class SeacheHandler implements ActionListener{
		@Override 
		public void actionPerformed(ActionEvent a){
			String t = text.getText();
			TreeSet<Product> result = new TreeSet<Product>();
			result = database.inv.sortList(t);
			try {
				Sorting(result);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(panel, "Key word is wrong, try something else", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	
	
	private class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
		Login login = new Login(database);
		login.setSize(600,400);
		login.setVisible(true);
		loginItem.setEnabled(false);
		logoutItem.setEnabled(true);
		loggedIn = true;
		loggedOut = false;
		dispose();
		}

	}
	private class RegistListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0){
			//registration page
			Registration Register = new Registration(database);
			Register.setSize(600, 600);
			Register.setVisible(true);
			//Register.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			dispose();
		}
	}
	
	
	
	
	
	private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type) throws IOException {  
        BufferedImage resizedImage = new BufferedImage(width, height, type);  
        Graphics2D g = resizedImage.createGraphics();  
        g.drawImage(originalImage, 0, 0, width, height, null);  
        g.dispose();  
        return resizedImage;  
    }  
	
	public void showProduct(boolean sorting,  TreeSet<Product> result) throws IOException{
		
		viewPanel = new JPanel();
		products = new JPanel();
		products.setPreferredSize(new Dimension(product_panel_x, product_panel_y));
		if(!sorting){
			numOfProduct = database.inv.ProductSet.size();
			if(numOfProduct >= 6){
				product_panel_y +=  increment* (numOfProduct - 6);
				products.setPreferredSize(new Dimension(product_panel_x, product_panel_y));
			}
			for(int id : database.inv.ProductSet.keySet()){
				Product item = database.inv.ProductSet.get(id);
				
				String image = item.getImage();
				int code = item.getCode();
				
				BufferedImage img = ImageIO.read(new File(image));
				BufferedImage newImage = resizeImage(img, 200,200, BufferedImage.TYPE_INT_RGB);
				ImageIcon icon = new ImageIcon(newImage);
				
				JLabel label1 = new JLabel("<html><p style = \"color:red; font-size:12px; font-style:italic\">" + item.getName() + "</p>"+ "<br/>Code: "  + code+ "<br/>Price: $"  + item.getPrice() + "<br/>Quantity: " + item.getQty() + "<br/>Description: " + item.getDescription() + ".</html>");
				label1.setIcon(icon); 
				label1.setPreferredSize(new Dimension(item_x,item_y));
				label1.setHorizontalTextPosition(JLabel.RIGHT);
				label1.setVerticalTextPosition(JLabel.CENTER);
	     	    label1.setBorder(new LineBorder(Color.blue, 2, true));
	     	    
	     	    if(s!= null){
			 	    JButton button = new JButton("Add");
			 	    button.addActionListener(handler);
			 	    addButtons.put(code, button);
			 	    
			 	    products.add(label1);
			 	    products.add(button);
	     	    }else{
	     	    	products.add(label1);
	     	    }
			}
		}else{
			for(Product p: result){
				String image = p.getImage();
				int code = p.getCode();
				
				BufferedImage img = ImageIO.read(new File(image));
				BufferedImage newImage = resizeImage(img, 200,200, BufferedImage.TYPE_INT_RGB);
				ImageIcon icon = new ImageIcon(newImage);
				
				JLabel label1 = new JLabel("<html><p style = \"color:red; font-size:12px; font-style:italic\">" + p.getName() + "</p>"+ "<br/>Price: $"  + p.getPrice() + "<br/>Quantity: " + p.getQty() + "<br/>Description: " + p.getDescription() + ".</html>");
				label1.setIcon(icon); 
				label1.setPreferredSize(new Dimension(item_x,item_y));
				label1.setHorizontalTextPosition(JLabel.RIGHT);
				label1.setVerticalTextPosition(JLabel.CENTER);
	     	    label1.setBorder(new LineBorder(Color.blue, 2, true));
	     	    
	     	    if(s!= null){
			 	    JButton button = new JButton("Add");
			 	    button.addActionListener(handler);
			 	    addButtons.put(code, button);
			 	    
			 	    products.add(label1);
			 	    products.add(button);
	     	    }else{
	     	    	products.add(label1);
	     	    }
			}
		}
		
		JScrollPane scrollpane = new JScrollPane(products);
		scrollpane.setPreferredSize(new Dimension(scroll_x, scroll_y));
		JViewport view = new JViewport();
	    view.setView(products);
	    scrollpane.setViewport(view);
	    viewPanel = new JPanel();
	    viewPanel.add(scrollpane);
	    getContentPane().add(viewPanel, BorderLayout.CENTER);
		//contentPane.add(p);
	}
	
	public void Sorting(TreeSet<Product> result) throws IOException{
		
		contentPane.remove(viewPanel);
	    try {
			showProduct(true, result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    contentPane.revalidate();
	    contentPane.repaint();
	}
	
	private class PageHandler implements ActionListener{
		@Override 
		public void actionPerformed(ActionEvent ae){
			if(s!= null){
				shopperpage sp = new shopperpage(database, s);
				dispose();
			}
			else if(a!= null){
				Admin w;
				try {
					w = new Admin(id, database);
					w.setSize(800,800);
					w.setVisible(true);
					dispose();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(panel, "Try Again", "Error", JOptionPane.ERROR_MESSAGE);
				}
			
			}
			else
				JOptionPane.showMessageDialog(panel, "Please Login first", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			String ac = ae.getActionCommand();
			System.out.println(((JButton)(ae.getSource())).getText() + " Clicked !");
			int num = 0;
			if(ae.getSource()  == price){
				TreeSet<Product> result = new TreeSet<Product>();
				result = database.inv.sortList("Price");
				try {
					Sorting(result);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ac.equals("Name")){
				TreeSet<Product> result = new TreeSet<Product>();
				result = database.inv.sortList("Name");
				try {
					Sorting(result);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ac.equals("Quantity")){
				TreeSet<Product> result = new TreeSet<Product>();
				result = database.inv.sortList("Quantity");
				try {
					Sorting(result);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ac.equals("Category")){
				TreeSet<Product> result = new TreeSet<Product>();
				result = database.inv.sortList("Category");
				try {
					Sorting(result);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ac.equals("Add")){
				for(int id1 : addButtons.keySet()){
					JButton b = addButtons.get(id1);
					if(ae.getSource() == b){
						Product item = database.inv.ProductSet.get(id1);
						if(item.getQty() >0){
							int order  = s.shoppingCart.add(item, 1);
							try {
								Store s =new Store(database,id);
								s.setSize(800, 800);
								s.setVisible(true);
								dispose();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else{
							JOptionPane.showMessageDialog(panel, "cant add more !!!", "sold out!", JOptionPane.ERROR_MESSAGE);

						}
	//					System.out.println(order);   Not working
					}	
				}
			}
			
			
		}
}
//	public static void main(String args[]) throws ClassNotFoundException, IOException{
//		Project p = new Project();
//		
//		boolean result;
//		int id_c;
//		result = p.addUser("Doraemon@gmail.com", "BokuDoraemon",  true);
//    	System.out.println(result);
//    	result = p.addUser("kerry.chen@gmail.com", "TokyoGrade", false);
//    	System.out.println(result);
//    	
//    	
//    	
//    	
//    	id_c = p.database.log_in("Doraemon@gmail.com", "BokuDoraemon");
//		System.out.println("sessionID of administrator " + id_c);
//		
//		
//		
//    	
//		int cat_id = p.addCategory("Anime", id_c );
//		System.out.println("Category ID " + cat_id);
//		//addProduct(String prodName, String description, String image, double quantity,  int category, double price, int sessionID) 
//		int pro_id1 = p.addProduct("Product1","The legend of the legendary hero", "product1.jpg", 10, cat_id, 25.34, id_c);
//		System.out.println("ProductID " + pro_id1);
//		int pro_id2 = p.addProduct("Product2","Madomagi Homura is Hentai", "product2.jpg", 10, cat_id, 12.99, id_c);
//		System.out.println("ProductID " + pro_id2);
//		int pro_id3 = p.addProduct("Product3","This is a ninja", "product3.jpg", 10, cat_id, 5.99, id_c);
//		System.out.println("ProductID " + pro_id3);
//		int pro_id4 = p.addProduct("Product4","She is the family leader", "product4.jpg", 10, cat_id, 300.14, id_c);
//		System.out.println("ProductID " + pro_id4);
//		int pro_id5 = p.addProduct("Product5","The legendary knight", "product5.jpg", 10, cat_id, 40.23, id_c);
//		System.out.println("ProductID " + pro_id5);
//		int pro_id6 = p.addProduct("Product6","amazing", "product6.jpg", 10, cat_id, 40.23, id_c);
//		System.out.println("ProductID " + pro_id6);
//		int pro_id7 = p.addProduct("Product7","Robot Girls", "product7.jpg", 10, cat_id, 40.23, id_c);
//		System.out.println("ProductID " + pro_id7);
//		int pro_id8 = p.addProduct("Product8","She is the Legendary Knight", "product8.jpg", 10, cat_id, 40.23, id_c);
//		System.out.println("ProductID " + pro_id8);
//		int pro_id9 = p.addProduct("Product9","She is the Legendary Knight", "product9.jpg", 10, cat_id, 40.23, id_c);
//		System.out.println("ProductID " + pro_id9);
//		int pro_id10 = p.addProduct("Product10","She is the Legendary Knight", "product10.jpg", 10, cat_id, 40.23, id_c);
//		System.out.println("ProductID " + pro_id10);
//		int pro_id11 = p.addProduct("Product11","She is the Legendary Knight", "product11.jpg", 10, cat_id, 40.23, id_c);
//		System.out.println("ProductID " + pro_id11);
//		
//		p.database.logOut(id_c);
//		int id_a; 	
//		id_a = p.login("kerry.chen@gmail.com", "TokyoGrade");
//		System.out.println(id_a);
//		
//		//Store s1 = new Store(p.database,id_c);
//		Store s2 = new Store(p.database,id_a ); 
//	    //s1.setVisible(true);
//		s2.setVisible(true);
//	}
//
//
	public boolean hasLoggedOut() {
		return loggedOut;
	}





	
}