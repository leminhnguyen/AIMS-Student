package views.screen.payment;

import java.io.IOException;
import java.util.Map;

import controller.PaymentController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreen;

public class PaymentScreen extends BaseScreen {

	private int amount;

	private String contents;

	public PaymentScreen(Stage stage, String screenPath, int amount, String contents) throws IOException {
		super(stage, screenPath);
		this.amount = amount;
		this.contents = contents;
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

	@FXML
	void confirmToPayOrder(MouseEvent event) throws IOException {
		PaymentController ctrl = new PaymentController();
		Map<String, String> response = ctrl.payOrder(this.amount, this.contents, cardNumber.getText(), holderName.getText(),
				expirationDate.getText(), securityCode.getText());
		System.out.println(response);
		BaseScreen resultScreen = new ResultScreen(this.stage, Configs.RESULT_SCREEN_PATH, response.get("RESULT"), response.get("MESSAGE") );
		resultScreen.setPreviousScreen(this);
		resultScreen.setScreenTitle("Result Screen");
		resultScreen.show();
	}

}