package utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author nguyenlm Contains helper functions
 */
public class Utils {

	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static Logger LOGGER = getLogger(Utils.class.getName());
	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-4s] [%1$tF %1$tT] [%2$-7s] %5$s %n");
	}

	public static Logger getLogger(String className) {
		return Logger.getLogger(className);
	}

	public static String getCurrencyFormat(int num) {
		Locale vietname = new Locale("vi", "VN");
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(vietname);
		return defaultFormat.format(num);
	}

	
	public static String getToday() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date = new Date();
	    return dateFormat.format(date);
	}
	
	/**
	 * Return a {@link java.util.String String} that represents a {@link }.
	 * 
	 * @author hieudm
	 * @param a {@link java.util.Map Map}.
	 * @return a {@link java.util.String String}.
	 */
	public static String convertMapToJSON(Map<String, Object> map) {
//		System.out.println("inp "+map);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() instanceof Map) {
				Map<String, Object> tmp = (Map<String, Object>) entry.getValue();
				entry.setValue(convertMapToJSON(tmp));
			}
		}
		String mess = map.toString();
		mess = mess.replaceAll(",\\s+", ",");
		mess = mess.replaceAll("([^{},=]+)(,\\s*)?", "\"$1\"$2");
		mess = mess.replaceAll("\"+", "\"");
		mess = mess.replaceAll("=", ":");
//		System.out.println("end "+mess);
		return mess;
	}

	/**
	 * Return a {@link java.util.Map Map} that represents the mapping among
	 * attribute names and their values of an object.
	 * 
	 * @author hieudm
	 *         https://stackoverflow.com/questions/52406467/convert-object-to-map-in-java
	 * @param obj - an arbitrary {@link java.lang.Object Object}.
	 * @return a {@link java.util.Map Map} mapping the attribute names and its
	 *         values.
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static Map<String, Object> map(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> map = new HashMap<>();
		for (Field field : obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value = field.get(obj);
			try {
				if (!value.getClass().getPackage().getName().startsWith("java")) {
					value = Utils.map(value).toString();
				}
			} catch (Exception ex) {
				;
			}
			map.put(field.getName(), value);
			field.setAccessible(false);
		}
		return map;
	}

	/**
	 * Return a {@link java.lang.String String} that represents the cipher text
	 * encrypted by md5 algorithm.
	 * 
	 * @author hieudm vnpay
	 * @param message - plain text as {@link java.lang.String String}.
	 * @return cipher text as {@link java.lang.String String}.
	 */
	public static String md5(String message) {
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(message.getBytes("UTF-8"));
			// converting byte array to Hexadecimal String
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				sb.append(String.format("%02x", b & 0xff));
			}
			digest = sb.toString();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
			Utils.getLogger(Utils.class.getName());
			digest = "";
		}
		return digest;
	}

}