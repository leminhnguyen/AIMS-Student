package entity.exception;

public class NotEnoughBalanceException extends RuntimeException{

	public NotEnoughBalanceException() {
		
	}
	
	public NotEnoughBalanceException(String message) {
		super(message);
	}

}
