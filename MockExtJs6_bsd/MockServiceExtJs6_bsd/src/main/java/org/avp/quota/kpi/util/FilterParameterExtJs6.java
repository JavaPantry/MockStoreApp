package org.avp.quota.kpi.util;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * Nice inline enum use
 *
 *	filter.setType(FilterParameter.FilterType.NUMERIC);
 *	and
 *	if(filterParameters[i].getType()==FilterParameter.FilterType.NUMERIC){...}
 *	
 *	public class FilterParameter implements Serializable {
 *		public enum FilterType {
 *			   STRING, NUMERIC, DATE, BOOLEAN
 *			 }
 *		private FilterType type = FilterType.STRING; // "string", "numeric", "date", "boolean"
 *		...
 *	}
 * @author Q05459
 *
 */
public class FilterParameterExtJs6 implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public FilterParameterExtJs6(){}
	
	/**
	 * @param operator
	 * @param value
	 * @param field
	 */
	public FilterParameterExtJs6(String field, String value, FilterOperator operator) {
		super();
		this.operator = operator;
		this.value = value;
		this.field = field;
	}
	public FilterParameterExtJs6(String field, Number value, FilterOperator operator) {
		super();
		this.operator = operator;
		this.value = value.toString();
		this.field = field;
	}

	public FilterParameterExtJs6(String field, FilterOperator operator, String value, List<String> values) {
		// TODO Auto-generated constructor stub
	}

	private FilterOperator operator = FilterOperator.LIKE; // "like", "in", "eq", "gt", "lt"
	
	private String value; // filter fields value
	private List<String> values; // in case of type='list' ExtJs send value as array
	private String field; // when filter set by grid header
	private String property; // when filter set programmatically by adding filter to store
	
	
	public FilterOperator getOperator() {return operator;}
	public void setOperator(FilterOperator operator) {this.operator = operator;}
	public String getValue() {return value;}
	public void setValue(String value) {this.value = value;}
	public String getField() {return field;}
	public void setField(String field) {this.field = field;}
	public String getProperty() {return property;}
	public void setProperty(String property) {this.property = property;}
	
	public List<String> getValues() {return values;}
	public void setValues(List<String> values) {this.values = values;}
	
	@Override
	public String toString() {
		return String.format("FilterParameter [operator=%s, value=%s, field=%s, property=%s]",
							operator, value, field, property);
	}
}
