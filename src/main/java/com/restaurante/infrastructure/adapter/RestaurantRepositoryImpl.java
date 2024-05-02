package com.restaurante.infrastructure.adapter;

import com.restaurante.application.repository.RestaurantRepository;
import com.restaurante.domain.Restaurant;
import com.restaurante.infrastructure.mapper.RestaurantMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantCrudRepository restaurantCrudRepository;

    private final RestaurantMapper restaurantMapper;

    public RestaurantRepositoryImpl(RestaurantCrudRepository restaurantCrudRepository, RestaurantMapper restaurantMapper) {
        this.restaurantCrudRepository = restaurantCrudRepository;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantMapper.toUser(restaurantCrudRepository.save(restaurantMapper.toUserEntity(restaurant)));
    }

    @Override
    public Restaurant findByEmail(String email) {
        return restaurantMapper.toUser(restaurantCrudRepository.findByEmail(email).get());
    }

    @Override
    public Restaurant findById(Integer id) {
        return restaurantMapper.toUser(restaurantCrudRepository.findById(id).get());
    }
}
