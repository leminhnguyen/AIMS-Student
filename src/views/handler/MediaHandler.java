package views.handler;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MediaHandler extends FXMLHandler {
	public MediaHandler(String screenPath) throws IOException {
		super(screenPath);
	}

	@FXML
	protected HBox media;

	@FXML
	protected ImageView image;

	@FXML
	protected VBox description;

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
	}

	public void addDescription(Parent component) {
		// TODO Auto-generated method stub
		this.description.getChildren().add(component);
	}

}
