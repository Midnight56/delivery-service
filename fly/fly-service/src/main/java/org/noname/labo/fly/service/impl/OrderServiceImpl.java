package org.noname.labo.fly.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
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
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
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
		logger.info("All orders are loaded.");
		return orderList;
	}

	@Override
	public OrderBean getOrderById(Integer id) {
		Order order = orderDao.findById(id).get();
		OrderBean bean = converter.convertToBean(order, OrderBean.class);
		logger.info("order loaded successfully, order info: " + bean);
		return bean;
	}

	@Override
	public void saveOrder(OrderBean order) {
		Order saved = converter.convertToEntity(order, Order.class);
		orderDao.save(saved);
		logger.info("new order saved, order info: " + order );
	}

	@Override
	public void deleteOrderById(Integer id) {
		orderDao.deleteById(id);
		logger.info("current order deleted");
	}

	@Override
	public Iterable<OrderBean> findOrdersByUserId(Integer userId) {
		User user = userDao.findById(userId).get();
		Iterable<Order> orders = orderDao.findByUser(user);
		List<OrderBean> beanList = converter.convertToBeanList(orders, OrderBean.class);
		logger.info("orders by user's id=" + userId + " have been loaded successfully");
		return beanList;
	}

	@Override
	public Iterable<OrderBean> findOrdersByDate(Date date) {
		Iterable<Order> orders = orderDao.findByDate(date);
		List<OrderBean> beanList = converter.convertToBeanList(orders, OrderBean.class);
		logger.info("orders by date=" + date + " have been loaded successfully");
		return beanList;
	}

	@Override
	public Iterable<OrderBean> findOrdersByType(String packingType) {
		Iterable<Order> orders = orderDao.findByPackingType(packingType);
		List<OrderBean> beanList = converter.convertToBeanList(orders, OrderBean.class);
		logger.info("orders by packing type=" + packingType + " have been loaded successfully");
		return beanList;
	}

	@Override
	public Iterable<OrderBean> findByStatus(Boolean status) {
		Iterable<Order> orders = orderDao.findByStatus(status);
		List<OrderBean> beanList = converter.convertToBeanList(orders, OrderBean.class);
		logger.info("orders by status of execution=" + status + " have been loaded successfully");
		return beanList;
	}

	@Override
	public Iterable<OrderBean> findByEmployeeId(Integer emplId) {
		Iterable<Order> orders = orderDao.findByEmployeeId(emplId);
		List<OrderBean> beanList = converter.convertToBeanList(orders, OrderBean.class);
		logger.info("orders by employee's id=" + emplId + " have been loaded successfully");
		return beanList;
	}

	@Override
	public Iterable<OrderBean> findByStatusAndEmployeeId(Boolean status, Integer emplId) {
		Iterable<Order> orders = orderDao.findByStatusAndEmployeeId(status, emplId);
		List<OrderBean> beanList = converter.convertToBeanList(orders, OrderBean.class);
		logger.info("orders by process status=" + status + " and employee's id=" + emplId + " have been loaded successfully");
		return beanList;
	}	
}
