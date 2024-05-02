package com.restaurante.application.service;

import com.restaurante.application.repository.RestaurantRepository;
import com.restaurante.domain.Restaurant;

public class RestaurantService {

	private final RestaurantRepository restaurantRepository;

	public RestaurantService(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	public Restaurant crearRestaurant(Restaurant restaurant) {
		return restaurantRepository.crearRestaurant(restaurant);
	}

	public Restaurant buscarByEmail(String email) {
		return restaurantRepository.buscarByCorreo(email);
	}

	public Restaurant buscarById(Integer id) {
		return restaurantRepository.buscarById(id);
	}
}
