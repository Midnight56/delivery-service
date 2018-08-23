package org.noname.labo.fly.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
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
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

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
		logger.info("all users have been loaded successfully");
		return beanList;
	}

	@Override
	public UserBean findUserById(Integer id) {
		User user = userDao.findById(id).get();
		UserBean bean = converter.convertToBean(user, UserBean.class);
		logger.info("user by id=" + id + " loaded successfully, details: name - " + bean.getName());
		return bean;
	}

	@Override
	public UserBean findUserByName(String name) {
		User user = userDao.findByName(name);
		UserBean bean = converter.convertToBean(user, UserBean.class);
		logger.info("user by name " + name + " loaded successfully, details: id=" + bean.getId());
		return bean;
	}

	@Override
	public UserBean findUserByEmail(String email) {
		User user = userDao.findByEmail(email);
		UserBean bean = converter.convertToBean(user, UserBean.class);
		logger.info("user by e-mail " + email + " loaded successfully, details: id=" + bean.getId() + ", name - " + bean.getName());
		return bean;
	}

	@Override
	public void updateUser(UserBean user) {
		User userEntity = converter.convertToEntity(user, User.class);
		userDao.save(userEntity);
		logger.info("user " + user.getName() + " has been updated");
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
		logger.info("user " + user.getName() + " has been saved");
	}
	
	@Override
	public void deleteUserById(Integer id) {
		userDao.deleteById(id);
		logger.info("user has been deleted");
	}	
}
