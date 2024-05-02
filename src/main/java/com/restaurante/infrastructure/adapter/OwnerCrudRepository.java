package com.restaurante.infrastructure.adapter;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.restaurante.infrastructure.entity.OwnerEntity;

public interface OwnerCrudRepository extends CrudRepository<OwnerEntity, Integer> {

	Optional<OwnerEntity> findByEmail(String email);

}
