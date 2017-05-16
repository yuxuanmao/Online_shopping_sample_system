package gui;

import project.Category;
import project.Database;
import project.Inventory;
import project.Product;

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

public class AddProduct extends JFrame{

	private String name;
	private double price;
	private String description;
	private Category category;
	private int categoryid;
	private int quantity;
	private String imagePath;
	private List<Category> catlist;
	protected Database database;
	private Admin admin;
	private int sessionid;
	
	private JPanel panel;
	private JLabel nameLabel,priceLabel,descriptionLabel,categoryLabel,quantityLabel,imagePathLabel;
	private JTextField nameText,priceText,descriptionText,categoryText,quantityText,imagePathText;
	private GridLayout gLayout;
	private AddproListener addproListener;
	private JButton addproduct;
	
	public AddProduct(Database database,int id){
		super();
		this.sessionid = id;
		gLayout = new GridLayout(7,2);
		this.setLayout(gLayout);
		JPanel panel;
		FlowLayout pLayout = new FlowLayout(FlowLayout.CENTER);
		this.database = database;
		
		nameLabel = new JLabel("Enter Product Name: ");
		priceLabel = new JLabel("Enter Product Price: "); 
		descriptionLabel = new JLabel("Enter Product description: ");
		categoryLabel = new JLabel("Enter Product Category: ");
		quantityLabel = new JLabel("Enter Product Quantity: ");
		imagePathLabel = new JLabel("Enter Product image path: ");
		
		nameText = new JTextField(20);
		priceText = new JTextField(20);
		descriptionText = new JTextField(20);
		categoryText = new JTextField(20);
		quantityText = new JTextField(20);
		imagePathText = new JTextField(20);
		
		addproduct = new JButton("add product");
		addproListener = new AddproListener();
		addproduct.addActionListener(addproListener);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(nameLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(nameText);
		this.add(panel);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(priceLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(priceText);
		this.add(panel);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(descriptionLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(descriptionText);
		this.add(panel);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(categoryLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(categoryText);
		this.add(panel);
		
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(quantityLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(quantityText);
		this.add(panel);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(imagePathLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(imagePathText);
		this.add(panel);
		
		panel = new JPanel();
		panel.add(addproduct);
		this.add(panel);
		
	}
	
	
	private class AddproListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0){
			
			try{
			name = nameText.getText();
			price = Double.parseDouble(priceText.getText());
			description = descriptionText.getText();
			categoryid = Integer.parseInt(categoryText.getText());
			quantity = Integer.parseInt(quantityText.getText());
			imagePath = imagePathText.getText();

			
			int i = database.addProduct(name, description, imagePath, quantity, categoryid, price, sessionid);
			//System.out.println("add product check");
			if(i >=0){
			dispose();
			}
			else{
				JOptionPane.showMessageDialog(panel, "Check the input", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(panel, "Check the input", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	}
	