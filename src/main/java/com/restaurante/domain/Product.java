package com.restaurante.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	private Integer id;
	private String code;
	private String name;
	private String description;
	private BigDecimal price;
	private LocalDateTime dateCreated;
	private LocalDateTime dateUpdated;
}
