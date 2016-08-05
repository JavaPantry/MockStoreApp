package org.avp.company.model;

@SuppressWarnings("serial")
public class Contact implements java.io.Serializable {

	private String name;
	private String email;
	private String phone;
	private String phoneExt;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhoneExt() {
		return phoneExt;
	}
	
	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}
}
