package project;
import java.io.Serializable;
/**
 * This class is shopping cart. A shopping cart can add and remove product from linkeditem. shopper can check out in shopping cart and get the invoice.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class ShoppingCart implements Serializable{
	private double invoice = 0;
	private double delivery;
	private Shopper shopper;
	private ArrayList<LinkedItem> productList;
	public static int orderID = 0;
	public HashMap<DistributionCenter, Integer> Distributefrom = new HashMap<DistributionCenter, Integer>();
	/**
	 * this is the constructor of shopping cart
	 */
	public ShoppingCart(Shopper shopper){
		this.shopper = shopper;
		this.productList = new ArrayList<LinkedItem>();
	}
	/**
	 * This method add product into the linkeditem
	 * @param product
	 * @param quantity
	 */
	
	public int add(Product product, int quantity){
		int i = findIndex(product, productList);
		LinkedItem item =null;
		if(i>=0){
			item = productList.get(i);
			item.addQuantity(quantity);
		}
		else{//i<0 when i=-1 
			item = new LinkedItem(product, quantity, product.getCode());
			productList.add(item);
		}
		product.changeQty("subtract", quantity);
		return item.orderID;
			
		}
	/**
	 * This method remove product into the linkeditem
	 * @param product
	 * @param quantity
	 */
	public void remove(Product product, int quantity){
		int i = findIndex(product, productList);
		if(i>=0){
			LinkedItem item = productList.get(i);
			item.removeQuantity(quantity);
			product.changeQty("add", quantity);
		}
	}
	public ArrayList<LinkedItem> getProductList(){
		return productList;
	}
	/**
	 * This method display the shopping cart.
	 * @param productList
	 * @return return the product list in this shopping cart
	 */
	protected ArrayList<LinkedItem> displayShoppingCart(ArrayList<LinkedItem> productList){
		this.productList =productList;
		return productList;
	}
	/**
	 * This method calculate the invoice
	 * @param Productlist
	 * @return return the invoice of the shopper
	 */
	protected double calculatetotal(Graph G, Shopper customer, City whereTo){
		for(int i =0; i<productList.size();i++){
			invoice = invoice + productList.get(i).price; 
		}
		invoice += calculateDelivery(G, customer, whereTo);
		return invoice;
	}
	/**
	 * This method remove all item from the product list
	 */
	protected void cleanupShoppingCart(){
		productList.clear();		
	}
	/**
	 * This method calculate the delivery free by the distance
	 * @param G
	 * @param customer
	 * @param whereTo
	 * @return the delivery fees
	 */
	protected double calculateDelivery(Graph G, Shopper customer, City whereTo){
		double result = 0;
		try{
	//		double distance = shopper.address.nearestDistance;
	//		result = distance * 0.1;
			result = whereTo.calculateDistoShopper(customer, G);
			System.out.println("Shopping Cart calculateDelivery distance: " + result);
			result = result*0.01;
			return result;
		}catch(NullPointerException e){
			System.out.println("The Address has a problem");
		}
		return result;
	}
	//cleanup shopping cart/ deduct the quantity from inventory(?product / and print receipt
	/**
	 * This method is used to checkout. it clean up the product list and calculate the invoice;
	 * @param DC
	 * @param DCList
	 */
