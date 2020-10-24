package entity.exception;

/**
 * The MediaUpdateException wraps all unchecked exceptions You can use this
 * exception to inform negative quantity in Media products
 * 
 * @author nguyenlm
 */
public class MediaUpdateException extends RuntimeException {

	private static final long serialVersionUID = 1091337136123906298L;

	public MediaUpdateException() {

	}

	public MediaUpdateException(String message) {
		super(message);
	}

}
