package project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.sun.javafx.collections.MappingChange.Map;
/**
 * This class is database that stores all of the information of shoppers and administrators.
 * @author Kerry
 *
 */
public class Database {
	private String emailFile = "email.txt";
	private String invFile = "inventory.txt";
	public static int session_id = 0;
	public static int customer_id = 0;
	public static int admin_id = 0;
	public static int orderID = 0;
	public  static HashMap<Integer, Shopper> shoppers =  new HashMap<Integer, Shopper>();
	public  static HashMap<Integer, Administrator> administrators =  new HashMap<Integer, Administrator>();
	public String sessionFile = "session.txt";
	public Inventory inv = new Inventory(invFile);
	public  static HashMap<String, DistributionCenter> centers =  new HashMap<String, DistributionCenter>();
	
	/**
	 * This methods must generate a email file for storing user email and user type.
	 * @throws IOException
	 */
	public void generateFile() throws IOException{
		PrintWriter email = new PrintWriter(emailFile);
		email.close();
	}
	/**
	 * This method must register a new shopper user or administrator user. 
	 * Add the register's email to the emailFile
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @param address
	 * @param admin -> if true, add an administrator user, otherwise add a shopper user
	 * @return -> true if registration successful, false otherwise
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public  boolean registration(String name, String email, String password, String address, boolean admin) throws ClassNotFoundException, IOException{
		boolean registered = false;
		String fileName = email + ".txt";
		String text;
		if(email.equals("") == false){
			if(same_email(email) == false){
				PrintWriter printWriter = new PrintWriter(fileName, "UTF-8");
				printWriter.close();
				if(admin){
					Administrator a  = new Administrator(name, email, password, fileName, inv);
					try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
						oos.writeObject(a);
						
					}catch(IOException e){
						fail(e.getMessage());
					}
					text = email + ":" + "Administrator";
					
				}else{	
					customer_id++;
					Shopper s = new Shopper(name, email, password, address, fileName, customer_id, inv);
					try{
						FileOutputStream fileOut = new FileOutputStream(fileName);
						ObjectOutputStream out = new ObjectOutputStream(fileOut);
						out.writeObject(s);
						out.close();
						fileOut.close();
					}catch(IOException e){
						e.printStackTrace();
					}
					text = email + ":" + "Shopper";
					
				}
				
				try{
					FileWriter fstream = new FileWriter(emailFile, true);
					BufferedWriter out = new BufferedWriter(fstream);
					out.write(text);
					out.write("\n");
				    out.close();
			    }catch(Exception e){
					System.out.println("Error: " + e.getMessage());
				}
				registered = true;
			}else{
				registered = false;
			}
		}
		return registered;
		
	}
	
	/**
	 * This method must check whether the given email is used before or not.
	 * Read the emailFile to determine if it is already inside the file.
	 * @param email
	 * @return -> true if it is same email, false otherwise
	 */
	public  boolean same_email(String email) {
		boolean same_email = false;
		Scanner sFile;
		try {
			sFile = new Scanner(new File(emailFile));
			do{
				String line = sFile.nextLine();
				if(line.equals(email)){
					same_email = true;
		        	break;
				}
			}while(sFile.hasNextLine());	
		    sFile.close();
		} catch (Exception e) {	
		}
		return same_email;
	}
	/**
	 * This method must determine the type(Shopper or Administrator) of the user based on the given email.
	 * By accessing the emailFile, the type of the user can be found.
	 * @param email
	 * @return -> The type of the user: Shopper or Administrator
	 * @throws FileNotFoundException
	 */
	public  String checkType(String email) throws FileNotFoundException{
		Scanner sFile = new Scanner(new File("email.txt"));
		String user = "";
		boolean find = false;
		do{
			String line = sFile.nextLine();
			String[] lineData = line.split(":", 0);
			if(lineData[0].contains(email)){
				user = lineData[1];
				find = true;
			}
		}while(sFile.hasNextLine() && !find);	
	    sFile.close();
	    return user;
	}
	/**
	 * This method must allow the user to log in if the email and password is correct.
	 * @param email
	 * @param password
	 * @return -> The session ID of the logged user, return 0 means failing to log in
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public  int log_in(String email, String password) throws ClassNotFoundException, IOException{
    	Shopper s = null;
    	Administrator a = null;
    	int id = 0;
    	String file = email + ".txt";
    	
    	session_id++;
	   // try{
    		 FileInputStream fileIn = new FileInputStream(file);
    		 ObjectInputStream in = new ObjectInputStream(fileIn);
    		 if(checkType(email).contains("Shopper")){
    			 s = (Shopper) in.readObject();
    		     if(s.mail.equals(email) && s.password.equals(password)){
    		    	 s.session_ID = session_id;
    		    	 id = s.session_ID;
    		    	 shoppers.put(s.session_ID, s);
    		     }
    		 }else if(checkType(email).contains("Administrator")){
    			 a = (Administrator) in.readObject();
    		     if(a.email.equals(email) && a.password.equals(password)){
    		    	 a.ID = session_id;
    		    	 id = a.ID;
    		    	 administrators.put(a.ID, a);
    		     }
    		 }
    		 in.close();
    		 fileIn.close();
    	// }catch(Exception e){
    	//	 e.printStackTrace();
    	// }
	    return id;
	}
	
	
	/**
	 * This method must allow user to log out. After log user out, it must remove the 
	 * session ID. 
	 * @param id
	 */
    public void logOut(int id){
    	Shopper s = null;
    	Administrator a = null;
     
	     if(shoppers.containsKey(id)){
	    	 s = shoppers.get(id); 
	    	 shoppers.remove(id);
	    	 //System.out.println(s.name);
	    	 try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(s.fileName))){
					oos.writeObject(s);
				}catch(IOException e){
					fail(e.getMessage());
				}
	     }else if(administrators.containsKey(id)){
	    	 a = administrators.get(id);
	    	 administrators.remove(id);
	    	 try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(a.fileName))){
					oos.writeObject(a);
				}catch(IOException e){
					fail(e.getMessage());
				}
	     }
   	
    }
    public int addCategory(String catName, String de, int sessinID) {
		// Your code goes here.
		// Replace the statement below with the correct code.\
    	Administrator a = null;
    	if(administrators.containsKey(sessinID)){
 	    	 a = administrators.get(sessinID); 
    	}
 	   
		if(a != null){
			for(int k: inv.CategoryList.keySet()){
				
				if (inv.CategoryList.get(k).getName().equals(catName)){
					return -1;
				}
			}
			int code = a.addCategory(catName, de);
			return code;}

		else{
			return -1;
		}
	}
    /**
	 * Adds a new Customer to your application; the customer record that belongs 
	 * to a newly added shopper user that has no customer record on the system.
	 * @param custName -> The name of the customer
	 * @param city -> The city of the customer address
	 * @param street -> The street address of the customer
	 * @param sessionID -> A valid sessionID that belongs to an authenticated shopper user.
	 * @return -> The added customer ID
	 */
	public int addCustomer(String custName, String city, String street, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		Shopper s = null;
    	if(shoppers.containsKey(sessionID)){
 	    	 s = shoppers.get(sessionID); 
    	}
    	s.name = custName;
    	s.city = city;
    	s.street = street;
		return s.customer_ID;
	}
	/**
	 * Adds a new Product to your application
	 * @param prodName -> The product name
	 * @param category -> The product category.
	 * @param price -> The product sales price
	 * @param sessionID -> A session ID that belongs to an authenticated administrator
	 * @return -> Product ID if successful, -1 otherwise.
	 */
	
	public int addProduct(String prodName, String description, String image, int quantity,  int category, double price, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		Administrator a = null;
    	if(administrators.containsKey(sessionID)){
 	    	 a = administrators.get(sessionID); 
    	}
    	if(a != null){
    		int id = a.addProduct(prodName, description, image, quantity, price, category);
    		return id;
    	}else{
    		return -1;
    	}
	}
	/**
	 * Adds a distribution center to your application.
	 * If the given distribution center exists, or sesionID invalid, do nothing.
	 * @param city -> The city where distribution center must be based.
	 * @param sessionID -> A session ID that belongs to an authenticated administrator
	 * @throws VertexExistsException 
	 * @throws GraphIsFullException 
	 */
	public void addDistributionCenter(String city, int sessionID) throws GraphIsFullException, VertexExistsException {
		// Your code goes here
		Administrator a = null;
    	if(administrators.containsKey(sessionID)){
 	    	 a = administrators.get(sessionID); 
    	}
    	DistributionCenter dc = a.addCenter(city);
    	System.out.println("Database addDistributionCenter center: " + dc);
    	centers.put(city, dc);
	}
	/**
	 * Adds two nodes cityA, cityB to the shipping graph
	 * Adds a route (an edge to the shipping graph) from cityA to cityB with length distance
	 * If the nodes or the edge (or both) exist, does nothing
	 * @param cityA 
	 * @param cityB
	 * @param distance -> distance (in km, between cityA and cityB)
	 * @param sessionID -> A session ID that belongs to an authenticated administrator
	 */
	public void addRoute(String cityA, String cityB, int distance, int sessionID)throws GraphIsFullException, VertexExistsException{
		Administrator a = null;
    	if(administrators.containsKey(sessionID)){
    		System.out.println("a is not null");
 	    	 a = administrators.get(sessionID); 
    	}
    	boolean determineA = false;
		boolean determineB = false;
		City A = null;
		City B = null;
		for (int i = 0; i< inv.G.getNumVertices(); i++){
			City place = inv.G.getAllCities()[i];
			if(place != null && place.city.equals(cityA)){
				determineA = true;
				A = place;
			}
			
			if(place != null && place.city.equals(cityB)){
				determineB = true;
				B = place;
			}
		}
		if(determineA == false){
			A = new City(cityA);
			a.addCity(A);
		}
		if(determineB == false){
			B = new City(cityB);
			a.addCity(B);
		}
		
    	a.addRoad(A, B, distance);
	}
	/**
	 * Returns the best (shortest) delivery route for a given order 
	 * @param orderID -> The order ID we want the delivery route
	 * @param sessionID -> A valid sessionID that belongs to an authenticated shopper user.
	 * @return -> The actual route as an array list of cities, null if not successful
	 */
	public ArrayList<String> getDeliveryRoute(int orderID, int sessionID){
		Shopper s = null;
    	if(shoppers.containsKey(sessionID)){
 	    	 s = shoppers.get(sessionID); 
 	    	 if(orderID == s.customer_ID){
 	    		 return s.checkRoute();
 	    	 }else{
 	    		 System.out.println("not valid customer");
 	    		 return null;
 	    	 }
    	}else{
    		System.out.println("not valid customer");
    		return null;
    	}
	}
	/** 
	 * Computes the invoice amount for a given order.
	 * Please use the fixed price 0.01$/km to compute the shipping cost 
	 * @param orderID
	 * @param sessionID -> A valid sessionID that belongs to an authenticated shopper user.
	 * @return
	 */
	public double invoiceAmount(int orderID, int sessionID){
		Shopper s = null;
		City whereTo = null;
		double total = 0.0;
    	if(shoppers.containsKey(sessionID)){
 	    	 s = shoppers.get(sessionID); 
 	    	 if(orderID == s.customer_ID){
 	    		 for(City place : s.inventory.cityList){
 	    			 if(place.city == s.city){
 	    				 whereTo = place;
 	    			 }
 	    		 }
 	    		 total = s.getInvoice(whereTo);
 	    		 return total;
 	    	 }else{
 	    		 System.out.println("not valid customer");
 	    		 return total;
 	    	 }
    	}else{
    		System.out.println("not valid customer");
    		return total;
    	}
	}
	
	
	/**
	 * Computes the available quantity of prodID in a specific distribution center.
	 * @param prodID
	 * @param center
	 * @return -> Available quantity or -1 if prodID or center does not exist in the database
	 */
	public int prodInquiry(int prodID, String center) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		
		Product p = null;
		p = Inventory.ProductSet.get(prodID);
		if(p == null)
			return -1;
		else{
			DistributionCenter dc = centers.get(center);
			System.out.println("Database prodInquiry " + p.mapOfCenter.get(dc));
			return p.mapOfCenter.get(dc);
			
		}
	}
	/**
	 * Updates the stock quantity of the product identified by prodID
	 * @param prodID -> The product ID to be updated
	 * @param distCentre -> Distribution Center (in effect a city name)
	 * @param quantity -> Quantity to add to the existing quantity
	 * @param sessionID -> A session ID that belongs to an authenticated administrator
	 * If currently the product 112 has quantity 100 in Toronto,
	 * after the statement updateQuantity(112, "Toronto", 51)
	 * same product must have quantity 151 in the Toronto distribution center. 
	 * @return -> true if the operation could be performed, false otherwise.
	 * @throws Exception 
	 */
	public boolean updateQuantity(int prodID, String distCentre, int quantity, int sessionID)  {
		// Your code goes here.
		// Replace the statement below with the correct code.
		Administrator a = null;
    	if(administrators.containsKey(sessionID)){
 	    	 a = administrators.get(sessionID); 
 	    	 System.out.println("is admin");
    	}
    	Product p = null;
    	boolean found_center = false;
		p = Inventory.ProductSet.get(prodID);
		DistributionCenter center = null;
		for(DistributionCenter c : p.mapOfCenter.keySet()){
			System.out.println("database updateQuantity we are in: " + distCentre);
			System.out.println("database updateQuantity city comapre: " + c.city);
			if(c.city.equals(distCentre)){
				center = c;
				found_center = true;
			}
		}
		System.out.println("going to product");
		p.addCenterQty(center,quantity);
		return found_center;
	}
	/**
	 * Attempts an order in behalf of custID for quantity units of the prodID
	 * @param custID -> The customer ID
	 * @param prodID -> The product ID
	 * @param quantity -> The desired quantity
	 * @param sessionID -> A valid sessionID that belongs to an authenticated shopper user.
	 * @return -> The orderID if successful, -1 if not.
	 */
	public int placeOrder(int custID, int prodID, int quantity, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		Shopper s = null;
    	if(shoppers.containsKey(sessionID)){
 	    	 s = shoppers.get(sessionID); 
    	}
    	Product p = null;
    	p = inv.ProductSet.get(prodID);
    	int order_id = s.add(p, quantity);
    	
		return order_id;
	}
	public ArrayList<String> cagName(int sessionID){
		System.out.println("7");
		Administrator a = null;
    	if(administrators.containsKey(sessionID)){
	    	 a = administrators.get(sessionID); 
	    	 System.out.println("8");
    		}
    	ArrayList<String> categoryname = a.categoryName();
    	return categoryname;
	}
	public ArrayList<HashMap<Product, Integer>> salesReport(int sessionID){
		System.out.println("6");
		Administrator a = null;
    	if(administrators.containsKey(sessionID)){
	    	 a = administrators.get(sessionID); 
	    	 System.out.println("9");
    		}
    	ArrayList<HashMap<Product, Integer>> result = new ArrayList<HashMap<Product, Integer>>();
    	result = a.byCategory();
    	System.out.println("10");
    	return result;
    	
	}

	
	
    /**
     * This method must return the message of the exception when there are an error.
     * @param meg
     */
    private  void fail(String meg){
   	 System.out.println(meg);
    }
    public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
    	Database d = new Database();
    	boolean result;
		result = d.registration("kerry.chen@gmail.com", "TokyoGrade", null, null, false);
    	System.out.println(result);
