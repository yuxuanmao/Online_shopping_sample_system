package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import project.Database;
import project.LinkedItem;
import project.Shopper;
import project.ShoppingCart;

public class Invoice extends JFrame {
	private Shopper shopper;
	private JFrame J;
	private JButton checkout, cleancart;
	private JButton displaycart;
	private JPanel p,p2;
	private JLabel L;
	private ShoppingCart sc;
	private Database data;
	
	public Invoice(Database database,ShoppingCart shoppingcart){
		data = database;
		sc = shoppingcart;
		gui();
	}
	
	public void gui(){
		JFrame J = new JFrame("your invoice");
		J.setVisible(true);
		J.setSize(500, 500);
		p = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Double all = 0.0;
		if (sc.getlist().size() >0){
			int i = 0;
			for(LinkedItem l:sc.getlist()){
				System.out.println("ok");
				Double duo = l.getp().getPrice()* l.getq();
				System.out.println(duo);
				JLabel L = new JLabel("Name  :"+ l.getp().getName()+" ,price  :" +l.getp().getPrice() + " ,quantity  :"+ l.getq()+ "  =  " + duo.toString());
				c.gridx = 0;
				c.gridy = i;
				p.add(L, c);
				all += duo;
				i += 1;
			}
			all += sc.checkout();
			JLabel L = new JLabel("Totol amount is " + all.toString());
			c.gridx = 0;
			c.gridy = i;
			p.add(L,c);
				}
		else{
			JLabel L = new JLabel("nothing");
			c.gridx = 0;
			c.gridy = 0;
			p.add(L,c);
		}
		
		J.add(p, BorderLayout.CENTER);
	}
}