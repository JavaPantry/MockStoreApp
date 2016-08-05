package org.avp.quota.kpi.model.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


public final class EmployeeDto  implements Serializable{

	public EmployeeDto() {
		super();
	}
	
	private String employeeId;
	private String name; 
	private String status;					
	private String jobTitle;					
	private String company;					
	private String location;					
	private String locationName;					
	private String deptId;					
	private String deptName;					
	private String managerId;					
	private String managerLevel;					
	private String reportPath;					
	private String reportPathName;
	private String productLinesContentStr;
	private String salesRepresentativeId;

	public String getEmployeeId() {return employeeId;}
	public void setEmployeeId(String employeeId) {this.employeeId = employeeId;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}
	public String getJobTitle() {return jobTitle;}
	public void setJobTitle(String jobTitle) {this.jobTitle = jobTitle;}
	public String getCompany() {return company;}
	public void setCompany(String company) {this.company = company;}
	public String getLocation() {return location;}
	public void setLocation(String location) {this.location = location;}
	public String getLocationName() {return locationName;}
	public void setLocationName(String locationName) {this.locationName = locationName;}
	public String getDeptId() {return deptId;}
	public void setDeptId(String deptId) {this.deptId = deptId;}
	public String getDeptName() {return deptName;}
	public void setDeptName(String deptName) {this.deptName = deptName;}
	public String getManagerId() {return managerId;}
	public void setManagerId(String managerId) {this.managerId = managerId;}
	public String getManagerLevel() {return managerLevel;}
	public void setManagerLevel(String managerLevel) {this.managerLevel = managerLevel;}
	public String getReportPath() {return reportPath;}
	public void setReportPath(String reportPath) {this.reportPath = reportPath;}
	public String getReportPathName() {return reportPathName;}
	public void setReportPathName(String reportPathName) {this.reportPathName = reportPathName;}
	public void setProductLinesContentStr(String productLinesContentStr) {this.productLinesContentStr = productLinesContentStr;}
	public String getProductLinesContentStr() {return productLinesContentStr;}
	public String getSalesRepresentativeId() {return salesRepresentativeId;} 
	public void setSalesRepresentativeId(String salesRepresentativeId) {this.salesRepresentativeId = salesRepresentativeId;} 
}
