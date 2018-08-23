package org.noname.labo.fly.controllers;


import org.apache.log4j.Logger;
import org.noname.labo.fly.beans.OrderBean;
import org.noname.labo.fly.beans.UserBean;
import org.noname.labo.fly.service.OrderService;
import org.noname.labo.fly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	private static final Logger logger = Logger.getLogger(MainController.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public ModelAndView ApplicationEntry() {
		ModelAndView mav = new ModelAndView("startpage");
		logger.info("Startpage loaded");
		return mav;
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView showHomePage() {
		ModelAndView mav = new ModelAndView("me");
		logger.info("Welcome page loaded");
		return mav;
	}
	
	@ModelAttribute(name = "orderList")
	public Iterable<OrderBean> showOrders() {
		Iterable<OrderBean> orderList = orderService.findByStatusAndEmployeeId(true, 0);
		for (OrderBean order : orderList) {
			int userId = order.getUserId();
			UserBean user = userService.findUserById(userId);
			String name = user.getName();
			order.setUserName(name); 
		}
		return orderList;
	}
 }
