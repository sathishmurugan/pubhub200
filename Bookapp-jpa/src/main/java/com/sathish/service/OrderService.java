package com.sathish.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathish.model.Order;

import com.sathish.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	public void save(Order order) {
		orderRepository.save(order);
		
	}
	public Order findOne(Long id){
		return orderRepository.findOne(id);
	}
	public List<Order> findAllOrders()
	{
		return orderRepository.findAll();
	}
	public List<Order> findByUserIdOrderByIdDesc(Long userId){
		return orderRepository.findByUserIdOrderByIdDesc(userId);
	}
	public List<Order> findByUserId(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.findByUserIdOrderByIdDesc(id);
	}
}
