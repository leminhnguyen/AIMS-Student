package views.handler;

import java.io.IOException;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenHandler extends FXMLHandler {

	private Scene scene;
	private ScreenHandler prev;
	protected final Stage stage;

	private ScreenHandler(String screenPath) throws IOException {
		super(screenPath);
		this.stage = new Stage();
	}

	public void setPreviousScreen(ScreenHandler prev) {
		this.prev = prev;
	}

	public ScreenHandler getPreviousScreen() {
		return this.prev;
	}

	public ScreenHandler(Stage stage, String screenPath) throws IOException {
		super(screenPath);
		this.stage = stage;
	}

	public void show() {
		if (this.scene == null) {
			this.scene = new Scene(this.content);
		}
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	public void setScreenTitle(String string) {
		this.stage.setTitle(string);
	}

	protected List<Node> message;

	/*
	 * Forward component(s) from another controller to this one
	 * 
	 */
	protected void forward(List<Node> components) {
		this.message = components;
	}

}
