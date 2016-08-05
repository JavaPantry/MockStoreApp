package org.avp.quota.kpi.model.security;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @Column(length = 60)
    private String password;

    @Column(name="enabled", columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
    private boolean enabled;
    @Column(name="tokenExpired", columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
    private boolean tokenExpired;

    //

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") )
    private Collection<Role> roles;

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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Collection<Role> roles) {
        this.roles = roles;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
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
        if (!email.equals(user.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [firstName=").append(firstName).append("]").append("[lastName=").append(lastName).append("]").append("[username").append(email).append("]");
        return builder.toString();
    }

}
