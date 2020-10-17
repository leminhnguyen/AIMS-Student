package views.handler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import utils.Configs;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PaymentHandler extends ScreenHandler implements Initializable {

	public PaymentHandler(Stage stage, String screenPath) throws IOException {
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
	void confirmPayment(MouseEvent event) throws IOException {
		ScreenHandler controller = new ResultHanler(this.stage, Configs.RESULT_SCREEN_PATH);
		controller.setPreviousScreen(this);
		controller.setScreenTitle("Result Screen");
		controller.show();
	}

}
