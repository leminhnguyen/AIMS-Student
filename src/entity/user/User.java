package entity.user;

import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.media.Media;

public class User {
    
    private int id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private Cart cart;

    public User(int id, String name, String email, String address, String phone){
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public void addMediaToCart(Media media, int quantity, int price){
        CartMedia cm = new CartMedia(media, this.cart, quantity, price);
        this.cart.addCartMedia(cm);
    }

    public void removeMediaFromCart(CartMedia cm){
        this.cart.removeCartMedia(cm);
    }
    
    // override toString method
    @Override
    public String toString() {
        return "{" +
            "  username='" + name + "'" +
            ", email='" + email + "'" +
            ", address='" + address + "'" +
            ", phone='" + phone + "'" +
            "}";
    }

    // getter and setter
    public String getName() {
        return this.name;
    }

    public void setusername(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}