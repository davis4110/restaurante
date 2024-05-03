package com.restaurante.infrastructure.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurante.application.service.OrderProductService;
import com.restaurante.application.service.ProductService;
import com.restaurante.domain.Order;
import com.restaurante.domain.OrderProduct;
import com.restaurante.domain.Product;
import com.restaurante.domain.Reporte;
import com.restaurante.domain.Restaurant;
import com.restaurante.infrastructure.dto.OrderBillDTO;
import com.restaurante.infrastructure.dto.OrderDto;
import com.restaurante.infrastructure.dto.OrderProductBillDTO;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {

	private final ProductService productService;

	private final OrderProductService orderProductService;

	public OrderController(ProductService productService, OrderProductService orderProductService) {
		this.productService = productService;
		this.orderProductService = orderProductService;
	}

	@PostMapping("/create-order")
	public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto orderDto, BindingResult bindingResult) {
		Map<String, Object> response = new HashMap<>();
		response.put("exito", false);
		HttpStatus status = HttpStatus.CREATED;
		if (bindingResult.hasErrors()) {
			List<String> lstErrores = new ArrayList<String>();
			bindingResult.getAllErrors().forEach(e -> {
				lstErrores.add(e.getDefaultMessage());
				log.info("Error: {}", e.getDefaultMessage());
			});
			status = HttpStatus.BAD_REQUEST;
			response.put("mensaje", "Error en los datos enviados");
			response.put("error", lstErrores);
			return new ResponseEntity<Map<String, Object>>(response, status);
		}
		try {
			boolean errorProduct = false;
			BigDecimal total = BigDecimal.ZERO;
			List<OrderProduct> lstOrderProduct = new ArrayList<OrderProduct>();
			for (OrderBillDTO orderBill : orderDto.getLstListOrders()) {
				Product product = productService.getProductById(orderBill.getIdProduct());
				if (product != null) {

					OrderProduct orderProduct = new OrderProduct();
					orderProduct.setOrder(new Order());
					orderProduct.setProduct(product);
					orderProduct.setQuantity(orderBill.getQuantity());
					orderProduct.setTotal(orderProduct.getTotalPrice());
					log.info("Total: " + orderProduct.getTotal());
					total = total.add(orderProduct.getTotal());
					lstOrderProduct.add(orderProduct);
				} else {
					errorProduct = true;
					break;
				}
			}
			if (errorProduct) {
				status = HttpStatus.BAD_REQUEST;
				response.put("mensaje", "El ID del producto no Existe!");
				response.put("exito", false);
				return new ResponseEntity<Map<String, Object>>(response, status);
			}
			Order order = new Order();
			order.setDateCreated(LocalDateTime.now());
			order.setTotalBill(total);
			OrderProductBillDTO orderProductBill = new OrderProductBillDTO();
			orderProductBill.setLstOrderProduct(lstOrderProduct);
			orderProductBill.setOrderBill(order);
			order = orderProductService.create(orderProductBill);
			if (order != null) {
				response.put("mensaje", "Orden creada correctamente");
				response.put("exito", true);
			} else {
				response.put("mensaje", "Orden NO creada");
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, status);
		}

		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@GetMapping(value = "/consultar-restaurant/{id}")
	public ResponseEntity<?> findAllRestaurant(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		response.put("exito", false);
		HttpStatus status = HttpStatus.OK;
		try {
			Restaurant restaurant = new Restaurant();
			restaurant.setId(id);
			Iterable<Product> lstProducts = productService.buscarByRestaurant(restaurant);
			if (lstProducts != null) {
				response.put("mensaje", "OK");
				response.put("ventas_by_restaurante", lstProducts);
				response.put("exito", true);
			} else {
				response.put("mensaje", "No existen registros");
				response.put("employees", null);
				response.put("exito", false);
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, status);
		}

		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@GetMapping(value = "/consultar")
	public ResponseEntity<?> findAll() {
		Map<String, Object> response = new HashMap<>();
		response.put("exito", false);
		HttpStatus status = HttpStatus.OK;
		try {
			List<Reporte> lstReporte = orderProductService.buscarReporte();
			if (lstReporte != null) {
				response.put("mensaje", "OK");
				response.put("ventas", lstReporte);
				response.put("exito", true);
			} else {
				response.put("mensaje", "No existen registros");
				response.put("employees", null);
				response.put("exito", false);
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, status);
		}

		return new ResponseEntity<Map<String, Object>>(response, status);
	}
}
