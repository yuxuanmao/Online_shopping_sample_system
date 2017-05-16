package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project.Administrator;

public class UserManager {

	private List<User> users;
	private List<Administrator> admins;
	
	public UserManager() throws FileNotFoundException {
		users = new ArrayList<>();
		admins = new ArrayList<>();
		// load existing users
		// we will fake one user for testing purposes
		//scan if the user is admin
		Scanner adminscan = new Scanner(new File(""));
		while(adminscan.hasNextLine()){
			String adminname = adminscan.nextLine();
			String adminpass = adminscan.nextLine();
			Administrator a = new Administrator(adminname,adminpass);
			admins.add(a);
		}
		//scan all users
		Scanner userscan = new Scanner(new File(""));
		while(userscan.hasNextLine()){
		String username = userscan.nextLine();
		String pass = userscan.nextLine();
		User u = new User(username,pass);
		users.add(u);
		
		}
		
		
		
		
		
		//User user = new User("Ilir","pass");
		//users.add(user);
	}
	//method for checking if the user is admin;
	public boolean admin(User u){
		return admins.contains(u);
	}
	public boolean validate(User u) {
		return users.contains(u);
	}
	
}
