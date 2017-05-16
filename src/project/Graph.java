package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


/**
 * This class is the graph. The addresses of shopper and administrator are all in the graph.
 * 
 */
public class Graph implements GraphInterface {
	public static final int DEF_CAPACITY = 10;
	public static final int NULL_EDGE = 0;
	private int numVertices;
	private int maxVertices;
	private City[] vertices;
	private double[][] edges;
	private boolean[] marks; // marks[i] is mark for vertices[i]
	//protected static ArrayList<DistributionCenter> path;
	/**
	 * This is the construction of class graph
	 */
	public Graph() {
		numVertices = 0;
		maxVertices = DEF_CAPACITY;
		vertices = new City[DEF_CAPACITY];
		marks = new boolean[DEF_CAPACITY];
		edges = new double[DEF_CAPACITY][DEF_CAPACITY];
	}
	/**
	 * This is the construction of class graph
	 * @param maxV
	 */
	public Graph(int maxV)
	// Instantiates a graph with capacity maxV.
	{
		numVertices = 0;
		maxVertices = maxV;
		vertices = new City[maxV];
		marks = new boolean[maxV];
		edges = new double[maxV][maxV];
	}
	/**
	 * This method is to check if the graph is empty
	 * @return return true if this graph is empty; otherwise,returns false.
	 */
	public boolean isEmpty()
	// Returns true if this graph is empty; otherwise, returns false.
	{
		return (numVertices == 0);
	}
	/**
	 * This method is to check if the graph is full
	 * @return return true if this graph is full; otherwise,returns false.
	 */
	public boolean isFull()
	// Returns true if this graph is full; otherwise, returns false.
	{
		return (numVertices == maxVertices);
	}
	/**
	 * This method check if the bertices arrys contains vertex. If not, add vertex into the list.
	 * @param vertex
	 */

	public void addVertex(City vertex) throws GraphIsFullException,
			VertexExistsException {
		boolean flag = false;
		for(int i =0; i<numVertices; i++){
			if(vertices[i].getCity().equals(vertex.getCity())){
				flag = true;
			}
		}
		if(flag){
			System.out.println("hi");
			throw new VertexExistsException("Add vertex already exists.");
		}
		if(isFull()){
			throw new GraphIsFullException("Add attempted to a full graph.");
		}
//		if(Arrays.asList(vertices).contains(vertex)){
//			System.out.println("hi");
//			throw new VertexExistsException("Add vertex already exists.");
//		}

		else{
			vertices[numVertices] =vertex;
			numVertices +=1;
			
		}
	}
	/**
	 * This method add edge witht the specified weight from fromvertex to tovertex.
	 * @param fromvertex
	 * @param toVertex
	 */
	public void addEdge(City fromVertex, City toVertex)
	// Adds an edge with the specified weight from fromVertex to toVertex.
	{
        System.out.println("HI, add Edge");
		if(fromVertex != toVertex && Arrays.asList(vertices).contains(fromVertex) && Arrays.asList(vertices).contains(toVertex)){
			int iFrom = Arrays.asList(vertices).indexOf(fromVertex);
			int iTo = Arrays.asList(vertices).indexOf(toVertex);
			edges[iFrom][iTo] = 1.0;
			edges[iTo][iFrom] = 1.0;
			((City)fromVertex).addCity(toVertex, "afterVertex");
			((City)toVertex).addCity(fromVertex, "preVertex");
			
		}
	}
	/**
	 * This method add edge with the specified weight from fromvertex to tovertex.
	 * @param fromvertex
	 * @param toVertex
	 * @param weight
	 */
	public void addEdge(City fromVertex, City toVertex, double weight)
	// Adds an edge with the specified weight from fromVertex to toVertex.
	{
        System.out.println("HI, EDGE");
		if(fromVertex != toVertex && Arrays.asList(vertices).contains(fromVertex) && Arrays.asList(vertices).contains(toVertex)){
			int iFrom = Arrays.asList(vertices).indexOf(fromVertex);
			int iTo = Arrays.asList(vertices).indexOf(toVertex);
			edges[iFrom][iTo] = weight;
			edges[iTo][iFrom] = weight;
			
			((City)fromVertex).addCity(toVertex, "afterVertex");
			((City)toVertex).addCity(fromVertex, "preVertex");
			
		}
	}

