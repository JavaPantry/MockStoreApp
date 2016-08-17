package org.avp.security.service;

import java.util.List;

import org.avp.security.model.Authority;
import org.avp.security.model.User;
import org.avp.security.repository.AuthoritiesRepository;
import org.avp.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class CustomUserServiceImpl implements CustomUserService {

    @Autowired 
	private UserRepository userRepository;
	
	
	@Autowired
	private AuthoritiesRepository authoritiesRepository;

    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User foundByUserId = userRepository.findByUserId(username);
        final List<Authority> authorities = foundByUserId.getAuthorities();
        final List<GrantedAuthority> authoritiesForSpring = SecurityUtil.convertAuthorityEntieiesIntoSpringAuthorities(authorities);
        return new org.springframework.security.core.userdetails.User(username, foundByUserId.getPassword(), authoritiesForSpring); 
	}

	@Transactional()
	public void save(User user){
		userRepository.save(user);
	}
	
	@Transactional()
	public void save(Authority authoritiy){
		authoritiesRepository.save(authoritiy);
	}
}
