package views.screen.invoice;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import entity.order.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreen;
import views.screen.payment.PaymentScreen;

public class InvoiceScreen extends BaseScreen {

	private static Logger LOGGER = Utils.getLogger(InvoiceScreen.class.getName());

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

	public InvoiceScreen(Stage stage, String screenPath, Order order) throws IOException {
		super(stage, screenPath);
		this.order = order;
		setInvoiceInfo();
	}

	private void setInvoiceInfo(){
		HashMap<String, String> deliveryInfo = order.getDeliveryInfo();
		name.setText(deliveryInfo.get("name"));
		province.setText(deliveryInfo.get("province"));
		instructions.setText(deliveryInfo.get("instructions"));
		address.setText(deliveryInfo.get("address"));
		subtotal.setText(Utils.getCurrencyFormat(order.getAmount()));
		shippingFees.setText(Utils.getCurrencyFormat(order.getShippingFees()));
		total.setText(Utils.getCurrencyFormat(order.getAmount() + order.getShippingFees()));
	}

	@FXML
	void confirmInvoice(MouseEvent event) throws IOException {
		BaseScreen paymentScreen = new PaymentScreen(this.stage, Configs.PAYMENT_SCREEN_PATH);
		paymentScreen.setPreviousScreen(this);
		paymentScreen.setScreenTitle("Payment Screen");
		paymentScreen.show();
		LOGGER.info("Confirmed invoice");
	}

}
