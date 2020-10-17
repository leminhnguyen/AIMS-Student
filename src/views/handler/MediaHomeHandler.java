package views.handler;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import entity.media.Media;
import entity.order.Cart;
import entity.order.CartMedia;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Utils;

public class MediaHomeHandler extends FXMLHandler{

    private static Logger LOGGER = Utils.getLogger(MediaHomeHandler.class.getName());

    private Media media;
    private HomeHandler home;

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

    public MediaHomeHandler(String screenPath, Media media, HomeHandler home) throws IOException{
        super(screenPath);
        this.media = media;
        this.home = home;
        addToCartBtn.setOnMouseClicked(e -> {
            Cart cart = Cart.getCart();
            CartMedia cartMedia = new CartMedia(media, cart, media.getQuantity(), media.getPrice());
            cart.getListMedia().add(cartMedia);
            LOGGER.info("add media " + media.getTitle() + " to cart");
            home.getNumMediaCartLabel().setText(String.valueOf(cart.getListMedia().size() + " media"));
        });
        setMediaInfo();
    }

    public Media getMedia(){
        return media;
    }

    private void setMediaInfo(){
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
