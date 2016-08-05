package org.avp.quota.kpi.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@Table(name = "quotausers")
public final class UserDao  implements Serializable{

	@Transient
	private boolean userAdmin = true;
	
	
	public UserDao() {
		super();
	}
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@Column(name="userId")//@ColumnTransformer(read="RTRIM(LTRIM(userId))")
	private String userId;

	//Column(name="password")//@ColumnTransformer(read="RTRIM(LTRIM(password))")
	@Column(length = 60)
	private String password;

	/*
	 * Caused by: org.hibernate.exception.SQLGrammarException: could not extract ResultSet
	 * Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'userdao0_.enabled' in 'field list'
	 * 
	 */
	@Column(name="enabled", columnDefinition = "BIT", length = 1)//, columnDefinition="BIT boolean default false", nullable=false, 
	private Boolean enabled = true;
	
	/*
	 * http://www.mastertheboss.com/jboss-frameworks/hibernate-jpa/or-mapping/one-to-many-hibernatejpa-example
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<AuthoritiesDao> authorities = new ArrayList<AuthoritiesDao>();
	
	@Version
	@Column(name="version")
	private Long version;

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public Long getVersion() {return version;}
	public void setVersion(Long version) {this.version = version;}
	public String getUserId() {return userId;}
	public void setUserId(String userId) {this.userId = userId;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDao other = (UserDao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("UserDao [id=%s, version=%s, userId=%s, password=%s]", id,
								version, userId, password);
	}
	public boolean isUserAdmin() {
		return userAdmin;
	}
	public void setUserAdmin(boolean userAdmin) {
		this.userAdmin = userAdmin;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public List<AuthoritiesDao> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<AuthoritiesDao> authorities) {
		this.authorities = authorities;
	}

}
