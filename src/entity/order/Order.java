package entity.order;


public class Order {
    
    User user;
    Cart cart;

    public Order(User user, Cart cart) {
        this.user = user;
        this.cart = cart;
    }

}
