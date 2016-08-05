package org.avp.quota.kpi.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SortParameter implements Serializable {
	public static final String ASC = "ASC";
	public static final String DESC = "DESC";
	
	public SortParameter() {
		
	}
	public SortParameter(String property, String direction) {
		super();
		this.property = property;
		this.direction = direction;
	}
	private String property; // sorting field name;
	private String direction; // "ASC" or "DESC"
	
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return String.format("SortParameter [property=%s, direction=%s]",property, direction);
	}

	
	
}