//	public void checkout(DistributionCenter DC, ArrayList<DistributionCenter> DCList){
//		Set<DistributionCenter> whoHas = Availability(DC, DCList);
//		productList.clear();
//		for (LinkedItem item: productList){
//			System.out.println(item.toString());
//			}
//	}
	
	/**
	 * This method is used to checkout. it clean up the product list and calculate the invoice;
	 */
	public double checkout(){
		for(LinkedItem item : productList){
			OrderHistory.history.add(item);
			item.product.changeQty("subtract", item.quantity);
			Inventory.SalesReportSet.put(item.product, item.quantity);

			for(Product pro : Inventory.SalesReportSet.keySet()){
				if(pro.equals(item.product)){
					Inventory.SalesReportSet.put(pro, Inventory.SalesReportSet.get(pro) + item.quantity);
					break;
				}
			}
		}
		System.out.println("shoppingCart checkout cityList: " + shopper.inventory.cityList);
		System.out.println("shoppingCart checkout shopper city: " + shopper.city);
		for(City place : shopper.inventory.cityList){
			System.out.println("shoppingCart checkout 1");
			if(place.city.equals(shopper.city)){
				System.out.println("shoppingCart checkout 2");
				ArrayList<City> deliveryRoute = new ArrayList<City>();
				City closest = null; //closest city that has DC
				deliveryRoute = shopper.inventory.G.BFSVisitForShopper(place);
				closest = deliveryRoute.get(deliveryRoute.size() - 1);
				System.out.println("shoppingCart checkout closest: " + (closest == null));
				if(closest != null){
					System.out.println("shoppingCart checkout 3");
					Availability(closest.Dcenter, shopper.inventory.DCList); // subtracted quantity from mapOfCenter
					double deliveryCost = calculateDelivery(shopper.inventory.G, shopper, closest);
					productList.clear();
					System.out.println("shoppingCart checkout deliveryCost: " + deliveryCost);
					return deliveryCost;
				}else{
					System.out.println("shoppingCart checkout 4");
					productList.clear();
					System.out.println("no closest city can be searched for this shopper");
					return -1.0;
				}
			}
		}
		System.out.println("no such cityList that can find or no city satisfy shopper's city");
		return -1.0;
//		for (LinkedItem item: productList){
//			System.out.println(item.toString());
//			}
	
	}
	/**
	 * Used to check the delivery route from shopper home to the nearest DC
	 * @return a list of city
	 */
	protected ArrayList<String> CheckDeliveryRoute(){
		ArrayList<String> result = null;
		ArrayList<City> route = new ArrayList<City>();
		City nearestOne = null;
		City startVertex = null;
		if(productList.isEmpty()){
			System.out.println("no need to deliver since cart is empty");
		}else{
			for(City place : shopper.inventory.cityList){
				if(place.city == shopper.city){
					startVertex = place;
				}
			}
			if(startVertex == null){
				System.out.println("your address might be wrong");
			}else{
				route = shopper.inventory.G.BFSVisitForShopper(startVertex);
				nearestOne = route.get(route.size() - 1);
				result = new ArrayList<String>();
				for(City place : route){
					result.add(place.city);
				}
			}
		}
		return result;
		
	}
	
	/**
	 * This is the method used to determine which set of DC can satisfy shopper's order
	 * @param DC
	 * @param DCList
	 * @return -> set of distributionCenters that have stocks
	 */
	public void Availability(DistributionCenter DC, ArrayList<DistributionCenter> DCList){
		Set<DistributionCenter> whereFrom = new HashSet<DistributionCenter>();
		ArrayList<LinkedItem> copyCart = new ArrayList<LinkedItem>();
		copyCart.addAll(productList);
		for(LinkedItem item : copyCart){
			HashMap<DistributionCenter, Integer> centerMap = item.product.mapOfCenter;
			if(centerMap.containsKey(DC)){
				if(centerMap.get(DC) >= item.quantity){
					shopper.orderHistory.history.add(item);
					productList.remove(findIndex(item.product, productList));
					item.product.changeQty("subtract", item.quantity);
					item.product.mapOfCenter.put(DC, centerMap.get(DC) - item.quantity);
					whereFrom.add(DC);
					Distributefrom.put(DC, item.quantity);
				}else if(centerMap.get(DC) != 0 && centerMap.get(DC)<item.quantity){
					item.quantity -= centerMap.get(DC);
					item.product.mapOfCenter.put(DC, 0);
				}
			}
		}
		copyCart.clear();
		copyCart.addAll(productList);
		if(copyCart.isEmpty()){
			//return whereFrom;
		}else{
			for(DistributionCenter center : DCList){
				if(!copyCart.isEmpty()){
					for(LinkedItem item : copyCart){
						HashMap<DistributionCenter, Integer> centerMap = item.product.mapOfCenter;
						if(!center.equals(DC) && centerMap.containsKey(center)){
							if(centerMap.get(center) >= item.quantity){
								shopper.orderHistory.history.add(item);
								productList.remove(findIndex(item.product, productList));
								item.product.changeQty("subtract", item.quantity);
								item.product.mapOfCenter.put(center, centerMap.get(center) - item.quantity);
								whereFrom.add(center);
								Distributefrom.put(center, item.quantity);
							}else if(centerMap.get(center) != 0 && centerMap.get(center) < item.quantity){
								item.quantity -= centerMap.get(center);
								item.product.mapOfCenter.put(center, 0);
							}
						}
					}
				}
				copyCart.clear();
				copyCart.addAll(productList);
			}
			//return whereFrom;
		}
	}
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
	 * Get the product list
	 * @return product list
	 */
	public ArrayList<LinkedItem> getlist(){
		return productList;
	}
	/**
	 * Get the shopper 
	 * @return shopper object
	 */
	public Shopper getsp() {
		// TODO Auto-generated method stub
		return shopper;
	}
	
		
	
	
	

}
