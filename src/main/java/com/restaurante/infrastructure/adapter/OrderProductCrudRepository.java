package com.restaurante.infrastructure.adapter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.restaurante.infrastructure.entity.OrderProductEntity;
import com.restaurante.infrastructure.entity.ProductEntity;

public interface OrderProductCrudRepository extends CrudRepository<OrderProductEntity, Integer> {

	List<OrderProductEntity> findByPkProductEntity(ProductEntity productEntity);
}
