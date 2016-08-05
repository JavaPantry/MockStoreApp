package org.avp.quota.kpi.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.avp.quota.kpi.model.dao.EmployeeDao;
import org.avp.quota.kpi.model.dao.SalesRepEmployeeJoin;
import org.avp.quota.kpi.model.dao.SalesRepresentativeDao;
import org.avp.quota.kpi.model.dao.TocDao;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@SuppressWarnings("serial")
public class SalesRepresentativeDto  implements Serializable{

	public SalesRepresentativeDto() {
		super();
	}

	private String salesRepresentativeId;
	private String salesRepresentativeName;
	private EmployeeDto user;
	private boolean allAccess;
	private List<TocDao> tocs = new ArrayList<TocDao>();
	private List<EmployeeDto> managers = new ArrayList<EmployeeDto>();

	public String getSalesRepresentativeId() {return salesRepresentativeId;}
	public void setSalesRepresentativeId(String salesRepresentativeId) {this.salesRepresentativeId = salesRepresentativeId;}
	public String getSalesRepresentativeName() {return salesRepresentativeName;}
	public void setSalesRepresentativeName(String salesRepresentativeName) {this.salesRepresentativeName = salesRepresentativeName;}
	public boolean getAllAccess() {return allAccess;}
	public void setAllAccess(boolean allAccess) {this.allAccess = allAccess;}
	public EmployeeDto getUser() {return user;}
	public void setUser(EmployeeDto user) {this.user = user;}
	public List<EmployeeDto> getManagers() {return managers;}
	public void setManagers(List<EmployeeDto> managers) {this.managers = managers;}
	public List<TocDao> getTocs() {return tocs;}
	public void setTocs(List<TocDao> tocs) {this.tocs = tocs;}


}
