package org.noname.labo.fly.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.noname.labo.fly.beanConverter.EntityBeanConverter;
import org.noname.labo.fly.beans.RoleBean;
import org.noname.labo.fly.dao.RoleDao;
import org.noname.labo.fly.dao.UserDao;
import org.noname.labo.fly.domain.Role;
import org.noname.labo.fly.domain.User;
import org.noname.labo.fly.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	private static final Logger logger = Logger.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EntityBeanConverter converter;
	
	
	@Override
	public Iterable<RoleBean> getAllRoles() {
		Iterable<Role> roles = roleDao.findAll();
		Iterable<RoleBean> beanList = converter.convertToBeanList(roles, RoleBean.class);
		logger.info("all roles are loaded");
		return beanList;
	}


	@Override
	public RoleBean getRoleById(Integer roleId) {
		Role role = roleDao.findById(roleId).get();
		RoleBean bean = converter.convertToBean(role, RoleBean.class);
		logger.info("role by id=" + roleId + " loaded");
		return bean;
	}


	@Override
	public void saveRole(RoleBean bean) {
		Role role = converter.convertToEntity(bean, Role.class);
		roleDao.save(role);
		logger.info("new role was created");
	}


	@Override
	public Iterable<RoleBean> getRolesForUser(Integer userId) {
		User user = userDao.findById(userId).get();
		Set<Role> userRoles = user.getRoleList();
		List<RoleBean> beanList = converter.convertToBeanList(userRoles, RoleBean.class);
		logger.info("list of roles for user with id=" + userId + " was loaded");
		return beanList;	
	}


	@Override
	public RoleBean getRoleByName(String name) {
		Role role = roleDao.findByName(name);
		RoleBean bean = converter.convertToBean(role, RoleBean.class);
		logger.info("role " + name + " was found");
		return bean;
	}
}
