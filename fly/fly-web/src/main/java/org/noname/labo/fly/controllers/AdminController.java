package org.noname.labo.fly.controllers;


import org.noname.labo.fly.beans.OrderBean;
import org.noname.labo.fly.beans.RoleBean;
import org.noname.labo.fly.beans.UserBean;
import org.noname.labo.fly.service.OrderService;
import org.noname.labo.fly.service.RoleService;
import org.noname.labo.fly.service.SecurityService;
import org.noname.labo.fly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SecurityService securityService;
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String showAll(@ModelAttribute("userList") Iterable<UserBean> list, 
								@ModelAttribute("orderList") Iterable<OrderBean> orderlist) {
		return "adminpage";
	}
	
	@RequestMapping(value = "/users/{userId}/block", method = RequestMethod.GET)
		public String blockUser(@PathVariable("userId") Integer id) {
		UserBean currentUser = securityService.getAccount();
		RoleBean role = roleService.getRoleByName("admin");
		if (currentUser.getRoles().contains(role)) {
			UserBean user = userService.findUserById(id);
			user.setBlock(true);
			userService.updateUser(user);
			return "redirect:/admin/show";
		} else
			return "redirect:/welcome";
	}
	
	@RequestMapping(value = "/users/{userId}/unblock", method = RequestMethod.GET)
		public String unBlockUser(@PathVariable("userId") Integer id) {
		UserBean currentUser = securityService.getAccount();
		RoleBean role = roleService.getRoleByName("admin");
		if (currentUser.getRoles().contains(role)) {
			UserBean user = userService.findUserById(id);
			user.setBlock(false);
			userService.updateUser(user);
			return "redirect:/admin/show";
		} else
			return "redirect:/welcome";
		
	}
	
	@RequestMapping(value = "/users/{userId}/make-admin", method = RequestMethod.GET)
		public String allowAdminFunction(@PathVariable("userId") Integer id) {
		UserBean currentUser = securityService.getAccount();
		RoleBean role = roleService.getRoleByName("admin");
		if (currentUser.getRoles().contains(role)) {
			UserBean user = userService.findUserById(id);
			user.addNewRole(role);
			userService.updateUser(user);
			return "redirect:/admin/show";
		} else
			return "redirect:/welcome";
	}
	
	@RequestMapping(value = "/users/{userId}/delete-admin", method = RequestMethod.GET)
		public String deleteAdminFunction(@PathVariable("userId") Integer id) {
		UserBean currentUser = securityService.getAccount();
		RoleBean role = roleService.getRoleByName("admin");
		if (currentUser.getRoles().contains(role)) {
			UserBean user = userService.findUserById(id);
			user.removeRole(role);
			userService.updateUser(user);
			return "redirect:/admin/show";
		} else
			return "redirect:/welcome";
	}
	
	@RequestMapping(value = "/orders/{id}/close", method = RequestMethod.GET)
	public ModelAndView closeOrder(@PathVariable("id") Integer id) {
		UserBean currentUser = securityService.getAccount();
		RoleBean role = roleService.getRoleByName("admin");
		if (currentUser.getRoles().contains(role)) {
			OrderBean order = orderService.getOrderById(id);
			order.setStatus(false);
			orderService.saveOrder(order);
		}
		return new ModelAndView("redirect:/admin/show");
	}
	
	@ModelAttribute(name = "userList")
	public Iterable<UserBean> allUsers() {
		Iterable<UserBean> list = userService.getAllUsers();
		return list;
	}
	
	@ModelAttribute(name = "orderList")
	public Iterable<OrderBean> showOrders() {
		Iterable<OrderBean> orderList = orderService.getAllOrders();
		for (OrderBean order : orderList) {
			int userId = order.getUserId();
			UserBean user = userService.findUserById(userId);
			String name = user.getName();
			order.setUserName(name);
		}
		return orderList;
	}
}
