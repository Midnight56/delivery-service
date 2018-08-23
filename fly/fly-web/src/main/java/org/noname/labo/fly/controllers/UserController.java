package org.noname.labo.fly.controllers;


import org.apache.log4j.Logger;
import org.noname.labo.fly.beans.UserBean;
import org.noname.labo.fly.service.SecurityService;
//import org.noname.labo.fly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	/*@Autowired
	private UserService userService;*/
	
	@Autowired
	private SecurityService securityService;
	
	@RequestMapping(value="/users/profile", method=RequestMethod.GET)
	public ModelAndView showMyProfile() {
		UserBean user = securityService.getAccount();
		ModelAndView mav = new ModelAndView("profile");
		mav.addObject("currentUser", user);
		logger.info("profile page for user with id=" + user.getId());
		return mav;		
	}
}
