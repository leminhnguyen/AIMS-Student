package controller;

import java.sql.SQLException;
import java.util.List;

import entity.media.Media;
import entity.cart.Cart;
import entity.cart.CartMedia;

public class ViewCartController extends BaseController{
    
    public void checkAvailabilityOfProduct() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }

}
