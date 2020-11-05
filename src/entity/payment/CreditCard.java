package entity.payment;

import java.sql.Timestamp;

public class CreditCard {
	private String cardCode;
	private String owner;
	private int cvvCode;
	private String dateExpired;

	public CreditCard(String cardNumber, String cardHolderName, String expirationDate, int cardSecurityCode) {
		super();
		this.cardCode = cardNumber;
		this.owner = cardHolderName;
		this.dateExpired = expirationDate;
		this.cvvCode = cardSecurityCode;
	}
}
