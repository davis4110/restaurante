package com.restaurante.infrastructure.dto;

import java.io.Serializable;

import com.restaurante.domain.Product;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderBillDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "El ID del producto es requerido")
	private Integer idProduct;

	@NotBlank(message = "Cantidad es requerido")
	private Integer quantity;

	private Product product;
}
