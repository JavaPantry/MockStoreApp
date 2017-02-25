package org.avp.quota.kpi.web.configuration;

import org.avp.quota.kpi.configuration.HsqlJUnitDataServiceModuleConfiguration;
import org.avp.quota.kpi.configuration.JbossDataServiceModuleConfiguration;
import org.avp.quota.kpi.configuration.TomcatDataServiceModuleConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import thymeleafexamples.stsm.business.SpringBusinessConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


//exlude: 
//TomcatDataServiceBsdModuleConfiguration.class from at.Import
//JbossDataServiceModuleConfiguration.class,
//HsqlJUnitDataServiceModuleConfiguration.class //TODO - <AP> do we really need it here?!
//SpringConfigurationThymeleaf.class
//WebSecurityConfig.class,


// removed from scan ,"org.avp.security.service"
@Configuration
public class SpringConfiguration  extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        //servletContext.setAttribute("spring.profiles.active", "Tomcat");
		servletContext.setInitParameter("spring.profiles.active", "Tomcat");
        //Set multiple active profile
        // not exists anymore servletContext.setInitParameter("spring.profiles.active", "dev, testdb");

	}

	public SpringConfiguration() {
		//-Djava.security.krb5.conf=krb5.conf
		//Threw exception System.getenv().put("java.security.krb5.conf", "krb5.conf");
		//System.getenv().containsKey("java.security.krb5.conf");
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
    	return new Class<?>[] { SpringConfigurationWebMvc.class };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { SpringBusinessConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
