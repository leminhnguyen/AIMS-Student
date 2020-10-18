package controller;

import java.sql.SQLException;
import java.util.List;

import entity.media.Media;
import entity.cart.Cart;

public class ViewCartController {
    
    public void checkAvailabilityOfProduct() throws SQLException{
        Cart cart = Cart.getCart();
        List mediaInCart = cart.getListMedia();
        for (Object object : mediaInCart) {
            Media media = (Media) object;
            media.getQuantity();
        }
    }


}
