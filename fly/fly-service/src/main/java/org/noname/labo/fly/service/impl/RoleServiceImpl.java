package org.noname.labo.fly.service.impl;

import java.util.List;
import java.util.Set;

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
		return beanList;
	}


	@Override
	public RoleBean getRoleById(Integer roleId) {
		Role role = roleDao.findById(roleId).get();
		RoleBean bean = converter.convertToBean(role, RoleBean.class);
		return bean;
	}


	@Override
	public void saveRole(RoleBean bean) {
		Role role = converter.convertToEntity(bean, Role.class);
		roleDao.save(role);
	}


	@Override
	public Iterable<RoleBean> getRolesForUser(Integer userId) {
		User user = userDao.findById(userId).get();
		Set<Role> userRoles = user.getRoleList();
		List<RoleBean> beanList = converter.convertToBeanList(userRoles, RoleBean.class);
		return beanList;
	}


	@Override
	public RoleBean getRoleByName(String name) {
		Role role = roleDao.findByName(name);
		RoleBean bean = converter.convertToBean(role, RoleBean.class);
		return bean;
	}
}
