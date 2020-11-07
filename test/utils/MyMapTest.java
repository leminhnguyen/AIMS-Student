package utils;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class MyMapTest {
//	@Test
	void toStringWithColonTest() throws IllegalArgumentException, IllegalAccessException {
		MyMap text = new MyMap();
		text.put("secretKey", "BUXj/7/gHHI=");
//		text.put("version", "1.0.1");

		MyMap transaction = new MyMap();
		transaction.put("cardCode", "118609_group1_2020");
		transaction.put("owner", "Group 1");
		transaction.put("cvvCode", "185");
		transaction.put("dateExpired", "1125");
		
		transaction.put("command", "pay");		
		transaction.put("transactionContent", "Thanh toan Mass");
		transaction.put("amount", "100");
		transaction.put("createdAt", "2020-11-04 10:55:26");
		
		text.put("transaction", transaction);
		System.out.println("plaintText:" + text.toJSON());
		System.out.println("cipherText:" + Utils.md5(text.toJSON()));
	}
	@Test
	void toMyMapString() {
		MyMap text = new MyMap();
		text.put("secretKey", "BUXj/7/gHHI=");
//		text.put("version", "1.0.1");

		MyMap transaction = new MyMap();
		transaction.put("cardCode", "118609_group1_2020");
		transaction.put("owner", "Group 1");
		transaction.put("cvvCode", "185");
		transaction.put("dateExpired", "1125");
		
		transaction.put("command", "pay");		
		transaction.put("transactionContent", "Thanh toan Mass");
		transaction.put("amount", "100");
		transaction.put("createdAt", "2020-11-04 10:55:26");
		
		text.put("transaction", transaction);
		
		System.out.println(text.toJSON());
		Map<String, Object> m = MyMap.toMyMap(text.toJSON(), 0);
		System.out.println(m);
	}
}
