package com.restaurante.infrastructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.restaurante.domain.Order;
import com.restaurante.infrastructure.entity.OrderEntity;

@Mapper(componentModel = "spring", uses = {OrderProductMapper.class, ProductMapper.class})
public interface OrderMapper {

    @Mappings(
            {
                    @Mapping(source = "id", target = "id"),
                    @Mapping(source = "dateCreated", target = "dateCreated"),
                    @Mapping(source = "totalBill", target = "totalBill"),
            }
    )
    Order toOrder(OrderEntity orderEntity);

    Iterable<Order> toOrders(Iterable<OrderEntity> orderEntities);

    @InheritInverseConfiguration
    OrderEntity toOrderEntity(Order order);
}
