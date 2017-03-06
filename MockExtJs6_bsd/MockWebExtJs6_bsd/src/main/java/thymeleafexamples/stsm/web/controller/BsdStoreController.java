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

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


@Controller
public class BsdStoreController {

    @Autowired
    private VarietyService varietyService;

    @Autowired
    private SeedStarterService seedStarterService;

    public BsdStoreController() {
        super();
    }


    private Integer wizardStep;// = new Integer(1);

    // rely on this annotation cause http://stackoverflow.com/questions/42627745/thymeleaf-render-model-attribute-prior-it-set-in-controller-method
    // at ModelAttribute("wizardStep")
    //public Integer wizardStep(){
    //    return wizardStep;
    //}

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
    public String showRepairStep1(Model model, final SeedStarter seedStarter) {
        seedStarter.setDatePlanted(Calendar.getInstance().getTime());
        wizardStep = 1;
        model.addAttribute("wizardStep", wizardStep);
        return "bsd/bsdStoreOnlineHome";
    }

    @RequestMapping({"/bsdStoreOnlineHome2"})
    public String showRepairStep2(Model model, final SeedStarter seedStarter) {
        seedStarter.setDatePlanted(Calendar.getInstance().getTime());
        wizardStep = 2;
        model.addAttribute("wizardStep", wizardStep);
        return "bsd/bsdStoreOnlineHome";
    }

    @RequestMapping(value="/bsdStoreOnlineHome", params={"save"})
    public String saveSeedstarter(final SeedStarter seedStarter, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "bsd/bsdStoreOnlineHome";
        }
        this.seedStarterService.add(seedStarter);
        model.clear();
        return "redirect:/bsdStoreOnlineHome";
    }

    @RequestMapping(value="/bsdStoreOnlineHome", params={"addRow"})
    public String addRow(final SeedStarter seedStarter, final BindingResult bindingResult) {
        seedStarter.getRows().add(new Row());
        return "bsd/bsdStoreOnlineHome";
    }

    @RequestMapping(value="/bsdStoreOnlineHome", params={"removeRow"})
    public String removeRow(final SeedStarter seedStarter, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        seedStarter.getRows().remove(rowId.intValue());
        return "bsd/bsdStoreOnlineHome";
    }
}
