package org.avp.quota.kpi.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

/**
 * 
 * Utility class for working with HTML, encodings, etc.
 *
 */
public final class HtmlUtility {

	private static Logger logger = Logger.getLogger(HtmlUtility.class);
	
	public static final String DEFAULT_ENCODING = "ISO-8859-1";
	
	private HtmlUtility() {}
	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String encodeUrl(String url) throws UnsupportedEncodingException {
		try {
			return URLEncoder.encode(url, DEFAULT_ENCODING).replace("+", "%20");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
			throw(e);
		}
	}

}
