package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import project.Database;
import project.Inventory;
import project.Shopper;;
public class shopperpage extends JFrame {
	private JFrame J;
	private JButton shoppingcart, orderhistorypage;
	private JButton showproducts;
	private JPanel p,p2;
	private JLabel L;
	private Shopper shopper;
	private Database data = new Database();
	
	public shopperpage(Database database,Shopper s){
		this.data = database;
		shopper = s;
		gui();}
	public void gui(){
		J = new JFrame("Shopper page");
		J.setVisible(true);
		J.setSize(600, 500);
		J.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		p.setBackground(Color.DARK_GRAY);
		showproducts = new JButton("showproducts");
		showproducts.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					try {
						Store store = new Store(data,shopper.getSessionID());
						store.setSize(800, 800);
						store.setVisible(true);
						J.dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		}
			);
		c.gridx = 0;
		c.gridy = 0;
		p.add(showproducts,c);
		
		
		shoppingcart = new JButton("shoppingcart");
		shoppingcart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					new shoppingcartpage(data,shopper.getsc());
					J.dispose();
			}
		}
			);
		c.gridx = 0;
		c.gridy = 2;
		p.add(shoppingcart,c);
		
		
		
		orderhistorypage = new JButton("orderhistory");
		orderhistorypage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					new orderhistorypage(data,shopper.gettoh());
					J.dispose();
			}
		}
			);
		c.gridx = 0;
		c.gridy = 1;
		p.add(orderhistorypage,c);
		
		
		L = new JLabel("welcome");
		p2 = new JPanel(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		p2.setBackground(Color.WHITE);
		p2.add(L);
		J.add(p2,BorderLayout.CENTER);
		J.add(p, BorderLayout.WEST);
		JMenuBar mb = new JMenuBar();
		JMenu log = new JMenu("log");
		JMenuItem logout = new JMenuItem("logout");
		log.add(logout);
		logout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					Store s = new Store(data);
					s.s = null;
					s.setSize(800, 800);
					s.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(p, "Check input", "Error", JOptionPane.ERROR_MESSAGE);
				}
				J.dispose();
			}
		});
		mb.add(log);
		J.setJMenuBar(mb);
			
		
	}
/*	public static void main(String[] args){
		Inventory inv = new Inventory("");
		Shopper s1 = new Shopper("","",inv) ;
		new Shopperpage(data,s1);
	}
		*/
	}