package com.restaurante.infrastructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.restaurante.domain.Owner;
import com.restaurante.infrastructure.entity.OwnerEntity;

@Mapper(componentModel = "spring", uses = {RestaurantMapper.class})
public interface OwnerMapper {

    @Mappings(
            {
                    @Mapping(source = "id", target = "id"),
                    @Mapping(source = "identification", target = "identification"),
                    @Mapping(source = "firstName", target = "firstName"),
                    @Mapping(source = "lastName", target = "lastName"),
                    @Mapping(source = "email", target = "email"),
                    @Mapping(source = "address", target = "address"),
                    @Mapping(source = "cellphone", target = "cellphone"),
                    @Mapping(source = "dateCreated", target = "dateCreated"),
                    @Mapping(source = "restaurant", target = "restaurant")
            }
    )

    Owner toOwner(OwnerEntity ownerEntity);

    Iterable<Owner> toOwners(Iterable<OwnerEntity> ownerEntities);

    @InheritInverseConfiguration
    OwnerEntity toOwnerEntity(Owner owner);
}
