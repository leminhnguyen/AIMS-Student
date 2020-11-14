package views.screen.payment;

import java.io.IOException;
import java.util.Map;

import controller.PaymentController;
import entity.cart.Cart;
import common.exception.PlaceOrderException;
import entity.invoice.Invoice;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreen;
import views.screen.popup.PopupScreen;

public class PaymentScreen extends BaseScreen {

	@FXML
	private Button btnConfirmPayment;

	@FXML
	private ImageView loadingImage;

	private Invoice invoice;

	public PaymentScreen(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}

	public PaymentScreen(Stage stage, String screenPath, Invoice invoice) throws IOException {
		super(stage, screenPath);
		this.invoice = invoice;
		
		btnConfirmPayment.setOnMouseClicked(e -> {
			try {
				confirmToPayOrder();
				Cart.getCart().emptyCart();
			} catch (Exception exp) {
				System.out.println(exp.getStackTrace());
			}
		});
	}

	@FXML
	private Label pageTitle;

	@FXML
	private TextField cardNumber;

	@FXML
	private TextField holderName;

	@FXML
	private TextField expirationDate;

	@FXML
	private TextField securityCode;

	void confirmToPayOrder() throws IOException{
		String contents = "pay order";
		PaymentController ctrl = new PaymentController();
		Map<String, String> response = ctrl.payOrder(invoice.getAmount(), contents, cardNumber.getText(), holderName.getText(),
				expirationDate.getText(), securityCode.getText());
		System.out.println(response);
		BaseScreen resultScreen = new ResultScreen(this.stage, Configs.RESULT_SCREEN_PATH, response.get("RESULT"), response.get("MESSAGE") );
		resultScreen.setPreviousScreen(this);
		resultScreen.setHomeScreen(homeScreen);
		resultScreen.setScreenTitle("Result Screen");
		resultScreen.show();
	}

}