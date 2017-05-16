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
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
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
import project.GraphIsFullException;
import project.VertexExistsException;

public class MaintainShipping extends JFrame{
	private int sessionid;
	private Admin admin;
	private String city1;
	private String city2;
	private int distance;
	public Database database;
	
	private JPanel panel;
	private JLabel City1Label, City2Label,DistanceLabel;
	private JTextField City1Text,City2Text,DistanceText;
	private GridLayout gLayout;
	private RouteListener routeListener;
	private JButton addroute;
	
	public MaintainShipping(Database database, int id){
		super();
		this.sessionid = id;
		this.database = database;
		gLayout = new GridLayout(5,2);
		this.setLayout(gLayout);
		JPanel panel;
		FlowLayout pLayout = new FlowLayout(FlowLayout.LEFT);
		
		City1Label = new JLabel("Enter City1 name: ");
		City2Label = new JLabel("Enter City2 name: ");
		DistanceLabel = new JLabel("Enter Distance between two cities: ");
		
		City1Text = new JTextField(20);
		City2Text = new JTextField(20);
		DistanceText = new JTextField(20);
		
		addroute = new JButton("add");
		routeListener = new RouteListener();
		addroute.addActionListener(routeListener);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(City1Label);
		this.add(panel);
		panel = new JPanel();
		panel.add(City1Text);
		this.add(panel);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(City2Label);
		this.add(panel);
		panel = new JPanel();
		panel.add(City2Text);
		this.add(panel);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(DistanceLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(DistanceText);
		this.add(panel);
		
		panel = new JPanel();
		panel.add(addroute);
		this.add(panel);
		
	}
	
	private class RouteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0){
			try{
			city1 = City1Text.getText();
			city2 = City2Text.getText();
			distance = Integer.parseInt(DistanceText.getText());
			}catch(NumberFormatException e){
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(panel, "Check input", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
			
			try {
				//System.out.println(city1);
				//System.out.println(city2);
				//System.out.println(distance);
				//System.out.println(sessionid);
				database.addRoute(city1, city2, distance, sessionid);
				//System.out.println("Maintain shipping check");
				dispose();
			} catch (GraphIsFullException | VertexExistsException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(panel, "Graph is full or the city already exist", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	

}
