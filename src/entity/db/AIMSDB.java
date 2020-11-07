package entity.db;

import java.sql.DriverManager;
import java.util.logging.Logger;
import java.sql.Connection;
import utils.*;

public class AIMSDB {

	private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
	private static Connection connect;

	public static Connection getConnection() {
		if (connect != null) return connect;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + Configs.DB_NAME + "?autoReconnect=true&useSSL=false",
					Configs.DB_USERNAME, Configs.DB_PASSWORD);
			LOGGER.info("Connect database successfully");
			return connect;
		} catch (Exception e) {
			LOGGER.severe("Connection failed:\n" + e.getMessage());
			return null;
		}
	}

    public static void close() {
        try {
            connect.close();
        } catch (Exception e) {
            LOGGER.severe("Close failed");
        }
    }

    public static void main(String[] args) {
        AIMSDB.getConnection();
    }
}
