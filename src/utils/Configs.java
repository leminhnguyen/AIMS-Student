package utils;

/**
 * @author nguyenlm
 * Contains the configs for AIMS Project
 */
public class Configs {

    // program configs
    public static final int BASE_COST_FOR_SINGLE_BIKE = 400000;
    public static final int BASE_COST_FOR_POWER_BIKE = 700000;
    public static final int MAXIMUM_MINUTE_FOR_POWER_BIKE = 100;
    public static final int BASE_COST_FOR_DOUBLE_BIKE = 500000;

    // api constants
    public static final String GET_BALANCE_URL = "https://ecopark-system-api.herokuapp.com/api/card/balance/118609_group1_2020";
    public static final String GET_VEHICLECODE_URL = "https://ecopark-system-api.herokuapp.com/api/get-vehicle-code/1rjdfasdfas";
    public static final String INCREASE_BALANCE = "https://ecopark-system-api.herokuapp.com/api/card/increase";
    public static final String DECREASE_BALANCE = "https://ecopark-system-api.herokuapp.com/api/card/decrease";
    public static final String RESET_URL = "https://ecopark-system-api.herokuapp.com/api/card/reset";

    // demo data
    public static final String POST_DATA = "{\"cardId\": \"118609_group1_2020\", \"money\": 50000}";
    public static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMTg2MDlfZ3JvdXAxXzIwMjAiLCJpYXQiOjE1OTkxMTk5NDl9.y81pBkM0pVn31YDPFwMGXXkQRKW5RaPIJ5WW5r9OW-Y";

    // database config
    public static final String DB_NAME = "aims";
    public static final String DB_USERNAME = System.getenv("DB_USERNAME");
    public static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
}
