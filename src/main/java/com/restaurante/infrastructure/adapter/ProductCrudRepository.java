package com.restaurante.infrastructure.adapter;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.restaurante.domain.Reporte;
import com.restaurante.infrastructure.entity.ProductEntity;
import com.restaurante.infrastructure.entity.RestaurantEntity;

public interface ProductCrudRepository extends CrudRepository<ProductEntity, Integer> {

	Iterable<ProductEntity> findByRestaurantEntity(RestaurantEntity restaurantEntity);

	@Query(value = " SELECT   "
			+ " res.id AS idRestaurante, res.name AS nombreRestaurante, "
			+ " pro.id AS idProducto, pro.code as codigoProducto, pro.name AS nombreProducto,  "
			+ " orp.quantity AS cantidadProductos,  pro.price AS valorProducto, "
			+ " orp.total AS totalProducto, ord.total_Bill AS totalFactura, ord.id AS idOrden "
			+ " FROM orders_products orp " + " INNER JOIN product pro ON orp.product_id = pro.id "
			+ " INNER JOIN restaurant res ON pro.restaurant_entity_id =  res.id "
			+ " INNER JOIN orders ord ON ord.id = orp.order_id ", nativeQuery = true)
	List<Reporte> consultarReporte();

}
