package org.avp.quota.kpi.web.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.avp.quota.kpi.model.dao.EmployeeDao;
import org.avp.quota.kpi.model.dao.ProductLine;
import org.avp.quota.kpi.model.dao.SalesRepEmployeeJoin;
import org.avp.quota.kpi.model.dao.SalesRepEmployeePK;
import org.avp.quota.kpi.model.dao.SalesRepresentativeDao;
import org.avp.quota.kpi.model.dao.TocDao;
import org.avp.quota.kpi.model.dto.EmployeeDto;
import org.avp.quota.kpi.model.dto.ProductLineDTO;
import org.avp.quota.kpi.model.dto.SalesRepresentativeDto;
import org.avp.quota.kpi.util.DtoFactory;
import org.avp.quota.kpi.util.FilterOperator;
import org.avp.quota.kpi.util.FilterParameterExtJs6;
import org.avp.quota.kpi.util.FilterType;
import org.avp.quota.kpi.util.GeneralUtil;
import org.avp.quota.kpi.util.SortParameter;
import org.sporcic.extjs.ExtData;
import org.sporcic.extjs.ExtResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonProperty;

@Controller
public class MaintenanceController extends AbstractQuotaKPIController {

	private static Logger logger = Logger.getLogger(MaintenanceController.class);
	
	public MaintenanceController() {}
	
	@RequestMapping(value={"/ajax/checkSalesRepId"}, method=RequestMethod.POST)
	@ResponseBody
	public String checkSalesRepId(
			@RequestParam(value="salesRepId", required=true) String salesRepId
			) {
		logger.debug("checkSalesRepId("+salesRepId+")" );
		boolean salesRepIdExists = quotaService.checkSalesRepId(salesRepId);
		
		if(salesRepIdExists){
			return "{success:false,message:'salesRepId "+salesRepId+" already exists'}";
		}		
		return SUCCESS_RESPONSE;
	}
	
