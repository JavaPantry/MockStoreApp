package org.avp.quota.kpi.service.interfaces;

import java.util.List;

import org.avp.quota.kpi.model.dao.AuthoritiesDao;
import org.avp.quota.kpi.model.dao.BudgetDao;
import org.avp.quota.kpi.model.dao.CategoryDao;
import org.avp.quota.kpi.model.dao.EmployeeDao;
import org.avp.quota.kpi.model.dao.ProductLine;
import org.avp.quota.kpi.model.dao.QuotaDao;
import org.avp.quota.kpi.model.dao.SalesRepEmployeeJoin;
import org.avp.quota.kpi.model.dao.SalesRepresentativeDao;
import org.avp.quota.kpi.model.dao.TocDao;
import org.avp.quota.kpi.model.dao.UserDao;
import org.avp.quota.kpi.model.dto.BudgetDto;
import org.avp.quota.kpi.model.dto.EmployeeDto;
import org.avp.quota.kpi.model.dto.QuotaDto;
import org.avp.quota.kpi.model.dto.TotalDto;
import org.avp.quota.kpi.util.FilterParameterExtJs6;
import org.avp.quota.kpi.util.SortParameter;
import org.springframework.data.domain.Page;

public interface QuotaService {
	
	public List<QuotaDao> getQuotas();
	public Page<QuotaDao> getPaginatedFilteredQuotas(int limit, int page, int start, FilterParameterExtJs6[] filterParameters, SortParameter[] sortParameters);
	public TotalDto getFilteredSummAggregateQuota(final FilterParameterExtJs6[] filterParameters);
	public TotalDto getFilteredSummAggregateBudget(final FilterParameterExtJs6[] filterParameters);
	
	public QuotaDao getQuotaById(Long id);
	public void updateQuota(QuotaDao quotaDao);
	public void updateQuotaDto(QuotaDto dto);
	public boolean isExistQuota(QuotaDto requestQuota);
	public boolean isExistBudget(BudgetDto dto);

	public void updateQuotasValues(QuotaDao quota);
	public void deleteQuotaById(Long id);
	public void deleteBudgetById(Long id);
	
	public List<BudgetDao> getBudgets();
	public Page<BudgetDao> getPaginatedFilteredBudgets(int limit, int pageIndex , int start, FilterParameterExtJs6[] filterParameters, 
														SortParameter[] sortParameters);
	public BudgetDao getBudgetById(Long id);
	public void updateBudget(BudgetDao budgetDao);
	public void updateBudgetDto(QuotaDto dto);
	public void updateBudgetsValues(BudgetDao budgetDao);
	
	public List<UserDao> getUsers();
	public List<EmployeeDao> getFilteredEmployee( final FilterParameterExtJs6[] filterParameters, SortParameter[] sortParameters);
	public List<EmployeeDao> getEmployees();
	public List<EmployeeDao> getEmployeesNotInSalesRep(String salesRepId, String managerId, String strQuery);
	public UserDao getUserById(String userId);
	
 	public List<TocDao> getTocs();
 	//QKPI-55 public List<TocDao> getTocNotInSalesRep(String salesRepId);
 	public List<TocDao> getTocNotInSalesRep(String salesRepId, String strQuery);
	public TocDao getTocById(String tocId);
	public void linkTocAndSalesRepresentative(String tocId,	String salesRepresentativeId);
	public void deleteTocFromSalesRep(String salesRepresentativeId, List<TocDao> dtos);
	
	
	public List<SalesRepresentativeDao> getSalesRepresentatives();
	public Page<SalesRepresentativeDao> getPaginatedFilteredSalesRepresentatives(int limit, int pageIndex /*1-based*/, int start
																				,final FilterParameterExtJs6[] filterParameters, SortParameter[] sortParameters);
	public boolean checkSalesRepId(String salesRepId);

	public List<CategoryDao> getCategories();
	
	public List<ProductLine> getProductLines();
	public void save(UserDao user);
	public void save(AuthoritiesDao authoritiy);
	public void save(ProductLine lineA);
	public void saveSalesRepresentativeHeader(SalesRepresentativeDao salesRepresentative);

	
	public List<SalesRepEmployeeJoin>	getSalesRepEmployeeJoinsFor(String salesRepresentativeId,String managerId);
	public List<SalesRepEmployeeJoin>	getSalesRepEmployeeJoinsFor(String salesRepresentativeId,String managerId, String productLineCode);
	public SalesRepEmployeeJoin			getOneSalesRepEmployeeJoinsFor(String salesRepresentativeId,String managerId, String productLineCode);
	public void save(SalesRepEmployeeJoin salesRepEmployeeJoin);
	public void delete(SalesRepEmployeeJoin salesRepEmployeeJoin);
	
	public void save(TocDao toc);
	public void save(EmployeeDao employee);
	public void save(CategoryDao category);
	public void save(QuotaDao q);
	public void save(BudgetDao q);
	public SalesRepresentativeDao getSalesRepresentativeById(String string);
	public EmployeeDao getEmployeeById(String string);
	public ProductLine getProductLineByCode(String string);
	public void deleteSalesRepresentative(String salesRepresentativeId);

	public List<EmployeeDao> getSalesRepManagersFor(String salesRepresentativeId);
	public List<EmployeeDto> getSalesRepManagerDtosFor(String salesRepresentativeId);
	//public void updateSalesRepresentativeManager(EmployeeDto dto);
	public void deleteSalesRepresentativeManager(EmployeeDto dto);
	
	
	
	
}
