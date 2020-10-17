package entity.db;

import java.sql.SQLException;
import java.util.List;

import entity.media.CD;
import entity.media.Media;

public class CDModel extends MediaModel {

    @Override
    public Media getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM "+
                     "aims.CD " +
                     "INNER JOIN aims.Media " +
                     "ON Media.id = CD.id " +
                     "where Media.id = " + id + ";";
        res = stm.executeQuery(sql);
		if(res.next()) {
            
            // from media table
            String title = "";
            String type = res.getString("type");
            int price = res.getInt("price");
            String category = res.getString("category");
            int quantity = res.getInt("quantity");

            // from CD table
            String artist = res.getString("artist");
            String recordLabel = res.getString("recordLabel");
           
            return new CD(id, title, category, price, quantity, type, 
                          artist, recordLabel);
            
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
