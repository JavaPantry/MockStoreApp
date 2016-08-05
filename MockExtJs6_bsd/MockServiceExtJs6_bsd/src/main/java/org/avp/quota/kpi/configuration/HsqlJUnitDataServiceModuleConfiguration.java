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
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"org.avp.quota.kpi.repository", "org.avp.quota.kpi.repository.security", "org.avp.company.model"})//,"org.avp.quota.kpi.service"
@ComponentScan(basePackages={"org.avp.quota.kpi.service"})
@Profile("HSQL_JUNIT")
public class HsqlJUnitDataServiceModuleConfiguration{
	private static Logger logger = Logger.getLogger(HsqlJUnitDataServiceModuleConfiguration.class);
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPersistenceUnitName("hibernate_bsd");
		em.setPackagesToScan(new String[] { "org.avp.quota.kpi.model.dao", "org.avp.quota.kpi.model.security" });
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(getJpaProperties());
		em.afterPropertiesSet();
		logger.debug("EntityManagerFactory = "+ em.getObject());
		return em.getObject();
	}

   @Bean
   public DataSource dataSource() {
	   EmbeddedDatabaseBuilder builder =  new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL);
	   builder.setName("PUBLIC");
           //.addScript("classpath:schema.sql")
           //.addScript("classpath:test-data.sql")
	   DataSource ds = builder.build();
	   return ds;
   }
   
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

   Properties getJpaProperties() {
      Properties properties = new Properties();
      properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");//these are should not be used in memory test: "create","validate"
      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
      properties.setProperty("hibernate.show_sql","true");
      return properties;
   }
}
