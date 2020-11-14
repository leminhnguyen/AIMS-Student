package subsystem;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

public interface InterbankInterface {

	public abstract PaymentTransaction payOrder(CreditCard card, int amount, String contents) throws PaymentException, UnrecognizedException;

	public abstract PaymentTransaction refund(CreditCard card, int amount, String contents) throws PaymentException, UnrecognizedException;

}
