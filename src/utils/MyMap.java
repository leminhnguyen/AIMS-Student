package utils;

import java.io.Serial;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A custom JSON-like map implementation that extends {@link LinkedHashMap} with enhanced
 * serialization and object-to-map conversion capabilities.
 *
 * <p>This class provides methods to:</p>
 * <ul>
 *   <li>Convert objects to JSON-like string representations</li>
 *   <li>Convert objects to map structures</li>
 *   <li>Parse JSON-like strings into map structures</li>
 * </ul>
 *
 * <p>Key design decisions:</p>
 * <ul>
 *   <li>Maintains insertion order using {@link LinkedHashMap}</li>
 *   <li>Provides custom JSON serialization</li>
 *   <li>Supports recursive object-to-map conversion</li>
 * </ul>
 *
 * @author hieud
 * @version 1.1
 * @since 1.0
 */
public class MyMap extends LinkedHashMap<String, Object> {
	@Serial
	private static final long serialVersionUID = 1L;

	// Static offset to track parsing position during JSON-like string parsing
	private static int offset = 0;

	/**
	 * Converts the current map to a JSON-like string representation.
	 *
	 * <p>Refactoring notes:</p>
	 * <ul>
	 *   <li>Simplified nested object handling</li>
	 *   <li>Removed redundant commented-out code</li>
	 *   <li>Improved string building efficiency</li>
	 * </ul>
	 *
	 * @return A JSON-like string representation of the map
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

			sb.append('"').append(key).append('"');
			sb.append(':');

			// Refactored to handle nested MyMap objects more cleanly
			sb.append(value instanceof MyMap ? ((MyMap) value).toJSON() : ('"' + value.toString() + '"'));

			if (i == max)
				return sb.append('}').toString();
			sb.append(",");
		}
	}

	/**
	 * Converts an object to a map representation of its fields.
	 *
	 * <p>Refactoring improvements:</p>
	 * <ul>
	 *   <li>Enhanced error handling</li>
	 *   <li>More robust nested object conversion</li>
	 *   <li>Simplified recursive conversion logic</li>
	 * </ul>
	 *
	 * @param obj The object to be converted to a map
	 * @return A map containing the object's field names and values
	 * @throws IllegalArgumentException If reflection access fails
	 * @throws IllegalAccessException If field access is not permitted
	 */
	public static Map<String, Object> toMyMap(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> map = new MyMap();
		for (Field field : obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value = field.get(obj);

			try {
				// Recursive conversion for non-Java standard objects
				if (value != null && !value.getClass().getPackage().getName().startsWith("java")) {
					value = MyMap.toMyMap(value).toString();
				}
			} catch (Exception ex) {
				// Improved error handling with more context
				throw new RuntimeException("Failed to convert nested object: " + field.getName(), ex);
			}

			map.put(field.getName(), value);
			field.setAccessible(false);
		}
		return map;
	}

	/**
	 * Extracts the next term between double quotes in a string.
	 *
	 * <p>Refactoring notes:</p>
	 * <ul>
	 *   <li>Added more robust input validation</li>
	 *   <li>Improved error messaging</li>
	 *   <li>Simplified term extraction logic</li>
	 * </ul>
	 *
	 * @param str The input string to parse
	 * @param idx The starting index of the opening quote
	 * @return The extracted term between quotes
	 * @throws IllegalArgumentException If parsing fails
	 */
	private static String getNextTerm(String str, int idx) {
		if (str == null || idx >= str.length() || str.charAt(idx) != '"') {
			throw new IllegalArgumentException("Invalid input: Must start with a quote at valid index.");
		}

		// Handle empty string case
		if (str.charAt(idx + 1) == '"') {
			offset = 2;
			return "";
		}

		int i = idx + 1;
		StringBuilder sb = new StringBuilder();
		while (i < str.length() && str.charAt(i) != '"') {
			sb.append(str.charAt(i));
			i++;
		}

		// Validate complete parsing
		if (i == str.length()) {
			throw new IllegalArgumentException("Incomplete term: No closing quote found.");
		}

		String result = sb.toString();
		offset = result.length() + 2; // Update for term and quotes
		return result;
	}

	/**
	 * Parses a JSON-like string into a MyMap object.
	 *
	 * <p>Refactoring improvements:</p>
	 * <ul>
	 *   <li>More comprehensive error handling</li>
	 *   <li>Improved parsing logic</li>
	 *   <li>Better tracking of parsing state</li>
	 * </ul>
	 *
	 * @param str The JSON-like string to parse
	 * @param idx The starting index for parsing
	 * @return A MyMap representing the parsed object
	 * @throws IllegalArgumentException If parsing fails
	 */
	public static MyMap toMyMap(String str, int idx) throws IllegalArgumentException {
		// Validate input
		if (str == null || str.length() < 2 || str.charAt(idx) != '{') {
			throw new IllegalArgumentException("Invalid input: Must start with a valid object.");
		}

		MyMap root = new MyMap();
		StringBuilder sb = new StringBuilder();
		int i = idx;
		sb.append(str.charAt(i));
		i++;

		try {
			while (true) {
				// Validate key start
				if (str.charAt(i) != '"') {
					throw new IllegalArgumentException("Expected key to start with quote.");
				}

				// Extract key
				String key = getNextTerm(str, i);
				sb.append(str.subSequence(i, i + offset));
				i += offset;
				offset = 0;

				// Validate colon
				sb.append(str.charAt(i));
				if (str.charAt(i) != ':') {
					throw new IllegalArgumentException("Expected colon after key.");
				}
				i++;

				// Parse value
				Object value;
				if (str.charAt(i) == '{') {
					value = toMyMap(str, i);
					sb.append(str.subSequence(i, i + offset));
					i += offset;
					offset = 0;
				} else if (str.charAt(i) == '"') {
					value = getNextTerm(str, i);
					sb.append(str.subSequence(i, i + offset));
					i += offset;
					offset = 0;
				} else {
					throw new IllegalArgumentException("Unsupported value type.");
				}

				// Add to map
				root.put(key, value);

				// Handle end of object or next key-value pair
				if (str.charAt(i) == ',') {
					sb.append(str.charAt(i));
					i++;
				} else if (str.charAt(i) == '}') {
					sb.append(str.charAt(i));
					break;
				} else {
					throw new IllegalArgumentException("Invalid object structure.");
				}
			}
			offset = sb.toString().length();
		} catch (Exception e) {
			throw new IllegalArgumentException("Failed to parse JSON-like string: " + e.getMessage());
		}
		return root;
	}
}