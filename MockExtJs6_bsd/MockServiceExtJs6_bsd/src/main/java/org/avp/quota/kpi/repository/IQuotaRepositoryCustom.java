package org.avp.quota.kpi.repository;

import org.avp.quota.kpi.model.dto.TotalDto;
import org.avp.quota.kpi.util.FilterParameterExtJs6;

public interface IQuotaRepositoryCustom{

	public TotalDto sumQuotaWithFilters(FilterParameterExtJs6[] filters);
	public TotalDto sumBudgetWithFilters(FilterParameterExtJs6[] filters);
}
