package org.noname.labo.fly.service;

import org.noname.labo.fly.beans.UserBean;

public interface UserService {

	Iterable<UserBean> getAllUsers();
	
	UserBean findUserById(Integer id);
	
	UserBean findUserByName(String name);
	
	UserBean findUserByEmail(String email);
	
	void updateUser(UserBean user);
	
	void saveNewUser(UserBean user);
	
	void deleteUserById(Integer id);
}