	@RequestMapping(value={"/ajax/salesReps"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findAjaxSalesReps(
			@RequestParam(value="limit", required=false) Integer limit, 
			@RequestParam(value="page", required=false) Integer pageIndex,//1 - based index 
			@RequestParam(value="start", required=false) Integer start,
			@RequestParam(value="sort", required=false) String sort,
			@RequestParam(value="filter", required=false) String filter
			) {
        ExtData response = new ExtData();
        logger.debug("(limit="+limit+", page="+pageIndex+", start="+start+", sort="+sort+", filter="+filter+")");
    	FilterParameterExtJs6[] filterParameters = getFiltersFromJson(filter);
    	
    	//QKPI-80: Trick to resolve 'Unable to resolve attribute [userName] against path'
    	if(filterParameters != null){
	    	for (int i = 0; i < filterParameters.length; i++) {
	    		FilterParameterExtJs6 filterParameter = filterParameters[i];
	    		if(filterParameter.getField().equalsIgnoreCase("userName")){
	    			filterParameter.setField("user.name");
	    		}
			}
    	}
 
    	SortParameter[] sortParameters = getSortFromJson(sort);;

    	//List<SalesRepresentativeDao>  salesRepresentatives= quotaService.getSalesRepresentatives();
    	Page<SalesRepresentativeDao> salesRepresentativeDaoPage = quotaService.getPaginatedFilteredSalesRepresentatives(limit, pageIndex, start, filterParameters, sortParameters); 
    	List<SalesRepresentativeDao>  salesRepresentatives= salesRepresentativeDaoPage.getContent();

    	
		List<SalesRepresentativeDto> dtos = new ArrayList<SalesRepresentativeDto>();
		for (SalesRepresentativeDao salesRepresentative:salesRepresentatives) {
			SalesRepresentativeDto dto = DtoFactory.createDtoFromDao(salesRepresentative);
			dtos.add(dto);
		}
    	
        response.add(dtos);
        response.setTotal(salesRepresentativeDaoPage.getTotalElements());
        response.setSuccess(true);
		return response;
	}
	
	
	@RequestMapping(value={"/ajax/updateSalesRep"}, method=RequestMethod.POST)
	@ResponseBody
	public String updateSalesRep(//@RequestBody SalesRepresentativeDto salesRepresentativeDto
				@RequestParam(value="salesRepresentativeId", required=false) String salesRepresentativeId,
				@RequestParam(value="salesRepresentativeName", required=false) String salesRepresentativeName,
				@RequestParam(value="userId", required=false) String userId,
				@RequestParam(value="allAccess", required=false) Boolean allAccess
			) {
		SalesRepresentativeDao dao = quotaService.getSalesRepresentativeById(salesRepresentativeId);
		if (dao == null){ //create new
			dao = new SalesRepresentativeDao();
			dao.setSalesRepresentativeId(salesRepresentativeId);
		}
		dao.setSalesRepresentativeName(salesRepresentativeName);

		EmployeeDao user = quotaService.getEmployeeById(userId);
		dao.setUser(user);
		if(allAccess == null)
			allAccess = false;
		dao.setAllAccess(allAccess);
		quotaService.saveSalesRepresentativeHeader(dao);
    	return SUCCESS_RESPONSE;
	}	
	/**
	 * Request with sorting and filtering:
	 * 
	 * filter [{"type":"string","value":"cig","field":"salesRepresentativeId"},{"type":"string","value":"tbd","field":"salesRepresentativeName"},{"type":"string","value":"2014","field":"year"}]
	 * limit	35
	 * page	1
	 * sort	[{"property":"salesRepresentativeName","direction":"ASC"}]
	 * start	0 
	 */
	@RequestMapping(value={"/ajax/lookupusers"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse lookupUsers( @RequestParam(value="query", required=false) String query, 
									@RequestParam(value="limit", required=false) Integer limit, 
									@RequestParam(value="page", required=false) Integer page, 
									@RequestParam(value="start", required=false) Integer start){
        ExtData response = new ExtData();
        logger.debug("(limit="+limit+", page="+page+", start="+start+")");
    	SortParameter[] sortParameters = null;//getSortFromJson(sort);
    	FilterParameterExtJs6[] filterParameters = null;
    	FilterParameterExtJs6[] templateFilters = {new FilterParameterExtJs6("employeeId",query,FilterOperator.LIKE),
    											new FilterParameterExtJs6("name",query,FilterOperator.LIKE)};//getFiltersFromJson(filter);
    	/*
    	 * smart-ass trick to avoid construct array through List 
    	 */
    	if(!GeneralUtil.isEmpty(query)){
    		filterParameters = templateFilters;
    	}
        //List<EmployeeDao> employees = new ArrayList<EmployeeDao>(); 
   		//Page<EmployeeDao> employeesPage = quotaService.getPaginatedFilteredEmployee(limit, page, start, filterParameters, sortParameters);
    	List<EmployeeDao> employees = quotaService.getFilteredEmployee(filterParameters, sortParameters);
		List<EmployeeDto> dtos = new ArrayList<EmployeeDto>();
		for (EmployeeDao employee:employees) {
			EmployeeDto dto = DtoFactory.createDtoFromDao(employee);
			dtos.add(dto);
		}
		response.add(dtos);
        response.setSuccess(true);
		return response;
	}

	@RequestMapping(value={"/ajax/users"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findAjaxUsers(
			@RequestParam(value="salesRepId", required=false) String salesRepId,
			@RequestParam(value="topManagerId", required=false) String topManagerId,
			@RequestParam(value="query", required=false) String query
			) {
        ExtData response = new ExtData();
        List<EmployeeDao> employees = new ArrayList<EmployeeDao>(); 
        //if(GeneralUtil.isEmpty(salesRepId)){		
    	//	employees = quotaService.getEmployees();
        //}else{
    		employees = quotaService.getEmployeesNotInSalesRep(salesRepId, topManagerId, query);
        //}
		List<EmployeeDto> dtos = new ArrayList<EmployeeDto>();
		for (EmployeeDao employee:employees) {
			EmployeeDto dto = DtoFactory.createDtoFromDao(employee);
			dtos.add(dto);
		}
		response.add(dtos);
        response.setSuccess(true);
		return response;
	}

	@RequestMapping(value={"/ajax/lookuptoc"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse lookupToc(
			@RequestParam(value="salesRepId", required=false) String salesRepId,
			@RequestParam(value="query", required=false) String query 
			) {
        ExtData response = new ExtData();
    	List<TocDao> tocs = new ArrayList<TocDao>();
        if(GeneralUtil.isEmpty(salesRepId)){		
        	tocs = quotaService.getTocs();
        }else{
        	tocs = quotaService.getTocNotInSalesRep(salesRepId, query);
        }
    	 
		response.add(tocs);
        response.setSuccess(true);
		return response;
	}
/*QKPI-55
	@RequestMapping(value={"/ajax/tocs"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findAjaxTocs(
			@RequestParam(value="salesRepId", required=false) String salesRepId
			) {
        ExtData response = new ExtData();
    	
    	List<TocDao> tocs = new ArrayList<TocDao>();
        if(GeneralUtil.isEmpty(salesRepId)){		
        	tocs = quotaService.getTocs();
        }else{
        	tocs = quotaService.getTocNotInSalesRep(salesRepId);
        }
    	 
		response.add(tocs);
        response.setSuccess(true);
		return response;
	}*/
	
	/*
	 * Returns:
	 * {  
	 * "data":[  
	 * {"employeeId":"C05622","name":"Taizaburo Ted Egawa","status":"A","jobTitle":"* President","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTZ910","deptName":"General Administration","managerId":"C02924","managerLevel":"7"},
	 * {"employeeId":"C11266","name":"Makoto Shibata","status":"T","jobTitle":"* Mgr, Budget \u0026 Product Ctr","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTA110","deptName":"TR Camera Administration","managerId":"C08420","managerLevel":"15","reportPath":"C08420,T02899,C05622","reportPathName":"Masaharu Choki;Justin Lam;Taizaburo Ted Egawa"},
	 * {"employeeId":"C11266","name":"Makoto Shibata","status":"T","jobTitle":"* Mgr, Budget \u0026 Product Ctr","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTA110","deptName":"TR Camera Administration","managerId":"C08420","managerLevel":"15","reportPath":"C08420,T02899,C05622","reportPathName":"Masaharu Choki;Justin Lam;Taizaburo Ted Egawa"},
	 * {"employeeId":"C11266","name":"Makoto Shibata","status":"T","jobTitle":"* Mgr, Budget \u0026 Product Ctr","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTA110","deptName":"TR Camera Administration","managerId":"C08420","managerLevel":"15","reportPath":"C08420,T02899,C05622","reportPathName":"Masaharu Choki;Justin Lam;Taizaburo Ted Egawa"},
	 * {"employeeId":"C11266","name":"Makoto Shibata","status":"T","jobTitle":"* Mgr, Budget \u0026 Product Ctr","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTA110","deptName":"TR Camera Administration","managerId":"C08420","managerLevel":"15","reportPath":"C08420,T02899,C05622","reportPathName":"Masaharu Choki;Justin Lam;Taizaburo Ted Egawa"}
	 * ],
	 * "total":5,"success":true
	 * }
	 */
	@RequestMapping(value={"/ajax/salesRepManagers"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findSalesRepManagers(
			@RequestParam(value="salesRepresentativeId", required=false) String salesRepresentativeId,
			@RequestParam(value="limit", required=false) Integer limit, 
			@RequestParam(value="page", required=false) Integer page, 
			@RequestParam(value="start", required=false) Integer start,
			@RequestParam(value="sort", required=false) String sort,
			@RequestParam(value="filter", required=false) String filter
			) {
        ExtData response = new ExtData();
        logger.debug("(salesRepresentativeId = "+salesRepresentativeId+", limit = "+limit+", page = "+page+", start="+start+", sort = "+sort+", filter = "+filter+")");
    	FilterParameterExtJs6[] filterParameters = getFiltersFromJson(filter);
    	SortParameter[] sortParameters = getSortFromJson(sort);
    	
		List<EmployeeDto> draftsDtos = quotaService.getSalesRepManagerDtosFor(salesRepresentativeId);
		Map<String, EmployeeDto> mapOfEmployeeDto = new HashMap<String, EmployeeDto>();
		for (EmployeeDto employeeDto : draftsDtos) {
			EmployeeDto storedDto = mapOfEmployeeDto.get(employeeDto.getEmployeeId()); 
			if(storedDto == null){
				mapOfEmployeeDto.put(employeeDto.getEmployeeId(),employeeDto);
				storedDto = employeeDto;
			}else{
				String productLinesContentStr = storedDto.getProductLinesContentStr();
				productLinesContentStr += ", "+employeeDto.getProductLinesContentStr();
				storedDto.setProductLinesContentStr(productLinesContentStr);
			}
		}
		List<EmployeeDto> dtos = new ArrayList<EmployeeDto>();
		Set<String> keys = mapOfEmployeeDto.keySet();
		for (String key : keys) {
			EmployeeDto dto = mapOfEmployeeDto.get(key);
			dtos.add(dto);
		}
		/*List<EmployeeDao> managers = quotaService.getSalesRepManagersFor(salesRepresentativeId);
		  for (EmployeeDao manager:managers) {
			EmployeeDto dto = DtoFactory.createDtoFromDao(manager);
			//dto.setManagerId(managerId);
			//dto.setSalesRepresentativeId(salesRepresentativeId);
			dtos.add(dto);
		}*/
		response.add(dtos);
        response.setSuccess(true);
		return response;
	}

	/*
	 * commented in SalesRepresentativeManagersListForm:salesRepresentativeManagersListFormStore
	 * covered by ajax/updateProductLines
	@RequestMapping(value={"ajax/updateSalesRepManagers"}, method=RequestMethod.POST)
	public String updateSalesRepManagers(@RequestBody SalesRepresentativeManagerData salesRepresentativeManagers) {
		logger.debug("updateSalesRepManagers");
		List<EmployeeDto> dtos = salesRepresentativeManagers.getSalesRepManagers();
		for (EmployeeDto salesRepManager : dtos) {
			quotaService.updateSalesRepresentativeManager( salesRepManager);
		}
		return SUCCESS_RESPONSE;
	}*/
	
	@RequestMapping(value={"ajax/updateProductLines"}, method=RequestMethod.POST)
	@ResponseBody
	public String updateProductLines(@RequestBody SalesRepEmployeeJoinData requestSalesRepEmployeeJoinData){
    	//List<ProductLine> productLines = quotaService.getProductLines();
		//logger.debug("productLines:"+productLines);
		logger.debug("productLines:"+requestSalesRepEmployeeJoinData);
		List<ProductLineDTO> dtos = requestSalesRepEmployeeJoinData.getProductLines();
		for (ProductLineDTO productLineDTO : dtos) {
			SalesRepEmployeeJoin salesRepEmployeeJoin = quotaService.getOneSalesRepEmployeeJoinsFor(productLineDTO.getSalesRepresentativeId()
																									,productLineDTO.getManagerId()
																									,productLineDTO.getCode());
			if(productLineDTO.isExists()){
				//check and create if required
				if(salesRepEmployeeJoin == null){
					SalesRepresentativeDao salesRepresentative = quotaService.getSalesRepresentativeById(productLineDTO.getSalesRepresentativeId());
					EmployeeDao employee = quotaService.getEmployeeById(productLineDTO.getManagerId());
					ProductLine line = quotaService.getProductLineByCode(productLineDTO.getCode());
					salesRepEmployeeJoin = new SalesRepEmployeeJoin(new SalesRepEmployeePK(salesRepresentative, employee,line));
					quotaService.save(salesRepEmployeeJoin);
					logger.debug("Created:"+salesRepEmployeeJoin);
				}else{
					String errorMsg = ""+productLineDTO.getSalesRepresentativeId()+", "+productLineDTO.getManagerId()+", "+productLineDTO.getCode();
					return "{success:false,message:'("+errorMsg+") already exists'}";
				}
			}else{//if(productLineDTO.isExists() == false)
				// check and delete TODO - <AP> not good way to trett request as delete command (in case of new manager all product lines will be sent)
				if(salesRepEmployeeJoin != null){
/*					SalesRepresentativeDao salesRepresentative = quotaService.getSalesRepresentativeById(productLineDTO.getSalesRepresentativeId());
					EmployeeDao employee = quotaService.getEmployeeById(productLineDTO.getManagerId());
					ProductLine line = quotaService.getProductLineByCode(productLineDTO.getCode());
					salesRepEmployeeJoin = new SalesRepEmployeeJoin(new SalesRepEmployeePK(salesRepresentative, employee),line);*/
					quotaService.delete(salesRepEmployeeJoin);
					logger.debug("Deleted:"+salesRepEmployeeJoin);
				}else{
					String errorMsg = ""+productLineDTO.getSalesRepresentativeId()+", "+productLineDTO.getManagerId()+", "+productLineDTO.getCode();
					return "{success:false,message:'To be deleted entity ("+errorMsg+") does NOT exist'}";
				}
			}
		}//eof for (ProductLineDTO productLineDTO : dtos)
    	return SUCCESS_RESPONSE;
	}
	static class SalesRepEmployeeJoinData extends ExtResponse {
		@JsonProperty("data")
	    private List<ProductLineDTO> data = new ArrayList<ProductLineDTO>();
		public List<ProductLineDTO> getProductLines() {return data;}
		public void setProductLines(List<ProductLineDTO> quotas) {this.data = quotas;}
	}
	
	/*
	 * Start of delete Sales Representative
	 */
	@RequestMapping(value={"ajax/deleteSalesReps"}, method=RequestMethod.POST)
	@ResponseBody
	public String deleteSalesReps(@RequestBody SalesRepData salesRepresentatives) {
		logger.debug("deleteSalesReps");
		List<SalesRepresentativeDto> dtos = salesRepresentatives.getSalesReps();
		for (SalesRepresentativeDto salesRep : dtos) {
			quotaService.deleteSalesRepresentative(salesRep.getSalesRepresentativeId());
		}
		return SUCCESS_RESPONSE;
	}

	static class SalesRepData extends ExtResponse {
		@JsonProperty("data")
	    private List<SalesRepresentativeDto> data = new ArrayList<SalesRepresentativeDto>();
		public List<SalesRepresentativeDto> getSalesReps() {return data;}
		public void setSalesReps(List<SalesRepresentativeDto> reps) {this.data = reps;}
	}
	//eof delete Sales Representative
	
	/*
	 * Start of delete Sales Representative manager
	 * 
	 * call from SalesRepresentativeManagersListForm.js
	 *		salesRepresentativeManagersListFormStore
	 *			storeId: 'salesRepresentativeManagersListFormStore'
	 *			destroy : 'ajax/deleteSalesRepManagers'  
	 */
	@RequestMapping(value={"ajax/deleteSalesRepManagers"}, method=RequestMethod.POST)
	@ResponseBody
	public String deleteSalesRepManagers(@RequestBody SalesRepresentativeManagerData salesRepresentativeManagers) {
		logger.debug("deleteSalesRepManagers");
		List<EmployeeDto> dtos = salesRepresentativeManagers.getSalesRepManagers();
		for (EmployeeDto dto : dtos) {
			//quotaService.deleteSalesRepresentativeManager(salesRepManager);
			List<SalesRepEmployeeJoin> salesRepEmployeeJoins = quotaService.getSalesRepEmployeeJoinsFor(dto.getSalesRepresentativeId(),dto.getEmployeeId());
			for (SalesRepEmployeeJoin salesRepEmployeeJoin : salesRepEmployeeJoins) {
				//delete(salesRepEmployeeJoin);
				quotaService.delete(salesRepEmployeeJoin);
			}
		}
		return SUCCESS_RESPONSE;
	}
	
	static class SalesRepresentativeManagerData extends ExtResponse {
		@JsonProperty("data")
	    private List<EmployeeDto> data = new ArrayList<EmployeeDto>();
		public List<EmployeeDto> getSalesRepManagers() {return data;}
		public void setSalesRepManagers(List<EmployeeDto> reps) {this.data = reps;}
	}
	//eof delete Sales Representative manager

	@RequestMapping(value={"/ajax/productLines"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findProductLines(
			@RequestParam(value="salesRepresentativeId", required=false) String salesRepresentativeId,
			@RequestParam(value="managerId", required=false) String managerId,
			@RequestParam(value="limit", required=false) Integer limit, 
			@RequestParam(value="page", required=false) Integer page, 
			@RequestParam(value="start", required=false) Integer start,
			@RequestParam(value="sort", required=false) String sort,
			@RequestParam(value="filter", required=false) String filter
			) {
        ExtData response = new ExtData();
        logger.debug("(salesRepresentativeId = "+salesRepresentativeId+", managerId = "+managerId+", limit = "+limit+", page = "+page+", start="+start+", sort = "+sort+", filter = "+filter+")");
    	FilterParameterExtJs6[] filterParameters = getFiltersFromJson(filter);
    	SortParameter[] sortParameters = getSortFromJson(sort);
    	List<ProductLine> productLines = quotaService.getProductLines();
		List<ProductLineDTO> dtos = new ArrayList<ProductLineDTO>();
		List<SalesRepEmployeeJoin> salesRepEmployeeJoins = quotaService.getSalesRepEmployeeJoinsFor(salesRepresentativeId, managerId);
		for (ProductLine productLine:productLines) {
			ProductLineDTO dto = DtoFactory.createDtoFromDao(productLine);
			dto.setExists(false);
			dto.setManagerId(managerId);
			dto.setSalesRepresentativeId(salesRepresentativeId);
			if(doesExistsIn(salesRepEmployeeJoins, dto))
				dto.setExists(true);
			dtos.add(dto);
		}
		response.add(dtos);
        response.setSuccess(true);
		return response;
	}

	private boolean doesExistsIn(List<SalesRepEmployeeJoin> salesRepEmployeeJoins, ProductLineDTO dto){
		for (SalesRepEmployeeJoin salesRepEmployeeJoin : salesRepEmployeeJoins) {
			if(salesRepEmployeeJoin.getProductLine().getCode().equals(dto.getCode()))
				return true;
		}
		return false;
	}
	@RequestMapping(value={"ajax/linkTocAndSalesRepresentative"}, method=RequestMethod.POST)
	@ResponseBody
	public String linkTocAndSalesRepresentative(@RequestBody SalesRepTocJoinData requestSalesRepTocJoinData){
		logger.debug("requestSalesRepTocJoinData:"+requestSalesRepTocJoinData);
		SalesRepTocLink salesRepTocLink = requestSalesRepTocJoinData.getSalesRepTocLink();
/*		SalesRepresentativeDao salesRepresentative = quotaService.getSalesRepresentativeById(salesRepTocLink.getSalesRepresentativeId());
		if(salesRepresentative == null)
			return FAIL_RESPONSE;
		TocDao toc = quotaService.getTocById(salesRepTocLink.getTocId());
		if(toc == null)
			return FAIL_RESPONSE;
		salesRepresentative.getTocs().add(toc);
		quotaService.save(salesRepresentative);// throws java.lang.StackOverflowError
*/
		quotaService.linkTocAndSalesRepresentative(salesRepTocLink.getTocId(), salesRepTocLink.getSalesRepresentativeId());
		return SUCCESS_RESPONSE;
	}
	class SalesRepTocLink{
		String tocId;
		String salesRepresentativeId;
		public String getTocId() {return tocId;}
		public void setTocId(String tocId) {this.tocId = tocId;}
		public String getSalesRepresentativeId() {return salesRepresentativeId;}
		public void setSalesRepresentativeId(String salesRepresentativeId) {this.salesRepresentativeId = salesRepresentativeId;}
	
	}
	static class SalesRepTocJoinData extends ExtResponse {
		@JsonProperty("data")
	    private SalesRepTocLink data;
		public SalesRepTocLink getSalesRepTocLink() {return data;}
		public void setSalesRepTocLink(SalesRepTocLink link) {this.data = link;}
	}
	
	/*static class SalesRepTocJoinData extends ExtResponse {
		@JsonProperty("data")
	    private List<SalesRepTocLink> data = new ArrayList<SalesRepTocLink>();
		public List<SalesRepTocLink> getProductLines() {return data;}
		public void setProductLines(List<SalesRepTocLink> quotas) {this.data = quotas;}
	}*/

	/*
	 * Call from MaintenanceController:openTocListForm:function(userId,salesRepRecord)
	 * or	from MaintenanceController:saveTocDetailForm:function(button)
	 */
	@RequestMapping(value={"/ajax/salesRepTocs"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findSalesRepTocs(
			@RequestParam(value="salesRepresentativeId", required=false) String salesRepresentativeId,
			@RequestParam(value="limit", required=false) Integer limit, 
			@RequestParam(value="page", required=false) Integer page, 
			@RequestParam(value="start", required=false) Integer start,
			@RequestParam(value="sort", required=false) String sort,
			@RequestParam(value="filter", required=false) String filter
			) {
        ExtData response = new ExtData();
        logger.debug("(salesRepresentativeId = "+salesRepresentativeId+", limit = "+limit+", page = "+page+", start="+start+", sort = "+sort+", filter = "+filter+")");
    	FilterParameterExtJs6[] filterParameters = getFiltersFromJson(filter);
    	SortParameter[] sortParameters = getSortFromJson(sort);
    	
		SalesRepresentativeDao salesRepresentative = quotaService.getSalesRepresentativeById(salesRepresentativeId);
		List<TocDao> tocs = salesRepresentative.getTocs();
		response.add(tocs);
        response.setSuccess(true);
		return response;
	}
	
	@RequestMapping(value={"ajax/deleteSalesRepTocs"}, method=RequestMethod.POST)
	@ResponseBody
	public String deleteSalesRepTocs(@RequestBody TocData tocs,
									@RequestParam(value="salesRepresentativeId", required=false) String salesRepresentativeId) {
		
		logger.debug("deleteSalesRepTocs");
		//SalesRepresentativeDao salesRep = 
		List<TocDao> dtos = tocs.getTocs();
		quotaService.deleteTocFromSalesRep(salesRepresentativeId, dtos);
		return SUCCESS_RESPONSE;
	}
	static class TocData extends ExtResponse {
		@JsonProperty("data")
	    private List<TocDao> data;
		public List<TocDao> getTocs() {return data;}
		public void setTocs(List<TocDao> tocs) {this.data = tocs;}
	}

}
