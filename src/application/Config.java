package application;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Config {
	public static String CURRENCY = "VND";

	public static final String INVOICE_SCREEN_PATH = "/views/fxml/invoice.fxml";
	public static final String PAYMENT_SCREEN_PATH = "/views/fxml/payment.fxml";
	public static final String RESULT_SCREEN_PATH = "/views/fxml/result.fxml";
	public static final String SPLASH_SCREEN_PATH = "/views/fxml/splash.fxml";
	public static final String CART_SCREEN_PATH = "/views/fxml/cart.fxml";
	public static final String SHIPPING_SCREEN_PATH = "/views/fxml/shipping.fxml";
	public static final String MEDIA_PATH = "/views/fxml/media.fxml";

	public static Font REGULAR_FONT = Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, 24);

	public static String[] PROVINCES = { "Bắc Giang", "Bắc Kạn", "Cao Bằng", "Hà Giang", "Lạng Sơn", "Phú Thọ",
			"Quảng Ninh", "Thái Nguyên", "Tuyên Quang", "Yên Bái", "Điện Biên", "Hòa Bình", "Lai Châu", "Sơn La",
			"Bắc Ninh", "Hà Nam", "Hải Dương", "Hưng Yên", "Nam Định", "Ninh Bình", "Thái Bình", "Vĩnh Phúc", "Hà Nội",
			"Hải Phòng", "Hà Tĩnh", "Nghệ An", "Quảng Bình", "Quảng Trị", "Thanh Hóa", "Thừa Thiên-Huế", "Đắk Lắk",
			"Đắk Nông", "Gia Lai", "Kon Tum", "Lâm Đồng", "Bình Định", "Bình Thuận", "Khánh Hòa", "Ninh Thuận",
			"Phú Yên", "Quảng Nam", "Quảng Ngãi", "Đà Nẵng", "Bà Rịa-Vũng Tàu", "Bình Dương", "Bình Phước", "Đồng Nai",
			"Tây Ninh", "Hồ Chí Minh", "An Giang", "Bạc Liêu", "Bến Tre", "Cà Mau", "Đồng Tháp", "Hậu Giang",
			"Kiên Giang", "Long An", "Sóc Trăng", "Tiền Giang", "Trà Vinh", "Vĩnh Long", "Cần Thơ" };
}
