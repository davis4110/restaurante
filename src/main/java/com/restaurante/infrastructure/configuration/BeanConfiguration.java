package com.restaurante.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restaurante.application.repository.EmployeeRepository;
import com.restaurante.application.repository.OwnerRepository;
import com.restaurante.application.repository.RestaurantRepository;
import com.restaurante.application.service.EmployeeService;
import com.restaurante.application.service.OwnerService;
import com.restaurante.application.service.RestaurantService;

@Configuration
public class BeanConfiguration {

	@Bean
	public RestaurantService userService(RestaurantRepository userRepository) {
		return new RestaurantService(userRepository);
	}

	@Bean
	public OwnerService ownerService(OwnerRepository ownerRepository) {
		return new OwnerService(ownerRepository);
	}

	@Bean
	public EmployeeService employeeService(EmployeeRepository employeeRepository) {
		return new EmployeeService(employeeRepository);
	}


}
