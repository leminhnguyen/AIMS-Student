package entity.exception;

/**
 * The NegativeQuantityException wraps all unchecked exceptions
 * You can use this exception to inform negative quantity in Media products
 * @author nguyenlm
 */
public class NegativeQuantityException extends RuntimeException{
    
	private static final long serialVersionUID = 1091337136123906298L;
	
	public NegativeQuantityException() {
		
	}
	
	public NegativeQuantityException(String message) {
		super(message);
	}

}