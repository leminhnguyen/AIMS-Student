package views.screen.home;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import entity.media.Media;
import entity.cart.*;
import common.exception.MediaNotAvailableException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.FXMLScreen;
import views.screen.popup.PopupScreen;

public class MediaHomeScreen extends FXMLScreen{

    @FXML
    protected ImageView mediaImage;

    @FXML
    protected Label mediaTitle;

    @FXML
    protected Label mediaPrice;

    @FXML
    protected Label mediaAvail;

    @FXML
    protected Spinner<Integer> spinnerChangeNumber;

    @FXML
    protected Button addToCartBtn;

    private static Logger LOGGER = Utils.getLogger(MediaHomeScreen.class.getName());
    private Media media;
    private HomeScreen home;

    public MediaHomeScreen(String screenPath, Media media, HomeScreen home) throws SQLException, IOException{
        super(screenPath);
        this.media = media;
        this.home = home;
        addToCartBtn.setOnMouseClicked(event -> {
            try {
                if (spinnerChangeNumber.getValue() > media.getQuantity()) throw new MediaNotAvailableException();
                Cart cart = Cart.getCart();
                CartMedia cartMedia = new CartMedia(media, cart, spinnerChangeNumber.getValue(), media.getPrice());
                cart.getListMedia().add(cartMedia);
                home.getNumMediaCartLabel().setText(String.valueOf(cart.getTotalMedia() + " media"));
                LOGGER.info("Added " + cartMedia.getQuantity() + " " + media.getTitle() + " to cart");
                PopupScreen.success("The media " + media.getTitle() + " added to Cart");
            } catch (MediaNotAvailableException exp) {
                try {
                    String message = "Not enough media:\nRequired: " + spinnerChangeNumber.getValue() + "\nAvail: " + media.getQuantity();
                    LOGGER.severe(message);
                    PopupScreen.error(message);
                } catch (Exception e) {
                    LOGGER.severe("Cannot add media to cart: ");
                }
                
            } catch (Exception exp) {
                LOGGER.severe("Cannot add media to cart: ");
                exp.printStackTrace();
            }
        });
        setMediaInfo();
    }

    public Media getMedia(){
        return media;
    }

    private void setMediaInfo() throws SQLException {
        // set the cover image of media
        File file = new File(media.getImageURL());
        Image image = new Image(file.toURI().toString());
        mediaImage.setFitHeight(160);
        mediaImage.setFitWidth(152);
        mediaImage.setImage(image);

        mediaTitle.setText(media.getTitle());
        mediaPrice.setText(Utils.getCurrencyFormat(media.getPrice()));
        mediaAvail.setText(Integer.toString(media.getQuantity()));
        spinnerChangeNumber.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1)
        );

        setImage(mediaImage, media.getImageURL());
    }
    
}
