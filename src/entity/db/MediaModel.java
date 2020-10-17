package entity.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.media.Media;

public abstract class MediaModel {
    
    public Statement stm;
    public ResultSet res;
    
    public abstract Media getMediaById(int id) throws SQLException;

    public abstract List getAllMedia();

    public void updateMediaFieldById(String tbname, int id, String field, Object value) throws SQLException {
        if (value instanceof String){
            value = "\"" + value + "\"";
        }
        stm.executeUpdate(" update Book set" + " " +
                            field + "=" + value + " " +
                            "where id=" + id + ";");
    }

    public abstract void insertMedia(Media media);

    public abstract void deleteMedia(Media media);
}
