package org.noname.labo.fly.service.impl;

import org.apache.log4j.Logger;
import org.noname.labo.fly.beanConverter.EntityBeanConverter;
import org.noname.labo.fly.dao.UserOptionsDao;
import org.noname.labo.fly.service.UserOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserOptionsServiceImpl implements UserOptionsService{
	
	private static final Logger logger = Logger.getLogger(UserOptionsServiceImpl.class);
	
	@Autowired
	UserOptionsDao userOptionsDao;
	
	@Autowired
	EntityBeanConverter converter;

	@Override
	public Integer findUserByRating(Integer rating) {
		Integer userId = userOptionsDao.findUserByRating(rating);
		logger.info("user with rating=" + rating + " was found, details: user id=" + userId);
		return userId;
	}

	@Override
	public Integer findBlockedUser(Boolean block) {
		Integer userId = userOptionsDao.findBlockedUser(block);
		logger.info("blocked user was found, details: user id=" + userId);
		return userId;
	}

	
}
