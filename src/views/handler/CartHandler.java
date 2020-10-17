package views.handler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entity.order.Cart;
import entity.order.CartMedia;
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

public class CartHandler extends ScreenHandler implements Initializable {

	public CartHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}

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

	@FXML
	void requestToPlaceOrder(MouseEvent event) throws IOException {
		ScreenHandler controller = new ShippingHandler(this.stage, Configs.SHIPPING_SCREEN_PATH);
		controller.setPreviousScreen(this);
		controller.setScreenTitle("Shipping Screen");
		controller.show();
		this.message = new ArrayList<Node>();
		this.message.add(subtotal);
		controller.forward(this.message);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

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
				MediaOfCartHandler mediaHandler = new MediaOfCartHandler(Configs.MEDIA_PATH);
				mediaHandler.changeMedia(title, Integer.toString(price), currency);
				mediaHandler.setCartMedia(cartMedia);

				// add delete button
				Button deleteButton = new Button("Delete");
				deleteButton.setFont(Configs.REGULAR_FONT);
				deleteButton.setOnMouseClicked((e) -> {
					cart.getChildren().remove(mediaHandler.getContent());
					this.show();
				});

				mediaHandler.addDescription(deleteButton);

				// add spinner
				cart.getChildren().add(mediaHandler.getContent());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
