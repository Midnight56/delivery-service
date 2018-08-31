package org.noname.labo.fly.api;

import org.apache.log4j.Logger;
import org.noname.labo.fly.beans.OrderBean;
import org.noname.labo.fly.beans.UserBean;
import org.noname.labo.fly.service.OrderService;
import org.noname.labo.fly.service.SecurityService;
import org.noname.labo.fly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/orders")
public class OrderApi {
	
	private static final Logger logger = Logger.getLogger(OrderApi.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SecurityService securityService;
	
	@ResponseBody
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<OrderBean>> showOrders() {
	    Iterable<OrderBean> orderList = orderService.findByStatusAndEmployeeId(true, 0);
		for (OrderBean order : orderList) {
			int userId = order.getUserId();
			UserBean user = userService.findUserById(userId);
			String name = user.getName();
			order.setUserName(name); 
	    }
		logger.info("orderlist in rest controller created");
	    return new ResponseEntity<Iterable<OrderBean>>(orderList, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderBean> getOrder(@PathVariable Integer id) {
		OrderBean bean = orderService.getOrderById(id);
		int userId = bean.getUserId();
		UserBean user = userService.findUserById(userId);
		String name = user.getName();
		bean.setUserName(name); 
		logger.info("order with id=" + id + " was found");
		return new ResponseEntity<OrderBean>(bean, HttpStatus.OK);
	}
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderBean> createNewOrder(@RequestBody OrderBean order) {
		Integer userId = securityService.getAccountId();
		order.setUserId(userId);
		order.setEmployeeId(0);
		order.setStatus(true);
		orderService.saveOrder(order);
		logger.info("new order created, details: " + order);
		return new ResponseEntity<OrderBean>(order, HttpStatus.CREATED);
	}
	
	@ResponseBody
	@GetMapping(value = "/{customerId}/show-orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<OrderBean>> showCustomerOrders(@PathVariable Integer customerId) {
		Iterable<OrderBean> orders = orderService.findOrdersByUserId(customerId);
		logger.info("Orders by customer with id=" + customerId + " loaded");
		return new ResponseEntity<Iterable<OrderBean>>(orders, HttpStatus.OK);
	}
}
