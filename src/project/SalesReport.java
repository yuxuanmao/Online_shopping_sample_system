package project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class SalesReport {
	public ArrayList<String> categoryName;

	public SalesReport(){
	}
	
	public ArrayList<ArrayList<Product>> byCategory(){
		ArrayList<ArrayList<Product>> result = new ArrayList<ArrayList<Product>>();
		for(Category c: Inventory.CategoryList.values()){
			categoryName.add(c.toString());
			ArrayList<Product> byCag = new ArrayList<Product>();
			for(Product p :Inventory.ProductSet.values()){
				if(p.getCategory()==c){
					byCag.add(p);
				}
			}
		result.add(byCag);	
		}
		return result;
	}

}
