package gui;

import java.awt.Choice;
import java.awt.event.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.Administrator;
import project.Database;
import project.Shopper;

public class Registration extends JFrame{
	private Database database;
	private String name;
	private String email;
	private String password;
	private String address;
	private boolean admin;
	
	private JTextField nameText, emailText, pswText, addressText; //adminText;
	private GridLayout gLayout;
	private JLabel nameLabel, emailLabel, pswLabel, adsLabel, adminLabel,exceptionLabel;
	private ButtonHandler handler;
	private BackHandler backHandler;
	private JButton signin, back;
	private JComboBox<String> userState;
	
	JPanel panel;
	
	
	
	public Registration(Database database){
		super("Registration");
		
		this.database = database;
		
		gLayout = new GridLayout(7,7);
		this.setLayout(gLayout);
		FlowLayout pLayout = new FlowLayout(FlowLayout.LEADING);
		
		nameLabel = new JLabel("Enter your name.");
		emailLabel = new JLabel("Enter your email.");
		emailLabel.setToolTipText("This email will use as your id.");
		pswLabel = new JLabel("Enter your password.");
		adsLabel = new JLabel("Enter your address.");
		adsLabel.setToolTipText("Enter your address, if you not have one now leave it blank");
		adminLabel = new JLabel("Are you an Admin or a Shopper?");
		adminLabel.setToolTipText("Enter A if you're an admin, enter S if you're a shopper.");
		exceptionLabel = new JLabel("");
		
		nameText = new JTextField(20);
		emailText = new JTextField(20);
		pswText = new JTextField(20);		
		addressText = new JTextField(20);
		//adminText = new JTextField(20);
		userState = new JComboBox<String>();
		userState.addItem("ADMIN");
		userState.addItem("SHOPPER");
		
		signin = new JButton("Sign in");
		handler = new ButtonHandler();
		signin.addActionListener(handler);
		
		back = new JButton("Back");
		backHandler = new BackHandler();
		back.addActionListener(backHandler);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(nameLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(nameText);
		this.add(panel);
		
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
		panel.setLayout(pLayout);
		panel.add(adsLabel);
		this.add(panel);
		panel = new JPanel();
		panel.add(addressText);
		this.add(panel);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(adminLabel);
		this.add(panel);
		//panel = new JPanel();
		//panel.add(adminText);
		//this.add(panel);
		
		panel = new JPanel();
		panel.add(userState);
		this.add(panel);
		
		panel = new JPanel();
		panel.add(signin);
		this.add(panel);
		
		panel = new JPanel();
		panel.add(back);
		this.add(panel);
		
		panel = new JPanel();
		panel.setLayout(pLayout);
		panel.add(exceptionLabel);
		

	}


	private class BackHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent action){
			//close current window
			//change title to FrontPage
			
			Store f;
			try {
				f = new Store(database);
				f.setSize(800, 800);
				f.setVisible(true);
				dispose();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(panel, "Check your information again", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	
	
	private class ButtonHandler implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent action){
			name = nameText.getText();
			email = emailText.getText();
			password = pswText.getText();
			address = addressText.getText();
			System.out.println("Registeration actionperformed address: " + address);
			String state = (String) userState.getSelectedItem();
			if(state.equals("ADMIN"))
				admin = true;
			else if(state.equals("SHOPPER"))
				admin = false;
			try {
			
				boolean d = database.registration(name, email, password, address, admin);
				
				if(d){
					int id = database.log_in(email, password);
					//change Title to FrontPage
					if(id > 0){
						String s = database.checkType(email);
						if(s.equals("Shopper")){
							Shopper s2 = null;
							for(int id1 : database.shoppers.keySet()){
								if(id1 == id){
									s2 = database.shoppers.get(id1);
								}
							}
							if( s2.mail.equals(email)){
								Shopper a = s2;
								shopperpage w =new shopperpage(database, a);
								//w.setSize(500,500);
								//w.setVisible(true);
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
								Admin w =new Admin(id,database);
								w.setSize(800,800);
								w.setVisible(true);
								dispose();
								}	
							}
					
						}
					//dispose();
				}
				else if(!d){
					//System.out.println("Wrong");
					JOptionPane.showMessageDialog(panel, "Check your information again", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
			} catch (Exception e) {
				exceptionLabel.setText("Not success, change some of your information");
				JOptionPane.showMessageDialog(panel, "Check your information again", "Error", JOptionPane.ERROR_MESSAGE);
				//System.out.println("Try something else.");
			}
		}
	}
	
}
