package org.noname.labo.fly.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.noname.labo.fly.beans.OrderBean;
import org.noname.labo.fly.beans.RoleBean;
import org.noname.labo.fly.beans.UserBean;
import org.noname.labo.fly.service.OrderService;
import org.noname.labo.fly.service.RoleService;
import org.noname.labo.fly.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/orders")
public class OrderController {
	
	private static final Logger logger = Logger.getLogger(OrderController.class);
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SecurityService securityService;
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String createOrderForm(Model model) {
		model.addAttribute("newOrder", new OrderBean());
		logger.info("page for opening new order loaded");
		return "create_order";
	}
	
	@RequestMapping(value = "/create-order", method = RequestMethod.POST)
	public ModelAndView createOrder(@ModelAttribute("newOrder") OrderBean order) {
		Integer userId = securityService.getAccountId();
		order.setUserId(userId);
		order.setEmployeeId(0);
		order.setStatus(true);
		orderService.saveOrder(order);
		logger.info("new order created, details: " + order);
		return new ModelAndView("redirect:/welcome");
	}
	
	@RequestMapping(value = "/customer/showOrders", method = RequestMethod.GET)
	public String showMyOrders(Model model) {
		Integer id = securityService.getAccountId();
		Iterable<OrderBean> orders = orderService.findOrdersByUserId(id);
		model.addAttribute("orderList", orders);
		logger.info("all orders for customer with id=" + id);
		return "orders";
	}
	
	@RequestMapping(value = "/courier/showOrders", method = RequestMethod.GET)
	public String showOrders(Model model) {
		Integer id = securityService.getAccountId();
		Iterable<OrderBean> orders = orderService.findByEmployeeId(id);
		model.addAttribute("orderList", orders);
		logger.info("all orders for courier with id=" + id);
		return "orders";
	}
	
	@RequestMapping(value = "/{id}/accept-order", method = RequestMethod.GET)
	public String acceptOrder(@PathVariable("id") Integer id) {
		Integer emplId = securityService.getAccountId();
		OrderBean order = orderService.getOrderById(id);
		order.setEmployeeId(emplId);
		orderService.saveOrder(order);
		logger.info("order with id=" + id + " accepted by courier with id=" + emplId);
		return "redirect:/orders/courier/showOrders";
	}
	
	@RequestMapping(value = "/{id}/close-order", method = RequestMethod.GET)
	public String closeOrder(@PathVariable("id") Integer id) {
		OrderBean order = orderService.getOrderById(id);
		order.setStatus(false);
		orderService.saveOrder(order);
		UserBean user = securityService.getAccount();
		logger.info("order with id=" + id + " closed by user with id=" + user.getId() + " and name " + user.getName());
		RoleBean roleCourier = roleService.getRoleByName("courier");
		if (user.getRoles().contains(roleCourier)) {
			return "redirect:/orders/courier/showOrders";
		} else
			return "redirect:/orders/customer/showOrders";	
	}
	
	@RequestMapping(value = "/{id}/cancel-order", method = RequestMethod.GET)
	public ModelAndView cancelOrder(@PathVariable("id") Integer id) {
		OrderBean order = orderService.getOrderById(id);
		order.setEmployeeId(0);
		orderService.saveOrder(order);
		logger.info("order with id=" + id + " cancelled");
		return new ModelAndView("redirect:/orders/courier/showOrders");
	}

	@ModelAttribute(name = "packingList")
	public List<String> getPackingType() {
		List<String> packingList = new ArrayList<>();
		packingList.add("letter");
		packingList.add("package");
		packingList.add("box");		
		return packingList;
	}
} 
