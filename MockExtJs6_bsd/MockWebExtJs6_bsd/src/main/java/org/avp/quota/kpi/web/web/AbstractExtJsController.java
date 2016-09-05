package org.avp.quota.kpi.web.web;


import org.avp.quota.kpi.util.FilterParameterExtJs6;
import org.avp.quota.kpi.util.GeneralUtil;
import org.avp.quota.kpi.util.SortParameter;

public class AbstractExtJsController {
	public static final String SUCCESS_RESPONSE = "{success:true,message:'Ok'}";
	public static final String FAIL_RESPONSE = "{success:false,message:'Fail'}";
	
	public AbstractExtJsController() {
		super();
	}

	protected SortParameter[] getSortFromJson(String sortStr) {
//		if(GeneralUtil.isEmpty(sortStr))
			return null;
//		SortParameter[] sortParameters = GsonUtil.getArrayFromJson(sortStr, SortParameter[].class);
//		return sortParameters;
	}

	/* 
	 * TODO - <AP> extract to static method in ExtJsUtility class otherwise need to copy this function to test case
	 */
	protected FilterParameterExtJs6[] getFiltersFromJson(String filterStr) {
//		if(GeneralUtil.isEmpty(filterStr))
			return null;
//		FilterParameterExtJs6[] filterParameters  = GsonUtil.getArrayFromJson(filterStr, FilterParameterExtJs6[].class);
//		//TODO - <AP> sometime ExtJs sends not 'property' but 'field'. Is it true for ExtJs 6.0.#?
//		for (FilterParameterExtJs6 filterParameter : filterParameters) {
//			if(!GeneralUtil.isEmpty(filterParameter.getProperty()))
//					filterParameter.setField(filterParameter.getProperty());
//		}
//		return filterParameters;
	}

	
}