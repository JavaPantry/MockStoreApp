package thymeleafexamples.stsm.web.controller;

import org.apache.log4j.Logger;
import org.avp.bsd.dto.OrderHeaderDto;
import org.avp.bsd.dto.ProductDto;
import org.avp.bsd.model.*;
import org.avp.bsd.service.BsdService;
import org.avp.quota.kpi.web.web.AbstractExtJsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class BsdTlClientController extends AbstractExtJsController {

	private static Logger logger = Logger.getLogger(BsdTlClientController.class);

	@Autowired
	protected BsdService bsdService;

	public BsdTlClientController() {}

	@RequestMapping(value={"/tl/products"}, method=RequestMethod.GET)
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
		Store userStore = user.getStore();
		
		//dirty trick
		Store store = bsdService.findStoreById(userStore.getId());
		
        logger.debug("/tl/products userName"+user.getUserId());
        logger.debug("/tl/products store"+store.getStoreName());
        
    	//TODO - <AP> do something with that Integer crap
    	if(limit == null) limit=20;
    	if(pageIndex == null) pageIndex=1;
    	if(start == null) start=0;
    	
    	
    	Set<ProductPriceInStore> productsPriceInStore = store.getProductsInStore();
    	List<Product> products = new ArrayList<Product>();//bsdService.getProducts();
    	for (ProductPriceInStore productPriceInStore : productsPriceInStore) {
    		Product product = productPriceInStore.getPk().getProduct();
    		products.add(product);
    		logger.debug("/tl/products read "+product.getSku()+" product");
            
		}    	
    	logger.debug("/tl/products read "+products.size()+" products");
		List<ProductDto> productsDtos = org.avp.bsd.service.DtoFactory.createProductDtoList(products);

//    	response.add(products);
//    	response.setTotal(100);
//      response.setSuccess(true);
        return productsDtos;
	}
	
	
	@RequestMapping(value={"/tl/products/{id}"}, method=RequestMethod.GET)
	@ResponseBody
	public Product getProductAngular(@PathVariable String id ) {
        logger.debug("/tl/products(id="+id+")");
    	Product product = bsdService.getProduct(id);
        return product;
	}
	
	
	@RequestMapping(value={"/tl/orders"}, method=RequestMethod.GET)
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
