package project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the distribution center that ship the product to the address of shopper.
 * 
 */
public class DistributionCenter implements PleaceInterface, Serializable{
	
	protected static int nameID = 0;
	protected int code;
	public int name;
	protected List<DistributionCenter> preVertex;
	protected List<DistributionCenter> afterVertex;
	protected String city;
	/**
	 * This is the constructor
	 * @param city is a string
	 */
	public DistributionCenter(String city) {
		// TODO Auto-generated constructor stub
		this.name = nameID;
		this.city = city;
		this.preVertex = new ArrayList<DistributionCenter>();
		this.afterVertex = new ArrayList<DistributionCenter>();
		nameID ++;
		code = nameID;
	}
	
	/*
	 * used to determine the stock of items in cart in this distributionCenter
	 * @return a list of available items in this distributionCenter
	 */
	/**
	 * Determine the stock
	 * @param cart
	 * @return the stock 
	 */
	public ArrayList<LinkedItem> determineStock(ArrayList<LinkedItem> cart){
		//boolean result = false;
		ArrayList<LinkedItem> stockedItems = new ArrayList<LinkedItem>();
		for (LinkedItem item : cart){
			if(item.product.mapOfCenter.get(this) >= item.quantity){
				stockedItems.add(item);
			}
		}
//		for (LinkedItem stockedOne : stockedItems){
//			cart.remove(stockedOne);
//		}
		return stockedItems;	
	}
	
	/**
	 * This method add center to vertex list
	 * @param DC
	 * @param code
	 */
	
	public void addCenter(DistributionCenter DC, String code){
		if(code == "preVertex"){
			preVertex.add(DC);
		}else if(code == "afterVertex"){
			afterVertex.add(DC);
		}
	}
	
	/**
	 * Return the string representation of the class
	 */
	@Override
	public String toString(){
		return "DC"+ code + ": " + name;
	}
	/**
	 * Return the preVertex
	 * @return
	 */
	public List<DistributionCenter> getPreVertex() {
		return preVertex;
	}
	/**
	 * Return the AfterVertex
	 * @return
	 */
	public List<DistributionCenter> getAfterVertex() {
		return afterVertex;
	}
	/**
	 * Get the city
	 * @return	the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * Get the code
	 * @return
	 */
	public int getCode(){
		return code;
	}

	
}
