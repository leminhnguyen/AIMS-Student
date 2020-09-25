package entity.order;

import java.util.List;

public class Cart {
    
    private List<CartMedia> lstCartMedia;
    private int userId;
    private static List carts;

    public static Cart getCart(int userId){

        for (Object object : carts) {
            Cart cart  = (Cart) object;
            if (cart.userId == userId) return cart;
        }
        Cart cart = new Cart(userId);
        carts.add(cart);
        return cart;
    }

    private Cart(int userId){
        this.userId = userId;
    }

    public void addCartMedia(CartMedia cm){
        lstCartMedia.add(cm);
    }

    public void removeCartMedia(CartMedia cm){
        lstCartMedia.remove(cm);
    }

    public List getListMedia(){
        return lstCartMedia;
    }

}
