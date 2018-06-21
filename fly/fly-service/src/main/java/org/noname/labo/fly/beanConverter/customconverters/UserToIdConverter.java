package org.noname.labo.fly.beanConverter.customconverters;

import org.dozer.DozerConverter;
import org.noname.labo.fly.dao.UserDao;
import org.noname.labo.fly.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserToIdConverter extends DozerConverter<User, Integer> {
	
	@Autowired
	private UserDao userDao;

	public UserToIdConverter() {
		super(User.class, Integer.class);
	}

	@Override
	public Integer convertTo(User source, Integer destination) {
		if (source == null) {
			return null;
		}

		return source.getId();
	}

	@Override
	public User convertFrom(Integer source, User destination) {
		if (source == null) {
			return null;
		}

		return userDao.findById(source).get();
	}
}
