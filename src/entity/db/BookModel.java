package entity.db;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import entity.media.Book;
import entity.media.Media;
import utils.Utils;

public class BookModel extends MediaModel {

    private static BookModel instance;

    public static MediaModel getInstance() throws SQLException {
        if (instance == null) instance = new BookModel();
        return instance;
    }

    private BookModel() throws SQLException {
        stm = AIMSDB.getConnection().createStatement();
    }

    @Override
    public void insertMedia(Media media) {
       
    }

    @Override
    public void deleteMedia(Media media) {
       
    }

    @Override
    public Media getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM "+
                     "aims.Book " +
                     "INNER JOIN aims.Media " +
                     "ON Media.id = Book.id " +
                     "where Media.id = " + id + ";";
        res = stm.executeQuery(sql);
		if(res.next()) {

            // from Media table
            String title = "";
            String type = res.getString("type");
            int price = res.getInt("price");
            String category = res.getString("category");
            int quantity = res.getInt("quantity");

            // from Book table
            String author = res.getString("author");
            String coverType = res.getString("coverType");
            String publisher = res.getString("publisher");
            Date publishDate = res.getDate("publishDate");
            int numOfPages = res.getInt("numOfPages");
            String language = res.getString("language");
            String bookCategory = res.getString("bookCategory");
            
            return new Book(id, title, category, price, quantity, type, 
                            author, coverType, publisher, publishDate, numOfPages, language, bookCategory);
            
		} else {
			throw new SQLException();
		}
    }

    @Override
    public List getAllMedia() {
        return null;
    }

    public static void main(String[] args) throws SQLException {
        BookModel bm = (BookModel) BookModel.getInstance();
        Utils.getLogger(BookModel.class.getName()).info(bm.getMediaById(1).toString());
        bm.updateMediaFieldById("Book", 1, "author", "nguyenlm");
        Utils.getLogger(BookModel.class.getName()).info("Update successfully");
    }
    
}
