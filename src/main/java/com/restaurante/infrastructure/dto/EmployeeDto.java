package com.restaurante.infrastructure.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.restaurante.domain.Employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Identification es requerido")
	private String identification;

	@NotBlank(message = "Nombres es requerido")
	private String firstName;

	@NotBlank(message = "Apellidos es requerido")
	private String lastName;

	@Email(message = "Debe ingresar un email valido")
	private String email;

	@NotBlank(message = "Dirección es requerido")
	private String address;

	@NotBlank(message = "Celular es requerido")
	private String cellphone;

	@NotBlank(message = "El ID del restaurante es requerido")
	private String idRestaurant;

	public Employee employeeDtoToEmployee() {
		return new Employee(null, this.getIdentification(), this.getFirstName(), this.getLastName(), this.getEmail(),
				this.getAddress(), this.getCellphone(), LocalDateTime.now());
	}

}
