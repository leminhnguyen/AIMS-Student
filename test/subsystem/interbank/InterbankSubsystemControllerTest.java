package subsystem.interbank;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.payment.CreditCard;

public class InterbankSubsystemControllerTest {

	private static InterbankSubsystemController ctrl;
	private static CreditCard card;

	@BeforeAll
	public static void init() {
		card = new CreditCard("118609_group1_2020", "Group 1", 185, "1125");
		ctrl = new InterbankSubsystemController();
	}

	@Test
	void payOrderTest() {
		ctrl.payOrder(card, 100, "Pay 11/05/2020");
	}
}
