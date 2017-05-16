package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import project.Database;
import project.LinkedItem;
import project.OrderHistory;
import project.Shopper;

public class orderhistorypage extends JFrame {
	private Shopper shopper;
	private JFrame J;
	private JButton sort, cleancart;
	private JPanel p,p2;
	private JLabel L;
	private List<LinkedItem> history = new ArrayList<LinkedItem>();
	private Database data;
	private OrderHistory oh;
	
	
	public orderhistorypage(Database database,OrderHistory o){
		data = database;
		history = o.getoh();
		oh = o;

		gui();
	}
	public void gui(){
		J = new JFrame("your orderhistorypage");
		J.setVisible(true);
		J.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		J.setSize(500, 500);
		p = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		p.setBackground(Color.DARK_GRAY);
		p2 = new JPanel(new GridBagLayout());
		p2.setBackground(Color.WHITE);
		int i = 1;
		for(LinkedItem l:history){
			if (!(l == null)){
				L = new JLabel(l.toString());
				c.gridx= 0;
				c.gridy =i;
				p2.add(L,c);
				i += 1;

			}
			else{
				L = new JLabel("those are all the items");
				c.gridx = 0;
				c.gridy = i;
				p2.add(L,c);
				i += 1;

			}
		}
		J.add(p2, BorderLayout.CENTER);
		J.add(p, BorderLayout.WEST);;
		JMenuBar mb = new JMenuBar();
		JMenu back = new JMenu("back");
		JMenuItem backtoshopper = new JMenuItem("backtoshopper");
		back.add(backtoshopper);
		backtoshopper.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				shopperpage s = new shopperpage(data, oh.shopper);
				J.dispose();
			}
		});
		mb.add(back);
		J.setJMenuBar(mb);
	}
}