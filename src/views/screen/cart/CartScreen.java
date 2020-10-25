package views.screen.cart;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.ViewCartController;

import java.util.logging.Logger;

import entity.cart.CartMedia;
import entity.cart.Cart;
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

public class CartScreen extends BaseScreen {

	private static Logger LOGGER = Utils.getLogger(CartScreen.class.getName());

	@FXML
	private ImageView aimsImage;

	@FXML
	private Label pageTitle;

	@FXML
	private VBox vboxCart;

	@FXML
	private Label shippingFees;

	@FXML
	private Label labelAmount;

	@FXML
	private Label labelSubtotal;

	public CartScreen(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}

	public Label getLabelAmount() {
		return labelAmount;
	}

	public Label getLabelSubtotal() {
		return labelSubtotal;
	}

	@FXML
	public void requestToPlaceOrder(MouseEvent event) throws IOException {
		BaseScreen controller = new ShippingScreen(this.stage, Configs.SHIPPING_SCREEN_PATH);
		controller.setPreviousScreen(this);
		controller.setScreenTitle("Shipping Screen");
		controller.show();
		this.message = new ArrayList<Node>();
		this.message.add(labelSubtotal);
		controller.forward(this.message);
	}

	public void requestToViewCart(BaseScreen prevScreen) throws SQLException {
		setPreviousScreen(prevScreen);
		setScreenTitle("Cart Screen");
		ViewCartController.checkAvailabilityOfProduct();
		displayCartWithMediaAvailability();
		show();
	}

	public void updateCart(){
		
	}

	public void updateCartAmount(){
		// calculate subtotal and amount
		int subtotal = Cart.getCart().calSubtotal();
		int amount = (int)(subtotal + (Configs.PERCENT_VAT/100)*subtotal);

		// update subtotal and amount of Cart
		labelSubtotal.setText(Utils.getCurrencyFormat(subtotal));
		labelAmount.setText(Utils.getCurrencyFormat(amount));
	}
	
	private void displayCartWithMediaAvailability(){
		File file = new File("assets/images/Logo.png");
		Image im = new Image(file.toURI().toString());
		aimsImage.setImage(im);
		aimsImage.setOnMouseClicked(e -> {
			homeScreen.show();
		});

		try {
			for (Object cm : Cart.getCart().getListMedia()) {

				// display the attribute of vboxCart media
				CartMedia cartMedia = (CartMedia) cm;
				MediaCartScreen mediaCartScreen = new MediaCartScreen(Configs.CART_MEDIA_PATH, this);
				mediaCartScreen.setCartMedia(cartMedia);

				// add delete button
				Button deleteButton = new Button("Delete");
				deleteButton.setFont(Configs.REGULAR_FONT);
				deleteButton.setOnMouseClicked((e) -> {
					// update user cart
					Cart.getCart().removeCartMedia(cartMedia);

					// update vboxCart(GUI)
					vboxCart.getChildren().remove(mediaCartScreen.getContent());
					updateCartAmount();
					this.show();
				});

				// add delete button
				mediaCartScreen.addDescription(deleteButton);

				// add spinner
				vboxCart.getChildren().add(mediaCartScreen.getContent());

				// calculate subtotal and amount
				updateCartAmount();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
