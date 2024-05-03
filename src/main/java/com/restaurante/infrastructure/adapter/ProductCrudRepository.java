package com.restaurante.infrastructure.adapter;

import org.springframework.data.repository.CrudRepository;

import com.restaurante.infrastructure.entity.ProductEntity;

public interface ProductCrudRepository extends CrudRepository<ProductEntity, Integer> {

}
