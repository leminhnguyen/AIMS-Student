package views.screen.invoice;

import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Logger;

import entity.order.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;

public class InvoiceScreenHandler extends BaseScreenHandler {

	private static Logger LOGGER = Utils.getLogger(InvoiceScreenHandler.class.getName());

	@FXML
	private Label pageTitle;

	@FXML
	private Label name;

	@FXML
	private Label phone;

	@FXML
	private Label province;

	@FXML
	private Label address;

	@FXML
	private Label instructions;

	@FXML
	private Label subtotal;

	@FXML
	private Label shippingFees;

	@FXML
	private Label total;

	private Order order;

	private int amount = 0;

	private String contents;
	
	public InvoiceScreenHandler(Stage stage, String screenPath, Order order) throws IOException {
		super(stage, screenPath);
		this.order = order;
		this.amount = order.getAmount() + order.getShippingFees();
		this.contents = getTransactionContents();
		setInvoiceInfo();
	}

	private void setInvoiceInfo(){
		Hashtable<String, String> deliveryInfo = order.getDeliveryInfo();
		name.setText(deliveryInfo.get("name"));
		province.setText(deliveryInfo.get("province"));
		instructions.setText(deliveryInfo.get("instructions"));
		address.setText(deliveryInfo.get("address"));
		subtotal.setText(Utils.getCurrencyFormat(order.getAmount()));
		shippingFees.setText(Utils.getCurrencyFormat(order.getShippingFees()));
		total.setText(Utils.getCurrencyFormat(this.amount));
	}
	
	private String getTransactionContents() {
		return "PAYMENT TRANSACTION FOR AIMS ORDER";
	}
	
	@FXML
	void confirmInvoice(MouseEvent event) throws IOException {
		BaseScreenHandler paymentScreen = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH, amount, contents);
		paymentScreen.setPreviousScreen(this);
		paymentScreen.setScreenTitle("Payment Screen");
		paymentScreen.show();
		LOGGER.info("Confirmed invoice");
	}

}
