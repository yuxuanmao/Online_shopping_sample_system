package gui;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.Administrator;
import project.Database;
import project.Inventory;
import project.Product;
import project.Shopper;

public class Login extends JFrame {
	private String email;
	private String password;
	private Database database;
	
	
	private JPanel panel;
	private JLabel emailLabel, pswLabel, excLabel;
	private JTextField emailText, pswText;
	private GridLayout gLayout;
	private ButtonHandler handler;
	private BackHandler backHandler;
	private JButton login, back;
	
	
	public Login(Database database){
		super("Log in");
		gLayout = new GridLayout(5,2);
		this.setLayout(gLayout);
		JPanel panel;
		FlowLayout pLayout = new FlowLayout(FlowLayout.LEFT);
		this.database = database;
		
		emailLabel = new JLabel("Enter email");
		pswLabel = new JLabel("Enter correct password");
		excLabel = new JLabel("");
		
		emailText = new JTextField(20);
		pswText = new JTextField(20);
		
		login = new JButton("Log in");
		handler = new ButtonHandler();
		login.addActionListener(handler);
		
		back = new JButton("Back");
		backHandler = new BackHandler();
		back.addActionListener(backHandler);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(emailLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(emailText);
		this.add(panel);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(pswLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(pswText);
		this.add(panel);
		
		panel = new JPanel();
		panel.add(login);
		this.add(panel);
		
		panel = new JPanel();
		panel.add(back);
		this.add(panel);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(excLabel);
		this.add(panel);
		
		
	}
	
	private class BackHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent action){
			//close current window
			//change title to FrontPage
			try{
				Store f= new Store(database);
			f.setSize(800, 800);
			f.setVisible(true);
			dispose();}
			 catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(panel, "Store is not availble now, come back later", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	private class ButtonHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent a){
			email = emailText.getText();
			password = pswText.getText();
			try {
				int id = database.log_in(email, password);
				if(id>0){
					String s = database.checkType(email);
					if(s.equals("Shopper")){
						Shopper s2 = null;
						for(int id1 : database.shoppers.keySet()){
							if(id1 == id){
								s2 = database.shoppers.get(id1);
							}
						}
						if(s2.mail.equals(email)){
							Shopper shopper = s2;
							shopperpage w = new shopperpage(database, shopper);
							dispose();
						}
					}
					if(s.equals("Administrator")){
						Administrator s2 = null;
						for(int id1 : database.administrators.keySet()){
							if(id1 == id){
								s2 = database.administrators.get(id1);
							}
						}
						if( s2.email.equals(email)){
							Admin w =new Admin(id, database);
							w.setSize(800,800);
							w.setVisible(true);
							dispose();
							}
					}
				}

			} catch(Exception e){
				JOptionPane.showMessageDialog(panel, "Check your information again", "Error", JOptionPane.ERROR_MESSAGE);
			}
			 //catch (FileNotFoundException e) {
				//JOptionPane.showMessageDialog(panel, "Check your information again", "Error", JOptionPane.ERROR_MESSAGE);
			//	e.printStackTrace();
			//}
		}
	}
}