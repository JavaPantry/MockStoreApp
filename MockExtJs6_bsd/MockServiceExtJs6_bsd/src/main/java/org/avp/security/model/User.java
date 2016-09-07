package org.avp.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
/*
 * http://www.baeldung.com/role-and-privilege-for-spring-security-registration 
 * github for article removed
 *   
 * By searching 'PrivilegeRepository' Copy found: https://github.com/skiman77133/spring
 * 
 * lot of results in search github for 'spring-security-login-and-registration'
 * 
 * Original?:
 * https://github.com/eugenp/spring-security-registration
 * saved in c:\STS-3.6.4.Workspace_MPS_HEAD_BIRT\MockExtJs6\Documents\spring-security-registration-master.zip
 * 
 * Full copy TFSAPI/spring-security-login-and-registration/
 * https://github.com/sureshk2014/TFSAPI/tree/master/spring-security-login-and-registration
 */
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "USER_TYPE")
public class User {

	@JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@JsonProperty
	@Column(name="userId",length = 30)//@ColumnTransformer(read="RTRIM(LTRIM(userId))")
	private String userId;

	@JsonProperty
	@Column(length = 30)
    private String firstName;

	@JsonProperty
	@Column(length = 30)
    private String lastName;

	@JsonProperty
	@Column(length = 60)
    private String email;

    
	@Column(length = 60)
    private String password;

	@JsonProperty
	@Column(columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
    private boolean tokenExpired;

	/*
	 * Caused by: org.hibernate.exception.SQLGrammarException: could not extract ResultSet
	 * Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'userdao0_.enabled' in 'field list'
	 * 
	 */
	@JsonProperty
	@Column(columnDefinition = "BIT", length = 1)//, columnDefinition="BIT boolean default false", nullable=false, 
	private boolean enabled = true;
	
	/*
	 * http://www.mastertheboss.com/jboss-frameworks/hibernate-jpa/or-mapping/one-to-many-hibernatejpa-example
	 */
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<Authority> authorities = new ArrayList<Authority>();
	
	@Version
	@Column(name="version")
	private Long version;

    public User() {
        super();
        this.enabled = false;
        this.tokenExpired = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(final boolean expired) {
        this.tokenExpired = expired;
    }

	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    /*
     * let hibernate do that
     * at.Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User user = (User) obj;
        if (!id.equals(user.id)) {
            return false;
        }
        return true;
    }*/

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [firstName=").append(firstName).append("]").append("[lastName=").append(lastName).append("]").append("[username").append(email).append("]");
        return builder.toString();
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
