package project;



public class GraphIsFullException extends Exception{
	
	public GraphIsFullException(){
		super();
	}
	public GraphIsFullException(String message){
		super(message);
	}
}