package org.avp.bsd.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class BsdUser implements java.io.Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	private Integer storeId;
	private String emailAddress;
	private String password;
	@Column(columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
	private Boolean clientAdmin;
	@Column(columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
	private Boolean siteAdmin;
	private String firstName;
	private String lastName;
	private String phone;
	private String fax;
	private String subDivision;
	@Column(columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
	private Boolean active;
	private Date createDt;

	public BsdUser() {
	}

	public BsdUser(Integer id, Integer storeId, String emailAddress,
			String password, Boolean clientAdmin, Boolean siteAdmin,
			String firstName, String lastName, String phone, String fax,
			String subDivision, Boolean active, Date createDt) {
		this.id = id;
		this.storeId = storeId;
		this.emailAddress = emailAddress;
		this.password = password;
		this.clientAdmin = clientAdmin;
		this.siteAdmin = siteAdmin;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.fax = fax;
		this.subDivision = subDivision;
		this.active = active;
		this.createDt = createDt;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BsdUser))
			return false;
		BsdUser castOther = (BsdUser) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getStoreId() == castOther.getStoreId()) || (this
						.getStoreId() != null && castOther.getStoreId() != null && this
						.getStoreId().equals(castOther.getStoreId())))
				&& ((this.getEmailAddress() == castOther.getEmailAddress()) || (this
						.getEmailAddress() != null
						&& castOther.getEmailAddress() != null && this
						.getEmailAddress().equals(castOther.getEmailAddress())))
				&& ((this.getPassword() == castOther.getPassword()) || (this
						.getPassword() != null
						&& castOther.getPassword() != null && this
						.getPassword().equals(castOther.getPassword())))
				&& ((this.getClientAdmin() == castOther.getClientAdmin()) || (this
						.getClientAdmin() != null
						&& castOther.getClientAdmin() != null && this
						.getClientAdmin().equals(castOther.getClientAdmin())))
				&& ((this.getSiteAdmin() == castOther.getSiteAdmin()) || (this
						.getSiteAdmin() != null
						&& castOther.getSiteAdmin() != null && this
						.getSiteAdmin().equals(castOther.getSiteAdmin())))
				&& ((this.getFirstName() == castOther.getFirstName()) || (this
						.getFirstName() != null
						&& castOther.getFirstName() != null && this
						.getFirstName().equals(castOther.getFirstName())))
				&& ((this.getLastName() == castOther.getLastName()) || (this
						.getLastName() != null
						&& castOther.getLastName() != null && this
						.getLastName().equals(castOther.getLastName())))
				&& ((this.getPhone() == castOther.getPhone()) || (this
						.getPhone() != null && castOther.getPhone() != null && this
						.getPhone().equals(castOther.getPhone())))
				&& ((this.getFax() == castOther.getFax()) || (this.getFax() != null
						&& castOther.getFax() != null && this.getFax().equals(
						castOther.getFax())))
				&& ((this.getSubDivision() == castOther.getSubDivision()) || (this
						.getSubDivision() != null
						&& castOther.getSubDivision() != null && this
						.getSubDivision().equals(castOther.getSubDivision())))
				&& ((this.getActive() == castOther.getActive()) || (this
						.getActive() != null && castOther.getActive() != null && this
						.getActive().equals(castOther.getActive())))
				&& ((this.getCreateDt() == castOther.getCreateDt()) || (this
						.getCreateDt() != null
						&& castOther.getCreateDt() != null && this
						.getCreateDt().equals(castOther.getCreateDt())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getStoreId() == null ? 0 : this.getStoreId().hashCode());
		result = 37
				* result
				+ (getEmailAddress() == null ? 0 : this.getEmailAddress()
						.hashCode());
		result = 37 * result
				+ (getPassword() == null ? 0 : this.getPassword().hashCode());
		result = 37
				* result
				+ (getClientAdmin() == null ? 0 : this.getClientAdmin()
						.hashCode());
		result = 37
				* result
				+ (getSiteAdmin() == null ? 0 : this.getSiteAdmin()
						.hashCode());
		result = 37 * result
				+ (getFirstName() == null ? 0 : this.getFirstName().hashCode());
		result = 37 * result
				+ (getLastName() == null ? 0 : this.getLastName().hashCode());
		result = 37 * result
				+ (getPhone() == null ? 0 : this.getPhone().hashCode());
		result = 37 * result
				+ (getFax() == null ? 0 : this.getFax().hashCode());
		result = 37
				* result
				+ (getSubDivision() == null ? 0 : this.getSubDivision()
						.hashCode());
		result = 37 * result
				+ (getActive() == null ? 0 : this.getActive().hashCode());
		result = 37 * result
				+ (getCreateDt() == null ? 0 : this.getCreateDt().hashCode());
		return result;
	}

}
