package org.avp.quota.kpi.web.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.avp.bsd.dto.ProductDto;
import org.avp.bsd.model.BsdUser;
import org.avp.bsd.model.Product;
import org.avp.bsd.model.Store;
import org.avp.bsd.service.BsdService;
import org.avp.bsd.service.DtoFactory;
import org.avp.quota.kpi.util.FilterParameterExtJs6;
import org.avp.quota.kpi.util.SortParameter;
import org.avp.security.service.CustomUserService;
import org.sporcic.extjs.ExtData;
import org.sporcic.extjs.ExtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    	response.add(stores);
    	response.setTotal(stores.size());
        response.setSuccess(true);
        return response;
	}
	
	@RequestMapping(value={"/bsd/stores/test"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findStoresTest(
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
    	
    	response.add(stores);
    	response.setTotal(stores.size());
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
    	response.add(products);
    	response.setTotal(100);
        response.setSuccess(true);
        return response;
	}

	@RequestMapping(value={"/bsd/products/instore"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findProductsInStore(
			@RequestParam(value="storeId", required=true) Long storeId,
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
    	
    	//List<Product> products = bsdService.getProducts();
    	List<ProductDto> productDtos = bsdService.getProductPriceInStore(storeId);//DtoFactory.createProductDtoList(products);
    	response.add(productDtos);
    	response.setTotal(100);
        response.setSuccess(true);
        return response;
	}
	
	
	/*
	 * Remove mapping for 'bsd/products/instore/create' because all new records goes to update
	 * RequestMapping(value={"bsd/products/instore/create"}, method=RequestMethod.POST)
	 * public String createInStoreProducts(@RequestBody ProductInStoreJsonData requestProductInStore){
	 * 
	 */
	@RequestMapping(value={"bsd/products/instore/update"}, method=RequestMethod.POST)
	@ResponseBody
	public String updateInStoreProducts(
						@RequestBody ProductInStoreJsonData requestProductInStore,
						@RequestParam(value="storeId", required=true) Long storeId) throws Exception{
//		for (ProductDto productInStore : requestProductInStore.getProducts()) {
//			logger.debug("updateInStoreProducts: productInStore "+productInStore+"\n");
//		}
		bsdService.updateProductsPricesInStore(storeId, requestProductInStore.getProducts());
		
		return SUCCESS_RESPONSE;
	}
	
	@RequestMapping(value={"bsd/products/instore/delete"}, method=RequestMethod.POST)
	@ResponseBody
	public String deleteInStoreProducts(
			@RequestBody ProductInStoreJsonData requestProductInStore,
			@RequestParam(value="storeId", required=true) Long storeId) throws Exception{
		for (ProductDto productInStore : requestProductInStore.getProducts()) {
			logger.debug("updateInStoreProducts: productInStore "+productInStore+"\n");
		}
		bsdService.deleteProductsFromStore(storeId, requestProductInStore.getProducts());
		return SUCCESS_RESPONSE;
	}
	
	static class ProductInStoreJsonData extends ExtResponse {
		
		//public ProductInStoreJsonData(){/*required by jackson*/}
		
		@JsonProperty("data")
	    private ArrayList<ProductDto> data = new ArrayList<ProductDto>();

		public ArrayList<ProductDto> getProducts() {return data;}
		public void setProducts(ArrayList<ProductDto> quotas) {this.data = quotas;}

		public ArrayList<ProductDto> getData() {return data;}
		public void setData(ArrayList<ProductDto> data) {this.data = data;}
	}

	@RequestMapping(value={"/bsd/products/available"}, method=RequestMethod.GET)
	@ResponseBody
	public ExtResponse findAvailableForStoreProducts(
			@RequestParam(value="storeId", required=true) Long storeId,
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
    	
    	//List<Product> products = bsdService.getProducts();
    	List<ProductDto> productDtos = bsdService.getProductNotInStore(storeId);//DtoFactory.createProductDtoList(products);
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
    	
    	response.add(users);
    	response.setTotal(users.size());
        response.setSuccess(true);
        return response;
	}
	
	@RequestMapping(value={"/bsd/updateuser"}, method=RequestMethod.POST)
	@ResponseBody
	public String updateUser(BsdUser requestUser){
		logger.debug("updateUser(jsonRequest = '"+requestUser+"')");
		BsdUser user = new BsdUser();
		user.setUserId(requestUser.getUserId());
		user.setFirstName(requestUser.getFirstName());
		user.setLastName(requestUser.getLastName());
		userService.save(user);
		return SUCCESS_RESPONSE;
		}
	
	@RequestMapping(value={"/bsd/updateproduct"}, method=RequestMethod.POST)
	@ResponseBody
	public String updateProduct(Product requestProduct){
		logger.debug("updateProduct(jsonRequest = '"+requestProduct+"')");
		Product product = new Product();
		product.setSku(requestProduct.getSku());
		product.setEProductName(requestProduct.getEProductName());
		product.setEProductDescription(requestProduct.getEProductDescription());
		bsdService.save(product);
		return SUCCESS_RESPONSE;
		}

	@RequestMapping(value={"/bsd/updatestore"}, method=RequestMethod.POST)
	@ResponseBody
	public String updateStore(Store requestStore){
		logger.debug("updateStore(jsonRequest = '"+requestStore+"')");
		Store store = new Store();
		store.setStoreName(requestStore.getStoreName());
		store.setStoreDescription(requestStore.getStoreDescription());
		store.setClientName(requestStore.getClientName());
		bsdService.save(store);
		
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
