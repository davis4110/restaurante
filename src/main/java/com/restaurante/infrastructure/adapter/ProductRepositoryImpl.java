package com.restaurante.infrastructure.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.restaurante.application.repository.ProductRepository;
import com.restaurante.domain.Product;
import com.restaurante.infrastructure.entity.ProductEntity;
import com.restaurante.infrastructure.mapper.ProductMapper;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private final ProductCrudRepository productCrudRepository;

	private final ProductMapper productMapper;

	public ProductRepositoryImpl(ProductCrudRepository productCrudRepository, ProductMapper productMapper) {
		this.productCrudRepository = productCrudRepository;
		this.productMapper = productMapper;
	}

	@Override
	public Product crearProduct(Product product) {
		return productMapper.toProduct(productCrudRepository.save(productMapper.toProductEntity(product)));
	}

	@Override
	public Iterable<Product> buscarAll() {
		return productMapper.toProduct(productCrudRepository.findAll());
	}

	@Override
	public Product getProductById(Integer id) {
		Optional<ProductEntity> pro = productCrudRepository.findById(id);
		return (pro.isPresent() ? productMapper.toProduct(pro.get()) : null);
	}

}
