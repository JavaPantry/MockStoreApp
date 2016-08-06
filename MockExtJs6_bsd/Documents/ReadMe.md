# BDI ExtJs6+AngularJs project (branch from Mock Quota KPI ExtJs project)

- References
- point browser to http://localhost:8080/MockWebExtJs6_bsd/
- previous ReadMe saved as MockExtJs6_bsd/ReadMeApril2016.md
- mysterious TOC meaning **TOC - Transactional Organization Code**

- check bootstrap table http://issues.wenzhixin.net.cn/bootstrap-table/

## _On August 6, 2016

- To keep old database setup new `gitmockauth`
- Change welcome message to `App Admin mode`

## _On August 5, 2016_

- get rid of 'canon' word
- rename canonAdmin to siteAdmin
- remove properties
 
	_app.ad-domain=CCI.CANON.INFO
	_app.ad-server=ldap://MIS-DC1.CCI.CANON.INFO/
	_app.service-principal=HTTP/M005554MIS.cci.canon.info@CCI.CANON.INFO
	_app.keytab-location=http-web.keytab
	_app.ldap-search-base=DC=CCI,DC=CANON,DC=INFO
	_app.ldap-search-filter="(| (userPrincipalName={0}) (sAMAccountName={0}))"

SSO AD server related from securityConfig.properties


## _On August 4, 2016_

If turn off thymeleaf config: Get an error `javax.servlet.jsp.JspTagException: No message found under code 'label.login.page_title' for locale 'en'.`
  

### Check bootstrap example 
- [Sign-in](http://getbootstrap.com/examples/signin/)
-  downloading the [Bootstrap repository](https://github.com/twbs/bootstrap/archive/v3.3.7.zip).  Examples can be found in the docs/examples/ directory. 
- replace login.jsp with bootstrap version
- remove MockWebExtJs6_bsd/src/main/webapp/resources/css/cmac2013web.css
- remove MockWebExtJs6_bsd/src/main/webapp/resources/css/cw-images/bg.gif, bgitem.gif, bgmenu.gif, companyLogo.gif
- remove downloadReport.jsp 


### Clean-up
- remove requestBody.jsp, requestListBody.jsp, requestSearch.jsp, salesChannels.jsp, summaryReports.jsp, 
- remove uncaughtException.jsp, userBody.jsp, usersListBody.jsp, appErrorBody.jsp
- remove tiles/requestBodyEdit-Step0.jsp
- remove custom-functions.tld & references
- backup MockExtJs6_bsd_10_10.zip
- remove importCsv-Step##.jsp
- remove requestBodyEdit-Step##.jsp 

#### CMAC trace
- backup MockExtJs6_bsd_9_45.zip 
- remove org.avp.cmac2013.interceptors.CmacCacheEvent...
- remove MockServiceExtJs6_bsd/src/main/resources/ehcache.xml

## _On August 3, 2016_

### google hibernate generate sql script

- [How To Generate A Schema Creation Script With Hibernate 4, JPA And Maven](https://dzone.com/articles/how-generate-schema-creation)
- [Generate DDL with hibernate - Geoffroy Warin blog](https://geowarin.github.io/generate-ddl-with-hibernate.html)
- [Generating Database Schema using Hibernate](http://javarticles.com/2015/06/generating-database-schema-using-hibernate.html)

## _On June 29, 2016_

- chart store read data from server controller
- Error: one of values in section (probably Agriculture) renders as `null` regardless that json contains valid data

## _On June 13, 2016_

- Add dummy chart

![Dummy Chart](.\DummyChart.PNG)


## _On June 7, 2016_
- Spring-security-registration-master.md Security **Baeldung** ReadMe taken from c:\STS-3.6.4.Workspace_MPS_HEAD_BIRT\spring-security-registration-master\

## Guessing:

### TOC = Department?
- In project context TOC is sounds like a record for department
-- and orgLayerNumber property - place in organization hierarchy
- So the best name for this entity would be Department
- SalesRepesentative have relationship with Department (TOC)

### SalesRepesentative

- ids_sequrity in project context sounds like SalesRepesentative
- SalesRepesentative represents/(not really belongs) to Department (TOC)
- (see QKPI-108) TOC.id(dep-t) is unique in SalesRep<>TOC table
- But SalesRepesentative may represent many TOC(dep)

### SalesRepesentativeJoin

- SalesRepesentative connected with manager(employee table) via SalesRepesentativeJoin class. 
- SalesRepesentativeJoin also assign Product line code to (SalesRepesentative, manager) pair 
- All three codes make PK class SalesRepEmployeePK
- Record: 

			|(SalesRepesentative, manager)product Line|

![Simplified ERD](.\SimplifiedERD.PNG)


