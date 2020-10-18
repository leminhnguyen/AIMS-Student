package views.screen.invoice;

import java.io.IOException;
import java.util.List;

import utils.Configs;
import views.screen.BaseScreen;
import views.screen.payment.PaymentScreen;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InvoiceScreen extends BaseScreen {

	public InvoiceScreen(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}

	public void forward(List<Node> components) {
		this.message = components;
		this.subtotal.setText(((Label) this.message.get(0)).getText());
	}

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

	@FXML
	void requestToPayOrder(MouseEvent event) throws IOException {
		BaseScreen controller = new PaymentScreen(this.stage, Configs.PAYMENT_SCREEN_PATH);
		controller.setPreviousScreen(this);
		controller.setScreenTitle("Payment Screen");
		controller.show();
	}

}
