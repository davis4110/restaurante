package com.restaurante.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
	private Integer id;
	private String identification;
	private String name;
	private String email;
	private String address;
	private String cellphone;
	private LocalDateTime dateCreated;

	private List<Employee> lstEmployees;

}
