package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import project.Database;
import project.LinkedItem;
import project.Shopper;
import project.ShoppingCart;

public class shoppingcartpage extends JFrame {
	private Shopper shopper;
	private JFrame J;
	private JButton checkout, cleancart;
	private JButton displaycart,delete, decrease, up;
	private JPanel p,p2;
	private JLabel L;
	private ArrayList<LinkedItem> productLists ;
	private ShoppingCart sc;
	private Database data;
	
	public shoppingcartpage(Database database,ShoppingCart s){
		this.productLists = s.getlist();
		this.shopper = s.getsp();
		data = database;
		sc = s;
		gui();
	}
	public void gui(){
		J = new JFrame("your shopping cart");
		J.setVisible(true);
		J.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		J.setSize(500, 500);
		p = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		p.setBackground(Color.DARK_GRAY);
		p2 = new JPanel(new GridBagLayout());
		p2.setBackground(Color.WHITE);
		checkout = new JButton("checkout");
		checkout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Invoice(data,sc);
				//sc.checkout();
			}
		}
			);
		c.gridx = 0;
		c.gridy = 0;
		p.add(checkout, c);
		cleancart = new JButton("cleancart");
		cleancart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				productLists.clear();
				JOptionPane.showMessageDialog(p, "shoppingcart clean!", "!!!", JOptionPane.ERROR_MESSAGE);
			}
		});
		c.gridx = 0;
		c.gridy = 1;
		p.add(cleancart, c);
		int i = 1;
		if (productLists.size()> 0) {
			for(LinkedItem l:productLists){
				if (!(l == null)){
					L = new JLabel("name :" + l.getp().getName() +"  price :"+ l.getp().getPrice()+ ", quantity :"  + l.getq() );
					c.gridx= 0;
					c.gridy =i;
					p2.add(L,c);
					JButton delete = new JButton("delete");
					delete.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							productLists.remove(sc.findIndex(l.getp(), productLists));
							new shoppingcartpage(data,sc);

							
						}
					});
					c.gridx = 1 ;
					c.gridy = i;
					p2.add(delete, c);
					
					JButton increase = new JButton("increase");
					increase.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if(l.getp().getQty() > 0){
								int order  =shopper.shoppingCart.add(l.getp(), 1);
								new shoppingcartpage(data,sc);
								J.dispose();
							}else{
								JOptionPane.showMessageDialog(p2, "cant add more !!!", "sold out!", JOptionPane.ERROR_MESSAGE);
							}
							
						}
					});
					c.gridx = 2;
					c.gridy = i;
					p2.add(increase,c);
					
					JButton decrease = new JButton("decrease");
					decrease.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if(l.getq() > 1){
								shopper.shoppingCart.remove(l.getp(), 1);
							}else{
								productLists.remove(sc.findIndex(l.getp(), productLists));
							}
							new shoppingcartpage(data,sc);
							J.dispose();
							
						}
					});
					c.gridx = 3;
					c.gridy = i;
					p2.add(decrease,c);

				}
				else{
					System.out.println(1);

					L = new JLabel("those are all the items");
					c.gridx = 0;
					c.gridy = i;
					p2.add(L,c);
				}
				i ++;
			}
		}
		else {
			L = new JLabel("No items in shoppingcart");
			c.gridx = 0;
			c.gridy = 0;
			p2.add(L,c);
			}
		J.add(p2, BorderLayout.CENTER);
		J.add(p, BorderLayout.WEST);;
		JMenuBar mb = new JMenuBar();
		JMenu back = new JMenu("back");
		JMenuItem backtoshopper = new JMenuItem("backtoshopper");
		back.add(backtoshopper);
		backtoshopper.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				shopperpage s = new shopperpage(data,sc.getsp());
				J.dispose();
			}
		});
		mb.add(back);
		J.setJMenuBar(mb);
	}
}