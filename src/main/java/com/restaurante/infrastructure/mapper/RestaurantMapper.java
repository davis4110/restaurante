package com.restaurante.infrastructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.restaurante.domain.Restaurant;
import com.restaurante.infrastructure.entity.RestaurantEntity;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mappings(
            {
                    @Mapping(source = "id", target = "id"),
                    @Mapping(source = "identification", target = "identification"),
                    @Mapping(source = "name", target = "name"),
                    @Mapping(source = "email", target = "email"),
                    @Mapping(source = "address", target = "address"),
                    @Mapping(source = "cellphone", target = "cellphone"),
                    @Mapping(source = "dateCreated", target = "dateCreated"),
                    @Mapping(source = "employee", target = "lstEmployees")
            }
    )

    Restaurant toRestaurant(RestaurantEntity restaurantEntity);

    Iterable<Restaurant> toRestaurants(Iterable<RestaurantEntity> restaurantEntities);

    @InheritInverseConfiguration
    RestaurantEntity toRestaurantEntity(Restaurant restaurant);
}
