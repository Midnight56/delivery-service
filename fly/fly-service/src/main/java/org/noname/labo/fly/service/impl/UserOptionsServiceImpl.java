package org.noname.labo.fly.service.impl;

import org.noname.labo.fly.beanConverter.EntityBeanConverter;
import org.noname.labo.fly.dao.UserOptionsDao;
import org.noname.labo.fly.service.UserOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserOptionsServiceImpl implements UserOptionsService{
	
	@Autowired
	UserOptionsDao UserOptionsDao;
	
	@Autowired
	EntityBeanConverter converter;

	@Override
	public Integer findUserByRating(Integer rating) {
		return UserOptionsDao.findUserByRating(rating);
	}

	@Override
	public Integer findBlockedUser(Boolean block) {
		return UserOptionsDao.findBlockedUser(block);
	}

	
}
