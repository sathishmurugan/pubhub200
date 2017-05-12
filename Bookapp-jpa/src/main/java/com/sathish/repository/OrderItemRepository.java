package com.sathish.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sathish.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
