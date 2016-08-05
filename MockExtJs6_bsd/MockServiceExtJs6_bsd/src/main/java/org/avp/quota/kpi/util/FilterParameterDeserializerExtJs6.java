package org.avp.quota.kpi.util;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/*
 * 
 * http://stackoverflow.com/questions/31562157/gson-how-to-deserialize-array-or-empty-string
 */
public class FilterParameterDeserializerExtJs6 implements JsonDeserializer<FilterParameterExtJs6> {
	  public FilterParameterExtJs6 deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
		      throws JsonParseException{
		  
		JsonObject jsonObj = json.getAsJsonObject();
		FilterParameterExtJs6 filterParameter = new FilterParameterExtJs6();
		if(jsonObj.get("property") != null){
			filterParameter.setProperty(jsonObj.getAsJsonPrimitive("property").getAsString());
		}
		if(jsonObj.get("property") != null){
			filterParameter.setField(jsonObj.getAsJsonPrimitive("property").getAsString()); 
		}
		FilterOperatorDeserializer operatorDeserializer = new FilterOperatorDeserializer();
		filterParameter.setOperator(operatorDeserializer.deserialize(jsonObj.get("operator"), typeOfT, context));		
		
		JsonElement jElement = jsonObj.get("value");
		if(jElement.isJsonArray()) {
			List<String> values = Collections.emptyList();
			values = context.deserialize(jElement.getAsJsonArray(), new TypeToken<List<String>>(){}.getType());
			filterParameter.setValues(values);
		}else{
			filterParameter.setValue(jsonObj.getAsJsonPrimitive("value").getAsString());
		}
		
		return filterParameter;
	  }
}
