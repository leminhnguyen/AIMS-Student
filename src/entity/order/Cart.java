package entity.order;

import java.util.List;

import entity.media.Media;

public class Cart {
    
    private List<Media> lstMedia;
    public static Cart cart;

    public static Cart getCart(){
        if(cart == null) cart = new Cart();
        return cart;
    }

    private Cart(){

    }

    public void addMedia(Media media){
        lstMedia.add(media);
    }

    public void removeMedia(Media media){
        lstMedia.remove(media);
    }

    public List getListMedia(){
        return lstMedia;
    }

}
