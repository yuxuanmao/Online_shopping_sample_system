package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This class is inventory that store all the product
 * 
 */
public class Inventory implements Serializable {
	
	public static HashMap<Integer,Product> ProductSet = new HashMap<Integer, Product>();
	//protected HashMap<String, Integer> Encrypt;
	public static HashMap<Integer,Category> CategoryList = new HashMap<Integer, Category>();
	protected static String fileName;
	public static ArrayList<DistributionCenter> DCList = new ArrayList<DistributionCenter>();
	public static Set<City> cityList = new HashSet<City>();
	public static HashMap<Product,Integer> SalesReportSet = new HashMap<Product,Integer>();
	public static Graph G = new Graph();
	

	/**
	 * This is the constructor
	 */
	public Inventory(String pathName){
		fileName = pathName;
		
	}
	/**
	 * This method add new product to productset
	 * @param item
	 */
	public static void add(Product item){
		try{
			FileWriter fstream = new FileWriter(fileName, true);
			BufferedWriter out = new BufferedWriter(fstream);
			ProductSet.put(item.getCode(), item);
			String text = item.toString();
			out.write(text);
			//out.write("\n");
			out.close();
			}catch(Exception e){
				System.out.println("Error: " + e.getMessage());
			}
	}
	/**
	 * This method refresh the inventory file
	 * 
	 */
	protected void refreshInventoryFile(){
		try{
			FileWriter fstream = new FileWriter(fileName, true);
			BufferedWriter out = new BufferedWriter(fstream);
			for(int id : ProductSet.keySet()){
				Product item = ProductSet.get(id);
				String text = item.toString();
				out.write(text);
			}
			//out.write("\n");
			out.close();
			}catch(Exception e){
				System.out.println("Error: " + e.getMessage());
			}
	}
	/**
	 * This method add new category in the category list
	 * @param cag
	 */
	public void addCategory(int id, Category cag){
		CategoryList.put(id, cag);
	}
	/**
	 * This method display product list in certain way using the method sortlist;
	 * @param sortedby
	 * @
	 */
	public void displayProductList(String sortedBy){
		if(sortedBy != "All"){
			TreeSet<Product> result = sortList(sortedBy); 
			for (Product item : result){
				System.out.println(toString(item));
			}
		}else{
			for(int id : CategoryList.keySet()){
				Category cag  = CategoryList.get(id);
				TreeSet<Product> subSet = sortByCategory(cag.name);
				for (Product item : subSet){
					System.out.println(toString(item));
				}
			}
		}
	}
	/**
	 * This method sort the product list by different sortedby
	 * @param sortedby
	 * @return return a new treeset which is sorted
	 */
	public TreeSet<Product> sortList(String sortedBy){
		TreeSet<Product> result = new TreeSet<Product>();
		if(sortedBy == "Price"){
			result = new TreeSet<Product>(new PriceComp());
		}else if(sortedBy == "Name"){
			result = new TreeSet<Product>(new NameComp());
		}else if(sortedBy == "Quantity"){
			result = new TreeSet<Product>(new QuantityComp());
		}else{
			TreeSet<Product> subSet = sortByCategory(sortedBy);
			return subSet;	
		}
		
		for(int id : ProductSet.keySet()){
			Product item = ProductSet.get(id);
			result.add(item);
		}
		return result;
	}
	/**
	 * This method sort the category list by different sortedby
	 * @param sortedby
	 * @return return a new treeset which is sorted
	 */
	private TreeSet<Product> sortByCategory(String sortedBy){
		TreeSet<Product> subSet = new TreeSet<Product>(new NameComp());
		for(int id : ProductSet.keySet()){
			Product item = ProductSet.get(id);
			if(item.getCategory().name.equals(sortedBy)){
				subSet.add(item);
			}
		}
		return subSet;
	}
	/**
	 * This method return a string representation of this inventory
	 * @param item
	 * @return return a string representation of this inventory
	 */
	public String toString(Product item){
		return "Name: " + item.getName() + " " + "Price: " + item.getPrice() + " " + "Quantity: " + item.getQty();
	}
	/**
	 * This method add destribution center in dclist;
	 * @param cd
	 */
	public void addDC(DistributionCenter dc){
		DCList.add(dc);
	}
	/**
	 * this is used to add a new city to map
	 * @param city
	 * @throws VertexExistsException
	 * @throws GraphIsFullException
	 */
	public void addCity(City city)throws VertexExistsException, GraphIsFullException{
		cityList.add(city);
		G.addVertex(city);
	}
	
}

