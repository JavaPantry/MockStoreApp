package org.avp.security.service;

import org.avp.security.model.Authority;
import org.avp.security.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserService extends UserDetailsService {

	public void save(User user);
	public void save(Authority authoritiy);
	
}
