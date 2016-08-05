package org.avp.quota.kpi.web.configuration;

import org.avp.quota.kpi.configuration.HsqlJUnitDataServiceModuleConfiguration;
import org.avp.quota.kpi.configuration.JbossDataServiceModuleConfiguration;
import org.avp.quota.kpi.configuration.TomcatDataServiceModuleConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages={"org.avp.quota.kpi.web.service","org.avp.quota.kpi.service","org.avp.bsd.service"})

//exlude: 
//TomcatDataServiceBsdModuleConfiguration.class from at.Import
//JbossDataServiceModuleConfiguration.class,
//HsqlJUnitDataServiceModuleConfiguration.class //TODO - <AP> do we really need it here?!

@Import({WebSecurityConfig.class, SpringConfigurationThymeleaf.class, TomcatDataServiceModuleConfiguration.class})  
public class SpringConfiguration {

	public SpringConfiguration() {
		//-Djava.security.krb5.conf=krb5.conf
		//Threw exception System.getenv().put("java.security.krb5.conf", "krb5.conf");
		//System.getenv().containsKey("java.security.krb5.conf");
	}
	    
}
