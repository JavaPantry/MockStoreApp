package org.avp.quota.kpi.model.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.avp.quota.kpi.model.security.User;

@SuppressWarnings("serial")
@Entity
@Table(name = "quotausers")
public final class QuotaUser  extends User implements Serializable{

	@Column(columnDefinition = "BIT", length = 1)
	private boolean userAdmin = true;
	
	
	public QuotaUser() {
		super();
	}
	
	public boolean isUserAdmin() {
		return userAdmin;
	}
	public void setUserAdmin(boolean userAdmin) {
		this.userAdmin = userAdmin;
	}

}
