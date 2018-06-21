package org.noname.labo.fly.service.impl;

import org.noname.labo.fly.beans.LoggedAccountBean;
import org.noname.labo.fly.beans.UserBean;
import org.noname.labo.fly.service.SecurityService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SecurityServiceImpl implements SecurityService {

	@Override
	public Integer getAccountId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
        	return null;
        }
        
        Object principal = auth.getPrincipal();
        LoggedAccountBean bean = (LoggedAccountBean)principal;
        return bean.getUser().getId();
	}

	@Override
	public UserBean getAccount() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
        	return null;
        }
        
        Object principal = auth.getPrincipal();
        LoggedAccountBean bean = (LoggedAccountBean)principal;
        return bean.getUser();
	}

}
