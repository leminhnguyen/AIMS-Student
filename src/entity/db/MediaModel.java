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

    public abstract void updateMedia(int id, String field, Object value) throws SQLException;

    public abstract void insertMedia(Media media);

    public abstract void deleteMedia(Media media);
}
