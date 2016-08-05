package org.avp.quota.kpi.util;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;


public class FilterOperatorDeserializer implements JsonDeserializer<FilterOperator> {
	  public FilterOperator deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
		      throws JsonParseException{
		  
		  FilterOperator[] filterOperators = FilterOperator.values();
		    for (FilterOperator operator : filterOperators)
		    {
		      if (operator.operatorCode.equals(json.getAsString()))
		        return operator;
		    }
		    return null;
		  }
}
