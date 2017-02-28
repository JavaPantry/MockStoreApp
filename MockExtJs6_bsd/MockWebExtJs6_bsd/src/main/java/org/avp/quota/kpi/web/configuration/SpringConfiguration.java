package org.avp.quota.kpi.web.configuration;

import org.avp.quota.kpi.configuration.HsqlJUnitDataServiceModuleConfiguration;
import org.avp.quota.kpi.configuration.JbossDataServiceModuleConfiguration;
import org.avp.quota.kpi.configuration.TomcatDataServiceModuleConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import thymeleafexamples.stsm.business.SpringBusinessConfig;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;


//exlude: 
//TomcatDataServiceBsdModuleConfiguration.class from at.Import
//JbossDataServiceModuleConfiguration.class,
//HsqlJUnitDataServiceModuleConfiguration.class //TODO - <AP> do we really need it here?!
//SpringConfigurationThymeleaf.class
//WebSecurityConfig.class,

// at Import({WebSecurityConfig.class})
// removed from scan ,"org.avp.security.service"
//remove from import , SpringConfigurationThymeleaf.class
@Configuration
@ComponentScan(basePackages={"org.avp.quota.kpi.web.service","org.avp.quota.kpi.service","org.avp.bsd.service","org.avp.security.service"})
@Import({WebSecurityConfig.class, TomcatDataServiceModuleConfiguration.class, SpringBusinessConfig.class})
public class SpringConfiguration  {

	public SpringConfiguration() {
		//-Djava.security.krb5.conf=krb5.conf
		//Threw exception System.getenv().put("java.security.krb5.conf", "krb5.conf");
		//System.getenv().containsKey("java.security.krb5.conf");
	}

}
