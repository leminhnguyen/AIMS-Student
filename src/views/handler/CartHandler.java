package views.handler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Config;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CartHandler extends ScreenHandler implements Initializable {

	public CartHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}

	@FXML
	private Label pageTitle;

	@FXML
	private VBox cart;

	@FXML
	private Label subtotal;

	@FXML
	private Label currency;

	@FXML
	private Label shippingFees;

	@FXML
	private Label total;

	/*
	 * Proceed to shipping screen
	 * 
	 */
	@FXML
	void requestToPlaceOrder(MouseEvent event) throws IOException {
		ScreenHandler controller = new ShippingHandler(this.stage, Config.SHIPPING_SCREEN_PATH);
		controller.setPreviousScreen(this);
		controller.setScreenTitle("Shipping Screen");
		controller.show();

		// TODO Calculate subtotal & forward subtotal
		this.message = new ArrayList<Node>();
		this.message.add(subtotal);
		controller.forward(this.message);
	}

	/*
	 * Generate media or query media from database to load into cart screen
	 * 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			for (int i = 1; i <= 8; i++) {
				MediaHandler mediaHandler = new MediaOfCartHandler(Config.MEDIA_PATH);

				// change media
				mediaHandler.changeMedia(i + "", i + "0,000", "VND");

				// add delete button
				Button deleteButton = new Button("Delete");
				deleteButton.setFont(Config.REGULAR_FONT);
				deleteButton.setOnMouseClicked((e) -> {
					cart.getChildren().remove(mediaHandler.getContent());
					this.show();
				});
				mediaHandler.addDescription(deleteButton);

				// add spinner
				cart.getChildren().add(mediaHandler.getContent());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
