package org.avp.bsd.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shippingaddresses")
public class Address implements java.io.Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	
	// non-shipping properties this is contact person/user
	private Integer userId;
	private String shippingFirstName;
	private String shippingLastName;

	// non-shipping properties this is address
	private String shippingPhone;
	private String shippingAddressAlias; //Can it be used as an id?
	private String shippingAddress;
	private String shippingCity;
	private String shippingPostalCode;
	private String shippingProvince;
	private String shippingCountry;
	private String shippingCode;
	
	// shipping properties
	private String shippingHours;
	private String shippingInstructions;
	private String shippingCostCentre;
	@Column(columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
	private Boolean active;
	private Date createDt;

	public Address() {
	}

	public Address(Integer id, Integer userId,
			String shippingFirstName, String shippingLastName,
			String shippingAddressAlias, String shippingPhone,
			String shippingAddress, String shippingCity,
			String shippingPostalCode, String shippingProvince,
			String shippingCountry, String shippingCode, String shippingHours,
			String shippingInstructions, String shippingCostCentre,
			Boolean active, Date createDt) {
		this.id = id;
		this.userId = userId;
		this.shippingFirstName = shippingFirstName;
		this.shippingLastName = shippingLastName;
		this.shippingAddressAlias = shippingAddressAlias;
		this.shippingPhone = shippingPhone;
		this.shippingAddress = shippingAddress;
		this.shippingCity = shippingCity;
		this.shippingPostalCode = shippingPostalCode;
		this.shippingProvince = shippingProvince;
		this.shippingCountry = shippingCountry;
		this.shippingCode = shippingCode;
		this.shippingHours = shippingHours;
		this.shippingInstructions = shippingInstructions;
		this.shippingCostCentre = shippingCostCentre;
		this.active = active;
		this.createDt = createDt;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getShippingFirstName() {
		return this.shippingFirstName;
	}

	public void setShippingFirstName(String shippingFirstName) {
		this.shippingFirstName = shippingFirstName;
	}

	public String getShippingLastName() {
		return this.shippingLastName;
	}

	public void setShippingLastName(String shippingLastName) {
		this.shippingLastName = shippingLastName;
	}

	public String getShippingAddressAlias() {
		return this.shippingAddressAlias;
	}

	public void setShippingAddressAlias(String shippingAddressAlias) {
		this.shippingAddressAlias = shippingAddressAlias;
	}

	public String getShippingPhone() {
		return this.shippingPhone;
	}

	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}

	public String getShippingAddress() {
		return this.shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingCity() {
		return this.shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingPostalCode() {
		return this.shippingPostalCode;
	}

	public void setShippingPostalCode(String shippingPostalCode) {
		this.shippingPostalCode = shippingPostalCode;
	}

	public String getShippingProvince() {
		return this.shippingProvince;
	}

	public void setShippingProvince(String shippingProvince) {
		this.shippingProvince = shippingProvince;
	}

	public String getShippingCountry() {
		return this.shippingCountry;
	}

	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	public String getShippingCode() {
		return this.shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	public String getShippingHours() {
		return this.shippingHours;
	}

	public void setShippingHours(String shippingHours) {
		this.shippingHours = shippingHours;
	}

	public String getShippingInstructions() {
		return this.shippingInstructions;
	}

	public void setShippingInstructions(String shippingInstructions) {
		this.shippingInstructions = shippingInstructions;
	}

	public String getShippingCostCentre() {
		return this.shippingCostCentre;
	}

	public void setShippingCostCentre(String shippingCostCentre) {
		this.shippingCostCentre = shippingCostCentre;
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
		if (!(other instanceof Address))
			return false;
		Address castOther = (Address) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null && castOther.getUserId() != null && this
						.getUserId().equals(castOther.getUserId())))
				&& ((this.getShippingFirstName() == castOther
						.getShippingFirstName()) || (this
						.getShippingFirstName() != null
						&& castOther.getShippingFirstName() != null && this
						.getShippingFirstName().equals(
								castOther.getShippingFirstName())))
				&& ((this.getShippingLastName() == castOther
						.getShippingLastName()) || (this.getShippingLastName() != null
						&& castOther.getShippingLastName() != null && this
						.getShippingLastName().equals(
								castOther.getShippingLastName())))
				&& ((this.getShippingAddressAlias() == castOther
						.getShippingAddressAlias()) || (this
						.getShippingAddressAlias() != null
						&& castOther.getShippingAddressAlias() != null && this
						.getShippingAddressAlias().equals(
								castOther.getShippingAddressAlias())))
				&& ((this.getShippingPhone() == castOther.getShippingPhone()) || (this
						.getShippingPhone() != null
						&& castOther.getShippingPhone() != null && this
						.getShippingPhone()
						.equals(castOther.getShippingPhone())))
				&& ((this.getShippingAddress() == castOther
						.getShippingAddress()) || (this.getShippingAddress() != null
						&& castOther.getShippingAddress() != null && this
						.getShippingAddress().equals(
								castOther.getShippingAddress())))
				&& ((this.getShippingCity() == castOther.getShippingCity()) || (this
						.getShippingCity() != null
						&& castOther.getShippingCity() != null && this
						.getShippingCity().equals(castOther.getShippingCity())))
				&& ((this.getShippingPostalCode() == castOther
						.getShippingPostalCode()) || (this
						.getShippingPostalCode() != null
						&& castOther.getShippingPostalCode() != null && this
						.getShippingPostalCode().equals(
								castOther.getShippingPostalCode())))
				&& ((this.getShippingProvince() == castOther
						.getShippingProvince()) || (this.getShippingProvince() != null
						&& castOther.getShippingProvince() != null && this
						.getShippingProvince().equals(
								castOther.getShippingProvince())))
				&& ((this.getShippingCountry() == castOther
						.getShippingCountry()) || (this.getShippingCountry() != null
						&& castOther.getShippingCountry() != null && this
						.getShippingCountry().equals(
								castOther.getShippingCountry())))
				&& ((this.getShippingCode() == castOther.getShippingCode()) || (this
						.getShippingCode() != null
						&& castOther.getShippingCode() != null && this
						.getShippingCode().equals(castOther.getShippingCode())))
				&& ((this.getShippingHours() == castOther.getShippingHours()) || (this
						.getShippingHours() != null
						&& castOther.getShippingHours() != null && this
						.getShippingHours()
						.equals(castOther.getShippingHours())))
				&& ((this.getShippingInstructions() == castOther
						.getShippingInstructions()) || (this
						.getShippingInstructions() != null
						&& castOther.getShippingInstructions() != null && this
						.getShippingInstructions().equals(
								castOther.getShippingInstructions())))
				&& ((this.getShippingCostCentre() == castOther
						.getShippingCostCentre()) || (this
						.getShippingCostCentre() != null
						&& castOther.getShippingCostCentre() != null && this
						.getShippingCostCentre().equals(
								castOther.getShippingCostCentre())))
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
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37
				* result
				+ (getShippingFirstName() == null ? 0 : this
						.getShippingFirstName().hashCode());
		result = 37
				* result
				+ (getShippingLastName() == null ? 0 : this
						.getShippingLastName().hashCode());
		result = 37
				* result
				+ (getShippingAddressAlias() == null ? 0 : this
						.getShippingAddressAlias().hashCode());
		result = 37
				* result
				+ (getShippingPhone() == null ? 0 : this.getShippingPhone()
						.hashCode());
		result = 37
				* result
				+ (getShippingAddress() == null ? 0 : this.getShippingAddress()
						.hashCode());
		result = 37
				* result
				+ (getShippingCity() == null ? 0 : this.getShippingCity()
						.hashCode());
		result = 37
				* result
				+ (getShippingPostalCode() == null ? 0 : this
						.getShippingPostalCode().hashCode());
		result = 37
				* result
				+ (getShippingProvince() == null ? 0 : this
						.getShippingProvince().hashCode());
		result = 37
				* result
				+ (getShippingCountry() == null ? 0 : this.getShippingCountry()
						.hashCode());
		result = 37
				* result
				+ (getShippingCode() == null ? 0 : this.getShippingCode()
						.hashCode());
		result = 37
				* result
				+ (getShippingHours() == null ? 0 : this.getShippingHours()
						.hashCode());
		result = 37
				* result
				+ (getShippingInstructions() == null ? 0 : this
						.getShippingInstructions().hashCode());
		result = 37
				* result
				+ (getShippingCostCentre() == null ? 0 : this
						.getShippingCostCentre().hashCode());
		result = 37 * result
				+ (getActive() == null ? 0 : this.getActive().hashCode());
		result = 37 * result
				+ (getCreateDt() == null ? 0 : this.getCreateDt().hashCode());
		return result;
	}

}
