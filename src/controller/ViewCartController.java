package controller;

import java.sql.SQLException;
import java.util.List;

import entity.media.Media;
import entity.cart.Cart;
import entity.cart.CartMedia;

public class ViewCartController {
    
    public static void checkAvailabilityOfProduct() throws SQLException{
        Cart cart = Cart.getCart();
        List mediaInCart = cart.getListMedia();
        for (Object object : mediaInCart) {
            CartMedia cartMedia = (CartMedia) object;
            cartMedia.getMedia().getQuantity();
        }
    }


}
