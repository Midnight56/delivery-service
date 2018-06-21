package org.noname.labo.fly.dao;

import java.util.Date;

import org.noname.labo.fly.domain.Order;
import org.noname.labo.fly.domain.User;
import org.springframework.data.repository.CrudRepository;


public interface OrderDao extends CrudRepository<Order, Integer>{
	
	Iterable<Order> findByUser(User user);
	
	Iterable<Order> findByDate(Date date);
	
	Iterable<Order> findByPackingType(String packingType);
	
	Iterable<Order> findByStatus(Boolean status);
	
	Iterable<Order> findByEmployeeId(Integer emplId);
	
	Iterable<Order> findByStatusAndEmployeeId(Boolean status, Integer emplId);
	
}
