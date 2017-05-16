package project;

import java.util.Comparator;
/**
 * 
 * This class compare the price for two product
 *
 */
public class PriceComp implements Comparator<Product>{

	public PriceComp() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Override compare method. It compare the price for two product.
	 * @param p1
	 * @param p2
	 * @return 1 if p1 > p2, -1 if p1 < p2
	 */
	@Override
	public int compare(Product p1, Product p2){
		if(p1.getPrice() > p2.getPrice()){
			return 1;
		}else{
			return -1;
		}
	}

}
