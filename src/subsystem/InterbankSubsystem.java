package subsystem;

import entity.exception.InternalServerErrorException;
import entity.exception.InvalidCardException;
import entity.exception.NotEnoughBalanceException;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem.interbank.InterbankSubsystemController;

public class InterbankSubsystem implements InterbankInterface {

	/**
	 * @see InterbankInterface#payOrder(entity.payment.CreditCard, int,
	 *      java.lang.String)
	 */
	public PaymentTransaction payOrder(CreditCard card, int amount, String contents)
			throws InternalServerErrorException, InvalidCardException, NotEnoughBalanceException {
		InterbankSubsystemController ctrl = new InterbankSubsystemController();
		PaymentTransaction transaction = ctrl.payOrder(card, amount, contents);
		return transaction;
	}

	/**
	 * @see InterbankInterface#refund(entity.payment.CreditCard, int,
	 *      java.lang.String)
	 */
	public PaymentTransaction refund(CreditCard card, int amount, String contents) {
		InterbankSubsystemController ctrl = new InterbankSubsystemController();
		return ctrl.refund(card, amount, contents);
	}

	/**
	 * @see InterbankInterface#getBalance(entity.payment.CreditCard)
	 */
	public int getBalance(CreditCard card) {
		InterbankSubsystemController ctrl = new InterbankSubsystemController();
		return ctrl.getBalance(card);
	}

}
