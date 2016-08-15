package org.avp.quota.kpi.web.service;

import java.util.List;

import org.avp.quota.kpi.model.security.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.google.common.base.Functions;
import com.google.common.collect.Iterables;


public final class SecurityUtil {

    private SecurityUtil() {
        throw new AssertionError();
    }

    // API
    public static final List<GrantedAuthority> convertAuthorityEntieiesIntoSpringAuthorities(final List<Authority> authorities) {
        final Iterable<String> authorityNames = Iterables.transform(authorities, Functions.toStringFunction());
        final String[] arrayOfAuthorityNames = Iterables.toArray(authorityNames, String.class);
        final List<GrantedAuthority> authoritiesForSpring = AuthorityUtils.createAuthorityList(arrayOfAuthorityNames);
        return authoritiesForSpring;
    }

}
