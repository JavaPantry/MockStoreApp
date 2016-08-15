package org.avp.quota.kpi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.avp.quota.kpi.model.dao.BudgetDao;
import org.avp.quota.kpi.model.dao.CategoryDao;
import org.avp.quota.kpi.model.dao.EmployeeDao;
import org.avp.quota.kpi.model.dao.ProductLine;
import org.avp.quota.kpi.model.dao.QuotaDao;
import org.avp.quota.kpi.model.dao.SalesRepEmployeeJoin;
import org.avp.quota.kpi.model.dao.SalesRepresentativeDao;
import org.avp.quota.kpi.model.dao.TocDao;
import org.avp.quota.kpi.model.dao.QuotaUser;
import org.avp.quota.kpi.model.dto.BudgetDto;
import org.avp.quota.kpi.model.dto.EmployeeDto;
import org.avp.quota.kpi.model.dto.QuotaDto;
import org.avp.quota.kpi.model.dto.TotalDto;
import org.avp.quota.kpi.model.security.AuthoritiesDao;
import org.avp.quota.kpi.repository.IAuthoritiesRepository;
import org.avp.quota.kpi.repository.IBudgetRepository;
import org.avp.quota.kpi.repository.ICategoryRepository;
import org.avp.quota.kpi.repository.IEmployeeRepository;
import org.avp.quota.kpi.repository.IProductLineRepository;
import org.avp.quota.kpi.repository.IQuotaRepository;
import org.avp.quota.kpi.repository.ISalesRepEmployeeJoinRepository;
import org.avp.quota.kpi.repository.ISalesRepresentativeRepository;
import org.avp.quota.kpi.repository.ITocRepository;
import org.avp.quota.kpi.repository.IUserRepository;
import org.avp.quota.kpi.repository.searchspec.SearchCriteriaUtility;
import org.avp.quota.kpi.service.interfaces.QuotaService;
import org.avp.quota.kpi.util.CollectionUtility;
import org.avp.quota.kpi.util.DtoFactory;
import org.avp.quota.kpi.util.FilterOperator;
import org.avp.quota.kpi.util.FilterParameterExtJs6;
import org.avp.quota.kpi.util.FilterType;
import org.avp.quota.kpi.util.GeneralUtil;
import org.avp.quota.kpi.util.SortParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service()
@Transactional
public class QuotaServiceImpl implements QuotaService {
	private static Logger logger = Logger.getLogger(QuotaServiceImpl.class);

	@Autowired
	private IQuotaRepository quotaRepository;

	@Autowired
	private IBudgetRepository budgetRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IAuthoritiesRepository authoritiesRepository;
	
	
	@Autowired
	private IEmployeeRepository employeeRepository;
	
	@Autowired
	private ITocRepository tocRepository;
	@Autowired
	private ISalesRepresentativeRepository salesRepresentativeRepository;

	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Autowired
	private IProductLineRepository productLineRepository;

	@Autowired
	private ISalesRepEmployeeJoinRepository salesRepEmployeeJoinRepository;
	
	@Transactional()
	public void deleteSalesRepresentative(String salesRepresentativeId){
		SalesRepresentativeDao salesRepresentative = salesRepresentativeRepository.findOne(salesRepresentativeId);
		
		Set<SalesRepEmployeeJoin> joins = salesRepresentative.getSalesRepEmployeeJoins();
		for (Iterator iterator = joins.iterator(); iterator.hasNext();) {
			SalesRepEmployeeJoin join = (SalesRepEmployeeJoin) iterator.next();
			EmployeeDao employee = join.getManager();
			Set<SalesRepEmployeeJoin> employeeJoins = employee.getSalesRepEmployeeJoins();
			employeeJoins.remove(join);
			//employeeRepository.saveAndFlush(employee);
			iterator.remove();
		}
		// QKPI-51 - Can't delete quota or budget
		Set<QuotaDao> quotas = salesRepresentative.getQuotas();
		for (Iterator iterator = quotas.iterator(); iterator.hasNext();) {
			QuotaDao quota = (QuotaDao) iterator.next();
			iterator.remove();
		}
		//salesRepresentativeRepository.saveAndFlush(salesRepresentative);
		//salesRepresentative = salesRepresentativeRepository.findOne(salesRepresentativeId);
		salesRepresentativeRepository.delete(salesRepresentativeId);
	}
	
