package org.noname.labo.fly.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.noname.labo.fly.beanConverter.EntityBeanConverter;
import org.noname.labo.fly.beans.OrderBean;
import org.noname.labo.fly.dao.OrderDao;
import org.noname.labo.fly.dao.UserDao;
import org.noname.labo.fly.domain.Order;
import org.noname.labo.fly.domain.User;
import org.noname.labo.fly.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EntityBeanConverter converter;

	@Override
	public Iterable<OrderBean> getAllOrders() {
		Iterable<Order> orders = orderDao.findAll();
		List<OrderBean> orderList = converter.convertToBeanList(orders, OrderBean.class);
		return orderList;
	}

	@Override
	public OrderBean getOrderById(Integer id) {
		Order order = orderDao.findById(id).get();
		OrderBean bean = converter.convertToBean(order, OrderBean.class);
		return bean;
	}

	@Override
	public void saveOrder(OrderBean order) {
		Order saved = converter.convertToEntity(order, Order.class);
		orderDao.save(saved);
	}

	@Override
	public void deleteOrderById(Integer id) {
		orderDao.deleteById(id);	
	}

	@Override
	public Iterable<OrderBean> findOrdersByUserId(Integer userId) {
		Optional<User> optional = userDao.findById(userId);
		User user = optional.get();
		Iterable<Order> orders = orderDao.findByUser(user);
		List<OrderBean> beanList = converter.convertToBeanList(orders, OrderBean.class);
		return beanList;
	}

	@Override
	public Iterable<OrderBean> findOrdersByDate(Date date) {
		Iterable<Order> orders = orderDao.findByDate(date);
		List<OrderBean> beanList = converter.convertToBeanList(orders, OrderBean.class);
		return beanList;
	}

	@Override
	public Iterable<OrderBean> findOrdersByType(String packingType) {
		Iterable<Order> orders = orderDao.findByPackingType(packingType);
		List<OrderBean> beanList = converter.convertToBeanList(orders, OrderBean.class);
		return beanList;
	}

	@Override
	public Iterable<OrderBean> findByStatus(Boolean status) {
		Iterable<Order> orders = orderDao.findByStatus(status);
		List<OrderBean> beanList = converter.convertToBeanList(orders, OrderBean.class);
		return beanList;
	}

	@Override
	public Iterable<OrderBean> findByEmployeeId(Integer emplId) {
		Iterable<Order> orders = orderDao.findByEmployeeId(emplId);
		List<OrderBean> beanList = converter.convertToBeanList(orders, OrderBean.class);
		return beanList;
	}

	@Override
	public Iterable<OrderBean> findByStatusAndEmployeeId(Boolean status, Integer emplId) {
		Iterable<Order> orders = orderDao.findByStatusAndEmployeeId(status, emplId);
		List<OrderBean> beanList = converter.convertToBeanList(orders, OrderBean.class);
		return beanList;
	}	
}
