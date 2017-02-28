package org.avp.quota.kpi.web.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.avp.quota.kpi.web.util.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * Controller for login.jsp
 *
 */
@Controller
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	private static final String LOGIN_FORM_ATTRIBUTE_NAME = "loginForm";
		
	public LoginController() {}
	
	@RequestMapping(value={"/login.jsp", "/login"}, method = RequestMethod.GET)
	public void displayLoginForm(ModelMap model,	@RequestParam(value = "error", required = false) String error,
													@RequestParam(value = "logout", required = false) String logout) {
		LoginForm loginForm = new LoginForm();
		loginForm.setUserName("Alexei Ptitchkin");
		model.addAttribute(LOGIN_FORM_ATTRIBUTE_NAME, loginForm);
		if (error != null) {
			model.addAttribute("error", "Invalid username and password!");
		}
		if (logout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}
	}
	
	@RequestMapping(value={"/logout.jsp", "/logout"})
	public String doLogout(HttpServletRequest request) {
		//userService.doLogout();
		request.getSession().invalidate();
		String ssoLogoutUrl = "/logout";
		String redirectUrl = String.format("redirect:%s", 
				(ssoLogoutUrl != null && ssoLogoutUrl.length()>0) ? ssoLogoutUrl : "/");
		return redirectUrl;
	}
}
