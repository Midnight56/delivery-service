package org.noname.labo.fly.beans;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityBean implements GrantedAuthority{

	private static final long serialVersionUID = 1L;
	
	private String role;

	public GrantedAuthorityBean(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return role;
	}

}
