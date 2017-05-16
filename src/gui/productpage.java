package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import project.LinkedItem;
import project.Shopper;

public class productpage extends JFrame {
	private JFrame J;
	private JButton sort ;
	private JPanel p;
	private JLabel L;
	private JComboBox co;
	String[] ways = {"incresing price","decresing price", "time"};
	
	public productpage(){
		gui();
	}
	
	public void gui(){
		J = new JFrame("products");
		J.setVisible(true);
		J.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		J.setSize(500, 500);
		p = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		p.setBackground(Color.DARK_GRAY);
		co = new JComboBox(ways);
		c.gridx = 1;
		c.gridy = 0;
		p.add(co,c);
		sort = new JButton("sort");
		sort.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					String s = co.getSelectedItem().toString();
			}
		}
			);

		c.gridx = 0;
		c.gridy = 0;
		p.add(sort, c);
		J.add(p, BorderLayout.WEST);;
	}
	
	
}