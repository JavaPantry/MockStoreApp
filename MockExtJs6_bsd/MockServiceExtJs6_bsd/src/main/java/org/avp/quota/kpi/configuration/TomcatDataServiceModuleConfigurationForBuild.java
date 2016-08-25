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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"org.avp.quota.kpi.repository", "org.avp.security.repository", "org.avp.bsd.repository"}, entityManagerFactoryRef="entityManagerFactory")
@ComponentScan(basePackages={"org.avp.quota.kpi.service", "org.avp.bsd.service", "org.avp.security.service"})
@Profile("Tomcat")
public class TomcatDataServiceModuleConfigurationForBuild extends AbstractTomcatDataServiceModuleConfiguration{
	private static Logger logger = Logger.getLogger(TomcatDataServiceModuleConfigurationForBuild.class);

	@Override
	public boolean isInBuildMode() {
		return true;
	}
}
