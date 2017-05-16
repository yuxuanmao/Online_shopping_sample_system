package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.LineBorder;

import project.Category;
import project.Database;
import project.DistributionCenter;
import project.Product;

public class ShowDC extends JFrame{
	private JPanel viewPanel, products;
	private JButton back;
	private BackHandler backhandler;
	private Database database;
	private int id;
	private int product_panel_x = 1200;
	private int product_panel_y = 1000;
	private int item_x = 700;
	private int item_y = 200;
	private int scroll_x =1300;
	private int scroll_y = 650;
	private int increment = 260;
	
	public ShowDC(Database database, int id){
		this.database = database;
		this.id = id;
		
		viewPanel = new JPanel();
		products = new JPanel();
		products.setPreferredSize(new Dimension(product_panel_x, product_panel_y));
		
		back = new JButton("Back");
		backhandler = new BackHandler();
		back.addActionListener(backhandler);
		
		System.out.println("showDC database centers: " + database.centers);
		for(String c : database.centers.keySet()){
			DistributionCenter dc = database.centers.get(c);
			String per = "";
			String after = "";
			String howmany = "";
			String ic =  c;
			int name = dc.name;
			String city = dc.getCity();
			int code = dc.getCode();
			List<DistributionCenter> preVertex = dc.getPreVertex();
			for(int i=0; i<preVertex.size();i++)
				per += preVertex.toString();
			List<DistributionCenter> afterVertex = dc.getAfterVertex();
			for(int i=0; i<afterVertex.size();i++)
				after += afterVertex.toString();
			
			for(Integer p: database.inv.ProductSet.keySet()){
				Product product = database.inv.ProductSet.get(p);
				int quantity = product.mapOfCenter.get(dc);
				howmany += product.getName() + ": " + quantity + "\n";
			}
			
			JLabel label1 = new JLabel("<html><p style = \"color:red; font-size:12px; font-style:italic\">" + city + "</p>" + "<br/>Code: " + code  + "<br/>name: " +name + "<br/>Pervious Distribution Center: " + per + "<br/>Next Distribution Center: " + after + "<br/> " + howmany +".</html>");
			label1.setPreferredSize(new Dimension(item_x,item_y));
			label1.setHorizontalTextPosition(JLabel.RIGHT);
			label1.setVerticalTextPosition(JLabel.CENTER);
     	    label1.setBorder(new LineBorder(Color.blue, 2, true));
     	    
     	    products.add(label1);
		}
		
		
		JScrollPane scrollpane = new JScrollPane(products);
		scrollpane.setPreferredSize(new Dimension(scroll_x, scroll_y));
		JViewport view = new JViewport();
	    view.setView(products);
	    scrollpane.setViewport(view);
	    viewPanel = new JPanel();
	    viewPanel.add(scrollpane);
	    viewPanel.add(back);
	    getContentPane().add(viewPanel, BorderLayout.CENTER);
	}
	
	private class BackHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent action){
			//close current window
			//change title to FrontPage
			try{
				Admin f= new Admin(id,database);
			f.setSize(800, 800);
			f.setVisible(true);
			dispose();}
			 catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(viewPanel, "Store is not availble now, come back later", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
