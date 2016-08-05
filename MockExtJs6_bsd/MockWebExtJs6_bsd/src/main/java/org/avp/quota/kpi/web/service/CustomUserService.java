package org.avp.quota.kpi.web.service;

import java.util.List;
import java.util.Set;

import org.avp.quota.kpi.model.dao.AuthoritiesDao;
import org.avp.quota.kpi.model.dao.UserDao;
import org.avp.quota.kpi.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority; 
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Component
/*
 * 
 *  8.1. The Custom UserDetailsService
 *  http://www.baeldung.com/registration-with-spring-mvc-and-spring-security
 *  Use
 *  @Autowired 
 *  private UserDetailsService userDetailsService;
 *  
 */
@Service("userDetailsService")
@Transactional
public class CustomUserService implements UserDetailsService {

    @Autowired 
	private IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserDao foundByUserId = userRepository.findByUserId(username);
        final List<AuthoritiesDao> authorities = foundByUserId.getAuthorities();
        final List<GrantedAuthority> authoritiesForSpring = SecurityUtil.convertAuthorityEntieiesIntoSpringAuthorities(authorities);
        return new org.springframework.security.core.userdetails.User(username, foundByUserId.getPassword(), authoritiesForSpring); 
	}

}
