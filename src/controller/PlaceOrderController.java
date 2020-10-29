package controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.exception.InvalidDeliveryInfoException;
import entity.order.Order;
import entity.order.OrderMedia;
import javafx.css.CssParser.ParseError;

public class PlaceOrderController extends BaseController{

    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    public void placeOrder() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }

    public Order createOrder() throws SQLException{
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                                   cartMedia.getMedia().getQuantity(), 
                                                   cartMedia.getMedia().getPrice());    
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }

    public void processDeliveryInfo(HashMap info) throws InterruptedException{
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
    }

    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException{
        LOGGER.info("Validate Delivery Info");
        if ( !(info.get("instructions") instanceof String) ||
             !(info.get("address") instanceof String) || 
             !(info.get("province") instanceof String) || 
             !(info.get("name") instanceof String)
             ) throw new InvalidDeliveryInfoException();
        try {
            Thread.sleep(2000); // simulate validate delivery info
            LOGGER.info("Validate Done");
            Integer.parseInt(info.get("phone"));
        } catch (NumberFormatException e) {
            throw new InvalidDeliveryInfoException();
        }
        
    } 

    public int calculateShippingFee(Order order){
        return (int)( (new Random().nextInt(10) + 1)/100 * order.getAmount());
    }
}