	@Transactional()
	public void save(QuotaUser user){
		userRepository.save(user);
	}
	
	@Transactional()
	public void save(AuthoritiesDao authoritiy){
		authoritiesRepository.save(authoritiy);
	}
	
	@Transactional()
	public void save(ProductLine line){
		productLineRepository.save(line);
	}
	@Transactional()
	public void saveSalesRepresentativeHeader(SalesRepresentativeDao dao){
		SalesRepresentativeDao salesRepresentative = salesRepresentativeRepository.findOne(dao.getSalesRepresentativeId());
		if(salesRepresentative == null){
			salesRepresentativeRepository.save(dao);
			return;
		}
		salesRepresentative.setSalesRepresentativeName(dao.getSalesRepresentativeName());
		salesRepresentative.setUser(dao.getUser());
		salesRepresentative.setAllAccess(dao.getAllAccess());
		salesRepresentativeRepository.save(salesRepresentative);
	}
	@Transactional()
	public void save(SalesRepEmployeeJoin salesRepEmployeeJoin){
		salesRepEmployeeJoinRepository.save(salesRepEmployeeJoin);
	}
	@Transactional()
	public void save(TocDao toc){
		tocRepository.save(toc);
	}
	@Transactional()
	public void save(EmployeeDao employee){
		employeeRepository.save(employee);
	}
	@Transactional()
	public void save(CategoryDao category){
		categoryRepository.save(category);
	}
	@Transactional()
	public void save(QuotaDao quota){
		QuotaDao quota1 = quotaRepository.save(quota);
		logger.debug("quotaDao.getSalesRepresentative() = "+quota1.getSalesRepresentative());
	}
	@Transactional()
	public void save(BudgetDao budget){
		budgetRepository.save(budget);
	}
	
	@Transactional(readOnly=true)
	public List<EmployeeDao> getSalesRepManagersFor(String salesRepresentativeId){
		List<EmployeeDao> managers = new ArrayList<EmployeeDao>();
		List<SalesRepEmployeeJoin> salesRepEmployeeJoins = salesRepEmployeeJoinRepository.findAll(SearchCriteriaUtility.findJoinsBySalesRep(salesRepresentativeId));
		for (SalesRepEmployeeJoin salesRepEmployeeJoin : salesRepEmployeeJoins) {
			managers.add(salesRepEmployeeJoin.getManager());
		}
		return managers;
	}
	
	@Transactional(readOnly=true)
	public List<EmployeeDto> getSalesRepManagerDtosFor(String salesRepresentativeId){
		List<EmployeeDto> managers = new ArrayList<EmployeeDto>();
		List<SalesRepEmployeeJoin> salesRepEmployeeJoins = salesRepEmployeeJoinRepository.findAll(SearchCriteriaUtility.findJoinsBySalesRep(salesRepresentativeId));
		for (SalesRepEmployeeJoin salesRepEmployeeJoin : salesRepEmployeeJoins) {
			EmployeeDto dto = DtoFactory.createDtoFromDao(salesRepEmployeeJoin.getManager());
			dto.setSalesRepresentativeId(salesRepresentativeId);
			dto.setProductLinesContentStr(salesRepEmployeeJoin.getProductLine().getCode());
			managers.add(dto);
		}
		return managers;
	}
	
