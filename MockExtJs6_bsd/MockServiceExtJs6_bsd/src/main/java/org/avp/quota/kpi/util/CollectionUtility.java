package org.avp.quota.kpi.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public final class CollectionUtility {

	private static Logger logger = Logger.getLogger(CollectionUtility.class);

	private CollectionUtility() {}
	
	/**
	 * 
	 * @param c
	 * @param token
	 * @return
	 */
	public static String serializeStringCollection(Collection<String> c, String token) {
		return serializeStringCollection(c, token, false, false);
	}
	
	/**
	 * Method to serialize collection of strings. Example output: "'string1', 'string2', 'string3'".
	 * Set parameter 'wrap' to true to include single quotes, as in example.
	 * @param c
	 * @param token
	 * @param wrap
	 * @param brackets
	 * @return
	 */
	public static String serializeStringCollection(Collection<String> c, String token, boolean wrap, boolean brackets) {
		String returnString = "";
		StringBuilder sb = new StringBuilder();   
		if (c != null && token != null) {
			sb.append(brackets ? "(" : "");
			
			int itemIndex = 1;
			for (String item : c) {
				if (item.length() > 0) {
					sb.append(wrap ? "'" : "")
						.append(item)
						.append(wrap ? "'" : "")
						.append(itemIndex < c.size() ? token : "");
				}
				
				itemIndex++;
			}

			sb.append(brackets ? ")" : "");
		}
		
		return returnString;
	}

	/**
	 * Converts collection of strings to collection of Long numbers.<br>
	 * Returns NULL if there is a problem with conversion.
	 * @param strings
	 * @return
	 */
	public static Iterable<Long> convertToLong(Collection<String> strings) {
		if (strings != null) {
			try {
				Collection<Long> numbers = new ArrayList<Long>(strings.size());
				for (String s : strings) {
					numbers.add(Long.parseLong(s));
				}
				
				return numbers;
			} catch (NumberFormatException e) {
				logger.error(e);
				throw(e);
			}
		}
		
		return null;
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static <T> Collection<T> copy(Collection<T> source) {
		return new LinkedList<T>(source);
	}
	
	/**
	 * Wraps parameter map into a new HashMap object.<br>
	 * Method created to turn immutable collections into editable objects
	 * @param sourceMap
	 * @return
	 */
	public static <K, V> Map<K, V> copyMap(Map<K, V> sourceMap) {
		return new LinkedHashMap<K,V>(sourceMap);
	}
	
	/**
	 * 
	 * @param array
	 * @return
	 */
	public static <T> Collection<T> fromArray(T[] array) {
		return new LinkedList<T>(Arrays.asList(array));
	}
	
	/**
	 * Converts generic Iterable to generic Collection;<br>
	 * internally working with LinkedList
	 * @param iterable
	 * @return
	 */
	public static <T> Collection<T> toCollection(Iterable<T> iterable) {
		Collection<T> collection = new LinkedList<T>();
		for (T item : iterable) {
			collection.add(item);
		}
		
		return collection;
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static <T> List<T> toList(Collection<T> source) {
		return new LinkedList<T>(source);
	}

	/**
	 * 
	 * @param unsorted
	 * @return
	 */
	public static Collection<String> sort(Collection<String> unsorted) {
		List<String> sorted = toList(unsorted);
		Collections.sort(sorted);
		return sorted;
	}
	
	/**
	 * 
	 * @param source
	 * @return
	 */
	public static <T> Collection<T> unique(Collection<T> source) {
		return new LinkedList<T>(new HashSet<T>(source));
	}
	
}
