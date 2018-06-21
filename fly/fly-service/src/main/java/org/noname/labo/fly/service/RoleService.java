package org.noname.labo.fly.service;

import org.noname.labo.fly.beans.RoleBean;

public interface RoleService {
	
	Iterable<RoleBean> getAllRoles();
	
	RoleBean getRoleById(Integer roleId);
	
	RoleBean getRoleByName(String name);
	
	void saveRole(RoleBean bean);
	
	Iterable<RoleBean> getRolesForUser(Integer userId);
}
