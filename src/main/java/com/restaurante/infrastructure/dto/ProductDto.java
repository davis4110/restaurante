package com.restaurante.infrastructure.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.restaurante.domain.Product;
import com.restaurante.domain.Restaurant;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Nombre es requerido")
	private String name;

	@NotBlank(message = "Descripcion es requerido")
	private String description;

	@NotBlank(message = "Precio es requerido")
	private String price;
	
	@NotBlank(message = "El ID del restaurante es requerido")
	private String idRestaurant;
	
	private Restaurant restaurant;

	public Product productDtoToProduct() {
		return new Product(
				null, 
				String.valueOf(UUID.randomUUID()), 
				this.getName(), 
				this.getDescription(), 
				new BigDecimal(this.getPrice()), 
				LocalDateTime.now(),
				null,
				this.restaurant
				);
	}

}
