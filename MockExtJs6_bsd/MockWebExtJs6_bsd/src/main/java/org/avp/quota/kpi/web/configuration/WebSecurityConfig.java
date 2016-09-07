package org.avp.quota.kpi.web.configuration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/*commented out Thursday, April 21, 2016
import org.avp.quota.kpi.web.service.CustomUserDetailsContextMapper;
*/

//don't need if Windows defined groups are used

/**
 * On Aug 8 added angular user
 * app.auth.adminGroupAngular=ROLE_QuotaKPI_ADMIN_ANGULAR
 * 
 * Existing groups:
 * 	app.auth.userGroup=QuotaKPI_USER  - TBR
 * 	app.auth.adminGroup=QuotaKPI_ADMIN
 *  
 *  
 * On Jan 19 add new groups:
 * 	app.auth.quotaGroup=QuotaKPI_QUOTA
 * 	app.auth.budgetGroup=QuotaKPI_BUDGET
 * 	app.auth.companyGroup=QuotaKPI_COMPANY
 * 	app.auth.reportGroup=QuotaKPI_REPORT
 * 
 * Now the groups and corespondent users have been setup:
 * 
 * group				user
 * QuotaKPI_QUOTA		sptestuser603
 * QuotaKPI_BUDGET		sptestuser601
 * QuotaKPI_COMPANY		sptestuser602
 * QuotaKPI_REPORT		sptestuser604
 * 
 * password: password
 * 
 * 
 * Previosly commented SPNEGO/kerberos stuff removed (backup in c:\STS-3.6.4.Workspace\backup\MockStubExtJs6_BeforeGroups_Jan19_2016.zip)
 * 
 */
@Configuration
@EnableWebSecurity
@PropertySource("classpath:securityConfig.properties")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static Logger logger = Logger.getLogger(WebSecurityConfig.class);
	
	/*	@Value("${app.ad-domain}")
	private String adDomain;

	@Value("${app.ad-server}")
	private String adServer;

	@Value("${app.service-principal}")
	private String servicePrincipal;

	@Value("${app.keytab-location}")
	private String keytabLocation;

	@Value("${app.ldap-search-base}")
	private String ldapSearchBase;

	@Value("${app.ldap-search-filter}")
	private String ldapSearchFilter;
	
	@Value("${app.auth.userGroup}")
	private String userGroup;
*/	
	@Value("${app.auth.adminGroup}")
	private String adminGroup;
	
	//app.auth.adminGroupAngular=ROLE_QuotaKPI_ADMIN_ANGULAR
	@Value("${app.auth.adminGroupAngular}")
	private String adminGroupAngular;

	
	@Value("${app.auth.quotaGroup}")
	private String quotaGroup;
	@Value("${app.auth.budgetGroup}")
	private String budgetGroup;
	@Value("${app.auth.companyGroup}")
	private String companyGroup;
	@Value("${app.auth.reportGroup}")
	private String reportGroup;
	
	/*
	commented out Thursday, April 21, 2016
	//don't need if Windows defined groups are used
	@Autowired
	CustomUserDetailsContextMapper customUserDetailsContextMapper;
	*/
	
	/*	
	 	This service autowired by service name and/or implemented interface
	 	@Service("userDetailsService")
		@Transactional
		public class CustomUserService implements UserDetailsService
	 */
	@Autowired 
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.debug("configure(HttpSecurity "+http+")");
		http.authorizeRequests()
				.antMatchers("/","/exthome","/clientStore","/app/**").authenticated() //.permitAll() those patterns should be excluded from permitted and authenticated/authorized
				.antMatchers( "/ajax/quotas/**").hasAnyAuthority(quotaGroup, adminGroup, adminGroupAngular)
				.antMatchers( "/ajax/budgets/**").hasAnyAuthority(budgetGroup, adminGroup,adminGroupAngular)
				.antMatchers( "/ajax/salesReps/**").hasAnyAuthority(companyGroup,adminGroup,adminGroupAngular)
				.antMatchers( "/report/**").hasAnyAuthority(reportGroup,adminGroup,adminGroupAngular)
				
				//"ROLE_BSD_DEALER"
				
				//.antMatchers( "/ajax/updateSalesRep").hasAnyAuthority(adminGroup)// + ", " + adminGroup)
				//jan 19 .antMatchers( "/ajax/**").permitAll()
				//.antMatchers( "/ajax/users/**").hasAnyAuthority(adminGroup)// + ", " + adminGroup)
				.antMatchers( "/companyUpload/**").permitAll()
				.antMatchers( "/resources/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin().loginPage("/login").failureUrl("/login?error").usernameParameter("userName").passwordParameter("password").permitAll()
			.and().logout().logoutSuccessUrl("/login?logout").permitAll()
			.and().csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//AVP switch provider
		//LDAP auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());

		//Custom
		//auth.authenticationProvider(customAuthenticationProvider());
		auth.authenticationProvider(authProvider());
	}

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
	
	/*
	 * TODO - <AP> TBR org/avp/quota/kpi/web/configuration/WebSecurityConfig:customAuthenticationProvider()
	@Bean
	public AuthenticationProvider customAuthenticationProvider() {
		AuthenticationProvider customAuthenticationProvider = new AbstractUserDetailsAuthenticationProvider() {
			@Override
			protected UserDetails retrieveUser(String username,
					UsernamePasswordAuthenticationToken authentication)
					throws AuthenticationException {
				//return customUserService.loadUserByUsername(username);
				return userDetailsService.loadUserByUsername(username);
			}
			
			@Override
			protected void additionalAuthenticationChecks(UserDetails userDetails,
					UsernamePasswordAuthenticationToken authentication)
					throws AuthenticationException {
				// TODO Auto-generated method stub
				
			}
		};
		return customAuthenticationProvider;
	}*/

	/** 
	 * commented out Thursday, April 21, 2016
	 * 
	 * 
	 * For Role based Access control using Spring Security and MVC, Mapping LDAP Groups to Authorities for Authorization
	 * @see http://javarevisited.blogspot.com/2013/07/role-based-access-control-using-spring-security-ldap-authorities-mapping-mvc.html 
	
	@Bean
	public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
		ActiveDirectoryLdapAuthenticationProvider adLdapAuthenticationProvider = new ActiveDirectoryLdapAuthenticationProvider(adDomain, adServer);
		//don't need if Windows defined groups are used
		adLdapAuthenticationProvider.setUserDetailsContextMapper(customUserDetailsContextMapper);
		return adLdapAuthenticationProvider;
	}*/
	
	/*
	 * To resolve ${} in @Value
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
