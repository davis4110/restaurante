package com.restaurante.infrastructure.adapter;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.restaurante.infrastructure.entity.RestaurantEntity;

public interface RestaurantCrudRepository extends CrudRepository<RestaurantEntity, Integer> {

	Optional<RestaurantEntity> findByEmail(String email);

}
