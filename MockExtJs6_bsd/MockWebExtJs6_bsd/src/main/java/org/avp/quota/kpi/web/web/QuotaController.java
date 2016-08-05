package org.avp.quota.kpi.web.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.avp.quota.kpi.model.dao.BudgetDao;
import org.avp.quota.kpi.model.dao.CategoryDao;
import org.avp.quota.kpi.model.dao.QuotaDao;
import org.avp.quota.kpi.model.dao.SalesRepresentativeDao;
import org.avp.quota.kpi.model.dto.BudgetDto;
import org.avp.quota.kpi.model.dto.CodeDto;
import org.avp.quota.kpi.model.dto.QuotaDto;
import org.avp.quota.kpi.model.dto.SalesRepresentativeDto;
import org.avp.quota.kpi.model.dto.TotalDto;
import org.avp.quota.kpi.util.BeanUtility;
import org.avp.quota.kpi.util.DtoFactory;
import org.avp.quota.kpi.util.FilterParameterExtJs6;
import org.avp.quota.kpi.util.GeneralUtil;
import org.avp.quota.kpi.util.SortParameter;
import org.avp.quota.kpi.web.web.MaintenanceController.SalesRepTocLink;
import org.codehaus.jackson.annotate.JsonProperty;
import org.sporcic.extjs.ExtData;
import org.sporcic.extjs.ExtResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.type.TypeFactory;

@Controller
public class QuotaController extends AbstractQuotaKPIController {

	private static Logger logger = Logger.getLogger(QuotaController.class);
	
	public QuotaController() {}
	
