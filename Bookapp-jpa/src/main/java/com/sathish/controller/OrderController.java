package com.sathish.controller;

import java.time.LocalDate;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathish.model.Order;
import com.sathish.model.User;
import com.sathish.service.OrderService;
import com.sathish.service.UserService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderService orderService;

	@Autowired
	UserService userService;
	
	@GetMapping
	public String lists(ModelMap modelMap, HttpSession session) {

		List<Order> list = orderService.findAllOrders();
		System.out.println("orders:"+ list.size());
		for (Order order : list) {
			System.out.println(order);
		}
		modelMap.addAttribute("ORDERS_LIST", list);
		return "order/list";

	}
	@GetMapping("/cart")
	public String cart() {
		return "order/cart";
	}
	@GetMapping("/myorders")
	public String list(ModelMap modelMap, HttpSession session) {
		User user=userService.findByEmail((String) session.getAttribute("logid"));
		Long id=user.getId();
		List<Order> list = orderService.findByUserId(id);
		System.out.println("orders:"+ list.size());
		for (Order order : list) {
			System.out.println(order);
		}
		modelMap.addAttribute("ORDERS_LIST", list);
		return "order/list";

	}
	@GetMapping("/updateStatus")
	public String updateStatus(@RequestParam("id") Long orderId , @RequestParam("status")String status ) {
		Order order = orderService.findOne(orderId);
		if ("CANCELLED".equals(status) ) {
			order.setCancelledDate(LocalDate.now());
		}
		else if ("DELIVERED".equals(status)) {
			order.setDeliveredDate(LocalDate.now());
		}
		
		order.setStatus(status);
		orderService.save(order);	
		return "redirect:../orders/myorders";
	}
	@PostMapping("/save")
	public String save(@RequestParam("total_amount") Integer totalAmount,
			@RequestParam("paymentmode") String paymentmode, HttpSession session) {

		Order orderInCart = (Order) session.getAttribute("MY_CART_ITEMS");
		User user = userService.findByEmail((String)session.getAttribute("logid"));
		if (orderInCart != null && orderInCart.getOrderItems().size() > 0) {

			System.out.println("user object"+user);
			orderInCart.setUser(user);
			orderInCart.setTotalPrice(totalAmount);
			orderInCart.setPaymentmode(paymentmode);

			orderService.save(orderInCart);
			session.removeAttribute("MY_CART_ITEMS");
		}

		return "redirect:../orders/myorders";
	}

	

}