	/**
	 * Get the vertices of the city 
	 * @return a queue of the vertices that are adjacent from vertex
	 */
	public Queue<City> getToVertices(City vertex)
	// Returns a queue of the vertices that are adjacent from vertex.
	{
		// Your code goes here
		Queue<City> result = new LinkedList<City>();
		int index = Arrays.asList(vertices).indexOf(vertex);
		for(int i = 0; i < numVertices; i++){
			if(edges[index][i]>0){
				result.add(vertices[i]);
			}
		}
		return(result);
	}
	/**
	 * Clear all vertice marks 
	 */
	public void clearMarks()
	// Sets marks for all vertices to false.
	{
		for (int i = 0; i < numVertices; i++)
			marks[i] = false;
	}

	/**
	 * Mark the vertex  
	 */
	public void markVertex(City vertex)
	// Sets mark for vertex to true.
	{
		// Your code goes here
		int i = Arrays.asList(vertices).indexOf(vertex);
		marks[i] = true;
	}

	/**
	 * Check whether the vertex is marked or not
	 * @return true if vertex is marked; otherwise, returns false.
	 */
	public boolean isMarked(City vertex)
	// Returns true if vertex is marked; otherwise, returns false.
	{
		// Your code goes here
		int i = Arrays.asList(vertices).indexOf(vertex);
		return(marks[i]);
	}
	/**
	 * Uses depth-first search algorithm to visit as much vertices as possible starting from startVertex. 
	 * In the process, keeps track of all vertices reachable from startVertex by adding them to a Set<T> variable. 
	 * Once done, returns the Set<T> that we build up.
	 * @param startVertex
	 * @return the Set<T> that we build up
	 */
	private Set<City> DFSVisit(City startVertex)
	// Uses depth-first search algorithm to visit as much vertices as
	// possible
	// starting from startVertex.
	// In the process, keeps track of all vertices reachable from
	// startVertex
	// by adding them to a Set<T> variable.
	// Once done, returns the Set<T> that we build up.
	//
	{
		// Your code goes here
		Set<City> result = new HashSet<City>();
		Stack<City> holdStack = new Stack<City>();
		holdStack.push(startVertex);
		while(!holdStack.isEmpty()){
			City hold = holdStack.peek();
			holdStack.pop();
			markVertex(hold);
			Queue<City> adjacent = getToVertices(hold);
			City something = adjacent.poll();
			while(something != null){
				if(!isMarked(something)){
					holdStack.push(something);
				}
				something = adjacent.poll();
			}
			//can the element show up twice?
			if(!result.contains(hold)){
				result.add(hold);
			}

		}
		return(result);
	}
	/**
	 * This method is used to find the route from DC that has the stock to nearest DC
	 * 
	 * You may need to think five cases
	 * 1) from this point we have the shortest route to the city(no DC, but have routes to other cities)
	 * 2) from this point we have the shortest route to the city(no DC, and no routes to other cities)
	 * 3) from this point we have the route to the city(yes DC, and yes stock)
	 * 4) from this point we have the shortest route to the city(yes DC, but no stock, and no routes to other cities)
	 * 5) from this point we have the shortest route to the city(yes DC, but no stock, but have routes to other cities)
	 * 
	 * @param startVertex
	 * @param cart
	 * @return
	 */
	public ArrayList<City> BFSVisitBetweenDC(City startVertex, ArrayList<LinkedItem> cart){
		ArrayList<City> result = new ArrayList<City>();
		//Queue<DistributionCenter> children = new LinkedList<DistributionCenter>();
		List<City> children = new ArrayList<City>();
		City nextVertex = startVertex;
		City fastVertex = startVertex;
		double routeWeight = 0.0;
		ArrayList<LinkedItem> stockedItems;
		stockedItems = startVertex.Dcenter.determineStock(cart);
		result.add(startVertex);
		while(stockedItems.size() != cart.size()){	
			nextVertex = fastVertex;
			children.addAll(nextVertex.preVertex);
			children.addAll(nextVertex.afterVertex);
			for(City place : children){
				int nextIndex = Arrays.asList(vertices).indexOf(nextVertex);
				int placeIndex = Arrays.asList(vertices).indexOf(place);
				if(place.Dcenter != null && !place.Dcenter.determineStock(cart).isEmpty()){
					fastVertex = place;
					routeWeight = edges[nextIndex][placeIndex];
					stockedItems = fastVertex.Dcenter.determineStock(cart);
				}else if(routeWeight == 0.0 || edges[nextIndex][placeIndex] <= routeWeight ){
					if(place.Dcenter == null && (place.preVertex.isEmpty() || place.afterVertex.isEmpty())){
						// dead end
					}else{
						stockedItems = new ArrayList<LinkedItem>();
						fastVertex = place;
						routeWeight = edges[nextIndex][placeIndex];
					}
				}
			}
			result.add(fastVertex);
		}
		return result;
		
	}
	
