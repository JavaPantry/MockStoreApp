/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package thymeleafexamples.stsm.web.controller;

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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import thymeleafexamples.stsm.business.entities.*;
import thymeleafexamples.stsm.business.services.SeedStarterService;
import thymeleafexamples.stsm.business.services.VarietyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
public class BsdStoreController {

    @Autowired
    private VarietyService varietyService;

    @Autowired
    private SeedStarterService seedStarterService;

    @Autowired
    protected BsdService bsdService;

    @Autowired
    protected CustomUserService userService;

    public BsdStoreController() {
        super();
    }

    @ModelAttribute("allTypes")
    public List<Type> populateTypes() {
        return Arrays.asList(Type.ALL);
    }
    
    @ModelAttribute("allFeatures")
    public List<Feature> populateFeatures() {
        return Arrays.asList(Feature.ALL);
    }
    
    @ModelAttribute("allVarieties")
    public List<Variety> populateVarieties() {
        return this.varietyService.findAll();
    }
    
    @ModelAttribute("allSeedStarters")
    public List<SeedStarter> populateSeedStarters() {
        return this.seedStarterService.findAll();
    }

    // at RequestMapping(value={"/","/bsdStoreOnlineHome"}, method= RequestMethod.GET)
    @RequestMapping({"/bsdStoreOnlineHome"})
    public String showRepairStep1(Authentication authentication, HttpSession session, ModelMap model, final SeedStarter seedStarter) {
        storePrincipal(model, session, authentication);

        seedStarter.setDatePlanted(Calendar.getInstance().getTime());

        BsdUser user = (BsdUser) session.getAttribute("appuser");
        Store userStore = user.getStore();
        //dirty trick
        Store store = bsdService.findStoreById(userStore.getId());

        Set<ProductPriceInStore> productsPriceInStore = store.getProductsInStore();
        List<Product> products = new ArrayList<Product>();//bsdService.getProducts();
        for (ProductPriceInStore productPriceInStore : productsPriceInStore) {
            Product product = productPriceInStore.getPk().getProduct();
            products.add(product);
            //logger.debug("/tl/products read "+product.getSku()+" product");

        }
        //logger.debug("/tl/products read "+products.size()+" products");
        List<ProductDto> productsDtos = org.avp.bsd.service.DtoFactory.createProductDtoList(products);

        model.addAttribute("products", productsDtos);
        return "bsd/bsdStoreOnlineHome";
    }

    /*@RequestMapping(value="/bsdStoreOnlineHome", params={"save"})
    public String saveSeedstarter(final SeedStarter seedStarter, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "bsd/bsdStoreOnlineHome";
        }
        this.seedStarterService.add(seedStarter);
        model.clear();
        return "redirect:/bsdStoreOnlineHome";
    }*/

    /*@RequestMapping(value="/bsdStoreOnlineHome", params={"addRow"})
    public String addRow(final SeedStarter seedStarter, final BindingResult bindingResult) {
        seedStarter.getRows().add(new Row());
        return "bsd/bsdStoreOnlineHome";
    }*/

    /*@RequestMapping(value="/bsdStoreOnlineHome", params={"removeRow"})
    public String removeRow(final SeedStarter seedStarter, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        seedStarter.getRows().remove(rowId.intValue());
        return "bsd/bsdStoreOnlineHome";
    }*/

    private void storePrincipal(ModelMap model, HttpSession session, Authentication authentication) {
        String userName = authentication.getName();
        User user = (User) userService.getDomainUser(userName);
        session.setAttribute("appuser", user);
        model.addAttribute("userFullName", user.getUserId());
    }
}
