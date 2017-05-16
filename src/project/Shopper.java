package project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
/**
 * This class is Shopper. Shopper can add and remove product. And shopper can cancel the shopping cart or the shopping cart will remain unchanged.
 * @author zhichao zhang
 */
public class Shopper implements Serializable {
	public String mail,name,password;
	protected Address address;
	protected boolean login;
	private final String USERSTATE = "Shopper";
	protected Inventory inventory;
	public ShoppingCart shoppingCart;
	protected OrderHistory orderHistory;
	protected int session_ID;
	protected int customer_ID;
	protected String city;
	protected String street;
	protected String fileName;
	/**
	 * This is the constructor of shopper
	 * @param name
	 * @param mail
	 * @param password
	 * @param address
	 * @param fileName
	 * @param inventory
	 */
	public Shopper(String name,String mail,String password,String address, String fileName, int ID, Inventory inventory){
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.city = address;
		this.fileName = fileName;
		this.customer_ID = ID;
		this.inventory  = inventory;
		this.shoppingCart = new ShoppingCart(this);
		this.orderHistory = new OrderHistory(this);
	}
	/**
	 * This is the constructor of shopper
	 * @param name
	 * @param password
	 * @param inventory
	 */
	public Shopper(String name, String password, Inventory inv){
		this.name = name;
		this.password = password;
		this.inventory = inv;
		this.shoppingCart = new ShoppingCart(this);
		this.orderHistory = new OrderHistory(this);
	}
	/**
	 * This method add the product into the shopping cart
	 * @param product
	 * @param quantity
	 * @return -> Return the order ID if successful, -1 if not
	 */
	public int add(Product product, int quantity){
		int order_id = -1;
		if(quantity > product.getQty() ){
			order_id = shoppingCart.add(product,quantity);
			product.changeQuantity(product.getQty() - quantity, USERSTATE);
		}
		return order_id;
	}
	/**
	 * This method remove the product into the shopping cart
	 * @param product
	 * @param quantity
	 */
	private void remove(Product product, int quantity){
		shoppingCart.remove(product,quantity);
		product.changeQuantity(product.getQty() - quantity, USERSTATE);
	}
	/**
	 * This method display the product list sorted by sortedby;
	 * @param sortedBy
	 */
	public void displayProductList(String sortedBy){
		inventory.displayProductList(sortedBy);
			
	}
	/**
	 * This method cancel the shoppingcart;
	 */
	public void cancelshoppingcart(){
		shoppingCart.cleanupShoppingCart();	
	}
	/**
	 * Check route of delivery
	 * @return a route as a list format
	 */
	public ArrayList<String> checkRoute(){
		return shoppingCart.CheckDeliveryRoute();
	}
	/*
	 * Check the invoice based on shoppingcart and delivery route
	 */
	/**
	 * This method gets the invoice
	 * @param whereTo
	 * @return the total invoice
	 */
	public double getInvoice(City whereTo){
		return shoppingCart.calculatetotal(this.inventory.G, this, whereTo);
	}
	/**
	 * This method gets the session ID of this shopper
	 * @return the session_ID of this shopper 
	 */
	public int getSessionID(){
		return this.session_ID;
	}
	/**
	 * This method gets the shopping cart of this shopper
	 * @return the ShoppingCart object of this shopper
	 */
	public ShoppingCart  getsc(){
		return shoppingCart;
	}
	/**
	 * This method gets the order history of this shopper
	 * @return the order history object of this shopper
	 */
	public OrderHistory gettoh(){
		return orderHistory;
	}

}