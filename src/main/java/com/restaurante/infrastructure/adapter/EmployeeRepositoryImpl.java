package com.restaurante.infrastructure.adapter;

import org.springframework.stereotype.Repository;

import com.restaurante.application.repository.EmployeeRepository;
import com.restaurante.domain.Employee;
import com.restaurante.infrastructure.mapper.EmployeeMapper;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

	private final EmployeeCrudRepository employeeCrudRepository;

	private final EmployeeMapper employeeMapper;

	public EmployeeRepositoryImpl(EmployeeCrudRepository ownerCrudRepository, EmployeeMapper employeeMapper) {
		this.employeeCrudRepository = ownerCrudRepository;
		this.employeeMapper = employeeMapper;
	}

	@Override
	public Employee crearEmployee(Employee employee) {
		return employeeMapper.toEmployee(employeeCrudRepository.save(employeeMapper.toEmployeeEntity(employee)));
	}

}
