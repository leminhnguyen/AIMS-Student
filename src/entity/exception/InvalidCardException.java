package entity.exception;

public class InvalidCardException extends RuntimeException {
	public InvalidCardException() {

	}

	public InvalidCardException(String message) {
		super(message);
	}

}
