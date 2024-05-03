package com.restaurante.infrastructure.adapter;

import org.springframework.data.repository.CrudRepository;

import com.restaurante.infrastructure.entity.OrderEntity;

public interface OrderCrudRepository extends CrudRepository<OrderEntity, Integer> {

}
