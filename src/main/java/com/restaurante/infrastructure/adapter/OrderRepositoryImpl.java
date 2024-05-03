package com.restaurante.infrastructure.adapter;

import org.springframework.stereotype.Repository;

import com.restaurante.application.repository.OrderRepository;
import com.restaurante.domain.Order;
import com.restaurante.infrastructure.mapper.OrderMapper;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

	private final OrderCrudRepository orderCrudRepository;

	private final OrderMapper orderMapper;

	public OrderRepositoryImpl(OrderCrudRepository orderCrudRepository, OrderMapper orderMapper) {
		this.orderCrudRepository = orderCrudRepository;
		this.orderMapper = orderMapper;
	}

	@Override
	public Order createOrder(Order order) {
		return orderMapper.toOrder(orderCrudRepository.save(orderMapper.toOrderEntity(order)));
	}

}
