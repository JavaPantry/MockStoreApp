package org.avp.bsd.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.avp.security.model.User;

@SuppressWarnings("serial")
@Entity
@Table(name = "bsdusers")
public class BsdUser extends User implements java.io.Serializable {

	@Column()
	private Integer storeId;
	@Column(columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
	private Boolean clientAdmin;
	@Column(columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
	private Boolean siteAdmin;
	private String phone;
	private String fax;
	private String subDivision;
	@Column(columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
	private Boolean active;
	private Date createDt;

	public BsdUser() {
	}

	public BsdUser(Integer storeId, String emailAddress,
			String password, Boolean clientAdmin, Boolean siteAdmin,
			String firstName, String lastName, String phone, String fax,
			String subDivision) {
		this.storeId = storeId;
		this.clientAdmin = clientAdmin;
		this.siteAdmin = siteAdmin;
		this.phone = phone;
		this.fax = fax;
		this.subDivision = subDivision;
		this.active = false;
		this.createDt = new Date();
	}

	public Integer getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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

}
