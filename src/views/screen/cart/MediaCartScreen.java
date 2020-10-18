package views.screen.cart;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import entity.cart.CartMedia;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.Utils;
import views.screen.FXMLScreen;

public class MediaCartScreen extends FXMLScreen {

	private static Logger LOGGER = Utils.getLogger(MediaCartScreen.class.getName());

	@FXML
	protected HBox hboxMedia;

	@FXML
	protected ImageView image;

	@FXML
	protected VBox description;

	@FXML
	protected Label labelOutOfStock;

	@FXML
	protected VBox spinnerFX;

	@FXML
	protected Label title;

	@FXML
	protected Label price;

	@FXML
	protected Label currency;

	public MediaCartScreen(String screenPath) throws IOException {
		super(screenPath);
		hboxMedia.setAlignment(Pos.CENTER);
	}

	public void addDescription(Parent component) {
		this.description.getChildren().add(component);
	}

	private CartMedia cartMedia;
	
	private Spinner<Integer> spinner;

	private static final int initialValue = 1;

	public void setCartMedia(CartMedia cartMedia) {
		this.cartMedia = cartMedia;
		setMediaInfo();
	}

	private void setMediaInfo() {
		title.setText(cartMedia.getMedia().getTitle());
		price.setText(String.valueOf(cartMedia.getPrice()));
		currency.setText("VND");
		File file = new File(cartMedia.getMedia().getImageURL());
		Image im = new Image(file.toURI().toString());
		image.setImage(im);
		initializeSpinner();
	}

	private void initializeSpinner() {
		SpinnerValueFactory<Integer> valueFactory = //
				new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, cartMedia.getMedia().getRemainQuantity());
		spinner = new Spinner<Integer>(valueFactory);
		spinner.setOnMouseClicked( e -> {
			int numOfProd = this.spinner.getValue();
			if (numOfProd > cartMedia.getMedia().getRemainQuantity()){
				if (cartMedia.getMedia().getRemainQuantity() == 0) {
					LOGGER.info("product " + cartMedia.getMedia().getTitle() + " out of stock");
					labelOutOfStock.setText("out of stock");
					numOfProd = 0;
				}else{
					LOGGER.info("product " + cartMedia.getMedia().getTitle() + " only remains " + cartMedia.getMedia().getRemainQuantity() + " (required " + numOfProd + ")");
					labelOutOfStock.setText("Sorry, Only " + cartMedia.getMedia().getRemainQuantity() + " remain in stock");
					spinner.getValueFactory().setValue(cartMedia.getMedia().getRemainQuantity());
					numOfProd = cartMedia.getMedia().getRemainQuantity();
				}
			}else{
				spinner.getValueFactory().setValue(cartMedia.getQuantity());
			}
			price.setText(Integer.toString(numOfProd*cartMedia.getPrice()));
		});
		spinnerFX.setAlignment(Pos.CENTER);
		spinnerFX.getChildren().add(this.spinner);
	}

}
