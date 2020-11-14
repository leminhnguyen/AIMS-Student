package controller;

import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.media.Media;

public class BaseController {
    
    public CartMedia checkMediaInCart(Media media){
        return Cart.getCart().checkMediaInCart(media);
    }
}
