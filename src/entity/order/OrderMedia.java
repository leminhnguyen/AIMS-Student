package entity.order;

import entity.media.Media;

/**
 * Represents an order for a specific media item.
 */
public class OrderMedia {
    
    private Media media;
    private int price;
    private int quantity;

    /**
     * Constructs a new OrderMedia object.
     *
     * @param media    The media item being ordered.
     * @param quantity The number of units of the media item being ordered.
     * @param price    The price per unit of the media item.
     */
    public OrderMedia(Media media, int quantity, int price) {
        this.media = media;
        this.quantity = quantity;
        this.price = price;
    }
    
    /**
     * Returns a string representation of the OrderMedia object.
     *
     * @return A string containing the media, quantity, and price information.
     */
    @Override
    public String toString() {
        return "{" +
            "  media='" + media + "'" +
            ", quantity='" + quantity + "'" +
            ", price='" + price + "'" +
            "}";
    }
    
    /**
     * Gets the media item associated with this order.
     *
     * @return The Media object representing the ordered item.
     */
    public Media getMedia() {
        return this.media;
    }

    /**
     * Sets the media item for this order.
     *
     * @param media The Media object to be set for this order.
     */
    public void setMedia(Media media) {
        this.media = media;
    }

    /**
     * Gets the quantity of the media item in this order.
     *
     * @return The quantity of the media item.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Sets the quantity of the media item for this order.
     *
     * @param quantity The quantity to be set for this order.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the price per unit of the media item in this order.
     *
     * @return The price per unit of the media item.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Sets the price per unit of the media item for this order.
     *
     * @param price The price per unit to be set for this order.
     */
    public void setPrice(int price) {
        this.price = price;
    }

}
