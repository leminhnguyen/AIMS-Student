package common.exception;;

/**
 * The ProcessInvoiceException wraps all unchecked exceptions You can use this
 * exception to inform 
 * 
 * @author nguyenlm
 */
public class ProcessInvoiceException extends AimsException {

	private static final long serialVersionUID = 1091337136123906298L;

	public ProcessInvoiceException() {

	}

	public ProcessInvoiceException(String message) {
		super(message);
	}

}
