package subsystem.interbank;

import org.junit.jupiter.api.Test;

import entity.payment.CreditCard;

public class InterbankSubsystemControllerTest {
	@Test
	void payOrderTest() {
		CreditCard card = new CreditCard("1234 4567 7890", "DO MINH HIEU", "1123", 123);
		InterbankSubsystemController ctrl = new InterbankSubsystemController();
		ctrl.payOrder(card, 100, "Pay 11/05/2020");
	}
}
