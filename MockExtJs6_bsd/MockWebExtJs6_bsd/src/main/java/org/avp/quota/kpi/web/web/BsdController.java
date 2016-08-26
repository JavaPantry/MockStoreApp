package org.avp.quota.kpi.web.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.avp.bsd.dto.BsdUserDto;
import org.avp.bsd.dto.OrderHeaderDto;
import org.avp.bsd.dto.ProductDto;
import org.avp.bsd.dto.StoreDto;
import org.avp.bsd.model.BsdUser;
import org.avp.bsd.model.OrderHeader;
import org.avp.bsd.model.Product;
import org.avp.bsd.model.ProductPriceInStore;
import org.avp.bsd.model.Store;
import org.avp.bsd.service.BsdService;
import org.avp.bsd.service.DtoFactory;
import org.avp.quota.kpi.model.dao.BudgetDao;
import org.avp.quota.kpi.model.dao.CategoryDao;
import org.avp.quota.kpi.model.dao.QuotaDao;
import org.avp.quota.kpi.model.dao.SalesRepresentativeDao;
import org.avp.quota.kpi.model.dto.BudgetDto;
import org.avp.quota.kpi.model.dto.CodeDto;
import org.avp.quota.kpi.model.dto.QuotaDto;
import org.avp.quota.kpi.model.dto.SalesRepresentativeDto;
import org.avp.quota.kpi.model.dto.TotalDto;
import org.avp.quota.kpi.service.interfaces.QuotaService;
import org.avp.quota.kpi.util.BeanUtility;
import org.avp.quota.kpi.util.FilterParameterExtJs6;
import org.avp.quota.kpi.util.GeneralUtil;
import org.avp.quota.kpi.util.SortParameter;
import org.avp.quota.kpi.web.web.MaintenanceController.SalesRepTocLink;
import org.avp.quota.kpi.web.web.QuotaController.QuotaJsonData;
import org.avp.security.model.User;
import org.avp.security.service.CustomUserService;
import org.codehaus.jackson.annotate.JsonProperty;
import org.sporcic.extjs.ExtData;
import org.sporcic.extjs.ExtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.Authentication;

@Controller
public class BsdController extends AbstractExtJsController {

	private static Logger logger = Logger.getLogger(BsdController.class);
	
	@Autowired
	protected BsdService bsdService;
	
	@Autowired
	protected CustomUserService userService;
	
	public BsdController() {}
	
