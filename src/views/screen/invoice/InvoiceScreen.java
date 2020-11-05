package views.screen.invoice;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import entity.invoice.Invoice;
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

	private Invoice invoice;

	public InvoiceScreen(Stage stage, String screenPath, Invoice invoice) throws IOException {
		super(stage, screenPath);
		this.invoice = invoice;
		setInvoiceInfo();
	}

	private void setInvoiceInfo(){
		HashMap<String, String> deliveryInfo = invoice.getOrder().getDeliveryInfo();
		name.setText(deliveryInfo.get("name"));
		province.setText(deliveryInfo.get("province"));
		instructions.setText(deliveryInfo.get("instructions"));
		address.setText(deliveryInfo.get("address"));
		subtotal.setText(Utils.getCurrencyFormat(invoice.getOrder().getAmount()));
		shippingFees.setText(Utils.getCurrencyFormat(invoice.getOrder().getShippingFees()));
		total.setText(Utils.getCurrencyFormat(invoice.getOrder().getAmount() + invoice.getOrder().getShippingFees()));
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
