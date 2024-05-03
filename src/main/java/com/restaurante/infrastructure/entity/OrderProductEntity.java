package com.restaurante.infrastructure.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders_products")
public class OrderProductEntity {

	@EmbeddedId
	private OrderProductPK pk;

	private Integer quantity;
	
	private Integer total;
}
