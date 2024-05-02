package com.restaurante.infrastructure.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.restaurante.application.repository.RestaurantRepository;
import com.restaurante.domain.Restaurant;
import com.restaurante.infrastructure.entity.RestaurantEntity;
import com.restaurante.infrastructure.mapper.RestaurantMapper;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

	private final RestaurantCrudRepository restaurantCrudRepository;

	private final RestaurantMapper restaurantMapper;

	public RestaurantRepositoryImpl(RestaurantCrudRepository restaurantCrudRepository,
			RestaurantMapper restaurantMapper) {
		this.restaurantCrudRepository = restaurantCrudRepository;
		this.restaurantMapper = restaurantMapper;
	}

	@Override
	public Restaurant crearRestaurant(Restaurant restaurant) {
		return restaurantMapper
				.toRestaurant(restaurantCrudRepository.save(restaurantMapper.toRestaurantEntity(restaurant)));
	}

	@Override
	public Restaurant buscarByCorreo(String email) {
		Optional<RestaurantEntity> opRestaurant = restaurantCrudRepository.findByEmail(email);
		return restaurantMapper.toRestaurant((opRestaurant.isPresent()) ? opRestaurant.get() : null);

	}

	@Override
	public Restaurant buscarById(Integer id) {
		Optional<RestaurantEntity> opRestaurant = restaurantCrudRepository.findById(id);
		return restaurantMapper.toRestaurant((opRestaurant.isPresent()) ? opRestaurant.get() : null);
	}
}
