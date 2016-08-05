package org.avp.quota.kpi.util;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;


public class FilterTypeDeserializer implements JsonDeserializer<FilterType> {
	  public FilterType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
		      throws JsonParseException{
		  
		  FilterType[] filterTypes = FilterType.values();
		    for (FilterType type : filterTypes)
		    {
		      if (type.typeCode.equals(json.getAsString()))
		        return type;
		    }
		    return null;
		  }
}
