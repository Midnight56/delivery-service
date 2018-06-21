package org.noname.labo.fly.service.impl;

import java.util.List;

import org.noname.labo.fly.beanConverter.EntityBeanConverter;
import org.noname.labo.fly.beans.UserBean;
import org.noname.labo.fly.dao.RoleDao;
import org.noname.labo.fly.dao.UserDao;
import org.noname.labo.fly.domain.Role;
import org.noname.labo.fly.domain.User;
import org.noname.labo.fly.domain.UserOptions;
import org.noname.labo.fly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private EntityBeanConverter converter;

	@Override
	public Iterable<UserBean> getAllUsers() {
		Iterable<User> users = userDao.findAll();
		List<UserBean> beanList = converter.convertToBeanList(users, UserBean.class);
		return beanList;
	}

	@Override
	public UserBean findUserById(Integer id) {
		User user = userDao.findById(id).get();
		UserBean bean = converter.convertToBean(user, UserBean.class);
		return bean;
	}

	@Override
	public UserBean findUserByName(String name) {
		User user = userDao.findByName(name);
		UserBean bean = converter.convertToBean(user, UserBean.class);
		return bean;
	}

	@Override
	public UserBean findUserByEmail(String email) {
		User user = userDao.findByEmail(email);
		UserBean bean = converter.convertToBean(user, UserBean.class);
		return bean;
	}

	@Override
	public void updateUser(UserBean user) {
		User userEntity = converter.convertToEntity(user, User.class);
		userDao.save(userEntity);
	}

	@Override
	public void saveNewUser(UserBean user) {
		User userEntity = converter.convertToEntity(user, User.class);
		UserOptions userOptions = new UserOptions();
		userEntity.setOptions(userOptions);
		String roleName = user.getRole();
		Role role = roleDao.findByName(roleName);
		userEntity.addRole(role);
		userOptions.setUser(userEntity);
		userDao.save(userEntity);
	}
	
	@Override
	public void deleteUserById(Integer id) {
		userDao.deleteById(id);
	}	
}