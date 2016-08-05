package org.avp.quota.kpi.model.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CodeDto implements Serializable {
	private String code;
	private String name;

	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
}