	/**
	 * This method is search the nearest City that has DC from where shopper live(city)
	 * @param startVertex
	 * @return -> return a shortest route from shopper to nearest DC
	 */
	public ArrayList<City> BFSVisitForShopper(City startVertex){
		ArrayList<City> result = new ArrayList<City>();
		//Queue<DistributionCenter> children = new LinkedList<DistributionCenter>();
		List<City> children = new ArrayList<City>();
		City nextVertex = startVertex;
		City fastVertex = startVertex;
		double routeWeight = 0.0;
		//ArrayList<LinkedItem> stockedItems;
		//stockedItems = startVertex.determineStock(cart);
		result.add(startVertex);
		while(nextVertex.Dcenter == null){	
			children.addAll(nextVertex.preVertex);
			children.addAll(nextVertex.afterVertex);
			for(City place : children){
				int nextIndex = Arrays.asList(vertices).indexOf(nextVertex);
				int placeIndex = Arrays.asList(vertices).indexOf(place);
				if(routeWeight == 0.0 || edges[nextIndex][placeIndex] <= routeWeight){
					fastVertex = place;
					routeWeight = edges[nextIndex][placeIndex];
					//stockedItems = fastVertex.determineStock(cart);
				}
			}
			nextVertex = fastVertex;
			result.add(fastVertex);
		}
		return result;
		
	}
	
	/** 
	 * This method is used to calculate the total distance from one point to the end
	 * @param route
	 * @return
	 */
	public double calculateDistance(ArrayList<City> route){
		double weight = 0;
		for(int i=0 ; i < route.size()-1; i++){
			int from = Arrays.asList(vertices).indexOf(route.get(i));
			int to = Arrays.asList(vertices).indexOf(route.get(i + 1));
			System.out.println("G calculateDistance each route weight: " + edges[from][to]);
			weight = weight + edges[from][to];
		}
		return weight;
	}

	/**
	 * For each vertex that does not belong to the connected components already on the list, 
	 * call DFSVisit to obtain a set of vertices connected to the current vertex 
	 * Add the set to the list
	 * @return a list of connected components of the graph
	 */
	public ArrayList<Set<City>> connectedComponents()
	// Returns a list of connected components of the graph
	// For each vertex that does not belong to the connected components
	// already on the list,
	// call DFSVisit to obtain a set of vertices connected to the current
	// vertex
	// Add the set to the list
	{
		// Your code goes here
		Set<City> subresult = new HashSet<City>();
		ArrayList<Set<City>> result = new ArrayList<Set<City>>();
		clearMarks();
		for(int i = 0; i < numVertices; i++){
			if(!isMarked(vertices[i])){
				subresult = DFSVisit(vertices[i]);
				result.add(subresult);
			}
		}
		return(result);
	}
	
	/**
	 * get all city
	 * @return all of the city in vertices
	 */
	public City[] getAllCities(){
		return vertices;
	}
	/**
	 * Get the number of city
	 * @return the number of city
	 */
	public int getNumVertices(){
		return numVertices;
	}
	
}