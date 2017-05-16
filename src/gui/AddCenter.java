package gui;

import project.Inventory;
import project.VertexExistsException;
import project.DistributionCenter;
import project.GraphIsFullException;
import project.City;
import project.Database;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddCenter extends JFrame{
	private String cityname;
	//private DistributionCenter dc;
	private int sessionid;
	private Admin admin;
	protected Database database;
	
	private JPanel panel;
	private JLabel cityLabel;
	private JTextField cityText;
	private GridLayout gLayout;
	private AddcenListener addcenListener;
	private JButton addcenter;
	
	public AddCenter(Database database, int id){
		super();
		this.sessionid = id;
		this.database = database;
		gLayout = new GridLayout(5,2);
		this.setLayout(gLayout);
		JPanel panel;
		FlowLayout pLayout = new FlowLayout(FlowLayout.LEFT);
		
		cityLabel = new JLabel("Enter City Name(add this city as distribution center)");
		cityText = new JTextField(20);
		
		addcenter = new JButton("add");
		addcenListener = new AddcenListener();
		addcenter.addActionListener(addcenListener);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(cityLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(cityText);
		this.add(panel);
		
		panel = new JPanel();
		panel.add(addcenter);
		this.add(panel);
		
	}
	
	
	private class AddcenListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0){
			//System.out.println(database.centers.size());
			cityname = cityText.getText();
			try {
				database.addDistributionCenter(cityname, sessionid);
				//System.out.println("add center check");
				//System.out.println(database.centers.size());
				dispose();
			} catch (GraphIsFullException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(panel, "All city is on the map", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (VertexExistsException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(panel, "This city already exist", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	

}