package org.noname.labo.fly.controllers;


import org.noname.labo.fly.beans.UserBean;
import org.noname.labo.fly.service.SecurityService;
//import org.noname.labo.fly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	/*@Autowired
	private UserService userService;*/
	
	@Autowired
	private SecurityService securityService;
	
	@RequestMapping(value="/users/profile", method=RequestMethod.GET)
	public ModelAndView showMyProfile() {
		ModelAndView mav = new ModelAndView("profile");
		mav.addObject("currentUser");
		return mav;		
	}
	
	@ModelAttribute(name = "currentUser")
	public UserBean getCurrentUserId() {
		UserBean currentUser = securityService.getAccount();
		return currentUser;
	}
}
