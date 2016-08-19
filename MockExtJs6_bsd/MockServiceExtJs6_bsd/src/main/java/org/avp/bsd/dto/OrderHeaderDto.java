package org.avp.bsd.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

public class OrderHeaderDto implements java.io.Serializable {

	private Long id;
	
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

	public OrderHeaderDto() {
	}
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
