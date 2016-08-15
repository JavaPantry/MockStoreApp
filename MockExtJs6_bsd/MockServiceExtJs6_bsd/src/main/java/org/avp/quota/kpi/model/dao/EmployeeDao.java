package org.avp.quota.kpi.model.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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


@SuppressWarnings("serial")
@Entity
@Table(name = "ids_employee_profile")
public final class EmployeeDao  implements Serializable{

	public EmployeeDao() {
		super();
	}
	
	@Id
	@Column(name="employee_id") //trim over Id will not have effect @ColumnTransformer(read="RTRIM(LTRIM(employee_id))")
	private String employeeId;
	
	@Column(name="employee_nm")//@ColumnTransformer(read="RTRIM(LTRIM(employee_nm))")
	private String name; 
	
	@Column(name="status")//@ColumnTransformer(read="RTRIM(LTRIM(status))")
	private String status;					

	@Column(name="job_title")//@ColumnTransformer(read="RTRIM(LTRIM(job_title))")
	private String jobTitle;					

	@Column(name="company")//@ColumnTransformer(read="RTRIM(LTRIM(company))")
	private String company;					

	@Column(name="location")//@ColumnTransformer(read="RTRIM(LTRIM(location))")
	private String location;					

	@Column(name="location_nm")//@ColumnTransformer(read="RTRIM(LTRIM(location_nm))")
	private String locationName;					

	@Column(name="dept_id")//@ColumnTransformer(read="RTRIM(LTRIM(dept_id))")
	private String deptId;					

	@Column(name="dept_nm")//@ColumnTransformer(read="RTRIM(LTRIM(dept_nm))")
	private String deptName;					

	@Column(name="manager_id")//@ColumnTransformer(read="RTRIM(LTRIM(manager_id))")
	private String managerId;					

	@Column(name="manager_level")//@ColumnTransformer(read="RTRIM(LTRIM(manager_level))")
	private String managerLevel;					

	@Column(name="report_path")//@ColumnTransformer(read="RTRIM(LTRIM(report_path))")
	private String reportPath;					

	@Column(name="report_path_name")//@ColumnTransformer(read="RTRIM(LTRIM(report_path_name))")
	private String reportPathName;

