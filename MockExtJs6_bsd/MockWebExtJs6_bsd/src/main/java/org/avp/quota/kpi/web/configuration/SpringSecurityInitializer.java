package org.avp.quota.kpi.web.configuration;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * By removing "extends AbstractSecurityWebApplicationInitializer" application deployed and
 * no exception "No bean named 'springSecurityFilterChain' is defined"
 *
 * Of course protected url not accessible
 */
//at Order(1)
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer{//extends AbstractSecurityWebApplicationInitializer


}