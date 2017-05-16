package project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class City {
/**
 * author: Leon Mao
 * 
 * This class is used for each city we added and to store DistributionCenter or shopper
 * 
 */
	protected DistributionCenter Dcenter;
	protected static Set<Shopper> setShopper = new HashSet<Shopper>();
	protected String city;
	protected List<City> preVertex;
	protected List<City> afterVertex;
	
	/**
	 * This is a constructor
	 */
	public City(String city){
		this.city = city;
		this.preVertex = new ArrayList<City>();
		this.afterVertex = new ArrayList<City>();
		this.Dcenter = null;
	}
	
	/**
	 * add distributionCenter to Dcenter
	 * @param DistributionCenter DC
	 */
	public void addDCtoCity(DistributionCenter DC){
		if (Dcenter == null){
			this.Dcenter = DC;
		}else{
			System.out.println("There is a DC in this city already");
		}
	}
	
	/**
	 * add shopper to shopper list
	 * @param Shopper customer
	 */
	public void addShoppertoCity(Shopper customer){
		setShopper.add(customer);
	}
	
	/**
	 * remove DistributionCenter from City
	 * @param DistributionCenter DC
	 */
	public void removeDCfromCity(DistributionCenter DC){
		Dcenter = null;
	}
	
	/**
	 * remove shopper from City
	 * @param Shopper customer
	 */
	public void removeShopperfromCity(Shopper customer){
		setShopper.remove(customer);
	}
	
	/**
	 * Add city to its vertex list
	 * @param City city
	 * @param String code
	 */
	public void addCity(City city, String code){
		if(code == "preVertex"){
			preVertex.add(city);
		}else if(code == "afterVertex"){
			afterVertex.add(city);
		}
	}
	
	/**
	 * calculate the distance from customer's home to this City's distribution center
	 * @param Shopper customer
	 * @param Graph G
	 * @return double
	 */
	public double calculateDistoShopper(Shopper customer, Graph G){
		double distance = 0;
		if(customer.city == city && Dcenter == null){ 
			distance = 0;
		}else{
			// search the nearest city that has DC
			ArrayList<City> route = G.BFSVisitForShopper(this);
			System.out.println("City calculateDistoShopper route: " + route);
			distance = G.calculateDistance(route);
		}
		return distance;
	}
	/**
	 * This method get the string of this city include city name
	 */
	@Override
	public String toString(){
		return city;
	}
	/**
	 * This method gets the distribution center
	 * @return
	 */
	public DistributionCenter getDcenter() {
		return Dcenter;
	}
	/**
	 * This method gets the city
	 * @return
	 */
	public String getCity() {
		return city;
	}
	/**
	 * This method gets the preVertex
	 * @return
	 */
	public List<City> getPreVertex() {
		return preVertex;
	}
	/**
	 * This method gets the AfterVertex
	 * @return
	 */
	public List<City> getAfterVertex() {
		return afterVertex;
	}
	
}
