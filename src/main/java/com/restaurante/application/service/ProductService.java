package com.restaurante.application.service;

import com.restaurante.application.repository.ProductRepository;
import com.restaurante.domain.Product;
import com.restaurante.domain.Restaurant;

public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product crearProduct(Product product) {
		return productRepository.crearProduct(product);
	}

	public Iterable<Product> buscarAll() {
		return productRepository.buscarAll();
	}

	public Product getProductById(Integer id) {
		return productRepository.getProductById(id);
	}

	public Iterable<Product> buscarByRestaurant(Restaurant restaurant) {
		return productRepository.buscarByRestaurant(restaurant);
	}

}
