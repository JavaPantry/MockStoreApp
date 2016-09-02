package org.avp.bsd.model;

import java.util.Date;
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

import org.avp.security.model.User;

import com.google.gson.annotations.Expose;

@SuppressWarnings("serial")
@Entity
@Table(name = "bsdusers")
public class BsdUser extends User implements java.io.Serializable {

	/*
	 * TODO - <AP> to break circular loop in json marshaling use transient modifier
	 */
	@ManyToOne
	@JoinColumn(name="storeId")
	private Store store;
	
	@OneToMany(fetch=FetchType.EAGER , mappedBy = "user", cascade={CascadeType.ALL}, orphanRemoval=true)
	private Set<OrderHeader> orders;
	
	@Expose
	@Column(columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
	private Boolean clientAdmin;
	
	@Expose
	@Column(columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
	private Boolean siteAdmin;
	
	@Expose
	private String phone;
	
	@Expose
	private String fax;
	
	@Expose
	private String subDivision;
	
	@Expose
	@Column(columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
	private Boolean active;
	
	private Date createDt;

	public BsdUser() {
	}

	public BsdUser(Store store, String emailAddress,
			String password, Boolean clientAdmin, Boolean siteAdmin,
			String firstName, String lastName, String phone, String fax,
			String subDivision) {
		this.store = store;
		this.clientAdmin = clientAdmin;
		this.siteAdmin = siteAdmin;
		this.phone = phone;
		this.fax = fax;
		this.subDivision = subDivision;
		this.active = false;
		this.createDt = new Date();
	}

	public Boolean getClientAdmin() {
		return this.clientAdmin;
	}

	public void setClientAdmin(Boolean clientAdmin) {
		this.clientAdmin = clientAdmin;
	}

	public Boolean getSiteAdmin() {
		return this.siteAdmin;
	}

	public void setSiteAdmin(Boolean siteAdmin) {
		this.siteAdmin = siteAdmin;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getSubDivision() {
		return this.subDivision;
	}

	public void setSubDivision(String subDivision) {
		this.subDivision = subDivision;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}
