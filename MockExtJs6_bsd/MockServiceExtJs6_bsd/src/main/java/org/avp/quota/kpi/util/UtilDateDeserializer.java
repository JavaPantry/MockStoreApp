package org.avp.quota.kpi.util;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class UtilDateDeserializer implements JsonDeserializer<java.util.Date> {
	private static Logger logger = Logger.getLogger(UtilDateDeserializer.class);
	public Date deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		//return new java.util.Date(json.getAsJsonPrimitive().getAsLong());
		String string = "Nov 15, 2011 9:56:04 AM";//"January 2, 2010";
		Date date = null;
		try {
			date = new SimpleDateFormat("MMMM d, yyyy hh:mm:ss", Locale.ENGLISH).parse(string);
		} catch (ParseException e) {
			logger.error(e, e);
		}
		return date;
	}
}