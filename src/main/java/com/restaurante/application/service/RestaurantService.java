package com.restaurante.application.service;

import com.restaurante.application.repository.RestaurantRepository;
import com.restaurante.domain.Restaurant;

public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant createRestaurant(Restaurant restaurant){
        return restaurantRepository.createRestaurant(restaurant);
    }

    public Restaurant findByEmail(String email){
        return restaurantRepository.findByEmail(email);
    }

    public Restaurant findById(Integer id){
        return restaurantRepository.findById(id);
    }
}
