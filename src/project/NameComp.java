package project;



import java.util.Comparator;

public class NameComp implements Comparator<Product>{

	public NameComp() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	/**
	 * Compare the price of two product. Compare alphabetically 
	 * 
	 * @param p1
	 * @param p2
	 * @return 1 if p1 > p2, 0 if p1 == p2, -1 if p1 < p2 
	 */
	public int compare(Product p1, Product p2){
		return p1.getName().compareTo(p2.getName());
	}

}
