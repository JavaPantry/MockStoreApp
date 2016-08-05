package org.avp.company.model;

@SuppressWarnings("serial")
public class Address implements java.io.Serializable {

	private String street;
	private String suite;
	private String city;
	private String provinceCode;
	private String postalCode;
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getSuite() {
		return suite;
	}
	
	public void setSuite(String suite) {
		this.suite = suite;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getProvinceCode() {
		return provinceCode;
	}
	
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
