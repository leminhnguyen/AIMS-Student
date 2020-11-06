package subsystem.interbank;

import java.util.Map;

import entity.exception.InvalidCardException;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import utils.Configs;
import utils.MyMap;
import utils.Utils;

public class InterbankSubsystemController {

	private static final String PUBLIC_KEY = "AQzdE8O/fR8=";
	private static final String SECRET_KEY = "BUXj/7/gHHI=";
	private static final String PAY_COMMAND = "pay";
	private static final String VERSION = "1.0.1";

	// TODO
//	private static final String API_PATH;

	private static InterbankBoundary interbankBoundary = new InterbankBoundary();

//	public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
//		Map<String, Object> transaction = new JSON<String, Object>();
//		
//		try {
//			transaction.putAll(Utils.map(card));
//		} catch (IllegalArgumentException | IllegalAccessException e) {
//			throw new InvalidCardException("ERROR: INVALID CARD!");
//		}
//		transaction.put("command", PAY_COMMAND);
//		transaction.put("transactionContent", contents);
//		transaction.put("amount", amount);
//		transaction.put("createdAt", Utils.getToday());
//		
//		
//		Map<String, Object> requestMap = new JSON<String, Object>();
//		requestMap.put("version", VERSION);
//		requestMap.put("transaction", transaction);
//
//		String response = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateURL(requestMap));
////		System.out.println(response);
//		return null;
//	}

	public PaymentTransaction refund(CreditCard card, int amount, String contents) {
		return null;
	}

	public int getBalance(CreditCard card) {
		return 0;
	}

	private String generateData(Map<String, Object> data) {
		return ((MyMap) data).toJSON();
	}

	public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
		Map<String, Object> transaction = new MyMap();

		try {
			transaction.putAll(MyMap.toMyMap(card));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new InvalidCardException("ERROR: INVALID CARD!");
		}
		transaction.put("command", PAY_COMMAND);
		transaction.put("transactionContent", contents);
		transaction.put("amount", amount);
		transaction.put("createdAt", Utils.getToday());

		Map<String, Object> plainText = new MyMap();
		plainText.put("secretKey", SECRET_KEY);
		plainText.put("transaction", transaction);

		Map<String, Object> requestMap = new MyMap();
		requestMap.put("version", VERSION);
		requestMap.put("transaction", transaction);
		requestMap.put("appCode", PUBLIC_KEY);
		requestMap.put("hashCode", Utils.md5(((MyMap) plainText).toJSON()));

		String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
		MyMap response = null;
		try {
			response = MyMap.toMyMap(responseText, 0);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return makePaymentTransaction(response);
	}
//	{"secretKey":"BUXj/7/gHHI=","version":"1.0.1","transaction":{"cardCode":"118609_group1_2020","owner": "Group 1","cvvCode":"185","dateExpired":"1125","command":"pay","transactionContent":"Thanh toan Mass","amount":"100","createdAt":"2020-11-04 10:55:26"}

	private PaymentTransaction makePaymentTransaction(MyMap response) {
		if (response == null)
			return null;
		MyMap transcation = (MyMap) response.get("transaction");
		CreditCard card = new CreditCard((String) transcation.get("cardCode"), (String) transcation.get("owner"),
				Integer.parseInt((String) transcation.get("cvvCode")), (String) transcation.get("dateExpired"));
		PaymentTransaction trans = new PaymentTransaction((String) response.get("errorCode"), card, (String) transcation.get("transactionId"), (String) transcation.get("transactionContent"), Integer.parseInt((String) transcation.get("amount")), (String) transcation.get("createdAt"));
		return trans;
	}

}
