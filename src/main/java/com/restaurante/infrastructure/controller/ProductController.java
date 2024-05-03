package com.restaurante.infrastructure.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.restaurante.application.service.ProductService;
import com.restaurante.domain.Product;
import com.restaurante.infrastructure.dto.ProductDto;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<?> registerProduct(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
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
			Product product = productService.crearProduct(productDto.productDtoToProduct());
			crearProductos();
			if (product != null) {
				response.put("mensaje", "Producto creado correctamente");
				response.put("exito", true);
			} else {
				status = HttpStatus.BAD_REQUEST;
				response.put("mensaje", "El ID del restaurante no Existe!");
				response.put("exito", false);
				return new ResponseEntity<Map<String, Object>>(response, status);
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, status);
		}

		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@GetMapping
	public ResponseEntity<?> findAll(HttpSession httpSession) {
		Map<String, Object> response = new HashMap<>();
		response.put("exito", false);
		HttpStatus status = HttpStatus.OK;
		try {
			Iterable<Product> iteProduct = productService.buscarAll();
			if (iteProduct != null) {
				List<Product> lstProducts = new ArrayList<Product>();
				iteProduct.forEach(product -> {
					lstProducts.add(product);
				});
				response.put("mensaje", "OK");
				response.put("products", lstProducts);
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

	private void crearProductos() {
		for (int i = 0; i < 10; i++) {
			int dato = getRandom(1, 20);
			ProductDto productDto = new ProductDto("Carne " + dato, "Descripcion " + dato,
					String.valueOf(Math.round(getRandom(5000, 50000) / 1000.0) * 1000.0));
			productService.crearProduct(productDto.productDtoToProduct());
		}
	}

	public static int getRandom(int min, int max) {
		int range = (max - min) + 1;
		int random = (int) ((range * Math.random()) + min);
		return random;
	}

}
