package com.restaurante.infrastructure.adapter;

import org.springframework.stereotype.Repository;

import com.restaurante.application.repository.OrderProductRepository;
import com.restaurante.domain.Order;
import com.restaurante.domain.OrderProduct;
import com.restaurante.infrastructure.dto.OrderProductBillDTO;
import com.restaurante.infrastructure.entity.OrderEntity;
import com.restaurante.infrastructure.entity.OrderProductEntity;
import com.restaurante.infrastructure.mapper.OrderMapper;
import com.restaurante.infrastructure.mapper.OrderProductMapper;

@Repository
public class OrderProductRepositoryImpl implements OrderProductRepository {

	private final OrderProductCrudRepository orderProductCrudRepository;
	private final OrderCrudRepository orderCrudRepository;
	private final OrderProductMapper orderProductMapper;
	private final OrderMapper orderMapper;

	public OrderProductRepositoryImpl(OrderProductCrudRepository orderProductCrudRepository,
			OrderCrudRepository orderCrudRepository, OrderProductMapper orderProductMapper, OrderMapper orderMapper) {
		this.orderProductCrudRepository = orderProductCrudRepository;
		this.orderCrudRepository = orderCrudRepository;
		this.orderProductMapper = orderProductMapper;
		this.orderMapper = orderMapper;
	}

	@Override
	public Order create(OrderProductBillDTO orderProductBill) {
		OrderEntity orderEntity = orderCrudRepository.save(orderMapper.toOrderEntity(orderProductBill.getOrderBill()));
		Order order = orderMapper.toOrder(orderEntity);
		for (OrderProduct orderProduct : orderProductBill.getLstOrderProduct()) {
			orderProduct.setOrder(order);
			OrderProductEntity orderPro = orderProductMapper.toOrderProductEntity(orderProduct);
			orderProductMapper.toOrderProduct(orderProductCrudRepository.save(orderPro));
		}
		return order;
	}

	@Override
	public Iterable<OrderProduct> getOrderProducts() {
		return orderProductMapper.toOrderProducts(orderProductCrudRepository.findAll());
	}
}
