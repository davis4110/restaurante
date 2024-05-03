package com.restaurante.infrastructure.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.restaurante.domain.Order;
import com.restaurante.domain.OrderProduct;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<OrderBillDTO> lstListOrders;

	private OrderProduct orderProducts;
	private Integer quantity;
	private BigDecimal totalBill;
	private BigDecimal total;

	public Order OrderDtoToOrder() {
		return new Order(null, LocalDateTime.now(), totalBill);
	}

	public OrderProduct OrderProductDtoToOrderProduct() {
		return new OrderProduct(orderProducts.getOrder(), orderProducts.getProduct(), this.quantity,
				orderProducts.getTotalPrice());
	}

}
