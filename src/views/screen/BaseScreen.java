package views.screen;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import controller.BaseController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.screen.home.HomeScreen;

public class BaseScreen extends FXMLScreen {

	private Scene scene;
	private BaseScreen prev;
	protected final Stage stage;
	protected HomeScreen homeScreen;
	protected HashMap<String, String> messages;
	private BaseController bController;

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

	public void setBController(BaseController bController){
		this.bController = bController;
	}

	public BaseController getBController(){
		return this.bController;
	}

	public void forward(HashMap messages) {
		this.messages = messages;
	}

	public void setHomeScreen(HomeScreen homeScreen) {
		this.homeScreen = homeScreen;
	}

}
