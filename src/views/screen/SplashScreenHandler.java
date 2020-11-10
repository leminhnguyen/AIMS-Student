package views.screen;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SplashScreenHandler implements Initializable {

    @FXML
    ImageView logo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("assets/images/Logo.png");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }
}