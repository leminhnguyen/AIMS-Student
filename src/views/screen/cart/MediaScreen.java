package views.screen.cart;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import views.screen.*;

public class MediaScreen extends FXMLScreen {
	
	public MediaScreen(String screenPath) throws IOException {
		super(screenPath);
	}

	@FXML
	protected VBox vboxImageLogo;

	@FXML
	protected HBox media;

	@FXML
	protected ImageView image;

	@FXML
	protected VBox description;

	@FXML
	protected VBox spinnerFX;

	@FXML
	protected Label title;

	@FXML
	protected Label price;

	@FXML
	protected Label currency;

	public void changeMedia(String title, String price, String currency) {
		this.title.setText(title);
		this.price.setText(price);
		this.currency.setText(currency);
		File file = new File("assets/images/Media.png");
		Image im = new Image(file.toURI().toString());
		image.setImage(im);
	}

	public void addDescription(Parent component) {
		this.description.getChildren().add(component);
	}

}
