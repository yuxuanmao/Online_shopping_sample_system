package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import project.Category;
import project.Product;
import project.Inventory;

public class ProductManager {
	
	private List<Product> products = new ArrayList<Product>();
	
	public ProductManager() throws FileNotFoundException {
		// Load product from file here
		for (int id : Inventory.ProductSet.keySet()){
			Product p = Inventory.ProductSet.get(id);
			products.add(p);
		}

	}
	
    public List<Product> getProducts() {
    	return products;
    }

}
