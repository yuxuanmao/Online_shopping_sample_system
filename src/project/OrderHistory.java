package project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *	Class OrderHistory is a list that store all the product that a shopped has bought so far.
 *	
 */
public class OrderHistory implements Serializable {
	static protected List<LinkedItem> history = new ArrayList<LinkedItem>();
	public Shopper shopper;
	/**
	 * This is the construction of class order history;
	 */

	public OrderHistory(Shopper shopper){
		this.shopper = shopper;
	}
	/**
	 * This method add the product and the quantity of product to the histroy
	 * @param itme
	 * @param quantity
	 */
	public void addItem(Product item, int quantity){
		int i = findIndex(item, history);
		if(i<0){
			history.add(new LinkedItem(item, quantity, item.getCode()));
		}
		else{//findIndex return >=0
			history.get(i).addQuantity(quantity);
		}
	}
	/**
	 * This method remove the product and the quantity of product from the histroy
	 * @param itme
	 * @param quantity
	 */
	public void removeItem(Product item, int quantity){
		int i = findIndex(item, history);
		if(i>=0){
			if(history.get(i).quantity-quantity>0)
				history.get(i).removeQuantity(quantity);
			else if(history.get(i).quantity-quantity==0)
				history.remove(i);
			else{//history.get(i).quantity-quantity<0
				throw new NotEnoughQuantity();
			}
		}
	}
	//helper
	/**
	 * return the index of the product in the list, if the list doesn't contain the product, return -1.
	 * @param product
	 * @param list
	 * @return
	 */
	public int findIndex(Product product, List<LinkedItem> list){
		for(int i=0; i<list.size(); i++){
			if (list.get(i).equals(product)){
				return i;
			}
		}
		return -1;
	}
	/**
	 * This method display the history of a shopper
	 */
	public String display(){
		String result = "";
		for(int i=0; i<history.size();i++){
			result += history.toString();
		}
		return result;
	}
	/**
	 * get the order history 
	 * @return history that is the order history
	 */
	public List<LinkedItem> getoh(){
		return history;
	}
	/**
	 * Add the item to order history 
	 * @param item
	 */
	public void addLinkedItem(LinkedItem item){
		history.add(item);
	}
	
}