	/**
	 * serve for @RequestMapping(value={"ajax/deleteSalesRepManagers"}, method=RequestMethod.POST)  
	 * 
	 * delete all productLine occurrences of manager for Sales Representative 
	 */
	@Transactional()
	public void deleteSalesRepresentativeManager(EmployeeDto dto){
		//String salesRepresentativeId, String employeeId
		//TODO - <AP> delete all occurrences of managers for this salesRepresentativeId
		logger.debug("delete Sales Representative Manager");
		List<SalesRepEmployeeJoin> salesRepEmployeeJoins = getSalesRepEmployeeJoinsFor(dto.getSalesRepresentativeId(),dto.getEmployeeId());
		for (SalesRepEmployeeJoin salesRepEmployeeJoin : salesRepEmployeeJoins) {
			//delete(salesRepEmployeeJoin);
			salesRepEmployeeJoinRepository.delete(salesRepEmployeeJoin);
		}
		/*SalesRepresentativeDao salesRep = salesRepresentativeRepository.getOne(dto.getSalesRepresentativeId());
		if(null == salesRep)
			return;
		List<SalesRepEmployeeJoin> salesRepEmployeeJoins = salesRep.getSalesRepEmployeeJoins();
		for (Iterator iterator = salesRepEmployeeJoins.iterator(); iterator.hasNext();) {
			SalesRepEmployeeJoin salesRepEmployeeJoin = (SalesRepEmployeeJoin) iterator.next();
			if(salesRepEmployeeJoin.getManager().getEmployeeId().compareTo(dto.getEmployeeId()) == 0)
				iterator.remove();
		}
		salesRepresentativeRepository.save(salesRep);*/
	}

	/*
	 * commented in SalesRepresentativeManagersListForm:salesRepresentativeManagersListFormStore
	 * covered by ajax/updateProductLines
	 *
	@Transactional()
	public void updateSalesRepresentativeManager(EmployeeDto dto){
		//String salesRepresentativeId, String employeeId
		//TODO - <AP> delete all occurrences of managers for this salesRepresentativeId
		logger.debug("update Sales Representative Manager");
	}*/


