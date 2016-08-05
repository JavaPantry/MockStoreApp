package org.avp.quota.kpi.web.util;

/**
 * DTO for filtering [{"type":"numeric","comparison":"lt","value":2,"field":"value4"},
 * {"type":"string","value":"asd","field":"categoryCode"}]
 *
 * Typical request from grid with optional sort and filter arguments:
 *
 * http://localhost:8080/MockStubWeb/ajax/quotas?
 * filter=[{"type":"numeric","comparison":"lt","value":2,"field":"value1"},
 * {"type":"numeric","comparison":"lt","value":2,"field":"value2"},
 * {"type":"numeric","comparison":"lt","value":2,"field":"value4"},
 * {"type":"string","value":"asd","field":"categoryCode"}]
 * &page=1&start=0&limit=35
 * &sort=[{"property":"value6","direction":"ASC"}]
 *
 * @author Q05459
 */

public class ExtFilter {
	static final String NUMERIC_TYPE	= "numeric";
	static final String STRING_TYPE		= "string";
	static final String COMPARISON_LT	= "lt"; // <
	static final String COMPARISON_GT	= "gt"; // >

	String field;
	String type;
	String comparison;
	Object value;

	public String getField() {return field;}
	public void setField(String field) {this.field = field;}
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	public String getComparison() {return comparison;}
	public void setComparison(String comparison) {this.comparison = comparison;}
	public Object getValue() {return value;}
	public void setValue(Object value) {this.value = value;}

}
