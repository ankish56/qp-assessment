package com.grocerybooking.service;

import com.grocerybooking.model.OrderItem;
import com.grocerybooking.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void placeOrder(List<OrderItem> orderItems) {
        orderRepository.saveAll(orderItems);
    }
}
