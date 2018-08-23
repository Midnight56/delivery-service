package org.noname.labo.fly.controllers;


import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.noname.labo.fly.beans.UserBean;
import org.noname.labo.fly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loadLoginPage() {
		logger.info("Login page loaded");
		return "loginpage";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		logger.info("user " + auth.getName() + " is off.");
		return "redirect:/login";
	}
	
	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		String name = user.getName();
		UserBean bean = userService.findUserByName(name);
		if (bean.isBlock()) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You have permission to access this page!");
		}
		model.setViewName("403");
		logger.info("user " + user.getName() + "without necessary authorities tried to enter to the closed page");
		return model;
	}
}



