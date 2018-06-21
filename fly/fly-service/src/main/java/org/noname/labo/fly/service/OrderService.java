package org.noname.labo.fly.service;

import java.util.Date;

import org.noname.labo.fly.beans.OrderBean;

public interface OrderService {
	
	Iterable<OrderBean> getAllOrders();
	
	OrderBean getOrderById(Integer id);
	
	void saveOrder(OrderBean order);
	
	void deleteOrderById(Integer id);
	
	Iterable<OrderBean> findOrdersByUserId(Integer userId);
	
	Iterable<OrderBean> findOrdersByDate(Date date);
	
	Iterable<OrderBean> findOrdersByType(String packingType);
	
	Iterable<OrderBean> findByStatus(Boolean status);
	
	Iterable<OrderBean> findByEmployeeId(Integer emplId);
	
	Iterable<OrderBean> findByStatusAndEmployeeId(Boolean status, Integer emplId);
}
