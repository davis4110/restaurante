package com.restaurante.infrastructure.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.restaurante.domain.Restaurant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestaurantDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Identification es requerido")
	private String identification;

	@NotBlank(message = "Nombre es requerido")
	private String name;

	@Email(message = "Debe ingresar un email valido")
	private String email;

	@NotBlank(message = "Dirección es requerido")
	private String address;

	@NotBlank(message = "Celular es requerido")
	private String cellphone;

	public Restaurant restaurantDtoToRestaurant() {
		return new Restaurant(null, this.getIdentification(), this.getName(), this.getEmail(), this.getAddress(),
				this.getCellphone(), LocalDateTime.now());
	}

}
