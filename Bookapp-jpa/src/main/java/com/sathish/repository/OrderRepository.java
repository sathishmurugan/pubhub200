package com.sathish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sathish.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
public List<Order> findByUserIdOrderByIdDesc(Long userId);
	
	public List<Order> findByOrderByIdDesc();
	
}
