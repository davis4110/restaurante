package com.restaurante.infrastructure.adapter;

import org.springframework.data.repository.CrudRepository;

import com.restaurante.infrastructure.entity.OrderProductEntity;

public interface OrderProductCrudRepository extends CrudRepository<OrderProductEntity, Integer> {

}
