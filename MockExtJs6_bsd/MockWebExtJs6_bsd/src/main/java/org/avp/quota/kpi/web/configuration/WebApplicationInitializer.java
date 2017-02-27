package org.avp.quota.kpi.web.configuration;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import thymeleafexamples.stsm.business.SpringBusinessConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

// at Order(2)
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        //servletContext.setAttribute("spring.profiles.active", "Tomcat");
        servletContext.setInitParameter("spring.profiles.active", "Tomcat");
        //Set multiple active profile
        // not exists anymore servletContext.setInitParameter("spring.profiles.active", "dev, testdb");
    }


    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebSecurityConfig.class, SpringConfigurationWebMvc.class  };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { SpringBusinessConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

/*
	public static final String CHARACTER_ENCODING = "UTF-8";
	@Override
	protected Filter[] getServletFilters() {
		final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding(CHARACTER_ENCODING);
		encodingFilter.setForceEncoding(true);
		return new Filter[] { encodingFilter };
	}
*/

}
