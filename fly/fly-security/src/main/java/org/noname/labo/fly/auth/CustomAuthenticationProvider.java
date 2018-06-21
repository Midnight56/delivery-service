package org.noname.labo.fly.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.noname.labo.fly.beans.GrantedAuthorityBean;
import org.noname.labo.fly.beans.LoggedAccountBean;
import org.noname.labo.fly.beans.RoleBean;
import org.noname.labo.fly.beans.UserBean;
import org.noname.labo.fly.service.RoleService;
import org.noname.labo.fly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		LoggedAccountBean loggedUser = createLoggedAccount(authentication);
		String password = (String) authentication.getCredentials();
		if(!password.equals(loggedUser.getPassword())) {
			throw new BadCredentialsException("Неправильный пароль");
		}
		if (!loggedUser.isAccountNonLocked()) {
			throw new LockedException("Аккаунт заблокирован!", new Exception());
		}

		return new UsernamePasswordAuthenticationToken(loggedUser, password, loggedUser.getAuthorities());
	}

	@Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
	
	private LoggedAccountBean createLoggedAccount(Authentication authentication) {
		String name = authentication.getName();
		if(StringUtils.isBlank(name)) {
			throw new BadCredentialsException("Введите логин");
		}
		
		UserBean user = userService.findUserByName(name);
		if(user == null) {
			throw new BadCredentialsException("Пользователь с таким именем не найден");
		}
		Iterable<RoleBean> roles = roleService.getRolesForUser(user.getId());
		
		
		Collection<GrantedAuthorityBean> authorities = getAuthorities(roles);
		
		LoggedAccountBean loggedUser = new LoggedAccountBean(user, authorities);
		return loggedUser;
	}

	private Collection<GrantedAuthorityBean> getAuthorities(Iterable<RoleBean> roles) {
		Collection<GrantedAuthorityBean> authorities = new ArrayList<>();
		
		for(RoleBean role : roles) {
			GrantedAuthorityBean bean = new GrantedAuthorityBean(role.getName());
			authorities.add(bean);
		}
		return authorities;
	}
}
