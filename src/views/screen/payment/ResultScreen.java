package views.screen.payment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreen;
import views.screen.home.HomeScreen;

public class ResultScreen extends BaseScreen implements Initializable {

	private String result;
	private String message;

	public ResultScreen(Stage stage, String screenPath, String result, String message) throws IOException {
		super(stage, screenPath);
		this.result = result;
		this.message = message;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		resultLabel.setText(result);
		messageLabel.setText(message);
	}

	@FXML
	private Label pageTitle;

	@FXML
	private Label resultLabel;

	@FXML
	private Button okButton;
	
	@FXML
	private Label messageLabel;

	@FXML
	void confirmPayment(MouseEvent event) throws IOException {
		homeScreen.show();
	}

}
