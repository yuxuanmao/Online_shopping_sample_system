package project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class is Administrator. Administrator can add product, category,Displaycenter. And an administrator can change the nama,price,description,quantity of a product.  
 * @ zhichao zhang
 */
public class Administrator implements Serializable{
	protected String name;
	public String email;
	protected String password;
	protected boolean login;
	public final String USERSTATE = "Adminstor";
	protected Inventory inventory;
	public int ID;
	protected String fileName;
	
	/**
	 * this is the constructor of Administrator
	 * @param name
	 * @param email
	 * @param password
	 * @param filename
	 * @param inventory
	 */
	public Administrator(String name,String email, String password, String fileName, Inventory inventory) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.email= email;
		this.password = password;
		this.fileName = fileName;
		this.inventory = inventory;
	}
	/**
	 * this is the constructor of administrator
	 * @param name
	 * @param password
	 */
	public Administrator(String name, String password){
		this.name = name;
		this.password = password;
	}
	/**
	 * this method add product in the productet in inventory, if the product is already in the inventory, rewrite its quantity and price.
	 * @param name
	 * @param description
	 * @param image
	 * @param quantity
	 * @param price
	 * @param category
	 * @return the product ID which you added
	 */
	public int addProduct(String name,  String description, String image, int quantity, double price,  int category){
		int id = 0;
		Category c = inventory.CategoryList.get(category);
		int h;
		boolean flag = false;
		for(int i : inventory.ProductSet.keySet()){
			if(inventory.ProductSet.get(i).getName().equals(name)){
				h = i;
				Product p = inventory.ProductSet.get(i);
				p.changePrice(price, USERSTATE);
				p.changeQuantity(quantity);
				flag = true;
			}
		}
		
		
	if(!flag){
		Product item = new Product(name, price,description, image, quantity, c);
	
		if(inventory.ProductSet.get(item.id)== null){
			System.out.println("Added product");
			inventory.add(item);
			id = item.getCode();
			for(int i=0; i<inventory.DCList.size();i++){
				item.addDistributionCenter(inventory.DCList.get(i));
			}
			
		}
	}
		return id;
	}
	
	/**
	 * this method add category in the category list in inventory
	 * @param name
	 * @param deiscrition
	 */
	public int addCategory(String name,String descrition){
		Category cag = new Category(name,descrition);
		inventory.addCategory(cag.getCode(), cag);
		return cag.getCode();
	}
	
	/**
	 * Display the product after sorting
	 * @param sortedby
	 */
	public void displayProduct(String sortedby){
		inventory.displayProductList(sortedby);	
	}
	
	/**
	 * This method change the price of product
	 * @param item
	 * @param price
	 */
	public void changePrice(Product item,double price){
		item.changePrice(price, USERSTATE);
	}
	
	/**
	 * This method change the name of product
	 * @param item
	 * @param name
	 */
	public void changeName(Product item, String name){
		item.changeName(name, USERSTATE);
	}
	
	/**
	 * This method change the Description of product
	 * @param product
	 * @param descripition
	 */
	public void changeDescription(Product product,String description){
		product.changeDescription(description, USERSTATE);
	}
	
	/**
	 * This method change the category of product
	 * @param product
	 * @param cag
	 */
	public void changeCategory(Product product,Category cag){
		product.changeCategory(cag, USERSTATE);
	}
	
	/**
	 * This method change the quantity of product
	 * @param product
	 * @param quantity
	 */
	public void changeQuantity(Product product,int quantity){
		product.changeQuantity(quantity, USERSTATE);
	}
	
	/**
	 * This method add the distribution center to each product;
	 * @param center
	 * @throws VertexExistsException 
	 * @throws GraphIsFullException 
	 */
	// added new method that is used to add new distribution center by Leon Mao
	public DistributionCenter addCenter(String city) throws GraphIsFullException, VertexExistsException{
		DistributionCenter center = new DistributionCenter(city);
		inventory.addDC(center);
		boolean flag = false;
		for (City place : inventory.cityList){
			if(place.city.equals(city)){
				place.addDCtoCity(center);
				flag = true;
			}
		}
		System.out.println("Admin addCenter flag: " + flag);
		if(flag == true){
			City p = new City(city);
			p.Dcenter = center;
			inventory.cityList.add(p);
			inventory.G.addVertex(p);
		}
		for(int id : inventory.ProductSet.keySet()){
			Product item = inventory.ProductSet.get(id);
			item.addDistributionCenter(center, 0);
		}
		System.out.println("Admin addCenter new center: " + center);
		return center;
	}
	/**
	 * Add route from city to city
	 * @param cityA
	 * @param cityB
	 * @param distance
	 */
	public void addRoad(City cityA, City cityB, int distance){
		inventory.G.addEdge(cityA, cityB, distance);
		
	}
	/**
	 * Add city to graph
	 * @param city
	 * @throws VertexExistsException
	 * @throws GraphIsFullException
	 */
	public void addCity(City city)throws VertexExistsException, GraphIsFullException{
		inventory.addCity(city);
	}
	
	public ArrayList<String> categoryName(){
		ArrayList<String> resultname = new ArrayList<String>();
		for(Category c: Inventory.CategoryList.values()){
			System.out.println("1");
			resultname.add(c.toString());
			}
		
		return resultname;
	}
	/**
	 * This is the method used to make a hashmap that store the products based on category by default we set the value be 0
	 * because we have not sell anything yet
	 * @return ArrayList<ArrayList<Product>>
	 */
	public ArrayList<HashMap<Product, Integer>> byCategory(){
		ArrayList<HashMap<Product, Integer>> result = new ArrayList<HashMap<Product, Integer>>();
		for(Category c: Inventory.CategoryList.values()){
			//System.out.println("3");
			HashMap<Product, Integer> byCag = new HashMap<Product, Integer>();
			for(Product p :Inventory.SalesReportSet.keySet()){
				//System.out.println("4");
				if(p.getCategory()==c){
					byCag.put(p, 0);
					//System.out.println("5");
				}
			}
		result.add(byCag);	
		//System.out.println("111111cat"+byCag);
		}
		return result;
	}
	

}
