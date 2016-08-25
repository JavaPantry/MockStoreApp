package org.avp.quota.kpi.web.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.avp.bsd.service.BsdService;
import org.avp.security.model.User;
import org.avp.security.service.CustomUserService;
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
	protected CustomUserService userService;

	public IndexController() {}
	
	@RequestMapping(value={"/exthome"}, method=RequestMethod.GET)
	public String showExtHome(Authentication authentication, ModelMap model, HttpSession session, HttpServletRequest request) {
		storePrincipal(model, session, authentication);
		//logger.debug(userDto.getFullName());
		//model.addAttribute("userFullName", userDto.getFullName());
		return "exthome";
	}

	@RequestMapping(value={"/clientStore"}, method=RequestMethod.GET)
	public String showClientStore(Authentication authentication, ModelMap model, HttpSession session, HttpServletRequest request) {
		storePrincipal(model, session, authentication);
		//logger.debug(userDto.getFullName());
		//model.addAttribute("userFullName", userDto.getFullName());
		return "clientStore";
	}
	
	private void storePrincipal(ModelMap model, HttpSession session, Authentication authentication) {
        String userName = authentication.getName();
        User user = (User) userService.getDomainUser(userName);
		session.setAttribute("appuser", user);
		model.addAttribute("userFullName", user.getUserId());
	}
	
}
