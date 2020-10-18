package views.screen.shipping;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import utils.Configs;
import views.screen.BaseScreen;
import views.screen.invoice.InvoiceScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ShippingScreen extends BaseScreen implements Initializable {

	public ShippingScreen(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.province.getItems().addAll(Configs.PROVINCES);
	}

	@FXML
	private Label screenTitle;

	@FXML
	private TextField name;

	@FXML
	private TextField phone;

	@FXML
	private TextField address;

	@FXML
	private TextField instructions;

	@FXML
	private ComboBox<String> province;

	@FXML
	void confirmDelivery(MouseEvent event) throws IOException {
		BaseScreen controller = new InvoiceScreen(this.stage, Configs.INVOICE_SCREEN_PATH);
		controller.setPreviousScreen(this);
		controller.setScreenTitle("Invoice Screen");

		// TODO Calculate shipping fees and forward shipping info
//		this.message.add(shippingFees);
//		this.message.add(shippingInfo);
		controller.forward(this.message);
		controller.show();
	}

}
