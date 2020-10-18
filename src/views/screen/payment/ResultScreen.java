package views.screen.payment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import utils.Configs;
import views.screen.BaseScreen;
import views.screen.cart.CartScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ResultScreen extends BaseScreen implements Initializable {

	public ResultScreen(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	private Label pageTitle;

	@FXML
	private Label resultLabel;

	@FXML
	private Label messageLabel;

	@FXML
	private Button okButton;

	@FXML
	void confirmPayment(MouseEvent event) throws IOException {
		BaseScreen cartController = new CartScreen(this.stage, Configs.CART_SCREEN_PATH);
		cartController.setScreenTitle("Cart Screen");
		cartController.show();
	}

}
