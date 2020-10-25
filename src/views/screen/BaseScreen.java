package views.screen;

import java.io.IOException;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.screen.home.HomeScreen;

public class BaseScreen extends FXMLScreen {

	private Scene scene;
	private BaseScreen prev;
	protected final Stage stage;
	protected HomeScreen homeScreen;

	private BaseScreen(String screenPath) throws IOException {
		super(screenPath);
		this.stage = new Stage();
	}

	public void setPreviousScreen(BaseScreen prev) {
		this.prev = prev;
	}

	public BaseScreen getPreviousScreen() {
		return this.prev;
	}

	public BaseScreen(Stage stage, String screenPath) throws IOException {
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
	 */
	public void forward(List<Node> components) {
		this.message = components;
	}

	public void setHomeScreen(HomeScreen homeScreen) {
		this.homeScreen = homeScreen;
	}

}
