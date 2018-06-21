package org.noname.labo.fly.service;

import org.noname.labo.fly.beans.UserBean;

public interface SecurityService {

	Integer getAccountId();
	
	UserBean getAccount();
}
