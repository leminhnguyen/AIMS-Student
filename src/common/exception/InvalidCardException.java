<<<<<<< HEAD:src/common/exception/InvalidCardException.java
package common.exception;;
=======
package common.exception;
>>>>>>> features/pay-order:src/entity/exception/InvalidCardException.java

public class InvalidCardException extends PaymentException {
	public InvalidCardException() {
		super("ERROR: Invalid card!");
	}
}
