package org.avp.quota.kpi.model.dto;

public class ProductLineDTO {
	private String	salesRepresentativeId;
	private String	managerId;
	private String	code;
	private String	description;
	private boolean	exists;
	
	public boolean isExists() {return exists;}
	public void setExists(boolean exists) {this.exists = exists;}
	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public String getSalesRepresentativeId() {return salesRepresentativeId;}
	public void setSalesRepresentativeId(String salesRepresentativeId) {this.salesRepresentativeId = salesRepresentativeId;}
	public String getManagerId() {return managerId;}
	public void setManagerId(String managerId) {this.managerId = managerId;}
	@Override
	public String toString() {
		return String.format("ProductLineDTO [salesRepresentativeId=%s, managerId=%s, code=%s, description=%s, exists=%s]",
						salesRepresentativeId, managerId, code, description,exists);
	}
}
