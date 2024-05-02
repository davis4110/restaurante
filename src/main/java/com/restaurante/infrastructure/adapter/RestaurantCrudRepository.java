package com.restaurante.infrastructure.adapter;

import com.restaurante.infrastructure.entity.RestaurantEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantCrudRepository extends CrudRepository<RestaurantEntity, Integer> {

    Optional<RestaurantEntity> findByEmail(String email);

}
