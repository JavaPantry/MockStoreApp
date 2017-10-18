package org.avp.quota.kpi.web.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.avp.bsd.dto.ProductDto;
import org.avp.bsd.model.BsdUser;
import org.avp.bsd.model.Product;
import org.avp.bsd.model.ProductPriceInStore;
import org.avp.bsd.model.Store;
import org.avp.bsd.service.BsdService;
import org.avp.security.model.User;
import org.avp.security.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class IndexController extends AbstractQuotaKPIController {

	private static Logger logger = Logger.getLogger(IndexController.class);
	
	@Autowired
	protected CustomUserService userService;

	public IndexController() {}
	
	@RequestMapping(value={"/exthome"}, method=RequestMethod.GET)
	public String showExtHome(Authentication authentication, ModelMap model, HttpSession session, HttpServletRequest request) {
		storePrincipal(model, session, authentication);
		//logger.debug(userDto.getFullName());
		//model.addAttribute("userFullName", userDto.getFullName());
		return "exthome";
	}


	@Autowired
	protected BsdService bsdService;

	@RequestMapping(value={"/clientStore"}, method=RequestMethod.GET)
	public String showClientStore(Authentication authentication, ModelMap model, HttpSession session, HttpServletRequest request) {
		storePrincipal(model, session, authentication);
		//logger.debug(userDto.getFullName());
		model.addAttribute("userFullName", userDto.getFullName());


		BsdUser user = (BsdUser) session.getAttribute("appuser");
		Store userStore = user.getStore();

		//dirty trick
		Store store = bsdService.findStoreById(userStore.getId());

		logger.debug("/angular/products userName"+user.getUserId());
		logger.debug("/angular/products store"+store.getStoreName());

		//TODO - <AP> do something with that Integer crap
		/*Integer limit,pageIndex,start;
		if(limit == null) limit=20;
		if(pageIndex == null) pageIndex=1;
		if(start == null) start=0;*/


		Set<ProductPriceInStore> productsPriceInStore = store.getProductsInStore();
		List<Product> products = new ArrayList<Product>();//bsdService.getProducts();
		for (ProductPriceInStore productPriceInStore : productsPriceInStore) {
			Product product = productPriceInStore.getPk().getProduct();
			products.add(product);
			logger.debug("/angular/products read "+product.getSku()+" product");

		}
		logger.debug("/angular/products read "+products.size()+" products");
		List<ProductDto> productsDtos = org.avp.bsd.service.DtoFactory.createProductDtoList(products);

		model.put("products",productsDtos);
		return "clientStore";
	}
	
	private void storePrincipal(ModelMap model, HttpSession session, Authentication authentication) {
        String userName = authentication.getName();
        User user = (User) userService.getDomainUser(userName);
		session.setAttribute("appuser", user);
		model.addAttribute("userFullName", user.getUserId());
	}
	
}
