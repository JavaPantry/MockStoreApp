package org.avp.quota.kpi.web.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.avp.quota.kpi.service.interfaces.QuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.avp.quota.kpi.model.dao.AuthoritiesDao;
//import org.avp.quota.kpi.model.dao.UserDao;


/**
 * 
 * Rely on QuotaKPI_ADMIN and QuotaKPI_USER groups defined on Active directory server
 * 
 * Spring Security 3.1: Active Directory Authentication and local DB Authorization
 *		http://stackoverflow.com/questions/18844699/spring-security-3-1-active-directory-authentication-and-local-db-authorization
 * See also: Handling roles when authenticated to active directory with spring security 3.1
 *		http://stackoverflow.com/questions/8835818/handling-roles-when-authenticated-to-active-directory-with-spring-security-3-1
 * @author q05459
 */
@Component
public class CustomUserDetailsContextMapper implements UserDetailsContextMapper {
	private static Logger logger = Logger.getLogger(CustomUserDetailsContextMapper.class);
	
	/*commented out Thursday, April 21, 2016  ???circular dependencies???
	@Autowired
	protected QuotaService quotaService;*/
	@Autowired
	protected SystemUserDto userDto;
	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
	      /*
	       * IF WE RELY on Windows defined group, we don't need to consult authority table 
			List<SimpleGrantedAuthority> allAuthorities = new ArrayList<SimpleGrantedAuthority>();
			for (GrantedAuthority auth : authorities) {
				if (auth != null && !auth.getAuthority().isEmpty()) {
				   allAuthorities.add((SimpleGrantedAuthority) auth);
				}
			}
			// add additional roles from the database table
			UserDao user = quotaService.getUserById(username.toLowerCase());//
			if(user != null){
				List<AuthoritiesDao> userAuthorities = user.getAuthorities();
				for (AuthoritiesDao authority : userAuthorities) {
					SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getRole());
					allAuthorities.add(grantedAuthority);
				}
			}
			return new User(username, "", true, true, true, true, allAuthorities);
	       */
		logger.debug("mapUser context: "+ ctx.getStringAttribute("cn"));
		userDto.setFullName(ctx.getStringAttribute("cn"));
		logger.debug("User '"+username+"' has authorities: "+ authorities);
		return new User(username, "", true, true, true, true, authorities);
	}

	@Override
	public void mapUserToContext(UserDetails userDetails, DirContextAdapter ctx) {
		logger.debug("mapUser "+ userDetails);
	}

}