	/**
	 * see app/store/accounting/KpiChart.js
	 * 
	 * data: [
	 * { country: 'USA',     agr: 188217, ind: 2995787, ser: 12500746},
	 * { country: 'China',   agr: 918138, ind: 3611671, ser: 3792665},
	 * { country: 'Japan',   agr: 71568,  ind: 1640091, ser: 4258274},
	 * { country: 'UK',      agr: 17084,  ind: 512506,  ser: 1910915},
	 * { country: 'Russia',  agr: 78856,  ind: 727906,  ser: 1215198}
	 * ]
	 * @return
	 */
	@RequestMapping(value={"/ajax/quotas/chart"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse getChartData(){
        ExtData response = new ExtData();
    	
        List<ChartDataDto> dtos = new ArrayList<ChartDataDto>();
        dtos.add(new ChartDataDto("USA", 10l, 20l, 30l));
        dtos.add(new ChartDataDto("China", 15l, 40l, 15l));
        dtos.add(new ChartDataDto("Japan", 5l, 10l, 10l));
        dtos.add(new ChartDataDto("UK",    7l, 10l,  10l));
        dtos.add(new ChartDataDto("Russia", 5l, 5l,  5l));
        response.add(dtos);
        response.setSuccess(true);
		return response;
	}

	class ChartDataDto{
		private String country;
		private long arg;
		private long ind;
		private long ser;
		
		
		public ChartDataDto(String country, long arg, long ind, long ser) {
			super();
			this.country = country;
			this.arg = arg;
			this.ind = ind;
			this.ser = ser;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public long getArg() {
			return arg;
		}
		public void setArg(long arg) {
			this.arg = arg;
		}
		public long getInd() {
			return ind;
		}
		public void setInd(long ind) {
			this.ind = ind;
		}
		public long getSer() {
			return ser;
		}
		public void setSer(long ser) {
			this.ser = ser;
		}
		
	}
	
	
	@RequestMapping(value={"/ajax/salesRepCodes"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findAjaxSalesRepsCodes(){
        ExtData response = new ExtData();
    	List<SalesRepresentativeDao>  salesRepresentatives = quotaService.getSalesRepresentatives();
    	
		List<CodeDto> dtos = new ArrayList<CodeDto>();
		for (SalesRepresentativeDao salesRepresentative: salesRepresentatives) {
			CodeDto dto = DtoFactory.createCodeDtoFromDao(salesRepresentative);
			dtos.add(dto);
		}
        response.add(dtos);//
        response.setSuccess(true);
		return response;
	}

	@RequestMapping(value={"/ajax/categoryRepCodes"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findCategoriesCodes(){
        ExtData response = new ExtData();
    	List<CategoryDao>  categories = quotaService.getCategories();
    	
		List<CodeDto> dtos = new ArrayList<CodeDto>();
		for (CategoryDao category: categories) {
			CodeDto dto = DtoFactory.createCodeDtoFromDao(category);
			dtos.add(dto);
		}
        response.add(dtos);//
        response.setSuccess(true);
		return response;
	}
	
	/*
	 * ExtJs sends request
	 *
	 *  writer: {
	 *      type: 'json',
	 *      root: 'data',
	 *      writeAllFields: true,
	 *      encode: false
	 *      },
	 * [  
	 * {"id":1,"categoryCode":"","value1":666,"value2":0,"value3":0,"value4":0,"value5":0,"value6":4,"value7":0,"value8":0,"value9":0,"value10":999,"value11":0,"value12":0},
	 * {"id":2,"categoryCode":"","value1":666,"value2":0,"value3":0,"value4":0,"value5":0,"value6":666,"value7":0,"value8":0,"value9":0,"value10":999,"value11":0,"value12":0},
	 * {"id":3,"categoryCode":"","value1":666,"value2":0,"value3":0,"value4":0,"value5":0,"value6":0,"value7":2,"value8":0,"value9":0,"value10":999,"value11":0,"value12":0}
	 * ]
	 * 
	 * if writer define root property
	 *  writer: {
	 *      type: 'json',
	 *      root: 'data',
	 *      writeAllFields: true,
	 *      encode: false
	 *      },
	 * then json look like:
	 * {  
	 * "data":[  
	 * {"id":4, "categoryCode":"", "value1":666, "value2":0, "value3":0, "value4":0, "value5":0, "value6":1, "value7":0, "value8":0, "value9":0, "value10":9990, "value11":0, "value12":0},
	 * {"id":5, "categoryCode":"", "value1":666, "value2":0, "value3":0, "value4":0, "value5":0, "value6":0, "value7":3, "value8":0, "value9":0, "value10":9990, "value11":0, "value12":0}
	 * ]
	 * }
	 * 
	 * by deafault store send array if grid have more than one modified record otherwise it sends only json object
	 * http://stackoverflow.com/questions/11586243/extjs-4-1-making-json-array-for-store-sync
	 * if allowSingle set to false (writer: { allowSingle: false}) store will send array always
	 * {"data":[{"id":4,"categoryCode":"","value1":666,"value2":0,"value3":0,"value4":0,"value5":0,
	 * "value6":1,"value7":0,"value8":0,"value9":0,"value10":9990,"value11":0,"value12":0}]}
	 * 
	 * 
	 * if method declared as
	 * public String updateQuotas(@RequestBody ExtData jsonRequest){
	 * ExtData.data after json2Object will contain ListArray<LinkedTreeMap>
	 * [{id=4.0, categoryCode=, value1=666.0, value2=0.0, value3=0.0, value4=0.0, value5=1.0, value6=0.0, value7=0.0, value8=0.0, value9=0.0, value10=9990.0, value11=0.0, value12=0.0}]
	 * 
	 * if I declare inner class
	 * 	class QuotaJsonData extends ExtResponse {
	 *  @ JsonProperty("data")
	 *  private List<QuotaDao> data = new ArrayList<QuotaDao>();
	 * }
	 * and method as
	 * public String updateQuotas(@RequestBody QuotaJsonData jsonRequest){
	 * ExtData.data after json2Object will contain ListArray<QuotaDao>
	 * with QuotaDao [id=3, alt_salesrep_cd=null, category=null, amountType=0, year=0, value1=666, value2=0, value3=0, value4=0, value5=1, value6=0, value7=0, value8=0, value9=0, value10=999, value11=0, value12=0]
	 * 
	 */
	@RequestMapping(value={"/ajax/quotas/update"}, method=RequestMethod.POST)
	@ResponseBody
	public String updateQuotas(@RequestBody QuotaJsonData requestQuotas){
		logger.debug("updateQuotas(jsonRequest = '"+requestQuotas+"')");
		for (QuotaDao quota : requestQuotas.getQuotas()) {
			QuotaDao quotaDao = quotaService.getQuotaById(quota.getId());
			//BeanUtility.nullSafeMergeTo(quota, quotaDao, new String[]{"+salesRepresentative","category","amountType","year"});
			quotaDao.setValue1(quota.getValue1());
			quotaDao.setValue2(quota.getValue2());
			quotaDao.setValue3(quota.getValue3());
			quotaDao.setValue4(quota.getValue4());
			quotaDao.setValue5(quota.getValue5());
			quotaDao.setValue6(quota.getValue6());
			quotaDao.setValue7(quota.getValue7());
			quotaDao.setValue8(quota.getValue8());
			quotaDao.setValue9(quota.getValue9());
			quotaDao.setValue10(quota.getValue10());
			quotaDao.setValue11(quota.getValue11());
			quotaDao.setValue12(quota.getValue12());
			quotaService.updateQuotasValues(quotaDao);
		}
		return SUCCESS_RESPONSE;
		}
	
	@RequestMapping(value={"/ajax/quotas/create"}, method=RequestMethod.POST)
	@ResponseBody
	public String createQuotas(@RequestBody QuotaJsonDto requestQuotas){
		logger.debug("createQuotas(jsonRequest = '"+requestQuotas+"')");
		for (QuotaDto dto : requestQuotas.getQuotas()) {
			quotaService.updateQuotaDto(dto);
		}
		return SUCCESS_RESPONSE;
	}	

	
	@RequestMapping(value={"ajax/validateQuota"}, method=RequestMethod.POST)
	@ResponseBody
	public String validateQuota(@RequestBody HeaderData requestQuota
			,@RequestParam(value="type", required=false) String type){
		logger.debug("validateQuota(jsonRequest = '"+requestQuota+"', type="+type+")");
		boolean exists = false;
		
		if(!GeneralUtil.isEmpty(type) && type.equalsIgnoreCase("Budget")){
			BudgetDto dto = requestQuota.getQuotaDto();
			exists = quotaService.isExistBudget(dto);
		}else{
			QuotaDto dto = requestQuota.getQuotaDto();
			exists = quotaService.isExistQuota(dto);
		}
		
		if(exists)
			return FAIL_RESPONSE;
		else
			return SUCCESS_RESPONSE;
	}		
	@RequestMapping(value={"/ajax/quotas/delete"}, method=RequestMethod.POST)
	@ResponseBody
	public String deleteQuotas(@RequestBody QuotaJsonDto requestQuotas){
		logger.debug("deleteQuotas(jsonRequest = '"+requestQuotas+"')");
		for (QuotaDto dto : requestQuotas.getQuotas()) {
			quotaService.deleteQuotaById(dto.getId());
		}
		return SUCCESS_RESPONSE;
	}	
	
	@RequestMapping(value={"/ajax/budgets/delete"}, method=RequestMethod.POST)
	@ResponseBody
	public String deleteBudgets(@RequestBody QuotaJsonDto requestQuotas){
		logger.debug("deleteBudgets(jsonRequest = '"+requestQuotas+"')");
		for (QuotaDto dto : requestQuotas.getQuotas()) {
			quotaService.deleteBudgetById(dto.getId());
		}
		return SUCCESS_RESPONSE;
	}	
	
	@RequestMapping(value={"/ajax/budgets/create"}, method=RequestMethod.POST)
	@ResponseBody
	public String createBudgets(@RequestBody QuotaJsonDto requestQuotas){
			logger.debug("createQuotas(jsonRequest = '"+requestQuotas+"')");
			for (QuotaDto dto : requestQuotas.getQuotas()) {
				quotaService.updateBudgetDto(dto);
			}
			return SUCCESS_RESPONSE;
		}	
	
	@RequestMapping(value={"/ajax/budgets/update"}, method=RequestMethod.POST)
	@ResponseBody
	public String updateBudgets(@RequestBody BudgetJsonData requestBudgets){
		logger.debug("updateBudgets(jsonRequest = '"+requestBudgets+"')");
		for (BudgetDao budget : requestBudgets.getBudgets()) {
			BudgetDao budgetDao = quotaService.getBudgetById(budget.getId());
			//BeanUtility.nullSafeMergeTo(budget, budgetDao, new String[]{"+salesRepresentative","category","amountType","year"});
			budgetDao.setValue1(budget.getValue1());
			budgetDao.setValue2(budget.getValue2());
			budgetDao.setValue3(budget.getValue3());
			budgetDao.setValue4(budget.getValue4());
			budgetDao.setValue5(budget.getValue5());
			budgetDao.setValue6(budget.getValue6());
			budgetDao.setValue7(budget.getValue7());
			budgetDao.setValue8(budget.getValue8());
			budgetDao.setValue9(budget.getValue9());
			budgetDao.setValue10(budget.getValue10());
			budgetDao.setValue11(budget.getValue11());
			budgetDao.setValue12(budget.getValue12());
			//quotaService.updateBudget(budgetDao);
			quotaService.updateBudgetsValues(budgetDao);
		}
		return SUCCESS_RESPONSE;
		}	
	class QuotaJsonDto extends ExtResponse {
		@JsonProperty("data")
	    private List<QuotaDto> data = new ArrayList<QuotaDto>();
		public List<QuotaDto> getQuotas() {return data;}
		public void setQuotas(List<QuotaDto> quotas) {this.data = quotas;}
	}
	
	class QuotaJsonData extends ExtResponse {
		@JsonProperty("data")
	    private List<QuotaDao> data = new ArrayList<QuotaDao>();
		public List<QuotaDao> getQuotas() {return data;}
		public void setQuotas(List<QuotaDao> quotas) {this.data = quotas;}
	}
	
	class BudgetJsonData extends ExtResponse {
		@JsonProperty("data")
	    private List<BudgetDao> data = new ArrayList<BudgetDao>();
		public List<BudgetDao> getBudgets() {return data;}
		public void setBudgets(List<BudgetDao> budgets) {this.data = budgets;}
	}
	
	class HeaderData extends ExtResponse {
		@JsonProperty("data")
	    private QuotaDto data;
		public QuotaDto getQuotaDto() {return data;}
		public void setQuotaDto(QuotaDto link) {this.data = link;}
	}
	
	
	/* 
	 * Request with sorting and filtering:
	 * 
	 * filter [{"type":"string","value":"cig","field":"salesRepresentativeId"},{"type":"string","value":"tbd","field":"salesRepresentativeName"},{"type":"string","value":"2014","field":"year"}]
	 * limit	35
	 * page	1
	 * sort	[{"property":"salesRepresentativeName","direction":"ASC"}]
	 * start	0 
	*/
	@RequestMapping(value={"/ajax/quotas"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findAjaxQuotas(
			@RequestParam(value="limit", required=false) Integer limit, 
			@RequestParam(value="page", required=false) Integer pageIndex, //1-based
			@RequestParam(value="start", required=false) Integer start,
			@RequestParam(value="sort", required=false) String sort,
			@RequestParam(value="filter", required=false) String filter,
			@RequestParam(value="summary", required=false) String summary
			) {
        ExtData response = new ExtData();
        logger.debug("/ajax/quotas(limit="+limit+", page="+pageIndex+", start="+start+", sort="+sort+", filter="+filter+")");
    	FilterParameterExtJs6[] filterParameters = getFiltersFromJson(filter);
    	SortParameter[] sortParameters = getSortFromJson(sort);;
    	
    	//TODO - <AP> do something with that Integer crap
    	if(limit == null) limit=20;
    	if(pageIndex == null) pageIndex=1;
    	if(start == null) start=0;
    	
		Page<QuotaDao> quotaDaoPage = quotaService.getPaginatedFilteredQuotas(limit, pageIndex, start, filterParameters, sortParameters);
		List<QuotaDao> quotas = quotaDaoPage.getContent();
    	List<QuotaDto> dtos = DtoFactory.createQuotaDtoList(quotas);
    	response.add(dtos);
    	response.setTotal(quotaDaoPage.getTotalElements());
        response.setSuccess(true);
        
        //TODO - <AP> Meta 'grid.feature.RemoteSummary'
        if(!GeneralUtil.isEmpty(summary) && summary.equalsIgnoreCase("true")){
        	TotalDto totalDto = quotaService.getFilteredSummAggregateQuota(filterParameters);
        	//totalDto.updateTotal();
	        response.setSummary(totalDto);
        }
        return response;
	}
	
	@RequestMapping(value={"/ajax/budgets"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findAjaxBudgets(
			@RequestParam(value="limit", required=false) Integer limit, 
			@RequestParam(value="page", required=false) Integer pageIndex, //1-based
			@RequestParam(value="start", required=false) Integer start,
			@RequestParam(value="sort", required=false) String sort,
			@RequestParam(value="filter", required=false) String filter,
			@RequestParam(value="summary", required=false) String summary
			) {
        ExtData response = new ExtData();
        logger.debug("/ajax/budgets(limit="+limit+", page="+pageIndex+", start="+start+", sort="+sort+", filter="+filter+")");
    	FilterParameterExtJs6[] filterParameters = getFiltersFromJson(filter);
    	SortParameter[] sortParameters = getSortFromJson(sort);;
    	
    	Page<BudgetDao> budgetPage = quotaService.getPaginatedFilteredBudgets(limit, pageIndex, start, filterParameters, sortParameters);
		List<BudgetDao> budgets = budgetPage.getContent();
    	List<BudgetDto> dtos = DtoFactory.createBudgetDtoList(budgets);
    	response.add(dtos);
    	response.setTotal(budgetPage.getTotalElements());
        response.setSuccess(true);
        //TODO - <AP> Meta 'grid.feature.RemoteSummary'
        if(!GeneralUtil.isEmpty(summary) && summary.equalsIgnoreCase("true")){
        	TotalDto totalDto = quotaService.getFilteredSummAggregateBudget(filterParameters);
        	//totalDto.updateTotal();
	        response.setSummary(totalDto);
        }
		return response;
	}

	
}
