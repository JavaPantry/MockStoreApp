# Dummy Mock project

This is playground to be used as start for new projects 

## Quick start
- in MockServiceExtJs6_bsd/src/main/java/org/avp/quota/kpi/configuration/TomcatDataServiceModuleConfiguration.java change hibernate setting to create database from scratch 

	Properties getJpaProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "validate");//Select: "create" OR "validate" OR "default"
 
- run MockWebExtJs6_bsd/src/test/java/org/avp/quota/kpi/service/BuildAndExportDatabase.java as jUnit test
- confirm success and check database populated


## User Interface

- ExtJs6 (v 6.x)
- AngularJs (AngularJs I) to be replaced with Angular II
 
