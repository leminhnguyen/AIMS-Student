package common.exception;

/**
 * The PlaceOrderException wraps all unchecked exceptions You can use this
 * exception to inform 
 * 
 * @author nguyenlm
 */
public class PlaceOrderException extends RuntimeException {

	private static final long serialVersionUID = 1091337136123906298L;

	public PlaceOrderException() {

	}

	public PlaceOrderException(String message) {
		super(message);
	}

}
