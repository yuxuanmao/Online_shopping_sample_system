package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

public class showCategory extends JFrame{
	private JPanel viewPanel, products;
	private JButton back;
	private BackHandler backhandler;
	private Database database;
	private int id;
	private int product_panel_x = 1200;
	private int product_panel_y = 1000;
	private int item_x = 700;
	private int item_y = 100;
	private int scroll_x =1300;
	private int scroll_y = 650;
	private int increment = 260;
	
	public showCategory(Database database, int id){
		this.database = database;
		this.id = id;
		
		viewPanel = new JPanel();
		products = new JPanel();
		products.setPreferredSize(new Dimension(product_panel_x, product_panel_y));
		
		back = new JButton("Back");
		backhandler = new BackHandler();
		back.addActionListener(backhandler);
		
		for(int code : database.inv.CategoryList.keySet()){
			Category category = database.inv.CategoryList.get(code);
			
			int i =  code;
			String name = category.getName();
			String description = category.getDescription();
			JLabel label1 = new JLabel("<html><p style = \"color:red; font-size:12px; font-style:italic\">" + name + "</p>" + "<br/>Code: " + code + "<br/>Description: " + description + ".</html>");
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
