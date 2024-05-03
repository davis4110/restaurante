package com.restaurante.application.repository;

import com.restaurante.domain.Order;
import com.restaurante.domain.OrderProduct;
import com.restaurante.infrastructure.dto.OrderProductBillDTO;

public interface OrderProductRepository {

	public Order create(OrderProductBillDTO orderProductBill);

	public Iterable<OrderProduct> getOrderProducts();

}
