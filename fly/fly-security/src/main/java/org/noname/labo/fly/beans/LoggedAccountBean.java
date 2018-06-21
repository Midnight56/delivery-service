package org.noname.labo.fly.beans;

import java.util.Collection;

import org.noname.labo.fly.beans.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoggedAccountBean implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	
	private Collection<GrantedAuthorityBean> authorities;

	@Autowired
	private UserBean user;
	
	public LoggedAccountBean(UserBean user, Collection<GrantedAuthorityBean> authorities) {
		this.user = user;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		if(user.isBlock()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
	
	public UserBean getUser() {
		return user;
	}
}
