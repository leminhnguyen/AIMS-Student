package entity.order;


public class Order {
    
    private User user;
    private Cart cart;

    public Order(User user, Cart cart) {
        this.user = user;
        this.cart = cart;
    }

    

}
