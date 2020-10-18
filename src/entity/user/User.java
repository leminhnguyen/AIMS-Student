package entity.user;

import entity.media.Media;

import entity.media.Media;

public class User {
    
<<<<<<< HEAD:src/entity/user/User.java
    private int userId;
    private String username;
=======
    private int id;
    private String name;
>>>>>>> add model entity:src/entity/order/User.java
    private String email;
    private String address;
    private String phone;
    private Cart cart;

<<<<<<< HEAD:src/entity/user/User.java
    public User(){

    }

    public User(int userId, String username, String email, String address, String phone) {
        this.userId = userId;
        this.username = username;
=======
    public User(int id, String name, String email, String address, String phone){
        this.id = id;
        this.name = name;
>>>>>>> add model entity:src/entity/order/User.java
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.cart = Cart.getCart(this.id);
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
            "  username='" + username + "'" +
            ", email='" + email + "'" +
            ", address='" + address + "'" +
            ", phone='" + phone + "'" +
            "}";
    }

<<<<<<< HEAD:src/entity/user/User.java
    public int getUserId(){
        return this.userId;
    }

    public String getusername() {
        return this.username;
=======
    // getter and setter
    public String getName() {
        return this.name;
>>>>>>> add model entity:src/entity/order/User.java
    }

    public void setusername(String username) {
        this.username = username;
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