//		result = p.addUser("nobita@gmail.com", "Tasukete", false);
//    	System.out.println(result);
    	result = d.registration("Doraemon@gmail.com", "BokuDoraemon", null, null,  true);
    	System.out.println(result);
//		result = p.addUser( "Shizuka@gmail.com", "Mina-Yamete",  false);
//		System.out.println(result);
		
		int id_a; 
		int id_b;
		int id_c;
//		id_a = p.login("kerry.chen@gmail.com", "TokyoGrade");
//		System.out.println(id_a);
		
//		System.out.println("sessionID of customer " + id_a);
//		id_b = database.log_in("Shizuka@gmail.com", "Mina-Yamete");
//		System.out.println(id_b);
		Database d1 = new Database();
		id_c = d.log_in("Doraemon@gmail.com", "BokuDoraemon");
		System.out.println("sessionID of administrator " + id_c);
		d1.salesReport(id_c);
		int cat_id = d1.addCategory("Car", "",id_c );
		System.out.println("Added category " + cat_id);
//		int car_id = p.addCategory("Car", 5 );
//		System.out.println(car_id); 
//		System.out.println("Category ID " + cat_id);
//		System.out.println(p.addCustomer("John", "Toronto", "college strret", id_a));
//		int pro_id = p.addProduct("Lexus",cat_id, 0, id_c);
//		System.out.println("ProductID " + pro_id);
//		for(int id : database.inv.ProductSet.keySet()){
//			Product item = database.inv.ProductSet.get(id);
//			String text = item.toString();
//			System.out.println(text);
//		}
//		p.addDistributionCenter("Toronto", id_c);
//		DistributionCenter dc = (database.inv.DCList.get(0));
//		System.out.println(dc.city);
//		
//		System.out.println(p.updateQuantity(pro_id, dc.city, 10, id_c));
//		System.out.println(p.prodInquiry(pro_id, dc.city));
	}

}
