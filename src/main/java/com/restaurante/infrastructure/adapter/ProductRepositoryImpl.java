package com.restaurante.infrastructure.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.restaurante.application.repository.ProductRepository;
import com.restaurante.domain.Product;
import com.restaurante.domain.Restaurant;
import com.restaurante.infrastructure.entity.ProductEntity;
import com.restaurante.infrastructure.mapper.ProductMapper;
import com.restaurante.infrastructure.mapper.RestaurantMapper;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private final ProductCrudRepository productCrudRepository;
	private final RestaurantMapper restaurantMapper;
	private final ProductMapper productMapper;

	public ProductRepositoryImpl(ProductCrudRepository productCrudRepository, ProductMapper productMapper,
			RestaurantMapper restaurantMapper) {
		this.productCrudRepository = productCrudRepository;
		this.productMapper = productMapper;
		this.restaurantMapper = restaurantMapper;
	}

	@Override
	public Product crearProduct(Product product) {
		return productMapper.toProduct(productCrudRepository.save(productMapper.toProductEntity(product)));
	}

	@Override
	public Iterable<Product> buscarAll() {
		return productMapper.toProducts(productCrudRepository.findAll());
	}

	@Override
	public Product getProductById(Integer id) {
		Optional<ProductEntity> pro = productCrudRepository.findById(id);
		return (pro.isPresent() ? productMapper.toProduct(pro.get()) : null);
	}

	@Override
	public Iterable<Product> buscarByRestaurant(Restaurant restaurant) {
		Iterable<ProductEntity> productEntity = productCrudRepository
				.findByRestaurantEntity(restaurantMapper.toRestaurantEntity(restaurant));
		productMapper.toProducts(productEntity);
		return productMapper.toProducts(productEntity);
	}

}