	/*
	 * alt_salesrep_cd = 'CIGIT' and m.manager_id = 'C11266'
	 * SELECT alt_salesrep_cd,m.manager_id,employee_nm,first_prod_ctrl_cd FROM ids_manager as m, ids_employee_profile as e where alt_salesrep_cd = 'CIGIT' and m.manager_id = 'C11266' and m.manager_id = e.employee_id order by alt_salesrep_cd;
	 * alt_salesrep_cd, manager_id, employee_nm, 		first_prod_ctrl_cd
	 * 'CIGIT',			'C11266',	'Makoto Shibata',	'Q'
	 * 
	 * (non-Javadoc)
	 * @see org.avp.quota.kpi.service.interfaces.QuotaService#getSalesRepEmployeeJoinsFor(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly=true)
	public List<SalesRepEmployeeJoin> getSalesRepEmployeeJoinsFor(String salesRepresentativeId,String managerId) {
		List<SalesRepEmployeeJoin> salesRepEmployeeJoins = salesRepEmployeeJoinRepository.findAll(SearchCriteriaUtility.findJoinsBySalesRepAndManager(salesRepresentativeId,managerId));
		return salesRepEmployeeJoins;
	}
	
	public List<SalesRepEmployeeJoin> getSalesRepEmployeeJoinsFor(String salesRepresentativeId,String managerId, String productLineCode){
		List<SalesRepEmployeeJoin> salesRepEmployeeJoins = salesRepEmployeeJoinRepository.findAll(SearchCriteriaUtility.findJoinsBySalesRepManagerAndProdLine(salesRepresentativeId,managerId,productLineCode));
		return salesRepEmployeeJoins;
	}

	public SalesRepEmployeeJoin	getOneSalesRepEmployeeJoinsFor(String salesRepresentativeId,String managerId, String productLineCode){
		SalesRepEmployeeJoin salesRepEmployeeJoins = salesRepEmployeeJoinRepository.findOne(SearchCriteriaUtility.findJoinsBySalesRepManagerAndProdLine(salesRepresentativeId,managerId,productLineCode));
		return salesRepEmployeeJoins;
	}
	
	@Transactional()
	public void delete(SalesRepEmployeeJoin salesRepEmployeeJoin){
		salesRepEmployeeJoinRepository.delete(salesRepEmployeeJoin);
	}

	@Transactional(readOnly=true)
	public List<ProductLine> getProductLines() {
		List<ProductLine> productLines = productLineRepository.findAll();
		return productLines;
	}
	
	@Transactional(readOnly=true)
	public List<CategoryDao> getCategories() {
		List<CategoryDao> categoryDaoList = categoryRepository.findAll();			
		return categoryDaoList;
	}

	@Transactional(readOnly=true)
	public List<TocDao> getTocs() {
		List<TocDao> tocDaoList = tocRepository.findAll();			
		return tocDaoList;
	}
	@Transactional(readOnly=true)
	public TocDao getTocById(String tocId){
		return tocRepository.findOne(tocId);
	}
	@Transactional()
	public void linkTocAndSalesRepresentative(String tocId,	String salesRepresentativeId){
		SalesRepresentativeDao salesRepresentative = getSalesRepresentativeById(salesRepresentativeId);
		if(salesRepresentative == null)
			return;
		TocDao toc = getTocById(tocId);
		if(toc == null)
			return;
		salesRepresentative.getTocs().add(toc);
		salesRepresentativeRepository.save(salesRepresentative);// throws java.lang.StackOverflowError
		
	}
	@Transactional()
	public void deleteTocFromSalesRep(String salesRepresentativeId, List<TocDao> tocToRemove){
		SalesRepresentativeDao salesRepresentative = salesRepresentativeRepository.findOne(salesRepresentativeId);
		
		if(salesRepresentative == null)
			return;
		
		List<TocDao> tocs = salesRepresentative.getTocs();
		for (Iterator iterator = tocs.iterator(); iterator.hasNext();) {
			TocDao tocDao = (TocDao) iterator.next();
			if(tocToRemove.contains(tocDao)){
				iterator.remove();
			}
		}
		salesRepresentativeRepository.save(salesRepresentative);
	}
	
	
	@Transactional(readOnly=true)
	public List<SalesRepresentativeDao> getSalesRepresentatives() {
		List<SalesRepresentativeDao> salesRepresentativeDaoList = salesRepresentativeRepository.findAll();
		return salesRepresentativeDaoList;
	}

	 /** 
	 * Parameters: 
	 * int limit - limit per page
	 * int pageIndex - 1-based
	 * int start	
	 * final FilterParameter[] filterParameters - support for only String and Integer numeric 
	 * SortParameter[] sortParameters - only last sort name will apply
	 * 
	 */
	public Page<SalesRepresentativeDao> getPaginatedFilteredSalesRepresentatives(int limit, int pageIndex /*1-based*/, int start
																				,final FilterParameterExtJs6[] filterParameters, SortParameter[] sortParameters){
		Specification<SalesRepresentativeDao> spec = new Specification<SalesRepresentativeDao>() {
			@Override
			public Predicate toPredicate(Root<SalesRepresentativeDao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = buildPredicatesFromExt6Filters(root, cb, filterParameters);
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		Pageable pageable = constructPageSpecification(pageIndex-1, limit, start, sortParameters);
		return salesRepresentativeRepository.findAll(spec, pageable);
	}
	
	@Transactional(readOnly=true)
	public boolean checkSalesRepId(String salesRepId){
		return salesRepresentativeRepository.exists(salesRepId);
	}
	
	@Transactional(readOnly=true)
	public List<QuotaDao> getQuotas() {
		List<QuotaDao> quotaDaoList = quotaRepository.findAll();//SearchCriteriaUtility.findAdminUsers()			
		return quotaDaoList;
	}

	/*
	 * 
	 * TODO - <AP> need to create generic filter
	 * public List<QuotaDao> getPaginatedFilteredQuotas(int limit, int page, int start
	 * 		, final F i l t e r P a r a m e t e r[] filterParameters, SortParameter[] sortParameters){
	 * 
	 * 	Specification<QuotaDao> spec = new Specification<QuotaDao>() {
	 * 		@Override
	 * 		public Predicate toPredicate(Root<QuotaDao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	 * 			Collection<Predicate> predicates = new ArrayList<Predicate>();
	 * 			//Predicate salesRepresentativeIdPredicate = cb.equal(root.get("salesRepresentative").get("salesRepresentativeId"), salesRepresentativeId);
	 * 			//Predicate salesRepresentativeIdPredicate = cb.equal(root.get("pk").get("salesRepresentative").get("salesRepresentativeId"), salesRepresentativeId);
	 * 			//Predicate managerIdPredicate = cb.equal(root.get("manager").<String>get("employeeId"), managerId.trim());
	 * 			//Predicate managerIdPredicate = cb.equal(root.get("pk").get("manager").<String>get("employeeId"), managerId.trim());
	 * 			
	 * 			for (F i l t e r P a r a m e t e r filter : filterParameters) {
	 * 				String propertyName = filter.getField();
	 * 				//Path<String> path = root.get(propertyName).get("manager").<String>get("employeeId");
	 * 				Path<String> path = root.get(propertyName);
	 * 				Predicate filterPredicate = cb.like(path, "%"+filter.getValue()+"%");
	 * 				predicates.add(filterPredicate);
	 * 			}
	 * 			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	 * 		}
	 * 	};
	 * 	List<QuotaDao> quotaDaoList = quotaRepository.findAll(spec);//, pageable
	 * 	return quotaDaoList;
	 * }
	 * 
	 * Parameters: 
	 * int limit - limit per page
	 * int pageIndex - 1-based
	 * int start	
	 * final FilterParameterExtJs6[] filterParameters - support for only String and Integer numeric 
	 * SortParameter[] sortParameters - only last sort name will apply
	 * 
	 */
	public Page<QuotaDao> getPaginatedFilteredQuotas(int limit, int pageIndex /*1-based*/, int start
													,final FilterParameterExtJs6[] filterParameters, SortParameter[] sortParameters){	
		Specification<QuotaDao> spec = new Specification<QuotaDao>() {
			@Override
			public Predicate toPredicate(Root<QuotaDao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = buildPredicatesFromExt6Filters(root, cb, filterParameters);
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		Pageable pageable = constructPageSpecification(pageIndex-1, limit, start, sortParameters);
		Page<QuotaDao> quotaDaoPage = quotaRepository.findAll(spec, pageable);
		return quotaDaoPage;
	}

	public TotalDto getFilteredSummAggregateQuota(final FilterParameterExtJs6[] filterParameters){
		TotalDto aggregateTotalQuota = quotaRepository.sumQuotaWithFilters(filterParameters);
		return aggregateTotalQuota;
	}

	public Page<BudgetDao> getPaginatedFilteredBudgets(int limit, int pageIndex /*1-based*/, int start
													  ,final FilterParameterExtJs6[] filterParameters, SortParameter[] sortParameters){

		Specification<BudgetDao> spec = new Specification<BudgetDao>() {
			@Override
			public Predicate toPredicate(Root<BudgetDao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = buildPredicatesFromExt6Filters(root, cb, filterParameters);
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		Pageable pageable = constructPageSpecification(pageIndex-1, limit, start, sortParameters);
		Page<BudgetDao> budgetPage = budgetRepository.findAll(spec, pageable);
		return budgetPage;
	}

	public TotalDto getFilteredSummAggregateBudget(final FilterParameterExtJs6[] filterParameters){
		TotalDto aggregateTotalBudget = quotaRepository.sumBudgetWithFilters(filterParameters);
		return aggregateTotalBudget;
	}

	
	/* 
	 * PAgeable lookup does NOT work for us here because combo should be populated ahead with all choices to be able select random user
	 */

	public List<EmployeeDao> getFilteredEmployee( final FilterParameterExtJs6[] filterParameters, SortParameter[] sortParameters){
		Specification<EmployeeDao> spec = new Specification<EmployeeDao>() {
			@Override
			public Predicate toPredicate(Root<EmployeeDao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = buildPredicatesFromExt6Filters(root, cb, filterParameters);
				return cb.or(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		
		List<EmployeeDao> employees = null;
		//TODO - <AP> WTF?! why sort change on filterParameters absence 
		if(filterParameters!=null){
			employees = employeeRepository.findAll(spec, new Sort(Sort.Direction.ASC, "name"));
		}else{
			employees = employeeRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
		}
		return employees;
	}
	

	private Collection<Predicate> buildPredicatesFromExt6Filters(Root<?> root, CriteriaBuilder cb, FilterParameterExtJs6[] filterParameters) {
		Collection<Predicate> predicates = new ArrayList<Predicate>();
		if(filterParameters!=null && filterParameters.length>0){
			for (FilterParameterExtJs6 filter : filterParameters) {
				String propertyNames[] = null;
				if(filter.getField() != null){
					propertyNames = filter.getField().split("\\.");
				} else if(filter.getProperty() != null){
					propertyNames = filter.getProperty().split("\\.");
				} else {
					logger.error("!!!!No 'field' or 'property' property defined in filter!!!!");
				}
				String propertyName = propertyNames[0];
				Path<String> path = root.get(propertyName);
				for (int i = 1; i < propertyNames.length; i++) {
					propertyName = propertyNames[i];
					path = path.get(propertyName);
				}
				
				Predicate filterPredicate = null;
				if (filter.getOperator().equals(FilterOperator.LIKE)){
					filterPredicate = cb.like(path, "%"+filter.getValue()+"%");
				}else if (filter.getOperator().equals(FilterOperator.EQ)){
					filterPredicate = cb.equal(path, new Integer(filter.getValue()));	
				}else if (filter.getOperator().equals(FilterOperator.GT)){
					filterPredicate = cb.greaterThan((Path<Comparable>)root.<Comparable>get(propertyName), Integer.valueOf(filter.getValue()));
				}else if (filter.getOperator().equals(FilterOperator.LT)){
					filterPredicate = cb.lessThan((Path<Comparable>)root.<Comparable>get(propertyName), Integer.valueOf(filter.getValue()));
				}else if (filter.getOperator().equals(FilterOperator.IN)){
					List<String> values = filter.getValues();
					Collection<Predicate> orPredicates = new ArrayList<Predicate>();
					for (String value : values) {
						Predicate listPredicate = cb.equal(path, value);
						orPredicates.add(listPredicate);
					}
					Predicate orPredicate = cb.or(orPredicates.toArray(new Predicate[orPredicates.size()]));
					filterPredicate = orPredicate;
				}
				/*else if (filter.getType().equals(FilterType.EXACT)){
					filterPredicate = cb.equal(path, filter.getValue());
				}*/
				if(filterPredicate != null)
					predicates.add(filterPredicate);
			}
		}
		return predicates;
	}
	
	/**
     * Returns a new object which specifies the the wanted result page.
     * @param pageIndex - The 0-based index of the wanted result page
     * @param limit - number of records per page
     * @param sortParameters home made class to unmarshall from json (!!!only last element will sort!!!)
     * @return Pageable to pass to quotaRepository.findAll(spec, pageable);
     */
    private Pageable constructPageSpecification(int pageIndex, int limit, int start, SortParameter[] sortParameters) {
    	Pageable pageSpecification = null;
    	if(sortParameters== null || sortParameters.length == 0){
    		pageSpecification = new PageRequest(pageIndex, limit);
    	}else{
    		//TODO - <AP> This 'sort only on last element in array' should be optimized
    		for (int i = 0; i < sortParameters.length; i++) {
    			SortParameter sortParameter = sortParameters[i];
    			Sort.Direction direction = (sortParameter.getDirection().equalsIgnoreCase(SortParameter.ASC))?Sort.Direction.ASC:Sort.Direction.DESC; 
    			pageSpecification = new PageRequest(pageIndex, limit, new Sort(direction, sortParameter.getProperty()));
			}
    	}
        return pageSpecification;
    }
 
    @Transactional
	public void deleteQuotaById(Long id){
		
		// fix QKPI-51 - Can't delete quota or budget
		QuotaDao quota = quotaRepository.findOne(id);
		SalesRepresentativeDao salesRepresentative = quota.getSalesRepresentative();
		Set<QuotaDao> quotas = salesRepresentative.getQuotas();
		for (Iterator iterator = quotas.iterator(); iterator.hasNext();) {
			QuotaDao quotaInRep = (QuotaDao) iterator.next();
			if(quotaInRep.getId() == quota.getId()){
				//if( quotaInRep.getId().equals(quota.getId())){//quotaInRep.getId().longValue() == quota.getId().longValue()
				iterator.remove();
			}
		}
		//end fix QKPI-51
    	quotaRepository.delete(id);
    }
    
    @Transactional
	public void deleteBudgetById(Long id){
    	budgetRepository.delete(id);
    }
    
	@Transactional(readOnly=true)
	public QuotaDao getQuotaById(Long id) {
		QuotaDao quotaDao = quotaRepository.findOne(id);
		return quotaDao;
	}
	@Transactional
	public void updateQuota(QuotaDao quotaDao){
		quotaRepository.save(quotaDao);
	}
	@Transactional(readOnly=true)
	public boolean isExistQuota(QuotaDto quota){
		logger.debug("dto = "+quota);
		List<QuotaDao> daos = quotaRepository.findAll(SearchCriteriaUtility.findQuotaByHeader(quota));
		if(daos != null && daos.size()>0)
			return true;
		else
			return false;
	}
	@Transactional(readOnly=true)
	public boolean isExistBudget(BudgetDto dto){
		logger.debug("dto = "+dto);
		List<BudgetDao> daos = budgetRepository.findAll(SearchCriteriaUtility.findQuotaByHeader(dto));
		if(daos != null && daos.size()>0)
			return true;
		else
			return false;
	}
	
	@Transactional
	public void updateQuotaDto(QuotaDto dto){
		QuotaDao quota = new QuotaDao();
		SalesRepresentativeDao salesRepresentative = salesRepresentativeRepository.findOne(dto.getSalesRepresentativeId());
		quota.setSalesRepresentative(salesRepresentative);
		CategoryDao category = categoryRepository.findOne(dto.getCategoryId()); 
		quota.setCategory(category);
		quota.setAmountType(dto.getAmountType());
		quota.setYear(dto.getYear());
		quotaRepository.save(quota);
	}
	
	@Transactional
	public void updateBudgetDto(QuotaDto dto){
		BudgetDao budget = new BudgetDao();
		CategoryDao category = categoryRepository.findOne(dto.getCategoryId()); 
		budget.setCategory(category);
		budget.setAmountType(dto.getAmountType());
		budget.setYear(dto.getYear());
		budgetRepository.save(budget);
	}
	@Transactional
	public void updateQuotasValues(QuotaDao quota){
		QuotaDao dao = quotaRepository.findOne(quota.getId());
		dao.setValue1(quota.getValue1());
		dao.setValue2(quota.getValue2());
		dao.setValue3(quota.getValue3());
		dao.setValue4(quota.getValue4());
		dao.setValue5(quota.getValue5());
		dao.setValue6(quota.getValue6());
		dao.setValue7(quota.getValue7());
		dao.setValue8(quota.getValue8());
		dao.setValue9(quota.getValue9());
		dao.setValue10(quota.getValue10());
		dao.setValue11(quota.getValue11());
		dao.setValue12(quota.getValue12());
		quotaRepository.save(dao);
	}

	@Transactional(readOnly=true)
	public BudgetDao getBudgetById(Long id){
		BudgetDao budgetDao = budgetRepository.findOne(id);
		return budgetDao;
	}
	@Transactional
	public void updateBudget(BudgetDao budgetDao){
		budgetRepository.save(budgetDao);
	}
	public void updateBudgetsValues(BudgetDao budget){
		BudgetDao dao = budgetRepository.findOne(budget.getId());
		dao.setValue1(budget.getValue1());
		dao.setValue2(budget.getValue2());
		dao.setValue3(budget.getValue3());
		dao.setValue4(budget.getValue4());
		dao.setValue5(budget.getValue5());
		dao.setValue6(budget.getValue6());
		dao.setValue7(budget.getValue7());
		dao.setValue8(budget.getValue8());
		dao.setValue9(budget.getValue9());
		dao.setValue10(budget.getValue10());
		dao.setValue11(budget.getValue11());
		dao.setValue12(budget.getValue12());
		budgetRepository.save(dao);
	}
	
	@Transactional(readOnly=true)
	public List<BudgetDao> getBudgets(){
		List<BudgetDao> budgetDaoList = budgetRepository.findAll();//SearchCriteriaUtility.findAdminUsers()			
		return budgetDaoList;
	}

	
	
	@Transactional(readOnly=true)
	public List<QuotaUser> getUsers(){
		List<QuotaUser> userDaoList = userRepository.findAll();//SearchCriteriaUtility.findAdminUsers()			
		return userDaoList;
	}

	@Transactional(readOnly=true)
	public List<EmployeeDao> getEmployees() {
		List<EmployeeDao> employeeDaoList = employeeRepository.findAll();
		return employeeDaoList;
	}
	
	@Transactional(readOnly=true)
	public List<EmployeeDao> getEmployeesNotInSalesRep(String salesRepId, String topManagerId, String strQuery){
			SalesRepresentativeDao salesRep = salesRepresentativeRepository.findOne(salesRepId);
			List<String> managers = new ArrayList<String>();
			
			Set<SalesRepEmployeeJoin> joins = salesRep.getSalesRepEmployeeJoins();
			for (SalesRepEmployeeJoin salesRepEmployeeJoin : joins){
				String managerId = salesRepEmployeeJoin.getManager().getEmployeeId();
				if(!GeneralUtil.isEmpty(topManagerId) && topManagerId.equalsIgnoreCase(managerId)){
					continue;
				}
				managers.add(managerId);
			}
			List<EmployeeDao> employeeDaoList = null;
			if(GeneralUtil.isEmpty(managers)){
				//TODO - <AP> debug this: managers list is empty!!!
				logger.warn("TODO debug this: managers list is empty!!!");
				if(!GeneralUtil.isEmpty(strQuery)){
					employeeDaoList = employeeRepository.findAll(SearchCriteriaUtility.findEmployees(strQuery), new Sort(Sort.Direction.ASC, "name"));
				}else{
					employeeDaoList = employeeRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
				}
			}else{//QKPI-69: 
				employeeDaoList = employeeRepository.findAll(SearchCriteriaUtility.findEmployeesNotInSalesRep(managers, strQuery), new Sort(Sort.Direction.ASC, "name"));
			}
			return employeeDaoList;
	}
	
	@Transactional(readOnly=true)
	public List<TocDao> getTocNotInSalesRep(String salesRepId, String strQuery){
		SalesRepresentativeDao salesRep = salesRepresentativeRepository.findOne(salesRepId);
		List<TocDao> alreadyAssignedTocs = salesRep.getTocs();
		List<String> alreadyAssignedTocIds = new ArrayList<String>();
		for (TocDao alreadyAssignedToc : alreadyAssignedTocs) {
			alreadyAssignedTocIds.add(alreadyAssignedToc.getTocId());
		}
		List<TocDao> tocDaoList = null;
		if(GeneralUtil.isEmpty(alreadyAssignedTocIds)){
			//TODO - <AP> debug this: Implement query like in getEmployeesNotInSalesRep(...)
			tocDaoList = tocRepository.findAll();
		}else{
			//QKPI-55: tocDaoList = tocRepository.findAll(SearchCriteriaUtility.findTocNotInSalesRep(alreadyAssignedTocIds));
			tocDaoList = tocRepository.findAll(SearchCriteriaUtility.findTocNotInSalesRepAndTocLookup(alreadyAssignedTocIds,strQuery),new Sort(Sort.Direction.ASC, "tocName"));
		}
		return tocDaoList;
	}
	
	@Transactional(readOnly=true)
	public QuotaUser getUserById(String userId) {
//		Collection<UserDao> userDaoList = userRepository.findAll(SearchCriteriaUtility.findUserById(userId));
//		return CollectionUtility.toList(userDaoList).get(0);
		QuotaUser userDao = userRepository.findOne(SearchCriteriaUtility.findUserById(userId));
		return userDao;
	}
	@Transactional(readOnly=true)
	public SalesRepresentativeDao getSalesRepresentativeById(String id){
		//getOne(id) - return empty instance see http://stackoverflow.com/questions/24482117/when-use-getone-and-findone-methods-spring-data-jpa
		SalesRepresentativeDao dao = salesRepresentativeRepository.findOne(id);
		return  dao;
	}
	@Transactional(readOnly=true)
	public EmployeeDao getEmployeeById(String id){
		//getOne(id) - return empty instance see http://stackoverflow.com/questions/24482117/when-use-getone-and-findone-methods-spring-data-jpa
		EmployeeDao dao = employeeRepository.findOne(id);
		return  dao;
	}
	@Transactional(readOnly=true)
	public ProductLine getProductLineByCode(String id){
		//getOne(id) - return empty instance see http://stackoverflow.com/questions/24482117/when-use-getone-and-findone-methods-spring-data-jpa
		ProductLine dao = productLineRepository.findOne(id);
		return  dao;
	}

	
}
