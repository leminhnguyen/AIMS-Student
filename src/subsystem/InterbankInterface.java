package subsystem;

import utils.exception.PaymentException;
import utils.exception.UnrecognizedException;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

public interface InterbankInterface {

	public abstract PaymentTransaction payOrder(CreditCard card, int amount, String contents) throws PaymentException, UnrecognizedException;

	public abstract PaymentTransaction refund(CreditCard card, int amount, String contents) throws PaymentException, UnrecognizedException;

}
