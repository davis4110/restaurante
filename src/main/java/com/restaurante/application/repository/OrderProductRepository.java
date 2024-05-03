package com.restaurante.application.repository;

import java.util.List;

import com.restaurante.domain.Order;
import com.restaurante.domain.OrderProduct;
import com.restaurante.domain.Product;
import com.restaurante.domain.Reporte;
import com.restaurante.infrastructure.dto.OrderProductBillDTO;

public interface OrderProductRepository {

	public Order create(OrderProductBillDTO orderProductBill);

	public Iterable<OrderProduct> getOrderProducts();

	List<OrderProduct> getOrdersProductByProduct(Product product);
	
	public List<Reporte> consultarReporte();

}
