package com.sathish.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathish.model.Book;
import com.sathish.model.Order;
import com.sathish.model.OrderItem;
import com.sathish.model.User;
import com.sathish.service.BookService;
import com.sathish.service.OrderService;
@Controller
@RequestMapping("orderItems")
public class OrderItemControlle {
@Autowired
BookService bookService;
@Autowired
OrderService orderService;
@PostMapping("/addToCart")
public String addToCart(@RequestParam("book_id") Long bookId, @RequestParam("qty") Integer qty,
		HttpSession session) {

	User user = (User) session.getAttribute("LOGGED_IN_USER");

	Order order = (Order) session.getAttribute("MY_CART_ITEMS");

	// create order
	if (order == null) {
		order = new Order();
		order.setUser(user);
		order.setTotalPrice(0);
		order.setStatus("ORDERED");
	}
	List<OrderItem> orderItems = order.getOrderItems();

	// If already the item is added to cart, then add the quantity

	boolean isItemExists = false;
	for (OrderItem item : orderItems) {

		if (item.getBook().getId().equals(bookId)) {
			int totalQuantity = item.getQuantity() + qty;
			item.setQuantity(totalQuantity);
			isItemExists = true;
		}
	}

	//If item not exists, create new Item
	if ( ! isItemExists) {

		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(order);

		Book book = bookService.findOne(bookId);
		orderItem.setBook(book);
		orderItem.setQuantity(qty);
		orderItems.add(orderItem);
	}

	order.setOrderItems(orderItems);

	session.setAttribute("MY_CART_ITEMS", order);

	orderService.save(order);

	return "redirect:../orders/cart";
}

@GetMapping("/remove")
public String removeItemFromCart(@RequestParam("id") Integer id, HttpSession session) {

	System.out.println("Remove Item from Card:" + id);
	Order order = (Order) session.getAttribute("MY_CART_ITEMS");

	if (order != null && order.getOrderItems().size() > 0) {
		List<OrderItem> orderItems = order.getOrderItems();
		OrderItem itemSelected = orderItems.get(id);
		orderItems.remove(itemSelected);
		order.setOrderItems(orderItems);
		session.setAttribute("MY_CART_ITEMS", order);
	}

	return "redirect:../orders/cart";

}


}