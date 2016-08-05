package org.avp.quota.kpi.util;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SuppressWarnings("serial")
public class GsonUtil implements Serializable {
	
/*	@Deprecated
	public static <T> List<T> getListFromJson(Object obj, Class<T> clazz) {
		JSONObject jsonObject = JSONObject.fromObject(obj);
		List<T> list = new ArrayList<T>();
		for (Iterator<String> iter = jsonObject.keys(); iter.hasNext();) {

			String key = iter.next();
			JSONArray array = jsonObject.getJSONArray(key);
			for (int i = 0; i < array.size(); i++) {
				JSONObject object = (JSONObject) array.get(i);
				T t = (T) JSONObject.toBean(object, clazz);
				if (t != null)
					list.add(t);
			}
		}
		return list;
	}*/

	public static <T> T json2Obj(String json, Type type) {
		Gson gson = new GsonBuilder()
				/*.registerTypeAdapter(ISalesMonthFct.class, new GsonInterfaceAdapter<SalesMonthFct>())
				//.registerTypeAdapter(ISalesWeekFct.class, new GsonInterfaceAdapter<SalesWeekFct>())
				.registerTypeAdapter(IStatusType.class, new GsonInterfaceAdapter<StatusType>())
				.registerTypeAdapter(ISalesPrdByAcct.class, new GsonInterfaceAdapter<SalesPrdByAcct>())
				.registerTypeAdapter(IYearForecast.class, new GsonInterfaceAdapter<YearForecast>())
				.registerTypeAdapter(IDealer.class, new GsonInterfaceAdapter<Dealer>())
				.registerTypeAdapter(IProduct.class, new GsonInterfaceAdapter<Product>())
				.registerTypeAdapter(IUserProfile.class, new GsonInterfaceAdapter<UserProfile>())
				.registerTypeAdapter(IUserGroup.class, new GsonInterfaceAdapter<UserGroup>())
				.registerTypeAdapter(IDealerDetail.class, new GsonInterfaceAdapter<DealerDetail>())
				.registerTypeAdapter(IDealerQuota.class, new GsonInterfaceAdapter<DealerQuota>())
				.registerTypeAdapter(IDealerQuotaDetail.class, new GsonInterfaceAdapter<DealerQuotaDetail>())
				.registerTypeAdapter(java.util.Date.class,new UtilDateDeserializer()).setDateFormat(DateFormat.LONG)*/
				.create();
		return gson.fromJson(json, type);
	}
	public static String obj2json(Object bean) {
		Gson gson = new GsonBuilder()
						.registerTypeAdapter(java.util.Date.class,new UtilDateSerializer()).setDateFormat(DateFormat.LONG)
						//.registerTypeAdapter(Iinteface.class, new GsonInterfaceAdapter<IntefaceImpl>())
						.create();
		return gson.toJson(bean);
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getListFromJsonArray(String jsonString, Class<T[]> arrayClazz) {
	
		List<T> list = new ArrayList<T>();
		Gson gson = new GsonBuilder()
/*				.registerTypeAdapter(ISalesMonthFct.class, new GsonInterfaceAdapter<SalesMonthFct>())
				//.registerTypeAdapter(ISalesWeekFct.class, new GsonInterfaceAdapter<SalesWeekFct>())
				.registerTypeAdapter(IStatusType.class, new GsonInterfaceAdapter<StatusType>())
				.registerTypeAdapter(ISalesPrdByAcct.class, new GsonInterfaceAdapter<SalesPrdByAcct>())
				.registerTypeAdapter(IYearForecast.class, new GsonInterfaceAdapter<YearForecast>())
				.registerTypeAdapter(IDealer.class, new GsonInterfaceAdapter<Dealer>())
				.registerTypeAdapter(IProduct.class, new GsonInterfaceAdapter<Product>())
				.registerTypeAdapter(IUserProfile.class, new GsonInterfaceAdapter<UserProfile>())
				.registerTypeAdapter(IUserGroup.class, new GsonInterfaceAdapter<UserGroup>())
				.registerTypeAdapter(IDealerDetail.class, new GsonInterfaceAdapter<DealerDetail>())
				.registerTypeAdapter(IDealerQuota.class, new GsonInterfaceAdapter<DealerQuota>())
				.registerTypeAdapter(IDealerQuotaDetail.class, new GsonInterfaceAdapter<DealerQuotaDetail>())
				.registerTypeAdapter(java.util.Date.class,new UtilDateDeserializer()).setDateFormat(DateFormat.LONG)//.setDateFormat("MM/dd/yyyy")//
*/				.create();
		
		T[] array = (T[]) gson.fromJson(jsonString, arrayClazz);
		for (T entity : array) {
			list.add(entity);
		}
		return list;
	}

/*	@Deprecated
	@SuppressWarnings("unchecked")
	public static <T> List<T> getListFromJsonArray_Vianet_sf_json_JSONObject(Object obj, Class<T> clazz) {
		JSONArray jsonObjects = JSONArray.fromObject(obj);
		List<T> list = new ArrayList<T>();
		for (int i=0; i<jsonObjects.size();i++) {
			JSONObject jsonObject = jsonObjects.getJSONObject(i);
			T t = (T) JSONObject.toBean(jsonObject, clazz);
			if (t != null)
				list.add(t);
		}
		return list;
	}
*/	
	/*@Deprecated
	public static <T> T[] getArrayFromJson(String jsonString, Class<T[]> clazz) {
		Gson gson = new GsonBuilder()
			.registerTypeAdapter(java.util.Date.class,new UtilDateSerializer()).setDateFormat(DateFormat.LONG)
			.registerTypeAdapter(FilterType.class, new FilterTypeDeserializer())
			.registerTypeAdapter(FilterParameter.class, new FilterParameterDeserializer())
			.create();

	    T[] array = (T[]) gson.fromJson(jsonString, clazz);
		return array;
	}*/

	@Deprecated
	public static <T> T[] getArrayFromJson(String jsonString, Class<T[]> clazz) {
		Gson gson = new GsonBuilder()
			.registerTypeAdapter(java.util.Date.class,new UtilDateSerializer()).setDateFormat(DateFormat.LONG)
			.registerTypeAdapter(FilterOperator.class, new FilterOperatorDeserializer())
			.registerTypeAdapter(FilterParameterExtJs6.class, new FilterParameterDeserializerExtJs6())
			.create();

	    T[] array = (T[]) gson.fromJson(jsonString, clazz);
		return array;
	}
/*	public static JSONObject parseJSON(Object obj) {
		JSONObject jsonObject = JSONObject.fromObject(obj);
		return jsonObject;
	}*/
}
