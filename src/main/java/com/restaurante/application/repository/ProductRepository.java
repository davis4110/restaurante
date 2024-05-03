package com.restaurante.application.repository;

import com.restaurante.domain.Product;
import com.restaurante.domain.Restaurant;

public interface ProductRepository {

	public Product crearProduct(Product product);

	public Iterable<Product> buscarAll();

	public Product getProductById(Integer id);

	public Iterable<Product> buscarByRestaurant(Restaurant restaurant);

}
