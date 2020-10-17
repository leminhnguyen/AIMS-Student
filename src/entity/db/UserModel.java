package entity.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.order.User;

public class UserModel {

    private static UserModel instance;
    public Statement stm;
    public ResultSet res;

    public static UserModel getInstance() throws SQLException {
        if (instance == null) instance = new UserModel();
        return instance;
    }

    private UserModel() throws SQLException {
        stm = AIMSDB.getConnection().createStatement();
    }

    public User getUser(String username, String email){
        // TODO implement later if needed
        int userId = 123;
        String address = "HN";
        String phone = "012345678";
        return new User(userId, username, email, address, phone);
    }
}
