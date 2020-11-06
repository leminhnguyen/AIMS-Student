package utils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The {@link utils.MyMap JSON} class represents JSON objects. 
 * To create a new JSON object,
 * JSON jsonObject = new JSON();
 * jsonObject.put("key", value);
 * 
 * @author hieud
 *
 */
public class MyMap extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	/**
	 * Return a {@link java.lang.String String} that represents the JSON object.
	 * 
	 * @author hieudm
	 *         https://hg.openjdk.java.net/jdk8/jdk8/jdk/file/tip/src/share/classes/java/util/Hashtable.java
	 * @return a {@link java.lang.String String}.
	 */
	public String toJSON() {
		int max = size() - 1;
		if (max == -1)
			return "{}";

		StringBuilder sb = new StringBuilder();
		Iterator<Map.Entry<String, Object>> it = entrySet().iterator();

		sb.append('{');
		for (int i = 0;; i++) {
			Map.Entry<String, Object> e = it.next();
			String key = e.getKey();
			Object value = e.getValue();
			sb.append('"' + key.toString() + '"');
			sb.append(':');
			sb.append(value instanceof MyMap ? ((MyMap) value).toJSON() : ('"' + value.toString() + '"'));

//			if (value instanceof MyMap) {
//				sb.append(((MyMap) value).toJSON());
//			} else {
//				sb.append('"' + value.toString() + '"');
//			}
			if (i == max)
				return sb.append('}').toString();
			sb.append(",");
		}
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
	public static Map<String, Object> toMyMap(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> map = new MyMap();
		for (Field field : obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value = field.get(obj);
			try {
				if (!value.getClass().getPackage().getName().startsWith("java")) {
					value = MyMap.toMyMap(value).toString();
				}
			} catch (Exception ex) {
				;
			}
			map.put(field.getName(), value);
			field.setAccessible(false);
		}
		return map;
	}

	private static int offset = 0; // to trace the current index when calling a function

	/**
	 * Return a {@link java.lang.String String} that represents the term in between
	 * 2 double quote.
	 * 
	 * @author hieudm
	 * @param 
	 * str - {@link java.lang.String String}
	 * idx - the index of the open quote
	 * @return the term as {@link java.lang.String String} 
	 * @throws IllegalArgumentException
	 */
	private static String getNextTerm(String str, int idx) {
		if (str == null || idx >= str.length() || str.charAt(idx) != '"') {
			throw new IllegalArgumentException("Cannot resolve the input.");
		}

		if (str.charAt(idx + 1) == '"') {
			return new String();
		}

		int i = idx + 1;
		StringBuilder sb = new StringBuilder();
		do {
			sb.append(str.charAt(i));
			i++;
			if (i == str.length()) {
				throw new IllegalArgumentException("Cannot resolve the input.");
			}
		} while (str.charAt(i) != '"');

		String result = sb.toString();
		offset = result.length() + 2; // update iterator with the term and the 2 double quotes
		return sb.toString();
	}
	/**
	 * Return a {@link utils.MyMap MyMap} that represents the interested substring in a {@link java.lang.String String}.
	 * 
	 * @author hieudm
	 * @param 
	 * str - {@link java.lang.String String}
	 * idx - the index of the first character in the interested substring in the {@link java.lang.String String}
	 * @return the term as {@link utils.MyMap MyMap} 
	 * @throws IllegalArgumentException
	 */
	public static MyMap toMyMap(String str, int idx) throws IllegalArgumentException {
		if (str == null || str.length() < 2 || str.charAt(idx) != '{') {
			throw new IllegalArgumentException("Cannot resolve the input.");
		} else if (idx >= str.length()) {
			return null;
		}

		MyMap root = new MyMap();
		StringBuilder sb = new StringBuilder();
		int i = idx;
		sb.append(str.charAt(i));

		i++;
		try {
			while (true) {
				// open quote
				if (str.charAt(i) != '"') {
					throw new IllegalArgumentException("Cannot resolve the input.");
				}
				// get key
				String key;
				try {
					key = getNextTerm(str, i);
				} catch (Exception ex) {
					throw new IllegalArgumentException("Cannot resolve the input.");
				}
				if (key == null) {
					throw new IllegalArgumentException("Cannot resolve the input.");
				}

				sb.append(str.subSequence(i, i + offset));

				i += offset;
				offset = 0;

				// check colon
				sb.append(str.charAt(i));
				if (str.charAt(i) != ':') {
					throw new IllegalArgumentException("Cannot resolve the input.");
				}
				i++;
				// get value
				Object value;
				if (str.charAt(i) == '{') {
					value = toMyMap(str, i);
					sb.append(str.subSequence(i, i + offset));
					i += offset;
					offset = 0;
				} else if (str.charAt(i) == '"') {
					try {
						value = getNextTerm(str, i);
					} catch (Exception ex) {
						throw new IllegalArgumentException("Cannot resolve the input.");
					}
					if (value == null) {
						throw new IllegalArgumentException("Cannot resolve the input.");
					}
					sb.append(str.subSequence(i, i + offset));
					i += offset;
					offset = 0;
				} else {
					throw new IllegalArgumentException("Cannot resolve the input.");
				}
				//
				root.put(key, value);
				if (str.charAt(i) == ',') {
					sb.append(str.charAt(i));
					i++;
				} else if (str.charAt(i) == '}') {
					sb.append(str.charAt(i));
					break;
				} else {
					throw new IllegalArgumentException("Cannot resolve the input.");
				}
			}
			offset = sb.toString().length();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Cannot resolve the input.");
		}
		return root;
	}

}
