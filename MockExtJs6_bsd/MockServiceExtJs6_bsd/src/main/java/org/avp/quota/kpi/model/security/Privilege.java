package org.avp.quota.kpi.model.security;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "privilege")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public Privilege() {
        super();
    }

    public Privilege(final String name) {
        super();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Collection<Role> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        final Privilege privilege = (Privilege) obj;
        if (!privilege.equals(privilege.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Privilege [name=").append(name).append("]").append("[id=").append(id).append("]");
        return builder.toString();
    }
}