package org.avp.quota.kpi.web.configuration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
 * User thymeleaf with login id 'Thyme Leaf'
 * app.auth.thymeleafGroup=ROLE_BSD_DEALER_THYMELEAF
 *
 * password: password
 *
 at Configuration
 at EnableWebSecurity
 at PropertySource("classpath:securityConfig.properties")


 */

/*
* org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'springSecurityFilterChain' is defined
*
* read: http://www.baeldung.com/no-bean-named-springsecurityfilterchain-is-defined
*
*/

@Configuration
@EnableWebSecurity
@PropertySource("classpath:securityConfig.properties")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static Logger logger = Logger.getLogger(WebSecurityConfig.class);
	
	@Value("${app.auth.adminGroup}")
	private String adminGroup;
	
	//app.auth.adminGroupAngular=ROLE_QuotaKPI_ADMIN_ANGULAR
	@Value("${app.auth.adminGroupAngular}")
	private String adminGroupAngular;

	//app.auth.thymeleafGroup=ROLE_BSD_DEALER_THYMELEAF
	@Value("${app.auth.thymeleafGroup}")
	private String thymeleafGroup;

	@Value("${app.auth.quotaGroup}")
	private String quotaGroup;
	@Value("${app.auth.budgetGroup}")
	private String budgetGroup;
	@Value("${app.auth.companyGroup}")
	private String companyGroup;

	@Value("${app.auth.reportGroup}")
	private String reportGroup;

	@Value("${app.auth.repairGroup}")
	private String repairGroup;


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

	/*
	*
	.antMatchers( "/images/**").permitAll()
	.antMatchers( "/css/**").permitAll()
	.antMatchers( "/js/**").permitAll()
	.antMatchers( "/resources/**").permitAll()
	.antMatchers( "/app/**").permitAll()

*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.debug("configure(HttpSecurity "+http+")");
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/exthome","/clientStore","/app/**").authenticated() //.permitAll() those patterns should be excluded from permitted and authenticated/authorized
				.antMatchers( "/ajax/quotas/**").hasAnyAuthority(quotaGroup, adminGroup, adminGroupAngular)
				.antMatchers( "/ajax/budgets/**").hasAnyAuthority(budgetGroup, adminGroup,adminGroupAngular)
				.antMatchers( "/ajax/salesReps/**").hasAnyAuthority(companyGroup,adminGroup,adminGroupAngular)
				.antMatchers( "/report/**").hasAnyAuthority(reportGroup,adminGroup,adminGroupAngular)
				//TODO - <AP> repair online UI
				.antMatchers( "/repairOnlineHome/**").hasAnyAuthority(repairGroup)
				.antMatchers( "/bsdStoreOnlineHome/**").hasAnyAuthority(thymeleafGroup)

				//TODO - <AP> configure antMatchers for "ROLE_BSD_DEALER"
				//.antMatchers( "/ajax/updateSalesRep").hasAnyAuthority(adminGroup)// + ", " + adminGroup)
				//.antMatchers( "/ajax/**").permitAll()
				//.antMatchers( "/ajax/users/**").hasAnyAuthority(adminGroup)// + ", " + adminGroup)
				.antMatchers( "/companyUpload/**").permitAll()
				.antMatchers( "/resources/**").permitAll()
				.antMatchers( "/images/**").permitAll()
				.antMatchers( "/css/**").permitAll()

				.antMatchers( "/repair_images/**").permitAll()
				.antMatchers( "/repair_css/**").permitAll()
				.antMatchers( "/node_modules/**").permitAll()

				.antMatchers( "/js/**").permitAll()
				.antMatchers( "/resources/**").permitAll()
				.antMatchers( "/app/**").permitAll()
                .antMatchers( "/seedstartermngHome/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin().loginPage("/login").failureUrl("/login?error").usernameParameter("userName").passwordParameter("password").permitAll()
			.successHandler(appSuccessHandler())//declare your bean here
			.and().logout().logoutSuccessUrl("/login?logout").permitAll()
			.and().csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//LDAP auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
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
	
	@Bean
	public AuthenticationSuccessHandler appSuccessHandler(){
		return new AppUrlAuthenticationSuccessHandler();
	}

	/** 
	 * commented out Thursday, April 21, 2016
	 * 
	 * 
	 * For Role based Access control using Spring Security and MVC, Mapping LDAP Groups to Authorities for Authorization
	 * http://javarevisited.blogspot.com/2013/07/role-based-access-control-using-spring-security-ldap-authorities-mapping-mvc.html
	
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
