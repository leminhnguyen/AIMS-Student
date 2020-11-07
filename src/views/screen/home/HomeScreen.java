package views.screen.home;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.PlaceOrderController;
import controller.ViewCartController;
import entity.exception.ViewCartException;
import entity.media.Book;
import entity.media.CD;
import entity.media.DVD;
import entity.media.Media;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreen;
import views.screen.cart.CartScreen;


public class HomeScreen extends BaseScreen implements Initializable{

    public static Logger LOGGER = Utils.getLogger(HomeScreen.class.getName());

    @FXML
    private Label numMediaInCart;

    @FXML
    private ImageView aimsImage;

    @FXML
    private ImageView cartImage;

    @FXML
    private VBox vboxMedia1;

    @FXML
    private VBox vboxMedia2;

    @FXML
    private VBox vboxMedia3;

    @FXML
    private HBox hboxMedia;

    @FXML
    private SplitMenuButton splitMenuBtnSearch;

    private List homeItems;

    public HomeScreen(Stage stage, String screenPath) throws IOException{
        super(stage, screenPath);
    }

    public Label getNumMediaCartLabel(){
        return this.numMediaInCart;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO: implement later, get data from database
        try{
            this.homeItems = new ArrayList<>();
            Random rand = new Random();
            for (int i=1; i<=12; i++){

                Media book = new Book()
                                .setTitle("Book" + i)
                                .setPrice(rand.nextInt(1000))
                                .setQuantity(rand.nextInt(100))
                                .setType("book")
                                .setMediaURL(Configs.IMAGE_PATH + "/Books/book" + i + ".jpg");
                Media cd = new CD()
                                .setTitle("CD" + i)
                                .setPrice(rand.nextInt(1000))
                                .setQuantity(rand.nextInt(100))
                                .setMediaURL(Configs.IMAGE_PATH + "/CDs/cd" + i + ".jpg")
                                .setType("cd");
                Media dvd = new DVD()
                                .setTitle("DVD" + i)
                                .setPrice(rand.nextInt(1000))
                                .setQuantity(rand.nextInt(100))
                                .setMediaURL(Configs.IMAGE_PATH + "/DVDs/dvd" + i + ".jpg")
                                .setType("dvd");
                
                try {
                    MediaHomeScreen m1 = new MediaHomeScreen(Configs.HOME_MEDIA_PATH, book, this);
                    MediaHomeScreen m2 = new MediaHomeScreen(Configs.HOME_MEDIA_PATH, cd, this);
                    MediaHomeScreen m3 = new MediaHomeScreen(Configs.HOME_MEDIA_PATH, dvd, this);
                    this.homeItems.addAll(Arrays.asList(m1,m2,m3));
                } catch (Exception e) {
                    LOGGER.severe("Cannot add the mediaItem, see the logs");
                    LOGGER.info(e.getMessage());
                    e.printStackTrace();
                }
            }
        }catch (SQLException e){
            LOGGER.info("Errors occured: " + e.getMessage());
            e.printStackTrace();
        }
        
            
        aimsImage.setOnMouseClicked(e -> {
            addMediaHome(this.homeItems);
        });
        
        cartImage.setOnMouseClicked(e -> {
            CartScreen cartScreen;
            try {
                LOGGER.info("User clicked to view cart");
                cartScreen = new CartScreen(this.stage, Configs.CART_SCREEN_PATH);
                cartScreen.setHomeScreen(this);
                cartScreen.setBController(new ViewCartController());
                cartScreen.requestToViewCart(this);
            } catch (IOException | SQLException e1) {
                throw new ViewCartException(Arrays.toString(e1.getStackTrace()).replaceAll(", ", "\n"));
            }
        });
        addMediaHome(this.homeItems);
        addMenuItem(0, "Book", splitMenuBtnSearch);
        addMenuItem(1, "DVD", splitMenuBtnSearch);
        addMenuItem(2, "CD", splitMenuBtnSearch);
    }

    public void setImage(){
        // fix image path caused by fxml
        File file1 = new File(Configs.IMAGE_PATH + "/" + "Logo.png");
        Image img1 = new Image(file1.toURI().toString());
        aimsImage.setImage(img1);

        File file2 = new File(Configs.IMAGE_PATH + "/" + "cart.png");
        Image img2 = new Image(file2.toURI().toString());
        cartImage.setImage(img2);
    }

    public void addMediaHome(List items){
        ArrayList mediaItems = (ArrayList)((ArrayList) items).clone();
        hboxMedia.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();
        });
        while(!mediaItems.isEmpty()){
            hboxMedia.getChildren().forEach(node -> {
                int vid = hboxMedia.getChildren().indexOf(node);
                VBox vBox = (VBox) node;
                while(vBox.getChildren().size()<3 && !mediaItems.isEmpty()){
                    MediaHomeScreen media = (MediaHomeScreen) mediaItems.get(0);
                    vBox.getChildren().add(media.getContent());
                    mediaItems.remove(media);
                }
            });
            return;
        }
    }

    private void addMenuItem(int position, String text, MenuButton menuButton){
        MenuItem menuItem = new MenuItem();
        Label label = new Label();
        label.prefWidthProperty().bind(menuButton.widthProperty().subtract(31));
        label.setText(text);
        label.setTextAlignment(TextAlignment.RIGHT);
        menuItem.setGraphic(label);
        menuItem.setOnAction(e -> {
            // empty home media
            hboxMedia.getChildren().forEach(node -> {
                VBox vBox = (VBox) node;
                vBox.getChildren().clear();
            });

            // filter only media with the choosen category
            List filteredItems = new ArrayList<>();
            homeItems.forEach(me -> {
                MediaHomeScreen media = (MediaHomeScreen) me;
                if (media.getMedia().getTitle().toLowerCase().startsWith(text.toLowerCase())){
                    filteredItems.add(media);
                }
            });

            // fill out the home with filted media as category
            addMediaHome(filteredItems);
        });
        menuButton.getItems().add(position, menuItem);
    }

    
    
}
