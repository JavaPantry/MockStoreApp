package org.avp.quota.kpi.util;

/**
 * use as FilterParameter.FilterType.STRING or FilterParameter.FilterType.NUMERIC
 * 
 * STRING for  {type: 'string',dataIndex: 'categoryId'},
 * NUMERIC for {type: 'numeric',dataIndex: 'year'}]}
 */
public enum FilterType {
	STRING ("string" ),
	NUMERIC ("numeric" ),
	LIST ("list" ),
	EXACT("exact"),
	BOOLEAN ("boolean" ), //not supported
	DATE ("date" ); //not supported
    String typeCode ;
    FilterType(String type){ typeCode = type;}
    public String toString() { return typeCode;}
}