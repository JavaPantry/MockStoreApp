package org.avp.quota.kpi.web.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.avp.bsd.dto.OrderHeaderDto;
import org.avp.bsd.dto.ProductDto;
import org.avp.bsd.model.BsdUser;
import org.avp.bsd.model.OrderHeader;
import org.avp.bsd.model.Product;
import org.avp.bsd.model.ProductPriceInStore;
import org.avp.bsd.model.Store;
import org.avp.bsd.service.BsdService;
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
import org.avp.quota.kpi.util.DtoFactory;
import org.avp.quota.kpi.util.FilterParameterExtJs6;
import org.avp.quota.kpi.util.GeneralUtil;
import org.avp.quota.kpi.util.SortParameter;
import org.avp.quota.kpi.web.web.MaintenanceController.SalesRepTocLink;
import org.avp.security.model.User;
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
public class BsdClientController extends AbstractExtJsController {

	private static Logger logger = Logger.getLogger(BsdClientController.class);
	
	@Autowired
	protected BsdService bsdService;
	
	public BsdClientController() {}

	@RequestMapping(value={"/angular/products"}, method=RequestMethod.GET)
	@ResponseBody
	public List<ProductDto> findProductsAngular(
			HttpSession session,
			@RequestParam(value="limit", required=false) Integer limit, 
			@RequestParam(value="page", required=false) Integer pageIndex, //1-based
			@RequestParam(value="start", required=false) Integer start,
			@RequestParam(value="sort", required=false) String sort,
			@RequestParam(value="filter", required=false) String filter
			) {
		BsdUser user = (BsdUser) session.getAttribute("appuser");
		Store store = user.getStore();
		
		
        logger.debug("/angular/products userName"+user.getUserId());
        logger.debug("/angular/products store"+store.getStoreName());
        
    	//TODO - <AP> do something with that Integer crap
    	if(limit == null) limit=20;
    	if(pageIndex == null) pageIndex=1;
    	if(start == null) start=0;
    	
    	
    	Set<ProductPriceInStore> productsPriceInStore = store.getProductsInStore();
    	List<Product> products = new ArrayList<Product>();//bsdService.getProducts();
    	for (ProductPriceInStore productPriceInStore : productsPriceInStore) {
    		Product product = productPriceInStore.getPk().getProduct();
    		products.add(product);
    		logger.debug("/angular/products read "+product.getSku()+" product");
            
		}    	
    	logger.debug("/angular/products read "+products.size()+" products");
		List<ProductDto> productsDtos = org.avp.bsd.service.DtoFactory.createProductDtoList(products);

//    	response.add(products);
//    	response.setTotal(100);
//      response.setSuccess(true);
        return productsDtos;
	}
	
	
	@RequestMapping(value={"/angular/products/{id}"}, method=RequestMethod.GET)
	@ResponseBody
	public Product getProductAngular(@PathVariable String id ) {
        logger.debug("/angular/products(id="+id+")");
    	Product product = bsdService.getProduct(id);
        return product;
	}
	
	
	@RequestMapping(value={"/angular/orders"}, method=RequestMethod.GET)
	@ResponseBody
	public List<OrderHeaderDto> getOrders(
			HttpSession session,
			@RequestParam(value="limit", required=false) Integer limit, 
			@RequestParam(value="page", required=false) Integer pageIndex, //1-based
			@RequestParam(value="start", required=false) Integer start,
			@RequestParam(value="sort", required=false) String sort,
			@RequestParam(value="filter", required=false) String filter
			) {
		BsdUser user = (BsdUser) session.getAttribute("appuser");
		List<OrderHeader> orders = bsdService.getOrderHeadersByUser(user);
    	List<OrderHeaderDto> orderDtos = org.avp.bsd.service.DtoFactory.createDtoList(orders);
        return orderDtos;
	}	
	
	
}
