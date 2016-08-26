package org.avp.bsd.dto;

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

import org.avp.security.dto.UserDto;
import org.avp.security.model.User;

public class BsdUserDto extends UserDto implements java.io.Serializable {

	@Override
	public String toString() {
		return String.format(
				"BsdUserDto [ userId=%s, firstName=%s, clientAdmin=%s, siteAdmin=%s, phone=%s, fax=%s, subDivision=%s, active=%s, createDt=%s]",
				 getUserId(), getFirstName(), clientAdmin, siteAdmin, phone, fax, subDivision, active, createDt);
	}

	private StoreDto store;
	
	private Set<OrderHeaderDto> orders;
	
	private Boolean clientAdmin;
	private Boolean siteAdmin;
	private String phone;
	private String fax;
	private String subDivision;
	private Boolean active;
	private Date createDt;

	public BsdUserDto() {
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

	public StoreDto getStore() {
		return store;
	}

	public void setStore(StoreDto store) {
		this.store = store;
	}

}
