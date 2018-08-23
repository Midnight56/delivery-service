package org.noname.labo.fly.controllers;


import org.apache.log4j.Logger;
import org.noname.labo.fly.beans.UserBean;
import org.noname.labo.fly.service.UserService;
import org.noname.labo.fly.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {
	
	private static final Logger logger = Logger.getLogger(RegistrationController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	BCryptPasswordEncoder bcryptEncoder;
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("user", new UserBean());
		logger.info("registraion page loaded");
		return "registration";	
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.POST)
	public String registrationComplete(@ModelAttribute("user") UserBean user, BindingResult result) {
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			logger.error("error in registration user " + user.getName() + ": " + result);
			return "registration";
		} else {
			user.setPassword(bcryptEncoder.encode(user.getPassword()));
			userService.saveNewUser(user);
			logger.info("new user " + user.getName() + " registered");
			return "successReg";
		}	
	}
}
