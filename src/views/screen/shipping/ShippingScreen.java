package views.screen.shipping;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

import controller.PlaceOrderController;
import entity.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreen;
import views.screen.invoice.InvoiceScreen;
import views.screen.popup.PopupScreen;

public class ShippingScreen extends BaseScreen implements Initializable {

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

	private Order order;

	public ShippingScreen(Stage stage, String screenPath, Order order) throws IOException {
		super(stage, screenPath);
		this.order = order;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
		name.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                content.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
		this.province.getItems().addAll(Configs.PROVINCES);
	}

	@FXML
	void submitDeliveryInfo(MouseEvent event) throws IOException, InterruptedException, SQLException {

		// add info to messages
		HashMap messages = new HashMap<>();
		messages.put("name", name.getText());
		messages.put("phone", phone.getText());
		messages.put("address", address.getText());
		messages.put("instructions", instructions.getText());
		messages.put("province", province.getValue());
		try {
			// process and validate delivery info
			getBController().processDeliveryInfo(messages);
		} catch (InvalidDeliveryInfoException e) {
			PopupScreen.error("wrong format input");
			throw new InvalidDeliveryInfoException("wrong format input");
		}
	
		// calculate shipping fees
		int shippingFees = getBController().calculateShippingFee(order);
		order.setShippingFees(shippingFees);
		order.setDeliveryInfo(messages);
		
		// create invoice screen
		Invoice invoice = getBController().createInvoice(order);
		BaseScreen invoiceScreen = new InvoiceScreen(this.stage, Configs.INVOICE_SCREEN_PATH, invoice);
		invoiceScreen.setPreviousScreen(this);
		invoiceScreen.setHomeScreen(homeScreen);
		invoiceScreen.setScreenTitle("Invoice Screen");
		invoiceScreen.setBController(getBController());
		invoiceScreen.show();
	}

	public PlaceOrderController getBController(){
		return (PlaceOrderController) super.getBController();
	}

	public void notifyError(){
		// TODO: implement later on if we need
	}

}
