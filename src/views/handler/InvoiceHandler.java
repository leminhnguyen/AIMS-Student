package views.handler;

import java.io.IOException;
import java.util.List;

import application.Config;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InvoiceHandler extends ScreenHandler {

	public InvoiceHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
	}

	protected void forward(List<Node> components) {
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
		ScreenHandler controller = new PaymentHandler(this.stage, Config.PAYMENT_SCREEN_PATH);
		controller.setPreviousScreen(this);
		controller.setScreenTitle("Payment Screen");
		controller.show();
	}

}
