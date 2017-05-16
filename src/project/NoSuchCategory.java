package project;
/**
 * 
 * This exception will be called when there is no such category.
 *
 */
public class NoSuchCategory extends RuntimeException{
	
	public NoSuchCategory() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public NoSuchCategory(String message){
		super(message);
	}

}
