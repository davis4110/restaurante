package com.restaurante.infrastructure.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.restaurante.domain.OrderProduct;
import com.restaurante.infrastructure.entity.OrderProductEntity;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, OrderMapper.class})
public interface OrderProductMapper {

    @Mappings(
            {
                    @Mapping(source = "pk.productEntity", target = "product"),
                    @Mapping(source = "quantity", target = "quantity"),
                    @Mapping(source = "total", target = "total"),
                    @Mapping(source = "pk.orderEntity", target = "order"),
            }
    )
    OrderProduct toOrderProduct(OrderProductEntity orderProductEntity);

    Iterable<OrderProduct> toOrderProducts(Iterable<OrderProductEntity> orderProductEntities);

    List<OrderProduct> toOrderProductList(Iterable<OrderProductEntity> orderProductEntities);

    @InheritInverseConfiguration
    OrderProductEntity toOrderProductEntity(OrderProduct orderProduct);
}
