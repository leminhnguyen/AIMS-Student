package subsystem.interbank;

import java.util.HashMap;
import java.util.Map;

import entity.exception.InvalidCardException;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import utils.Configs;
import utils.Utils;

public class InterbankSubsystemController {

	private static final String PUBLIC_KEY = "AQzdE8O/fR8=";
	private static final String PAY_COMMAND = "pay";
	private static final String VERSION = "1.0.0";
	
	
	// TODO 
//	private static final String API_PATH;

	private static InterbankBoundary interbankBoundary = new InterbankBoundary();

	public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
		Map<String, Object> transaction = new HashMap<String, Object>();
		try {
			transaction.putAll(Utils.map(card));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new InvalidCardException("ERROR: INVALID CARD!");
		}
		transaction.put("command", PAY_COMMAND);
		transaction.put("transactionContent", contents);
		transaction.put("amount", amount);
		transaction.put("createdAt", Utils.getToday());
		
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("version", VERSION);
		requestMap.put("transaction", transaction);

		String response = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateURL(requestMap));
//		System.out.println(response);
		return null;
	}

	public PaymentTransaction refund(CreditCard card, int amount, String contents) {
		return null;
	}

	public int getBalance(CreditCard card) {
		return 0;
	}

	private String generateURL(Map<String, Object> data) {
		System.out.println(data);
		System.out.println(Utils.convertMapToJSON(data));
		return Utils.convertMapToJSON(data);
	}

}
