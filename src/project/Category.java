package project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Eva
 * Class Category is the category for product 
 *
 */
public class Category implements changeProperty, Serializable{
	protected String name;
	private String description;
	private int code;
	private static int id=0;
	//private List<Product> items; 
	/**
	 * Generate a new category
	 * @param name
	 * @param description
	 * @param code
	 */
	public Category(String name, String description){
		this.name = name;
		System.out.println("cd"+description);
		this.description = description;
		System.out.println("this "+ this.description);
		id++;
		this.code = id;
		//this.items = new ArrayList<Product>();
	}
	/**
	 * This method get name of the category
	 * @return the name of the category
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Return the code of this category
	 * @return code
	 */
	public int getCode(){
		return code;
	}
	
	/**
	 * Add a product into this category
	 * @param product
	 */
	//public void addProduct(Product product){
		//items.add(product);
	//}
	
	/**
	 * remove the product into this category
	 * @param product
	 */
	//public void removeProduct(Product product){
		//items.add(product);
	//}
	
	/**
	 * Change the description of this category
	 * @param newName
	 */
	@Override
	public void changeName(String newName, String userState){
		if(userState == "Adminstor")
			name = newName;
		else{
			throw new AccessDeniedException("You don't have access to this action, please login or login as an adminstor");
		}
	}
	
	/**
	 * Change the description of this category
	 * @param text
	 */
	@Override
	public void changeDescription(String text, String userState){
		if(userState == "Adminstor")
			description = text;
		else{
			throw new AccessDeniedException("You don't have access to this action, please login or login as an adminstor");
		}
	}
	/**
	 * Change the code 
	 * @param newCode
	 * @param userState
	 */
	@Override
	public void changeCode(int newCode, String userState){
		if(userState == "Adminstor")
		code = newCode;
		else{
			throw new AccessDeniedException("You don't have access to this action, please login or login as an adminstor");
		}
	}
	
	/**
	 * This method to get description
	 * @return
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * This method generates the string of this class includes category name
	 */
	@Override
	public String toString(){
		return "Category Name: "+name+" ";
	}

}
