package entity.media;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import java.sql.Statement;

import entity.db.AIMSDB;
import entity.exception.*;
import utils.Utils;

/**
 * The general media class, for another media it can be done by inheriting this class
 * @author nguyenlm
 */
public abstract class Media {

    private static Logger LOGGER = Utils.getLogger(Media.class.getName());

<<<<<<< HEAD
    protected Statement stm;
    protected int id;
=======
    int id;
>>>>>>> add model entity
    protected String title;
    protected String category;
    protected int value; // the real price of product (eg: 450)
    protected int price; // the price which will be displayed on browser (eg: 500)
    protected int quantity;
<<<<<<< HEAD
    protected String type;
    protected String imageURL;

    public Media() throws SQLException{
        //stm = AIMSDB.getConnection().createStatement();
    }

    public Media (int id, String title, String category, int price, int quantity, String type) throws SQLException{
=======
    String type;

    public Media (int id, String title, String category, int price, int quantity, String type){
>>>>>>> add model entity
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
<<<<<<< HEAD

        //stm = AIMSDB.getConnection().createStatement();
    }

    public int getQuantity() throws SQLException{
        // int updated_quantity = getMediaById(id).getQuantity();
        // this.quantity = updated_quantity;
        // return updated_quantity;
        return quantity;
    }

    public abstract Media getMediaById(int id) throws SQLException;

    public abstract List getAllMedia();

    public void updateMediaFieldById(String tbname, int id, String field, Object value) throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        if (value instanceof String){
            value = "\"" + value + "\"";
        }
        stm.executeUpdate(" update " + tbname + " set" + " " 
                          + field + "=" + value + " " 
                          + "where id=" + id + ";");
    }

    // getter and setter 
    public int getId() {
        return this.id;
=======
>>>>>>> add model entity
    }

    public String getTitle() {
        return this.title;
    }

    public Media setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCategory() {
        return this.category;
    }

    public Media setCategory(String category) {
        this.category = category;
        return this;
    }

    public int getPrice() {
        return this.price;
    }

    public Media setPrice(int price) {
        this.price = price;
        return this;
    }

<<<<<<< HEAD
    public String getImageURL(){
        return this.imageURL;
    }

    public Media setMediaURL(String url){
        this.imageURL = url;
        return this;
    }

    public Media setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
=======
    @Override
    public String toString() {
        return "title='" + getTitle() + "'" +
            ", category='" + getCategory() + "'" +
            ", price='" + getPrice() + "'" +
            ", quantity='" + this.quantity + "'";
>>>>>>> add model entity
    }

    public String getType() {
        return this.type;
    }

    public Media setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", title='" + title + "'" +
            ", category='" + category + "'" +
            ", price='" + price + "'" +
            ", quantity='" + quantity + "'" +
            ", type='" + type + "'" +
            ", imageURL='" + imageURL + "'" +
            "}";
    }    

}