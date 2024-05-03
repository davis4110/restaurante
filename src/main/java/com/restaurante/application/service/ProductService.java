package com.restaurante.application.service;

import com.restaurante.application.repository.ProductRepository;
import com.restaurante.domain.Product;

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
}
