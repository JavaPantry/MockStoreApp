package org.avp.quota.kpi.web.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.avp.bsd.model.BsdUser;
import org.avp.bsd.service.BsdService;
import org.avp.quota.kpi.model.dao.CategoryDao;
import org.avp.quota.kpi.model.dao.EmployeeDao;
import org.avp.quota.kpi.model.dao.SalesRepresentativeDao;
import org.avp.quota.kpi.model.dao.TocDao;
import org.avp.quota.kpi.model.dao.QuotaUser;
import org.avp.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController extends AbstractQuotaKPIController {

	private static Logger logger = Logger.getLogger(IndexController.class);
	
	@Autowired
	protected BsdService bsdService;

	public IndexController() {}
	
	@RequestMapping(value={"/exthome"}, method=RequestMethod.GET)
	public String showExtHome(Authentication authentication, ModelMap model, HttpSession session, HttpServletRequest request) {
		storePrincipal(model, session, authentication);
		logger.debug(userDto.getFullName());
		model.addAttribute("userFullName", userDto.getFullName());
		return "exthome";
	}

	@RequestMapping(value={"/clientStore"}, method=RequestMethod.GET)
	public String showClientStore(Authentication authentication, ModelMap model, HttpSession session, HttpServletRequest request) {
		storePrincipal(model, session, authentication);
		logger.debug(userDto.getFullName());
		model.addAttribute("userFullName", userDto.getFullName());
		return "clientStore";
	}
	/*@RequestMapping(value={"/bsdhome"}, method=RequestMethod.GET)
	public String showBsdHome(Authentication authentication, ModelMap model, HttpServletRequest request) {
		logger.debug(userDto.getFullName());
		model.addAttribute("userFullName", userDto.getFullName());
		return "bsdhome";
	}*/
	
	private void storePrincipal(ModelMap model, HttpSession session, Authentication authentication) {
        String userName = authentication.getName();
        User user = (User) bsdService.getDomainUser(userName);
		session.setAttribute("appuser", user);
	}
	
}
