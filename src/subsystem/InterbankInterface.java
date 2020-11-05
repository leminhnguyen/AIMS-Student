package subsystem;

import entity.exception.InternalServerErrorException;
import entity.exception.InvalidCardException;
import entity.exception.NotEnoughBalanceException;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

public interface InterbankInterface {

	public abstract PaymentTransaction payOrder(CreditCard card, int amount, String contents) throws InternalServerErrorException, InvalidCardException, NotEnoughBalanceException;

	public abstract PaymentTransaction refund(CreditCard card, int amount, String contents) throws InternalServerErrorException, InvalidCardException;

	public abstract int getBalance(CreditCard card) throws InternalServerErrorException, InvalidCardException;

}
