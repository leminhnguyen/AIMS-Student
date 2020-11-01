package views.screen.payment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import utils.Configs;
import views.screen.BaseScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PaymentScreen extends BaseScreen implements Initializable {

	public PaymentScreen(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

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
		BaseScreen resultScreen = new ResultScreen(this.stage, Configs.RESULT_SCREEN_PATH);
		resultScreen.setPreviousScreen(this);
		resultScreen.setScreenTitle("Result Screen");
		resultScreen.show();
	}

	public void requestToPayOrder(){

	}

}