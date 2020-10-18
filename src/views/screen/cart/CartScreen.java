package views.screen.cart;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.ViewCartController;
import entity.cart.*;
import entity.exception.ViewCartException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreen;
import views.screen.FXMLScreen;
import views.screen.shipping.ShippingScreen;

public class CartScreen extends BaseScreen implements Initializable {

	private static Logger LOGGER = Utils.getLogger(CartScreen.class.getName());

	@FXML
	private ImageView imageLogo;

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

	public CartScreen(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}

	@FXML
	void requestToPlaceOrder(MouseEvent event) throws IOException {
		BaseScreen controller = new ShippingScreen(this.stage, Configs.SHIPPING_SCREEN_PATH);
		controller.setPreviousScreen(this);
		controller.setScreenTitle("Shipping Screen");
		controller.show();
		this.message = new ArrayList<Node>();
		this.message.add(subtotal);
		controller.forward(this.message);
	}

	public void requestToViewCart(BaseScreen prevScreen) throws SQLException {
		setPreviousScreen(prevScreen);
		setScreenTitle("Cart Screen");
		ViewCartController.checkAvailabilityOfProduct();
		displayCartWithMediaAvailability();
		show();
	}
	
	private void displayCartWithMediaAvailability(){
		File file = new File("assets/images/Logo.png");
		Image im = new Image(file.toURI().toString());
		imageLogo.setImage(im);

		try {
			for (Object cm : Cart.getCart().getListMedia()) {

				// get attribute from CartMedia
				CartMedia cartMedia = (CartMedia) cm;
				String title = cartMedia.getMedia().getTitle();
				int price = cartMedia.getPrice();
				String currency = "VND";

				// display the attribute of cart media
				MediaCartScreen mediaCartScreen = new MediaCartScreen(Configs.CART_MEDIA_PATH);
				mediaCartScreen.setCartMedia(cartMedia);

				// add delete button
				Button deleteButton = new Button("Delete");
				deleteButton.setFont(Configs.REGULAR_FONT);
				deleteButton.setOnMouseClicked((e) -> {
					cart.getChildren().remove(mediaCartScreen.getContent());
					this.show();
				});

				// add delete button
				mediaCartScreen.addDescription(deleteButton);

				// add spinner
				cart.getChildren().add(mediaCartScreen.getContent());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
	}
}
