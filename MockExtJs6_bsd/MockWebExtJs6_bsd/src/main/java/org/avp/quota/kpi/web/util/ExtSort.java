package org.avp.quota.kpi.web.util;
/**
 * DTO for sorting [{"property":"value6","direction":"ASC"}]
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
 *
 * @author Q05459
 *
 */
public class ExtSort {
	static final String ASC = "ASC";
	static final String DESC = "DESC";

	String property;
	String direction;

	public String getProperty() {return property;}
	public void setProperty(String property) {this.property = property;}
	public String getDirection() {return direction;}
	public void setDirection(String direction) {this.direction = direction;}
}
