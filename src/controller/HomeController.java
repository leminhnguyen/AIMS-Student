package controller;

import java.sql.SQLException;
import java.util.List;

import entity.cart.Cart;
import entity.media.Media;

public class HomeController extends BaseController{


    public List getAllMedia() throws SQLException{
        return new Media().getAllMedia();
    }

}
