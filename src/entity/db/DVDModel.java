package entity.db;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import entity.media.DVD;
import entity.media.Media;

public class DVDModel extends MediaModel {

    @Override
    public Media getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM "+
                     "aims.DVD " +
                     "INNER JOIN aims.Media " +
                     "ON Media.id = DVD.id " +
                     "where Media.id = " + id + ";";
        res = stm.executeQuery(sql);
        if(res.next()) {
            
        // from media table
        String title = "";
        String type = res.getString("type");
        int price = res.getInt("price");
        String category = res.getString("category");
        int quantity = res.getInt("quantity");

        // from DVD table
        String discType = res.getString("discType");
        String director = res.getString("director");
        int runtime = res.getInt("runtime");
        String studio = res.getString("studio");
        String subtitles = res.getString("subtitle");
        Date releasedDate = res.getDate("releasedDate");
        String filmType = res.getString("filmType");

        return new DVD(id, title, category, price, quantity, type, discType, director, runtime, studio, subtitles, releasedDate, filmType);

        } else {
        throw new SQLException();
        }
    }

    @Override
    public List getAllMedia() {
        return null;
    }

    @Override
    public void insertMedia(Media media) {
        
    }

    @Override
    public void deleteMedia(Media media) {

    }
    
}
