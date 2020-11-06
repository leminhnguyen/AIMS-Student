package controller;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import entity.exception.InternalServerErrorException;
import entity.exception.InvalidCardException;
import entity.exception.NotEnoughBalanceException;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;
import utils.MyMap;

public class PaymentController extends BaseController {

	private String getExpirationDate(String date) throws InvalidCardException {
		String[] strs = date.split("/");
		if (strs.length != 2) {
			throw new InvalidCardException("ERROR: The expiration date is in invalid format");
		}

		String expirationDate = null;
		int month = -1;
		int year = -1;

		try {
			month = Integer.parseInt(strs[0]);
			year = Integer.parseInt(strs[1]);

		} catch (NumberFormatException ex) {
			throw new InvalidCardException("ERROR: The expiration date is invalid");
		}

		if (month < 1 || month > 12 || year < Calendar.getInstance().get(Calendar.YEAR) % 100 || year > 100) {
			throw new InvalidCardException("ERROR: The expiration date is invalid");
		} else {
			expirationDate = strs[0] + strs[1];
		}

		return expirationDate;
	}

	public Map<String, String> payOrder(int amount, String contents, String cardNumber, String cardHolderName,
			String expirationDate, String securityCode)
			throws InternalServerErrorException, InvalidCardException, NotEnoughBalanceException {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "PAYMENT FAILED!");
		try {
			CreditCard card = new CreditCard(cardNumber, cardHolderName, Integer.parseInt(securityCode),
					getExpirationDate(expirationDate));

			InterbankInterface interbank = new InterbankSubsystem();
			PaymentTransaction transaction = interbank.payOrder(card, amount, contents);
			try {
				System.out.println(MyMap.toMyMap(transaction));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO save transaction

			result.put("RESULT", "PAYMENT SUCCESSFUL!");
			result.put("MESSAGE", "");
		} catch (InternalServerErrorException ex) {
			result.put("MESSAGE", "Internal Server Error!");
		} catch (InvalidCardException ex) {
			result.put("MESSAGE", "Invalid Card!");
		} catch (NotEnoughBalanceException ex) {
			result.put("MESSAGE", "Not Enough Balance!");
		}
		return result;
	}
}