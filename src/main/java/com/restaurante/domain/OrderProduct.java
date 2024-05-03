package com.restaurante.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {

	private Order order;
	private Product product;
	private Integer quantity;
	private BigDecimal total;


	public BigDecimal getTotalPrice() {
		return this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
	}
}
