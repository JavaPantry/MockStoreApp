package org.avp.security.dto;

import java.io.Serializable;

public final class AuthorityDto  implements Serializable{

	public AuthorityDto() {
		super();
	}
	
	private Long id;
	private String role;

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getRole() {return role;}
	public void setRole(String role) {this.role = role;}

	@Override
	public String toString() {
		return role; //String.format("Authorities [id=%s, version=%s, user=%s, role=%s]", id, version, user, role);
	}
}
