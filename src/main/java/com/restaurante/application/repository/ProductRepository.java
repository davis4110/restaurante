package com.restaurante.application.repository;

import com.restaurante.domain.Product;

public interface ProductRepository {

	public Product crearProduct(Product product);

	public Iterable<Product> buscarAll();
	
	Product getProductById(Integer id);

}
