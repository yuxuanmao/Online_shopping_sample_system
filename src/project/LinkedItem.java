package project;

import java.io.Serializable;

/**
 * 
 * @author Eva
 *	Class LinkedItem is used to record what and how many product a shopper wanted
 *
 */

public class LinkedItem implements Serializable{
	protected double price;
	protected int quantity;
	protected String name;
	protected Product product;
	public int id;
	private int sockQty;
	protected static int orderID = 0;
	/**
	 * generate a new LinkedItem
	 * @param product
	 * @param quantity
	 */
	public LinkedItem(Product product, int quantity, int id){
		this.product = product;
		this.quantity = quantity;
		this.price = product.getPrice()*quantity;
		this.name = product.getName();
		this.sockQty = product.getQty();
		this.id  = id;
		orderID++;
	}
	public LinkedItem(){
		
	}
	/**
	 * the quantity of this product increase
	 * @param qty
	 */
	public void addQuantity(int qty){
		quantity += qty;
		
	}
	/**
	 * the quantity of this product decrease
	 * @param qty
	 */
	public void removeQuantity(int qty){
		quantity -= qty;
	}
	
	@Override
	public boolean equals(Object o){
		if(this.product.equals(((Product)o))){
			return true;
		}
		else return false;
	}
	/**
	 * override toString method: itemname*quantity: $price
	 */
	@Override
	public String toString(){
		return(name + "*" + quantity + ": $" + price);
	}
	/**
	 * Get the product
	 * @return product
	 */
	public Product getp(){
		return product;
	}
	/**
	 * Get the quantity
	 * @return quantity
	 */
	public int getq(){
		return quantity;
	}
}