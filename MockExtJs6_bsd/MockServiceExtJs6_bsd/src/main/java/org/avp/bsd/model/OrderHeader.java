package org.avp.bsd.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderHeader implements java.io.Serializable {

	@Id
	@GeneratedValue
	private Long id;
	private Long storeId;
	private Long userId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String phone;
	private String fax;
	private String subDivision;
	private String shippingFirstName;
	private String shippingLastName;
	private String shippingAddressAlias;
	private String shippingPhone;
	private String shippingpAddress;
	private String shippingCity;
	private String shippingPostalCode;
	private String shippingProvince;
	private String shippingCountry;
	private String shippingCode;
	private String shippingHours;
	private String shippingInstructions;
	private String shippingCostCentre;
	private String billAddress;
	private String billCity;
	private String billPostalCode;
	private String billProvince;
	private String ponumber;
	private String otherInformation;
	private Date createDt;

	public OrderHeader() {
	}
	public OrderHeader(Long id, Long storeId, Long userId, String firstName, String lastName, String emailAddress,	Date createDt) {
		this.id = id;
		this.storeId = storeId;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.createDt = createDt;
	}
	public OrderHeader(Long id, Long storeId, Long userId,
			String firstName, String lastName, String emailAddress,
			String phone, String fax, String subDivision,
			String shippingFirstName, String shippingLastName,
			String shippingAddressAlias, String shippingPhone,
			String shippingpAddress, String shippingCity,
			String shippingPostalCode, String shippingProvince,
			String shippingCountry, String shippingCode, String shippingHours,
			String shippingInstructions, String shippingCostCentre,
			String billAddress, String billCity, String billPostalCode,
			String billProvince, String ponumber, String otherInformation,
			Date createDt) {
		this.id = id;
		this.storeId = storeId;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phone = phone;
		this.fax = fax;
		this.subDivision = subDivision;
		this.shippingFirstName = shippingFirstName;
		this.shippingLastName = shippingLastName;
		this.shippingAddressAlias = shippingAddressAlias;
		this.shippingPhone = shippingPhone;
		this.shippingpAddress = shippingpAddress;
		this.shippingCity = shippingCity;
		this.shippingPostalCode = shippingPostalCode;
		this.shippingProvince = shippingProvince;
		this.shippingCountry = shippingCountry;
		this.shippingCode = shippingCode;
		this.shippingHours = shippingHours;
		this.shippingInstructions = shippingInstructions;
		this.shippingCostCentre = shippingCostCentre;
		this.billAddress = billAddress;
		this.billCity = billCity;
		this.billPostalCode = billPostalCode;
		this.billProvince = billProvince;
		this.ponumber = ponumber;
		this.otherInformation = otherInformation;
		this.createDt = createDt;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

	public String getShippingpAddress() {
		return this.shippingpAddress;
	}

	public void setShippingpAddress(String shippingpAddress) {
		this.shippingpAddress = shippingpAddress;
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

	public String getBillAddress() {
		return this.billAddress;
	}

	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}

	public String getBillCity() {
		return this.billCity;
	}

	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}

	public String getBillPostalCode() {
		return this.billPostalCode;
	}

	public void setBillPostalCode(String billPostalCode) {
		this.billPostalCode = billPostalCode;
	}

	public String getBillProvince() {
		return this.billProvince;
	}

	public void setBillProvince(String billProvince) {
		this.billProvince = billProvince;
	}

	public String getPonumber() {
		return this.ponumber;
	}

	public void setPonumber(String ponumber) {
		this.ponumber = ponumber;
	}

	public String getOtherInformation() {
		return this.otherInformation;
	}

	public void setOtherInformation(String otherInformation) {
		this.otherInformation = otherInformation;
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
		if (!(other instanceof OrderHeader))
			return false;
		OrderHeader castOther = (OrderHeader) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getStoreId() == castOther.getStoreId()) || (this
						.getStoreId() != null && castOther.getStoreId() != null && this
						.getStoreId().equals(castOther.getStoreId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null && castOther.getUserId() != null && this
						.getUserId().equals(castOther.getUserId())))
				&& ((this.getFirstName() == castOther.getFirstName()) || (this
						.getFirstName() != null
						&& castOther.getFirstName() != null && this
						.getFirstName().equals(castOther.getFirstName())))
				&& ((this.getLastName() == castOther.getLastName()) || (this
						.getLastName() != null
						&& castOther.getLastName() != null && this
						.getLastName().equals(castOther.getLastName())))
				&& ((this.getEmailAddress() == castOther.getEmailAddress()) || (this
						.getEmailAddress() != null
						&& castOther.getEmailAddress() != null && this
						.getEmailAddress().equals(castOther.getEmailAddress())))
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
				&& ((this.getShippingpAddress() == castOther
						.getShippingpAddress()) || (this.getShippingpAddress() != null
						&& castOther.getShippingpAddress() != null && this
						.getShippingpAddress().equals(
								castOther.getShippingpAddress())))
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
				&& ((this.getBillAddress() == castOther.getBillAddress()) || (this
						.getBillAddress() != null
						&& castOther.getBillAddress() != null && this
						.getBillAddress().equals(castOther.getBillAddress())))
				&& ((this.getBillCity() == castOther.getBillCity()) || (this
						.getBillCity() != null
						&& castOther.getBillCity() != null && this
						.getBillCity().equals(castOther.getBillCity())))
				&& ((this.getBillPostalCode() == castOther.getBillPostalCode()) || (this
						.getBillPostalCode() != null
						&& castOther.getBillPostalCode() != null && this
						.getBillPostalCode().equals(
								castOther.getBillPostalCode())))
				&& ((this.getBillProvince() == castOther.getBillProvince()) || (this
						.getBillProvince() != null
						&& castOther.getBillProvince() != null && this
						.getBillProvince().equals(castOther.getBillProvince())))
				&& ((this.getPonumber() == castOther.getPonumber()) || (this
						.getPonumber() != null
						&& castOther.getPonumber() != null && this
						.getPonumber().equals(castOther.getPonumber())))
				&& ((this.getOtherInformation() == castOther
						.getOtherInformation()) || (this.getOtherInformation() != null
						&& castOther.getOtherInformation() != null && this
						.getOtherInformation().equals(
								castOther.getOtherInformation())))
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
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getFirstName() == null ? 0 : this.getFirstName().hashCode());
		result = 37 * result
				+ (getLastName() == null ? 0 : this.getLastName().hashCode());
		result = 37
				* result
				+ (getEmailAddress() == null ? 0 : this.getEmailAddress()
						.hashCode());
		result = 37 * result
				+ (getPhone() == null ? 0 : this.getPhone().hashCode());
		result = 37 * result
				+ (getFax() == null ? 0 : this.getFax().hashCode());
		result = 37
				* result
				+ (getSubDivision() == null ? 0 : this.getSubDivision()
						.hashCode());
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
				+ (getShippingpAddress() == null ? 0 : this
						.getShippingpAddress().hashCode());
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
		result = 37
				* result
				+ (getBillAddress() == null ? 0 : this.getBillAddress()
						.hashCode());
		result = 37 * result
				+ (getBillCity() == null ? 0 : this.getBillCity().hashCode());
		result = 37
				* result
				+ (getBillPostalCode() == null ? 0 : this.getBillPostalCode()
						.hashCode());
		result = 37
				* result
				+ (getBillProvince() == null ? 0 : this.getBillProvince()
						.hashCode());
		result = 37 * result
				+ (getPonumber() == null ? 0 : this.getPonumber().hashCode());
		result = 37
				* result
				+ (getOtherInformation() == null ? 0 : this
						.getOtherInformation().hashCode());
		result = 37 * result
				+ (getCreateDt() == null ? 0 : this.getCreateDt().hashCode());
		return result;
	}

}