	/*
	 * transient for json convertor to not call getter
	 */
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)//QKPI-71: fetch=FetchType.EAGER
	private transient List<SalesRepresentativeDao> salesRepsByUser;
	public List<SalesRepresentativeDao> getSalesRepsByUser() {return salesRepsByUser;}
	public void setSalesRepsByUser(List<SalesRepresentativeDao> salesRepsByUser) {this.salesRepsByUser = salesRepsByUser;}

	/* Original from QuotaKPI_Oct28_RCv1.zip
	 * QKPI-71:
	 * 'at'OneToMany(fetch=FetchType.EAGER , mappedBy="pk.manager",cascade={CascadeType.ALL}, orphanRemoval=true)//,cascade={CascadeType.PERSIST, CascadeType.MERGE})
	 * 'at'Fetch(value = FetchMode.SUBSELECT)
	 * 
	 * by removing FetchMode.SUBSELECT and change to FetchType.LAZY we are delete extra select (backward reference from SalesRep->Quota)
	 * select salesrepem0_.manager_id as manager_1_3_1_, salesrepem0_.manager_id as manager_1_4_1_, salesrepem0_.first_prod_ctrl_cd as first_pr2_4_1_, salesrepem0_.alt_salesrep_cd as alt_sale3_4_1_, salesrepem0_.manager_id as manager_1_4_0_, salesrepem0_.first_prod_ctrl_cd as first_pr2_4_0_, salesrepem0_.alt_salesrep_cd as alt_sale3_4_0_ 
	 * 	from ids_manager salesrepem0_ where salesrepem0_.manager_id=?
	 * select salesrepem0_.manager_id as manager_1_3_1_, salesrepem0_.manager_id as manager_1_4_1_, salesrepem0_.first_prod_ctrl_cd as first_pr2_4_1_, salesrepem0_.alt_salesrep_cd as alt_sale3_4_1_, salesrepem0_.manager_id as manager_1_4_0_, salesrepem0_.first_prod_ctrl_cd as first_pr2_4_0_, salesrepem0_.alt_salesrep_cd as alt_sale3_4_0_ 
	 * 	from ids_manager salesrepem0_ where salesrepem0_.manager_id=?
	 * 
	*/
	@OneToMany(fetch=FetchType.LAZY , mappedBy="pk.manager",cascade={CascadeType.ALL}, orphanRemoval=true)
	private Set<SalesRepEmployeeJoin> salesRepEmployeeJoins;

	public Set<SalesRepEmployeeJoin> getSalesRepEmployeeJoins() {return salesRepEmployeeJoins;}
	public void setSalesRepEmployeeJoins(Set<SalesRepEmployeeJoin> salesRepEmployeeJoins) {
		this.salesRepEmployeeJoins = salesRepEmployeeJoins;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((deptId == null) ? 0 : deptId.hashCode());
		result = prime * result
				+ ((deptName == null) ? 0 : deptName.hashCode());
		result = prime * result
				+ ((employeeId == null) ? 0 : employeeId.hashCode());
		//result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((jobTitle == null) ? 0 : jobTitle.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result
				+ ((locationName == null) ? 0 : locationName.hashCode());
		result = prime * result
				+ ((managerId == null) ? 0 : managerId.hashCode());
		result = prime * result
				+ ((managerLevel == null) ? 0 : managerLevel.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((reportPath == null) ? 0 : reportPath.hashCode());
		result = prime * result
				+ ((reportPathName == null) ? 0 : reportPathName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDao other = (EmployeeDao) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (deptId == null) {
			if (other.deptId != null)
				return false;
		} else if (!deptId.equals(other.deptId))
			return false;
		if (deptName == null) {
			if (other.deptName != null)
				return false;
		} else if (!deptName.equals(other.deptName))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
/*		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;*/
		if (jobTitle == null) {
			if (other.jobTitle != null)
				return false;
		} else if (!jobTitle.equals(other.jobTitle))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (locationName == null) {
			if (other.locationName != null)
				return false;
		} else if (!locationName.equals(other.locationName))
			return false;
		if (managerId == null) {
			if (other.managerId != null)
				return false;
		} else if (!managerId.equals(other.managerId))
			return false;
		if (managerLevel == null) {
			if (other.managerLevel != null)
				return false;
		} else if (!managerLevel.equals(other.managerLevel))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reportPath == null) {
			if (other.reportPath != null)
				return false;
		} else if (!reportPath.equals(other.reportPath))
			return false;
		if (reportPathName == null) {
			if (other.reportPathName != null)
				return false;
		} else if (!reportPathName.equals(other.reportPathName))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String
				.format("EmployeeDao [employee_id=%s, name=%s, status=%s, jobTitle=%s, company=%s, location=%s, locationName=%s, deptId=%s, deptName=%s, managerId=%s, managerLevel=%s, reportPath=%s, reportPathName=%s]",//id=%s, version=%s, 
						employeeId, name, status, jobTitle,//id, version, 
						company, location, locationName, deptId, deptName,
						managerId, managerLevel, reportPath, reportPathName);
	}
	
	public EmployeeDao(String employeeId, String name, String status,
			String jobTitle, String company, String location,
			String locationName, String deptId, String deptName,
			String managerId, String managerLevel, String reportPath,
			String reportPathName/*,
			List<SalesRepresentativeDao> salesRepsByUser,
			List<SalesRepEmployeeJoin> salesRepEmployeeJoins*/) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.status = status;
		this.jobTitle = jobTitle;
		this.company = company;
		this.location = location;
		this.locationName = locationName;
		this.deptId = deptId;
		this.deptName = deptName;
		this.managerId = managerId;
		this.managerLevel = managerLevel;
		this.reportPath = reportPath;
		this.reportPathName = reportPathName;
		/*this.salesRepsByUser = salesRepsByUser;
		this.salesRepEmployeeJoins = salesRepEmployeeJoins;*/
	}					

	
}