	@RequestMapping(value={"/bsd/stores"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findStores(
			@RequestParam(value="limit", required=false) Integer limit, 
			@RequestParam(value="page", required=false) Integer pageIndex, //1-based
			@RequestParam(value="start", required=false) Integer start,
			@RequestParam(value="sort", required=false) String sort,
			@RequestParam(value="filter", required=false) String filter
			) {
        ExtData response = new ExtData();
        logger.debug("/bsd/products(limit="+limit+", page="+pageIndex+", start="+start+", sort="+sort+", filter="+filter+")");
    	FilterParameterExtJs6[] filterParameters = getFiltersFromJson(filter);
    	SortParameter[] sortParameters = getSortFromJson(sort);;
    	
    	//TODO - <AP> do something with that Integer crap
    	if(limit == null) limit=20;
    	if(pageIndex == null) pageIndex=1;
    	if(start == null) start=0;
    	
//		Page<Store> storePage = bsdService.getPaginatedFilteredQuotas(limit, pageIndex, start, filterParameters, sortParameters);
//		List<Store> quotas = storePage.getContent();
    	List<Store> stores = bsdService.getStores();
    	List<StoreDto> storeDtos = DtoFactory.createStoreDtoList(stores);
    	
    	response.add(storeDtos);
    	response.setTotal(storeDtos.size());
        response.setSuccess(true);
        return response;
	}
	@RequestMapping(value={"/bsd/products"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findProducts(
			@RequestParam(value="limit", required=false) Integer limit, 
			@RequestParam(value="page", required=false) Integer pageIndex, //1-based
			@RequestParam(value="start", required=false) Integer start,
			@RequestParam(value="sort", required=false) String sort,
			@RequestParam(value="filter", required=false) String filter
			) {
        ExtData response = new ExtData();
        logger.debug("/bsd/products(limit="+limit+", page="+pageIndex+", start="+start+", sort="+sort+", filter="+filter+")");
    	//FilterParameterExtJs6[] filterParameters = getFiltersFromJson(filter);
    	//SortParameter[] sortParameters = getSortFromJson(sort);;
    	
    	//TODO - <AP> do something with that Integer crap
    	if(limit == null) limit=20;
    	if(pageIndex == null) pageIndex=1;
    	if(start == null) start=0;
    	
//		Page<QuotaDao> quotaDaoPage = quotaService.getPaginatedFilteredQuotas(limit, pageIndex, start, filterParameters, sortParameters);
//		List<QuotaDao> quotas = quotaDaoPage.getContent();
    	
    	List<Product> products = bsdService.getProducts();
    	List<ProductDto> productDtos = DtoFactory.createProductDtoList(products);
    	response.add(productDtos);
    	response.setTotal(100);
        response.setSuccess(true);
        return response;
	}

	@RequestMapping(value={"/bsd/users"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findUsers(
			@RequestParam(value="limit", required=false) Integer limit, 
			@RequestParam(value="page", required=false) Integer pageIndex, //1-based
			@RequestParam(value="start", required=false) Integer start,
			@RequestParam(value="sort", required=false) String sort,
			@RequestParam(value="filter", required=false) String filter
			) {
        ExtData response = new ExtData();
        logger.debug("/users(limit="+limit+", page="+pageIndex+", start="+start+", sort="+sort+", filter="+filter+")");
    	FilterParameterExtJs6[] filterParameters = getFiltersFromJson(filter);
    	SortParameter[] sortParameters = getSortFromJson(sort);;
    	
    	//TODO - <AP> do something with that Integer crap
    	if(limit == null) limit=20;
    	if(pageIndex == null) pageIndex=1;
    	if(start == null) start=0;
    	
//		Page<Store> storePage = bsdService.getPaginatedFilteredQuotas(limit, pageIndex, start, filterParameters, sortParameters);
//		List<Store> quotas = storePage.getContent();
    	List<BsdUser> users = bsdService.getBsdUsers();//userService.getDomainUsers();
    	List<BsdUserDto> bsdUserDtos = DtoFactory.createBsdUserDtoList(users);
    	
    	response.add(bsdUserDtos);
    	response.setTotal(bsdUserDtos.size());
        response.setSuccess(true);
        return response;
	}
	
	
	@RequestMapping(value={"/bsd/updateuser"}, method=RequestMethod.POST)
	@ResponseBody
	public String updateUser(BsdUserDto requestUser){
		logger.debug("updateUser(jsonRequest = '"+requestUser+"')");
		
		return SUCCESS_RESPONSE;
		}
	
	@RequestMapping(value={"/bsd/updateproduct"}, method=RequestMethod.POST)
	@ResponseBody
	public String updateProduct(BsdUserDto requestProduct){
		logger.debug("updateUser(jsonRequest = '"+requestProduct+"')");
		
		return SUCCESS_RESPONSE;
		}

	@RequestMapping(value={"/bsd/updatestore"}, method=RequestMethod.POST)
	@ResponseBody
	public String updateStore(BsdUserDto requestStore){
		logger.debug("updateUser(jsonRequest = '"+requestStore+"')");
		
		return SUCCESS_RESPONSE;
		}
	
	
	@RequestMapping(value={"/bsd/products/dealer"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findAjaxQuotas(
			@RequestParam(value="limit", required=false) Integer limit, 
			@RequestParam(value="page", required=false) Integer pageIndex, //1-based
			@RequestParam(value="start", required=false) Integer start,
			@RequestParam(value="sort", required=false) String sort,
			@RequestParam(value="filter", required=false) String filter
			) {
        ExtData response = new ExtData();
        logger.debug("/bsd/products/dealer(limit="+limit+", page="+pageIndex+", start="+start+", sort="+sort+", filter="+filter+")");
    	FilterParameterExtJs6[] filterParameters = getFiltersFromJson(filter);
    	SortParameter[] sortParameters = getSortFromJson(sort);;
    	
    	//TODO - <AP> do something with that Integer crap
    	if(limit == null) limit=20;
    	if(pageIndex == null) pageIndex=1;
    	if(start == null) start=0;
    	
//		Page<QuotaDao> quotaDaoPage = quotaService.getPaginatedFilteredQuotas(limit, pageIndex, start, filterParameters, sortParameters);
//		List<QuotaDao> quotas = quotaDaoPage.getContent();
    	
    	List<Product> products = bsdService.getProducts();
    	response.add(products);
    	response.setTotal(100);
        response.setSuccess(true);
        return response;
	}
}
