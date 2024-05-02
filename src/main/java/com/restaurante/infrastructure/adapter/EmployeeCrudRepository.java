package com.restaurante.infrastructure.adapter;

import org.springframework.data.repository.CrudRepository;

import com.restaurante.infrastructure.entity.EmployeeEntity;

public interface EmployeeCrudRepository extends CrudRepository<EmployeeEntity, Integer> {

}
