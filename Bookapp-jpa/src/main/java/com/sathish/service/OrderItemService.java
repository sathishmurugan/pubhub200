package com.sathish.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathish.model.OrderItem;

import com.sathish.repository.OrderItemRepository;

@Service
public class OrderItemService {
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	
	public void save(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
	}
}
