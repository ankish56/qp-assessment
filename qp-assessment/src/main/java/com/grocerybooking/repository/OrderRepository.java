package com.grocerybooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocerybooking.model.OrderItem;

public interface OrderRepository extends JpaRepository<OrderItem, Long> {
}