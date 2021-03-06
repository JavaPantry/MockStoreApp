package org.avp.quota.kpi.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

public abstract class AbstractTomcatDataServiceModuleConfiguration{
	private static Logger logger = Logger.getLogger(AbstractTomcatDataServiceModuleConfiguration.class);

	/*
	 * !WARNING!: - The true value of this constant will rebuld and populate database with default data set
	 * 
	 * use abstract method isInBuildMode()
	 * private static final boolean BULD_DATABASE = true;
	 * 
	 */
	public abstract boolean isInBuildMode();

	/*
	 * google : Cannot resolve reference to bean 'entityManagerFactory' while setting constructor argument; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'entityManagerFactory' is defined
	 * http://stackoverflow.com/questions/24520602/spring-data-jpa-no-bean-named-entitymanagerfactory-is-defined-injection-of-a 
	 */
	//instead "public LocalContainerEntityManagerFactoryBean quotaEntityManagerFactory() {"
	//at.Bean (name = "quotaEntityManagerFactory")
	//public EntityManagerFactory quotaEntityManagerFactory() {
	@Bean 
	public EntityManagerFactory entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(dataSource());
      em.setPersistenceUnitName("hibernate_bsd");
      em.setPackagesToScan(new String[] { "org.avp.quota.kpi.model.dao", "org.avp.security.model", "org.avp.company.model", "org.avp.bsd.model"});
      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      em.setJpaVendorAdapter(vendorAdapter);
      em.setJpaProperties(getJpaProperties());
      em.afterPropertiesSet();//<-http://stackoverflow.com/questions/28817120/spring-jpa-multiple-persistence-units-injecting-entitymanager
      logger.debug("EntityManagerFactory = "+ em.getObject());
      return em.getObject();//instead return em; 
	}

   @Bean
   public DataSource dataSource(){
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
      //TODO - <AP> under jboss or tomcat database not switched till full clean deploymnt folder

        dataSource.setUrl("jdbc:mysql://localhost:3306/gitmockauth?createDatabaseIfNotExist=true ");
        //dataSource.setUrl("jdbc:mysql://ubuntu:3306/gitmockauth?createDatabaseIfNotExist=true ");

      dataSource.setUsername( "root" );
      dataSource.setPassword( "pswd" );
      return dataSource;
   }

   /*
    * need to add @DependsOn("transactionManager") to class annotations if transactionManager would be defined in separate class
    * add [, transactionManagerRef="transactionManager"] to TomcatDataServicBsdeModuleConfiguration's @EnableJpaRepositories(basePackages={"...repositories"})
    * 
    * see comments on transactionManager in TomcatDataServiceBsdModuleConfiguration.java
    * 
    *  Because same "UnsatisfiedDependencyException: Error creating bean with name 'transactionManager'"
    *  Instead public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
    *  Try:
    *  public PlatformTransactionManager transactionManager(){
    *  AND "transactionManager.setEntityManagerFactory(entityManagerFactory());" intead "transactionManager.setEntityManagerFactory(emf);"
    *  
    */
   @Bean
   public PlatformTransactionManager transactionManager(){
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(entityManagerFactory());
      return transactionManager;
   }

   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
      return new PersistenceExceptionTranslationPostProcessor();
   }
/*
 * to run CeareAndExportHSQLDBData with tomcat/mysql 
 * change in TomcatDataServiceModuleConfiguration.java 
 * properties.setProperty("hibernate.hbm2ddl.auto", "create");//"create","validate"
 * 
 * 
 *  com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'userdao0_.enabled' in 'field list'
 * 
 */
   Properties getJpaProperties() {
      Properties properties = new Properties();
      //TODO - <AP> to init database from scratch change hibernate.hbm2ddl.auto to create
      String operation = "validate";
      if(isInBuildMode()){//use abstract method instead final const BULD_DATABASE
    	  operation ="create";//Select: "create" OR "validate" OR "default"
      }
      properties.setProperty("hibernate.hbm2ddl.auto", operation);//Select: "create" OR "validate" OR "default"
      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

      //to output and nicely format HQL: 
      properties.setProperty("hibernate.show_sql", "true");
      properties.setProperty("hibernate.format_sql", "true");

      return properties;
   }
}
