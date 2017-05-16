package project;

import java.io.Serializable;

/**
 * This class is the address of shopper. 
 * 
 */
public class Address implements PleaceInterface, Serializable{
	protected String street;
	protected int xCoord;
	protected int yCoord;
	protected DistributionCenter nearest = null;
	protected double nearestDistance = 0;
	
	public Address(String street){
		this.street = street;
	}
	
	public Address(String street, int xCoord, int yCoord){
		this.street = street;
		this.xCoord =xCoord;
		this.yCoord = yCoord;
	}
	/**
	 * This method calculate the distance of two different place
	 * @param p1,p2
	 * @return -> return the distance of two different place in the graph
	 */

//	@Override
//	public double calculateDis(PleaceInterface p2) {
//		double x = p2.getxCoord() - xCoord;
//		double y = p2.getyCoord() - yCoord;
//		double result = Math.sqrt((x*x) + (y*y));
//		return result;
//	}
//	/**
//	 * This method is the getter of xcoord of a Address;
//	 * @return -> return the xcoord;
//	 */
//	
//
//	@Override
//	public int getxCoord() {
//		return xCoord;
//	}
//	/**
//	 * This method is the getter of ycoord of a Address;
//	 * @return -> return the ycoord;
//	 */
//
//	@Override
//	public int getyCoord() {
//		// TODO Auto-generated method stub
//		return yCoord;
//	}
	/**
	 * This method can add the distributioncenter that is nearest to the shopper
	 * @param cd
	 * @param distance
	 */
	
	public void addDC(DistributionCenter dc, double distance){
		this.nearest = dc;
		this.nearestDistance = distance;
	}
}
