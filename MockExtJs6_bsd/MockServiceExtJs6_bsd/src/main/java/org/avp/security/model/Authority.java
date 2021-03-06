package org.avp.security.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.avp.quota.kpi.model.dao.QuotaUser;

/**
 * app.auth.userGroup=QuotaKPI_USER
 * app.auth.quotaGroup=ROLE_QuotaKPI_QUOTA
 * app.auth.budgetGroup=ROLE_QuotaKPI_BUDGET
 * app.auth.companyGroup=ROLE_QuotaKPI_COMPANY
 * app.auth.reportGroup=ROLE_QuotaKPI_REPORT
 * app.auth.adminGroup=ROLE_QuotaKPI_ADMIN
 * app.auth.adminGroupAngular=ROLE_QuotaKPI_ADMIN_ANGULAR
 * 
 * @author Q05459
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "authorities")
public final class Authority  implements Serializable{

	public Authority() {
		super();
	}
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;


	@Column(name="role")//, nullable=false //@ColumnTransformer(read="RTRIM(LTRIM(role))")
	private String role;

	/*
	 * bi-directional many-to-one association to User
	 * http://www.mastertheboss.com/jboss-frameworks/hibernate-jpa/or-mapping/one-to-many-hibernatejpa-example
	 */
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
    
	@Version
	@Column(name="version")
	private Long version;

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public Long getVersion() {return version;}
	public void setVersion(Long version) {this.version = version;}
	public String getRole() {return role;}
	public void setRole(String role) {this.role = role;}
	public User getUser() {return user;}
	public void setUser(User user) {this.user = user;}

	@Override
	public String toString() {
		return role; //String.format("Authorities [id=%s, version=%s, user=%s, role=%s]", id, version, user, role);
	}

}
