package com.restaurante.infrastructure.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class OrderProductPK {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", updatable = true)
	private OrderEntity orderEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", updatable = true)
	private ProductEntity productEntity;

}
