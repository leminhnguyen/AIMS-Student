package controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;

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
                                                   cartMedia.getQuantity(), 
                                                   cartMedia.getPrice());    
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }

    public Invoice createInvoice(Order order) {
        return new Invoice(order);
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
             //!(info.get("phone").length() != 10) ||
             !(info.get("province") instanceof String) || 
             !(info.get("name") instanceof String)
             ) throw new InvalidDeliveryInfoException();
        try {
            Thread.sleep(1000); // simulate validate delivery info
            LOGGER.info("Validate Done");
            Integer.parseInt(info.get("phone"));
        } catch (NumberFormatException e) {
            throw new InvalidDeliveryInfoException();
        }
        
    } 

    public int calculateShippingFee(Order order){
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() );
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}
