package entity.order;

import entity.media.Media;

public class User {
    
    private int userId;
    private String username;
    private String email;
    private String address;
    private String phone;

    public User(){

    }

    public User(int userId, String username, String email, String address, String phone) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
    
    @Override
    public String toString() {
        return "{" +
            "  username='" + username + "'" +
            ", email='" + email + "'" +
            ", address='" + address + "'" +
            ", phone='" + phone + "'" +
            "}";
    }

    public int getUserId(){
        return this.userId;
    }

    public String getusername() {
        return this.username;
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
