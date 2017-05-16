package project;

import java.util.Comparator;
/**
 * 
 * This class compare the quantity for two product
 *
 */
public class QuantityComp implements Comparator<Product>{
	
	public QuantityComp(){
		
	}
	/**
	 * This method compare two product base on quantity
	 * @param p1
	 * @param p2
	 * @return 1 if p1 > p2, -1 if p1 < p2
	 */
	@Override
	public int compare(Product p1, Product p2){
		if(p1.getQty() > p2.getQty()){
			return 1;
		}else{
			return -1;
		}
	}
}
