package org.avp.quota.kpi.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.service.jta.platform.internal.JBossAppServerJtaPlatform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"org.avp.quota.kpi.repository", "org.avp.quota.kpi.repository.security", "org.avp.company.model"})
@ComponentScan(basePackages={"org.avp.quota.kpi.service"})
@Profile("JBossTest")
public class JbossTestDataServiceModuleConfiguration{

	private static Logger logger = Logger.getLogger(JbossTestDataServiceModuleConfiguration.class);
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setDataSource(dataSource());
		em.setJpaProperties(getJpaProperties());
		em.setPackagesToScan(new String[] { "org.avp.quota.kpi.model.dao", "org.avp.quota.kpi.model.security"});
        em.setPersistenceUnitName("hibernate_bsd");
        em.afterPropertiesSet();
        logger.debug("EntityManagerFactory = "+ em.getObject());
		return em.getObject();
	}


	@Bean
	public DataSource dataSource() {
		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(false);
		//DataSource dataSource = dsLookup.getDataSource("java:jboss/datasources/QuotaKPI");
		//DataSource dataSource = dsLookup.getDataSource("java:jboss/datasources/QuotaKPI_DEV");
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      dataSource.setUrl("jdbc:sqlserver://10.124.5.148:1433;databaseName=QuotaKPI");//mockstub");//mymindmap
      dataSource.setUsername( "fast_admin" );
      dataSource.setPassword( "maj4zA3a" );
	  return dataSource;
	}
	
/*   @Bean
   public JtaTransactionManager transactionManager() {
   	JtaTransactionManager transactionManager = new JtaTransactionManager();
   	transactionManager.setAllowCustomIsolationLevels(true);
   	transactionManager.setDefaultTimeout(1200); // 20 minutes
       return transactionManager;
   }*/
 
   @Bean
   public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(emf);

      return transactionManager;
   }
  
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
      return new PersistenceExceptionTranslationPostProcessor();
   }

	/*
	 * for hibernate 4.3.8 use 
	 * import org.hibernate.engine.transaction.jta.platform.internal.JBossAppServerJtaPlatform;
	 * for hibernate 4.2.12 use
	 * import org.hibernate.service.jta.platform.internal.JBossAppServerJtaPlatform;  
	 */
   private Properties getJpaProperties() {
       final Properties props = new Properties();
       props.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServer2005Dialect");
       props.setProperty("hibernate.hbm2ddl.auto", "create");//"create" "validate"
       props.setProperty("hibernate.default_schema", "dbo");//env.getProperty("org.avp. <app package name> .sql.schema") might be defined in server standalone.xml or properties

       //NO NEED props.setProperty("javax.persistence.transactionType", "JTA");
       //NO NEED props.setProperty("hibernate.transaction.jta.platform", JBossAppServerJtaPlatform.class.getName());
       return props;
   }

}
