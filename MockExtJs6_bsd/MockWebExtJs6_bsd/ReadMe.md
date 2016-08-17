See file://..\ReadMe.md


## August 16

- fix mess with security/user services
	
	- rename MockServiceExtJs6_bsd/src/main/java/org/avp/security/repository/IAuthoritiesRepository to AuthoritiesRepository
	- move security related entities/repositories and service to MockServiceExtJs_bsd
	- add dependency for org.springframework.security to MockServiceExtJs_bsd
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-web</artifactId>
			<version>${spring.security.version}</version>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-crypto</artifactId>
			<version>${spring.security.version}</version>
		</dependency>
		
	- move them under security domain avp.security.service.SecurityService
	- MockWebExtJs6_bsd/src/main/java/org/avp/quota/kpi/web/service/CustomUserService.java
	- move avp/quota/kpi/service/interfaces/QuotaService:save(User user);
	- test that one avp.security.repository.UserRepository is enough
		- avp.bsd.repository.BsdUserRepository for BsdUser
		- avp.quota.kpi.repository.IUserRepository for QuotaUser
		- avp.quota.kpi.repository.security.UserRepository for User
	- move User under security domain avp.security.model from org.avp.quota.kpi.model.security

- TODO - <AP>: TBR org/avp/quota/kpi/web/configuration/WebSecurityConfig:customAuthenticationProvider()
- TODO - <AP>: probably these resources requested login jsp   

	"NetworkError: 404 Not Found:
	- http://localhost:8080/MockWebExtJs6_bsd/resources/lib/bootstrap/dist/css/bootstrap.min.css"
	- http://localhost:8080/MockWebExtJs6_bsd/resources/lib/bootstrap-additions/dist/bootstrap-additions.min.css"
	- http://localhost:8080/MockWebExtJs6_bsd/resources/lib/angularjs-toaster/toaster.min.css"
	- http://localhost:8080/MockWebExtJs6_bsd/resources/lib/ladda/dist/ladda-themeless.min.css"
	- http://localhost:8080/MockWebExtJs6_bsd/resources/lib/font-awesome/css/font-awesome.min.css"


- __Side note__ : datatables.net

	C:\Tmp\npmTmp>npm init
	This utility will walk you through creating a package.json file.
	It only covers the most common items, and tries to guess sensible defaults.
	
	See `npm help json` for definitive documentation on these fields
	and exactly what they do.
	
	Use `npm install <pkg> --save` afterwards to install a package and
	save it as a dependency in the package.json file.
	
	Press ^C at any time to quit.
	name: (npmTmp)
	Sorry, name can no longer contain capital letters.
	name: (npmTmp) dymmy_project
	version: (1.0.0)
	description: dymmy project
	entry point: (index.js)
	test command:
	git repository:
	keywords:
	author:
	license: (ISC)
	About to write to C:\Tmp\npmTmp\package.json:
	
	{
	  "name": "dymmy_project",
	  "version": "1.0.0",
	  "description": "dymmy project",
	  "main": "index.js",
	  "scripts": {
	    "test": "echo \"Error: no test specified\" && exit 1"
	  },
	  "author": "",
	  "license": "ISC"
	}
	
	
	Is this ok? (yes) y
	
	C:\Tmp\npmTmp>npm install datatables.net --save
	dymmy_project@1.0.0 C:\Tmp\npmTmp
	`-- datatables.net@1.10.12
	  `-- jquery@3.1.0
	
	npm WARN dymmy_project@1.0.0 No repository field.
	
	C:\Tmp\npmTmp>npm install datatables.net-bs --save
	dymmy_project@1.0.0 C:\Tmp\npmTmp
	`-- datatables.net-bs@1.10.12
	npm WARN dymmy_project@1.0.0 No repository field.
	C:\Tmp\npmTmp>


## August 15

Fix mess with user accounts (user, bsduser, quota user), roles, user_roles, roles_priveleges, authorities etc.

For JPA inheritance types look at [JPA blog](http://www.thejavageek.com/jpa-tutorials/)
- [JPA table per concrete class example](http://www.thejavageek.com/2014/05/17/jpa-table-per-concrete-class-example/)
- [JPA Joined Inheritance Example](http://www.thejavageek.com/2014/05/17/jpa-joined-inheritance-example/)

- UserDao(quotaUser) BsdUser(users) extend org/avp/quota/kpi/model/security/User (hibernate inheritance)
- move BsdUser.email .etc to org/avp/quota/kpi/model/security/User (org/avp/quota/kpi/model/dao/UserDao) 
- make login against org/avp/quota/kpi/model/security/User instead of org/avp/quota/kpi/model/dao/UserDao 

Refactor AuthoritiesDao
- move AuthoritiesDao.java from _org/avp/quota/kpi/model/dao_ to _org/avp/quota/kpi/model/security/_
- rename AuthoritiesDao to Authority
- change AuthoritiesDao.user from type QuotaUser to org/avp/quota/kpi/model/security/User

### Angular mile stone v0.1.0.4
- AngularJs UI removed
- introduce ProductPriceInStore entity to link product and store
- updated test case to build database with Store and ProductPriceInStore

##  August 12

### Angular mile stone taken before removing AngularJs UI v0.1.0.3
- server side reading for products and orders
- three routes for product list, product detail and order list
- draft bootstrap orders table

## August 10

- to work on orders route
- create store <- prdInStore -> product


## August 09

Read products cause error in parsing standard json response

	map(response => response.json())

in home.ts change
 
      <div *ngFor="let product of products | async" class="col-sm-4 col-lg-4 col-md-4">
        <auction-product-item [product]="product"></auction-product-item>
      </div>
to

      <div *ngFor="let product of products.data | async" class="col-sm-4 col-lg-4 col-md-4">
        <auction-product-item [product]="product"></auction-product-item>
      </div>




### TODO
#### Eclipse TypeScript Plug-in

An Eclipse plug-in for developing in the TypeScript language.
https://github.com/palantir/eclipse-typescript
Installation

- Install Node.js
- Open Eclipse and go to Help->Install New Software
- Add the update site: http://eclipse-update.palantir.com/eclipse-typescript/
- Reboot Eclipse
- (optional) Right-click on a project and select Configure->Enable TypeScript Builder
