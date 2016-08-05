# Closed to continue in ReadMe.md
# BDI ExtJs6+AngularJs project (branch from Mock Quota KPI ExtJs project)

- References
- point browser to http://localhost:8080/MockWebExtJs6_bsd/

### _On April 28, 2016_
- add Order, OrderService and ordersList.html  
- rename templates/list.html to templates/productList.html

- move services/controllers/data from main.js under new folder
 
	services/ProductService.js
	services/OrderService.js
	controllers/OrderListController.js
	controllers/ProductListController.js
	data/Product.js
	data/Order.js
	

### _On April 27, 2016_
- introduce ui-route 
- extract list.html from bsdhome.jsp  

### _On April 26, 2016_ 
- Take out `app.service('ProductService', function (Product, $q, toaster)` from main.js in separate file
- Add `resources/js/` to template path

	/MockWebExtJs6_bsd/resources/js/templates/modal.create.tpl.html
	/MockWebExtJs6_bsd/resources/js/templates/form.html

- snapshot before splitting root viewport into views MockExtJs6_April26_bsd_beforeSplitView.zip
   

### _On April 25, 2016_ 
- Products appears on angular page
- Change Contact/person to Supply/products in angular code 
### _On April 22, 2016_ 
- give-up and Remove 2nd datasource (see 'hibernate validation throws an error' below) 
- Comment out ApplicationConfigurationService:initialize (backup c:\MockStub\MockStub.json to MockExtJs6_bsd\AppConfig\MockStub\)

### _On April 21, 2016_ **(Build in trouble)** Save as dead branch to switch back to one datasource

### _On April 21, 2016_ **(Build in trouble)**

- hibernate validation throws an error org.hibernate.HibernateException: Missing table: **authorities**
	
	DEBUG 2016-04-21 11:01:33,348 - org.hibernate.cfg.Configuration - Resolving reference to class: 
	org.avp.quota.kpi.model.dao.SalesRepresentativeDao
	org.avp.quota.kpi.model.dao.SalesRepresentativeDao
	org.avp.quota.kpi.model.dao.SalesRepresentativeDao
	org.avp.quota.kpi.model.dao.ProductLine
	org.avp.quota.kpi.model.dao.EmployeeDao
	org.avp.quota.kpi.model.dao.CategoryDao
	org.avp.quota.kpi.model.dao.EmployeeDao
	org.avp.quota.kpi.model.dao.TocDao
	org.avp.quota.kpi.model.dao.UserDao
	missing: 
	authorities
	ids_quota
	ids_manager
	ids_security
	org.avp.company.model.Company
	org.avp.company.model.Dealer
	org.avp.company.model.ShipTo
	org.avp.company.model.BillTo
	org.avp.quota.kpi.model.security.User
	org.avp.quota.kpi.model.security.User
	org.avp.quota.kpi.model.security.User
	org.avp.quota.kpi.model.security.Role
	org.avp.quota.kpi.model.security.Role
	org.avp.quota.kpi.model.security.Privilege

- add schema to all table annotations
- hibernate validation throws an error Caused by: org.hibernate.HibernateException: Missing table: **mockstubauth.authorities**


### _On April 20, 2016_ **(Build in trouble)**
- Add TomcatDataServiceBsdModuleConfiguration.java as data source to bsd database
- fool around with bsd database configuration
### _On April 15, 2016_
- rename bsd.model.Address to bsd.model.BsdAddress  
- render quota on angular page
- google `ngresource tutorial`

### _On April 14, 2016_
- add angularjs course lecture 6 content
- fix references to libs/css/jscript from bsdhome.jsp
- fix boolean in bsd entities

#### Add bsd entities 
- package org.avp.bsd.model
- Address.java
- BsdUser.java
- OrderDetail.java
- OrderHeader.java
- Product.java
- ProductPriceInStore.java
- Store.java
  
### _On April 13, 2016 4.p.m._ - Add AngularJs to bsdhome.jsp
 
### _On April 13, 2016_ - Add bsdhome.jsp
- Add bsdhome.jsp
- add user Tim with role ROLE_BSD_DEALER
- on index.jsp add redirect based on current user role

	<security:authorize access="hasRole('QuotaKPI_QUOTA')">
	<c:redirect url="/exthome"/>
	</security:authorize>
	
	<security:authorize access="hasRole('BSD_DEALER')">
	<c:redirect url="/bsdhome"/>
	</security:authorize>
  



### _On April 12, 2016_ - Add Custom CustomUserService implements UserDetailsService_
- use authProvider = DaoAuthenticationProvider instead AbstractUserDetailsAuthenticationProvider
- add BCryptPasswordEncoder(11) to authProvider.setPasswordEncoder(encoder())
- change toString in AuthoritiesDao to use guava collection _functional programming_
- now **Can login and use roles for access**

### _On April 11, 2016 4 p.m. - Add Custom CustomUserService implements UserDetailsService_
- switch from LDAP provider

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//AVP switch provider
		//LDAP auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());

		//Custom
		//auth.authenticationProvider(customAuthenticationProvider());
	}
- switch LDAP authentication provider to custom in avp.quota.kpi.web.configuration.WebSecurityConfig.java

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//AVP switch provider
		//LDAP auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());

		//Custom
		auth.authenticationProvider(customAuthenticationProvider());
	}
	@Bean
	public AuthenticationProvider customAuthenticationProvider() {
		AuthenticationProvider customAuthenticationProvider = new AbstractUserDetailsAuthenticationProvider() {
			@Override
			protected UserDetails retrieveUser(String username,
					UsernamePasswordAuthenticationToken authentication)
					throws AuthenticationException {
				return customUserService.loadUserByUsername(username);
			}
			
			@Override
			protected void additionalAuthenticationChecks(UserDetails userDetails,
					UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
				// TODO Auto-generated method stub
			}
		};
		return customAuthenticationProvider;
	}

- add __ROLE_QuotaKPI_COMPANY, ROLE_QuotaKPI_QUOTA, ROLE_QuotaKPI_BUDGET, ROLE_QuotaKPI_ADMIN__ roles in database 


 

### _On April 11, 2016 - Built with baeldung's user/role/privileges_
- still authenticate with LDAP
- use @Column(name="lunchOpen", columnDefinition = "BIT", length = 1) for private boolean lunchOpen properties
 


### _On February 25, 2016 - Add mps domain entities_
## What's inside 
### Add mps domain entities
- Address.java
- BillTo.java
- Contact.java
- Dealer.java
- Invoice.java
- InvoiceItem.java
- Machine.java
- MdsCompany.java
- Named.java
- NamedWithAddress.java
- ShipTo.java

### Simplified mapping produce schema
- company
- company_billto

- billto
- billto_shipto

- shipto
- shipto_machine
- machine

- ids_employee_profile
- ids_manager
- ids_mdse_stru_lv3
- ids_quota
- ids_security
- ids_security_toc
- ids_toc
- productline
- user
- users_roles
- role
- roles_privileges
- privilege
- users
- authorities
