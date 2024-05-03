package com.restaurante.application.service;

import com.restaurante.application.repository.OrderRepository;
import com.restaurante.domain.Order;

public class OrderService {

	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order createOrder(Order order) {
		return orderRepository.createOrder(order);
	}

}
