package org.avp.quota.kpi.web.service;

import java.io.Serializable;

import org.springframework.stereotype.Component;
/**
 * This is web-app replica of UserDao. Represents currently logged-in user. Filled by CustomUserDetailsContextMapper
 * TODO - <AP> add List of org.springframework.security.core.authority.SimpleGrantedAuthority
 * TODO - <AP> This class TBR
 * @author Q05459
 *
 */
@SuppressWarnings("serial")
@Component
public class SystemUserDto implements Serializable {

	private String firstName;
	private String lastName;
	private String email;
	private String employeeId;
	
	private String searchedBy;
	private String fullName;
	
	public SystemUserDto() {}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("SystemUserDto [firstName=%s, lastName=%s, email=%s, employeeId=%s, searchedBy=%s]",
						firstName, lastName, email, employeeId, searchedBy);
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the commonUserName
	 */
	public String getCommonUserName() {
		if (null != firstName && null != lastName) {
			return String.format("%s %s", firstName.trim(), lastName.trim());
		}
		
		return null;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the searchedBy
	 */
	public String getSearchedBy() {
		return searchedBy;
	}

	/**
	 * @param searchedBy the searchedBy to set
	 */
	public void setSearchedBy(String searchedBy) {
		this.searchedBy = searchedBy;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFullName() {
		return this.fullName;
	}
	
}
