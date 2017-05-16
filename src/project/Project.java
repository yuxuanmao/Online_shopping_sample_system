
package project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
/**
 * This class is intended to be your API
 * @author Ilir
 *
 */
public class Project {
	
	/**
	 * This method must add a new shopper user or administrator user.
	 * @param userID
	 * @param password
	 * @param admin -> if true, add an administrator user, otherwise add a shopper user
	 * @return -> true if operation successful, false otherwise
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public static Database database; 
	Graph G = new Graph();
	
	public Project() throws FileNotFoundException, UnsupportedEncodingException{
		database = new Database();
	}
	
	public boolean addUser(String userID, String password, boolean admin) throws ClassNotFoundException, IOException {
		// Your code goes here.
		// Replace the statement below with the correct code.
		return database.registration("", userID, password, "", admin);
	}
	
	
	/**
	 * Authenticates a user an creates an active work session
	 * @param userID 
	 * @param password
	 * @return -> SessionID if authentication successful, -1 otherwise.
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public int login(String userID, String password) throws ClassNotFoundException, IOException {
		// Your code goes here.
		// Replace the statement below with the correct code.
		return database.log_in(userID, password);
	}
	
	/**
	 * Makes sessionID unavailable for connection
	 * @param sessionID
	 */
	public void logout(int sessionID) {
		// your code goeshere
		database.logOut(sessionID);
	}
	
	/**
	 * This method must add a new category in your application.
	 * @param catName -> The name of the category to be added.
	 * @param sessionID -> A session ID that belongs to an authenticated administrator
	 * @return -> The ID of the category you created if successful, -1 if not successful.
	 */
	public int addCategory(String catName, int sessinID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		
		return database.addCategory(catName, "",sessinID);
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
		database.addDistributionCenter(city, sessionID);
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
		
		return database.addCustomer(custName, city, street, sessionID);
	}
	
	/**
	 * Adds a new Product to your application
	 * @param prodName -> The product name
	 * @param category -> The product category.
	 * @param price -> The product sales price
	 * @param sessionID -> A session ID that belongs to an authenticated administrator
	 * @return -> Product ID if successful, -1 otherwise.
	 */
	//addProduct(String prodName, String description, String image, double quantity,  int category, double price, int sessionID) 
	public int addProduct(String prodName, String description, String image, int quantity,  int category, double price, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		
		return database.addProduct(prodName, description, image, quantity, category, price, sessionID);
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
		return database.prodInquiry(prodID, center);
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
	public boolean updateQuantity(int prodID, String distCentre, int quantity, int sessionID) throws Exception {
		// Your code goes here.
		// Replace the statement below with the correct code.
		return database.updateQuantity(prodID, distCentre, quantity, sessionID);
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
	public void addRoute(String cityA, String cityB, int distance, int sessionID) throws VertexExistsException, GraphIsFullException{
		// Your code goes here
		database.addRoute(cityA, cityB, distance, sessionID);
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
		//database.placeOrder(custID, prodID, quantity, sessionID);
		return database.placeOrder(custID, prodID, quantity, sessionID);
	}
    
	/**
	 * Returns the best (shortest) delivery route for a given order 
	 * @param orderID -> The order ID we want the delivery route
	 * @param sessionID -> A valid sessionID that belongs to an authenticated shopper user.
	 * @return -> The actual route as an array list of cities, null if not successful
	 */
	public ArrayList<String> getDeliveryRoute(int orderID, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		return database.getDeliveryRoute(orderID, sessionID);
	}
	
	/** 
	 * Computes the invoice amount for a given order.
	 * Please use the fixed price 0.01$/km to compute the shipping cost 
	 * @param orderID
	 * @param sessionID -> A valid sessionID that belongs to an authenticated shopper user.
	 * @return
	 */
	public double invoiceAmount(int orderID, int sessionID) {
		// Your code goes here.
		// Replace the statement below with the correct code.
		return database.invoiceAmount(orderID, sessionID);
	}
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		Project p = new Project();
		boolean result;
//		p.database.generateFile();
//		result = p.addUser("kerry.chen@gmail.com", "TokyoGrade", false);
//    	System.out.println(result);
//		result = p.addUser("nobita@gmail.com", "Tasukete", false);
//    	System.out.println(result);
//    	result = p.addUser("Doraemon@gmail.com", "BokuDoraemon",  true);
//    	System.out.println(result);
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
		Database d = new Database();
		id_c = d.log_in("Doraemon@gmail.com", "BokuDoraemon");
		System.out.println("sessionID of administrator " + id_c);
//		
		int cat_id = p.addCategory("Car", id_c );
		System.out.println(cat_id);
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
