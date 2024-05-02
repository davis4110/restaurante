package com.restaurante.application.repository;

import com.restaurante.domain.Restaurant;

public interface RestaurantRepository {

    public Restaurant createRestaurant(Restaurant restaurant);
    public Restaurant findByEmail(String email);
    public Restaurant findById(Integer id);
}
