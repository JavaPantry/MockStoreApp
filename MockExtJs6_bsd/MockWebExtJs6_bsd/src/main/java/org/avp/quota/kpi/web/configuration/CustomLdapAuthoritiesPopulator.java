package org.avp.quota.kpi.web.configuration;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

/**
 * not getting calls in Active directory Provider
 * See working solution in CustomUserDetailsContextMapper
 * @author q05459
 *
 */
public class CustomLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator{
    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(
                    DirContextOperations userData, String username) {
            Collection<GrantedAuthority> gas = new HashSet<GrantedAuthority>();
            if(username.equals("q05459")) {
                    gas.add(new SimpleGrantedAuthority("QKPI_ADMIN"));
            }
            gas.add(new SimpleGrantedAuthority("USER"));
            return gas;
    }
}
