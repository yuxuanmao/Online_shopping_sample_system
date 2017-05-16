package project;
/**
 * This exception will be called when the quantity is not enough
 * 
 *
 */
public class NotEnoughQuantity extends RuntimeException{

	public NotEnoughQuantity() {
		super();
	}
	public NotEnoughQuantity(String message) {
		super (message);
	}

}
