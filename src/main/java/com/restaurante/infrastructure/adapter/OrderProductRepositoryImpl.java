package com.restaurante.infrastructure.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.restaurante.application.repository.OrderProductRepository;
import com.restaurante.domain.Order;
import com.restaurante.domain.OrderProduct;
import com.restaurante.domain.Product;
import com.restaurante.domain.Reporte;
import com.restaurante.infrastructure.dto.OrderProductBillDTO;
import com.restaurante.infrastructure.entity.OrderEntity;
import com.restaurante.infrastructure.entity.OrderProductEntity;
import com.restaurante.infrastructure.entity.ProductEntity;
import com.restaurante.infrastructure.mapper.OrderMapper;
import com.restaurante.infrastructure.mapper.OrderProductMapper;
import com.restaurante.infrastructure.mapper.ProductMapper;

@Repository
public class OrderProductRepositoryImpl implements OrderProductRepository {

	private final OrderProductCrudRepository orderProductCrudRepository;
	private final OrderCrudRepository orderCrudRepository;
	private final OrderProductMapper orderProductMapper;
	private final OrderMapper orderMapper;
	private final ProductMapper productMapper;

	public OrderProductRepositoryImpl(OrderProductCrudRepository orderProductCrudRepository,
			OrderCrudRepository orderCrudRepository, OrderProductMapper orderProductMapper, OrderMapper orderMapper,
			ProductMapper productMapper) {
		this.orderProductCrudRepository = orderProductCrudRepository;
		this.orderCrudRepository = orderCrudRepository;
		this.orderProductMapper = orderProductMapper;
		this.orderMapper = orderMapper;
		this.productMapper = productMapper;
	}

	@Override
	public Order create(OrderProductBillDTO orderProductBill) {
		OrderEntity orderEntity = orderCrudRepository.save(orderMapper.toOrderEntity(orderProductBill.getOrderBill()));
		Order order = orderMapper.toOrder(orderEntity);
		for (OrderProduct orderProduct : orderProductBill.getLstOrderProduct()) {
			orderProduct.setOrder(order);
			OrderProductEntity orderPro = orderProductMapper.toOrderProductEntity(orderProduct);
			orderProductMapper.toOrderProduct(orderProductCrudRepository.save(orderPro));
		}
		return order;
	}

	@Override
	public Iterable<OrderProduct> getOrderProducts() {
		return orderProductMapper.toOrderProducts(orderProductCrudRepository.findAll());
	}

	@Override
	public List<OrderProduct> getOrdersProductByProduct(Product product) {
		ProductEntity productEntity = productMapper.toProductEntity(product);
		List<OrderProductEntity> ss = orderProductCrudRepository.findByPkProductEntity(productEntity);
		return orderProductMapper.toOrderProductList(ss);
	}

	@Override
	public List<Reporte> consultarReporte() {
		List<Reporte> lstReportes = new ArrayList<Reporte>();
		Reporte reporte = new Reporte();
		Iterable<OrderProduct> ss = getOrderProducts();
		ss.forEach(repo -> {
			reporte.setIdRestaurante(repo.getProduct().getRestaurant().getId());
			reporte.setNombreRestaurante(repo.getProduct().getRestaurant().getName());
			reporte.setIdProducto(repo.getProduct().getId());
			reporte.setCodigoProducto(repo.getProduct().getCode());
			reporte.setNombreProducto(repo.getProduct().getName());
			reporte.setCantidadProductos(repo.getQuantity());
			reporte.setValorProducto(repo.getProduct().getPrice());
			reporte.setTotalProducto(Integer.valueOf(repo.getTotal().toString()));
			reporte.setTotalFactura(repo.getOrder().getTotalBill());
			reporte.setIdOrden(repo.getOrder().getId());
			lstReportes.add(reporte);
		});
		return lstReportes;
	}
}
