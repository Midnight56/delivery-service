package org.noname.labo.fly.controllers;


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
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public ModelAndView ApplicationEntry() {
		System.out.println("Start Page loaded");
		ModelAndView mav = new ModelAndView("startpage");
		return mav;
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView showHomePage() {
		System.out.println("Start Page loaded");
		ModelAndView mav = new ModelAndView("me");
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
