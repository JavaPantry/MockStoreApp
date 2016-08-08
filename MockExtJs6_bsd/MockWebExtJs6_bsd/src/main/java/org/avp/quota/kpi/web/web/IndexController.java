package org.avp.quota.kpi.web.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.avp.quota.kpi.model.dao.CategoryDao;
import org.avp.quota.kpi.model.dao.EmployeeDao;
import org.avp.quota.kpi.model.dao.SalesRepresentativeDao;
import org.avp.quota.kpi.model.dao.TocDao;
import org.avp.quota.kpi.model.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController extends AbstractQuotaKPIController {

	private static Logger logger = Logger.getLogger(IndexController.class);
	
	public IndexController() {}
	
	@RequestMapping(value={"/exthome"}, method=RequestMethod.GET)
	public String showExtHome(ModelMap model, HttpServletRequest request) {
		logger.debug(userDto.getFullName());
		model.addAttribute("userFullName", userDto.getFullName());
		return "exthome";
	}

	@RequestMapping(value={"/bsdhome"}, method=RequestMethod.GET)
	public String showBsdHome(ModelMap model, HttpServletRequest request) {
		logger.debug(userDto.getFullName());
		model.addAttribute("userFullName", userDto.getFullName());
		return "bsdhome";
	}
	
	@RequestMapping(value={"/clientStore"}, method=RequestMethod.GET)
	public String showClientStore(ModelMap model, HttpServletRequest request) {
		logger.debug(userDto.getFullName());
		model.addAttribute("userFullName", userDto.getFullName());
		return "clientStore";
	}
}
