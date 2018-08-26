package org.noname.labo.fly.api;

import org.apache.log4j.Logger;
import org.noname.labo.fly.beans.OrderBean;
import org.noname.labo.fly.beans.UserBean;
import org.noname.labo.fly.service.OrderService;
import org.noname.labo.fly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
}
