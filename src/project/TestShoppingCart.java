package project;

import java.util.ArrayList;

public class TestShoppingCart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Inventory inv = new Inventory("pathname");
		Shopper user = new Shopper("Alex", "1234", inv);
		ShoppingCart shopCart = new ShoppingCart(user);
		
		Product p1 = new Product("apple", 1.5,  5);
		Product p2 = new Product("car", 20000, 2);
		
		LinkedItem L1 = new LinkedItem(p1, 2, 1);
		LinkedItem L2 = new LinkedItem(p2, 1, 2);
		
		ArrayList<LinkedItem> cart = new ArrayList<LinkedItem>();
		cart.add(L1);
		cart.add(L2);
		
		City c1 = new City("vancouver");
		City c2 = new City("Toronto");
		City c3 = new City("Victoria");
		City c4 = new City("Alberta");
		City c5 = new City("Quebec");
		
		Graph G = new Graph();
		DistributionCenter DC1 = new DistributionCenter("Vancouver");
		DistributionCenter DC2 = new DistributionCenter("Toronto");
		DistributionCenter DC3 = new DistributionCenter("Victoria");
		DistributionCenter DC4 = new DistributionCenter("Alberta");
		
		ArrayList<DistributionCenter> DCList = new ArrayList<DistributionCenter>();
		DCList.add(DC1);
		DCList.add(DC2);
		DCList.add(DC3);
		DCList.add(DC4);
		
		p1.mapOfCenter.put(DC1, 0);
		p1.mapOfCenter.put(DC2, 5);
		p1.mapOfCenter.put(DC3, 0);
		p1.mapOfCenter.put(DC4, 0);
		
		p2.mapOfCenter.put(DC1, 0);
		p2.mapOfCenter.put(DC2, 0);
		p2.mapOfCenter.put(DC3, 0);
		p2.mapOfCenter.put(DC4, 2);
		
		shopCart.add(p1, 2);
		shopCart.add(p2, 1);
		
		//System.out.println(cart.indexOf(L1));
		System.out.println(shopCart.Availability(DC2, DCList));
	}

}
