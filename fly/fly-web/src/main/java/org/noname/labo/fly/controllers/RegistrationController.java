package org.noname.labo.fly.controllers;


import org.noname.labo.fly.beans.UserBean;
import org.noname.labo.fly.service.UserService;
import org.noname.labo.fly.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("user", new UserBean());
		return "registration";	
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.POST)
	public String registrationComplete(@ModelAttribute("user") UserBean user, BindingResult result) {
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "registration";
		} else {
			userService.saveNewUser(user);
			return "successReg";
		}	
	}
}
