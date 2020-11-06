package subsystem;

import entity.exception.PaymentException;
import entity.exception.UnrecognizedException;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

public interface InterbankInterface {

	public abstract PaymentTransaction payOrder(CreditCard card, int amount, String contents) throws PaymentException, UnrecognizedException;

	public abstract PaymentTransaction refund(CreditCard card, int amount, String contents) throws PaymentException, UnrecognizedException;

}
