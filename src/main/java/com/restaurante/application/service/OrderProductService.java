package com.restaurante.application.service;

import java.util.List;

import com.restaurante.application.repository.OrderProductRepository;
import com.restaurante.domain.Order;
import com.restaurante.domain.OrderProduct;
import com.restaurante.domain.Product;
import com.restaurante.domain.Reporte;
import com.restaurante.infrastructure.dto.OrderProductBillDTO;

public class OrderProductService {

	private final OrderProductRepository orderProductRepository;

	public OrderProductService(OrderProductRepository orderProductRepository) {
		this.orderProductRepository = orderProductRepository;
	}

	public Order create(OrderProductBillDTO orderProductBill) {
		return orderProductRepository.create(orderProductBill);
	}

	public List<OrderProduct> buscarOrderbyProduct(Product product) {
		return orderProductRepository.getOrdersProductByProduct(product);
	}

	public List<Reporte> buscarReporte() {
		return orderProductRepository.consultarReporte();
	}
}
