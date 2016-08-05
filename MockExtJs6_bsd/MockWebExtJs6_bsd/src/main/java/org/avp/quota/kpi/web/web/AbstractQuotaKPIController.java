package org.avp.quota.kpi.web.web;

import org.avp.quota.kpi.service.interfaces.QuotaService;
import org.avp.quota.kpi.web.service.SystemUserDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractQuotaKPIController extends AbstractExtJsController {

	public static final String FORM_ATTRIBUTE_NAME = "requestsListForm";
	public static final String SEARCH_FORM_ATTRIBUTE_NAME = "requestsSearchForm";
	@Autowired
	protected QuotaService quotaService;
	
	@Autowired
	protected SystemUserDto userDto;

}