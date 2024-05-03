package com.restaurante.application.service;

import com.restaurante.application.repository.OrderProductRepository;
import com.restaurante.domain.Order;
import com.restaurante.infrastructure.dto.OrderProductBillDTO;

public class OrderProductService {

	private final OrderProductRepository orderProductRepository;

	public OrderProductService(OrderProductRepository orderProductRepository) {
		this.orderProductRepository = orderProductRepository;
	}

	public Order create(OrderProductBillDTO orderProductBill) {
		return orderProductRepository.create(orderProductBill);
	}
}
