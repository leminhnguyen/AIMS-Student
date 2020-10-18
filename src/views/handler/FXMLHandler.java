package views.handler;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

class FXMLHandler {

	protected FXMLLoader loader;
	protected AnchorPane content;

	public FXMLHandler(String screenPath) throws IOException {
		this.loader = new FXMLLoader(getClass().getResource(screenPath));

		// Set this class as the controller
		this.loader.setController(this);

		this.content = (AnchorPane) loader.load();
	}

	public AnchorPane getContent() {
		return this.content;
	}

	public FXMLLoader getLoader() {
		return this.loader;
	}
}
