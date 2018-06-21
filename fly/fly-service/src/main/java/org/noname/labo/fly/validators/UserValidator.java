package org.noname.labo.fly.validators;

import org.noname.labo.fly.beans.UserBean;
import org.noname.labo.fly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator{

	@Autowired
	UserService userService;

	
	@Override
	public boolean supports(Class<?> aClass) {
		return UserBean.class.equals(aClass);
	}

	@Override
	public void validate(Object ob, Errors err) {
		
		UserBean user = (UserBean) ob;
		
		String formUserName = user.getName();
		UserBean userFromDb = userService.findUserByName(formUserName);
		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "name", "required");
		
		if(formUserName.length() < 3 || formUserName.length() > 20) {
			err.rejectValue("name", "username.badsize");
		}
		if(userFromDb != null) {
			err.rejectValue("name", "username.duplicate");
		}
			
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "password", "required");
		if(user.getPassword().length() < 7 || user.getPassword().length() > 20) {
			err.rejectValue("password", "password.badsize");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "email", "required");
		if (userFromDb != null) {
			if(userFromDb.getEmail().equals(user.getEmail())) {
				err.rejectValue("email", "email.duplicate");
			}
		}		
	}
}
