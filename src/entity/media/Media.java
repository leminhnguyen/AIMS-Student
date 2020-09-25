package entity.media;

import entity.exception.*;

/**
 * The general media class, for another media it can be done by inheriting this class
 * @author nguyenlm
 */
public class Media {

    int id;
    protected String title;
    protected String category;
    protected int price;
    protected int quantity;
    String type;

    public Media (int id, String title, String category, int price, int quantity, String type){
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "title='" + getTitle() + "'" +
            ", category='" + getCategory() + "'" +
            ", price='" + getPrice() + "'" +
            ", quantity='" + this.quantity + "'";
    }

    public void decreaseQuantity(){
        if(this.quantity - 1 < 0) throw new NegativeQuantityException("The quantity of this media is zero, you cannot decrease anymore");
        this.quantity -= 1;
    }

    public void increaseQuantity(){
        this.quantity += 1;
    }



}