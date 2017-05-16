package gui;

import project.Inventory;
import project.Category;
import project.Database;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddCategory extends JFrame{
	private int id;
	private String name;
	private String description;
	private Database database;
	private int sessionid;
	private Admin admin;
	private Category category;
	
	private JPanel panel;
	private JLabel cagLabel,dpLabel;
	private JTextField cagText,dpText;
	private GridLayout gLayout;
	private AddcagListener addcagListener;
	private JButton addcag;
	
	private List<Category> catlist;
	
	public AddCategory(Database database, int id){
		super();
		this.sessionid = id;
		this.database = database;
		gLayout = new GridLayout(5,2);
		this.setLayout(gLayout);
		JPanel panel;
		FlowLayout pLayout = new FlowLayout(FlowLayout.LEFT);
		
		cagLabel = new JLabel("Enter Category Name");
		dpLabel = new JLabel("Enter Category description");
		
		cagText = new JTextField(20);
		dpText = new JTextField(20);
		
		addcag = new JButton("add");
		addcagListener = new AddcagListener();
		addcag.addActionListener(addcagListener);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(cagLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(cagText);
		this.add(panel);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(dpLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(dpText);
		this.add(panel); 
		
		panel = new JPanel();
		panel.add(addcag);
		this.add(panel);
		
	}
	
	
	private class AddcagListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0){
			name = cagText.getText();
			description = dpText.getText();
			
			int i = database.addCategory(name,description, sessionid);
			if(i>=0){
				dispose();
				}
			else{
				JOptionPane.showMessageDialog(panel, "Check input", "Error", JOptionPane.ERROR_MESSAGE);
			}

			
		}
	}
	

}
