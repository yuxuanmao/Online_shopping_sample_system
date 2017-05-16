package project;
/**
 * 
 * @author Eva
 * The class AccessDeniedException is extends RuntimeException. It will be called 
 * when the user don't have access to the method.
 */
public class AccessDeniedException extends RuntimeException{
	  public AccessDeniedException()
	  {
	    super();
	  }

	  public AccessDeniedException(String message)
	  {
	    super(message);
	  }
}
