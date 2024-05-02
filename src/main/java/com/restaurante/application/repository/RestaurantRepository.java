package com.restaurante.application.repository;

import com.restaurante.domain.Restaurant;

public interface RestaurantRepository {

	public Restaurant crearRestaurant(Restaurant restaurant);

	public Restaurant buscarByCorreo(String email);

	public Restaurant buscarById(Integer id);

}
