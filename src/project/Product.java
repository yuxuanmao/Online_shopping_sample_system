package project;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 
 * @author Eva
 * Class Product is a product
 *
 */

public class Product implements changeProperty, Serializable{
	private String name;
	private double price;
	private String description;
	private Category category;
	private int quantity;
	private String imagePath;
	private int code;
	public static int id=0;
	// add new property for distribution center
	public HashMap<DistributionCenter, Integer> mapOfCenter;
	
	/**
	 * generate a product without category
	 * @param name
	 * @param price
	 * @param description
	 * @param image
	 * @param quantity
	 */
	public Product(String name, double price, int quantity){
		this.name = name;
		this.price = price;
		this.description = "";
		this.imagePath = "";
		this.quantity = quantity;
		id++;
		this.code = id;
		this.mapOfCenter = new HashMap<DistributionCenter, Integer>();
	}
	/**
	 * generate a new product
	 * @param name
	 * @param price
	 * @param description
	 * @param image
	 * @param quantity
	 * @param c 
	 * @param cag
	 */
	public Product(String name, double price, String description, String image, int quantity, Category c){
		this.name = name;
		this.price = price;
		this.description = description;
		this.imagePath = image;
		this.quantity = quantity;
		this.category = c;
		id++;
		this.code = id;
		this.mapOfCenter = new HashMap<DistributionCenter, Integer>();
	}
	
	/**
	 * Return this product ID
	 * @return code
	 */
	public int getCode(){
		return code;
	}
	/**
	 * change the quantity of the product
	 */
	public void changeQuantity(int newQty){
		this.quantity = newQty;
	}
	/**
	 * change the price of the product
	 * @param newPrice
	 * @param userState
	 */
	public void changePrice(double newPrice, String userState){
		if(userState == "Adminstor"){
			price = newPrice;
		}
		else{
			throw new AccessDeniedException("You don't have access to this action, please login or login as an adminstor");
		}
	}
	
	/**
	 * change the name of product
	 * @param newName
	 * @param userState
	 */
	@Override
	public void changeName(String newName, String userState){
		if(userState == "Adminstor"){
			name = newName;
		}
		else{
			throw new AccessDeniedException("You don't have access to this action, please login or login as an adminstor");
		}
	}
	
	/**
	 * change the name of product
	 * @param text
	 * @param userState
	 */
	@Override
	public void changeDescription(String text, String userState){
		if(userState == "Adminstor"){
			description = text;
		}
		else{
			throw new AccessDeniedException("You don't have access to this action, please login or login as an adminstor");
		}
	}
	/**
	 * change the category of product
	 * @param newCate
	 * @param userState
	 */
	public void changeCategory(Category newCate, String userState){
		if(userState == "Adminstor"){
			category = newCate;
		}
		else{
			Exception e = new AccessDeniedException("You don't have access to this action, please login or login as an adminstor");
		}
		
	}
	/**
	 * change the quantity of product
	 * @param newQuantity
	 * @param userState
	 */
	public void changeQuantity(int newQuantity, String userState){
		if(userState == "Adminstor"||userState == "ShoppingCart"){
			quantity = newQuantity;
		}
		else{
			throw new AccessDeniedException("You don't have access to this action, please login or login as an adminstor");
		}
		
	}
	/**
	 * change the Image Path of product
	 * @param path
	 * @param userState
	 */
	public void changeImage(String path, String userState){
		if(userState == "Adminstor"){
			imagePath = path;
		}
		else{
			throw new AccessDeniedException("You don't have access to this action, please login or login as an adminstor");
		}
		
	}

	/**
	 * change the code for the product
	 * @param i
	 * @param userState
	 */
	@Override
	public void changeCode(int i, String userState){
		if(userState == "Adminstor"){
			code = i;
		}
		else{
			throw new AccessDeniedException("You don't have access to this action, please login or login as an adminstor");
		}
	}
	/**
	 * Add new distributionCenter with quantity
	 * @param center
	 * @param quantity
	 */
	// added a new method to change mapOfCenter by Leon Mao
	public void addDistributionCenter(DistributionCenter center, int quantity){
		if(!mapOfCenter.containsKey(center)){
			mapOfCenter.put(center, quantity);
		}
	}
	/**
	 * add a new distributionCenter without assign its quantity
	 * @param center
	 */
	public void addDistributionCenter(DistributionCenter center){
		if(!mapOfCenter.containsKey(center)){
			mapOfCenter.put(center, 0);
		}
	}
	/**
	 *  added a new method that let us to add the quantity in the specific distribution center by Leon Mao
	 * @param center
	 * @param quantity
	 */
	public void addCenterQty(DistributionCenter center, int q) throws NotEnoughQuantity{
//		System.out.println(center.toString());
//		System.out.println(mapOfCenter.containsKey(center));
		if(mapOfCenter.containsKey(center)){
			System.out.println("contained");
			int newq = mapOfCenter.get(center)+q;
			System.out.println(newq);
			System.out.println(q);
			System.out.println(quantity);
			System.out.println(newq <= quantity);
			if(newq <= quantity){
				System.out.println("right");
				mapOfCenter.put(center, mapOfCenter.get(center)+q);
			}
			else if(newq>quantity){
				System.out.println("wrong");
				throw new NotEnoughQuantity("Not enough quantity");
			}
		}
	}
	/**
	 * Added a new method that let us to subtract the quantity in the specific distribution center by Leon Mao
	 * @param center
	 * @param quantity
	 */
	// added a new method that let us to subtract the quantity in the specific distribution center by Leon Mao
	public void subtractCenterQty(DistributionCenter center, int quantity){
		if(mapOfCenter.containsKey(center)){
			mapOfCenter.put(center, mapOfCenter.get(center)-quantity);
		}
	}
	/**
	 * Get the price for this product
	 * @return double
	 */
	public double getPrice(){
		return price;
	}
	/**
	 * Get the Name of this product
	 * @return String
	 */
	public String getName(){
		return name;
	}
	/**
	 * Get the quantity of this product
	 * @return double
	 */
	public int getQty(){
		return quantity;
	}
	/**
	 * Get the image of this product
	 * @return
	 */
	public String getImage(){
		return imagePath;
	}
	
	/**
	 * Get the category for this product
	 * @return Category
	 */
	public Category getCategory(){
		return category;
	}
	/**
	 * Get the category for this product
	 * @return String
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * Change the quantity of the product
	 * @param operate
	 * @param amount
	 */
	public void changeQty(String operate, int amount){
		if(operate == "subtract"){
			quantity -= amount;
		}else if(operate == "add"){
			quantity += amount;
		}
	}
	
	@Override
	/**
	 * Show the product in string
	 */
	public String toString(){
		return "Product name: " + name + " ,price: " + price + " ,description: " + description
				+" ,category: "+category + " ,quantity: "+quantity;
	}
}
