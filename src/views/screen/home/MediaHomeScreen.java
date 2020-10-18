package views.screen.home;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import entity.media.Media;
import entity.cart.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Utils;
import views.screen.FXMLScreen;

public class MediaHomeScreen extends FXMLScreen{

    private static Logger LOGGER = Utils.getLogger(MediaHomeScreen.class.getName());

    private Media media;
    private HomeScreen home;

    @FXML
    protected ImageView mediaImage;

    @FXML
    protected Label mediaTitle;

    @FXML
    protected Label mediaPrice;

    @FXML
    protected Label mediaAvail;

    @FXML
    protected Button addToCartBtn;

    public MediaHomeScreen(String screenPath, Media media, HomeScreen home) throws SQLException, IOException{
        super(screenPath);
        this.media = media;
        this.home = home;
        addToCartBtn.setOnMouseClicked(e -> {
            try {
                Cart cart = Cart.getCart();
                CartMedia cartMedia = new CartMedia(media, cart, media.getQuantity(), media.getPrice());
                cart.getListMedia().add(cartMedia);
                LOGGER.info("add media " + media.getTitle() + " to cart");
                home.getNumMediaCartLabel().setText(String.valueOf(cart.getListMedia().size() + " media"));
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
        mediaPrice.setText(Integer.toString(media.getPrice()));
        mediaAvail.setText(Integer.toString(media.getQuantity()));

        setImage(mediaImage, media.getImageURL());
    }
    
}
