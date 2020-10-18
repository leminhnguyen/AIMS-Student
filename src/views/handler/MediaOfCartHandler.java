package views.handler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class MediaOfCartHandler extends MediaHandler implements Initializable {
	public MediaOfCartHandler(String screenPath) throws IOException {
		super(screenPath);
		// TODO Auto-generated constructor stub
	}

	private Spinner<Integer> spinner;

	private static final int initialValue = 1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		// Value factory
		SpinnerValueFactory<Integer> valueFactory = //
				new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, initialValue);
		this.spinner = new Spinner<Integer>(valueFactory);

		this.media.getChildren().add(this.spinner);
	}

}
