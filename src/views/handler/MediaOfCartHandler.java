package views.handler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.order.CartMedia;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class MediaOfCartHandler extends MediaHandler implements Initializable {

	private CartMedia cartMedia;
	
	private Spinner<Integer> spinner;

	private static final int initialValue = 1;

	public MediaOfCartHandler(String screenPath) throws IOException {
		super(screenPath);
	}

	public void setCartMedia(CartMedia cartMedia) {
		this.cartMedia = cartMedia;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SpinnerValueFactory<Integer> valueFactory = //
				new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, initialValue);
		this.spinner = new Spinner<Integer>(valueFactory);
		this.spinner.setOnMouseClicked( e -> {
			int numOfProd = this.spinner.getValue();
			price.setText(Integer.toString(numOfProd*cartMedia.getPrice()));
		});
		spinnerFX.setAlignment(Pos.CENTER);
		spinnerFX.getChildren().add(this.spinner);
	}

}
