package com.restaurante.application.service;

import com.restaurante.application.repository.EmployeeRepository;
import com.restaurante.domain.Employee;

public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Employee crearEmployee(Employee employee) {
		return employeeRepository.crearEmployee(employee);
	}
}
