package controller;

import java.sql.SQLException;
import java.util.List;

import entity.cart.Cart;
import entity.cart.CartMedia;

public class PlaceOrderController {

    public static boolean checkAvailabilityOfProduct() throws SQLException{
        boolean allAvai = true;
        Cart cart = Cart.getCart();
        List mediaInCart = cart.getListMedia();
        for (Object object : mediaInCart) {
            CartMedia cartMedia = (CartMedia) object;
            int requiredQuantity = cartMedia.getQuantity();
            int availQuantity = cartMedia.getMedia().getQuantity();
            if (requiredQuantity > availQuantity) allAvai = false;
        }
        return allAvai;
    }

    public static void validateAndProcessDeliveryInfo(){
        // TODO: implement later
    }

    public int calculateShippingFee(){
        // TODO: implement later
        return 0;
    }
}
