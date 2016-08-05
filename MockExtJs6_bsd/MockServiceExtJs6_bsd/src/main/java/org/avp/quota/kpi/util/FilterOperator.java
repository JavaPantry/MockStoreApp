package org.avp.quota.kpi.util;

/**
 * ExtJs 6.x.x change json format for filters
 * Instead of 'type' use 'operator'
 * 
 * use as FilterParameterExtJs6.FilterType.STRING or FilterType.NUMERIC
 * 
 * STRING for  {type: 'string',dataIndex: 'categoryId'},
 * NUMERIC for {type: 'numeric',dataIndex: 'year'}]}
 * LIST ("list" ),
 * //EXACT("exact"),
 * BOOLEAN ("boolean" ), //not supported
 * DATE ("date" ); //not supported
 * 
 */
public enum FilterOperator {

	LIKE("like"),
	IN("in"),
	EQ("eq"),
	GT("gt"),//not implemented
	LT("lt");//not implemented
    
	String operatorCode ;
    FilterOperator(String operator){ operatorCode = operator;}
    public String toString() { return operatorCode;}
}