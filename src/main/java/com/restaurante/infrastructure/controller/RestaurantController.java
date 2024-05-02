package com.restaurante.infrastructure.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.restaurante.application.service.RestaurantService;
import com.restaurante.infrastructure.dto.RestaurantDto;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/restaurante")
@Slf4j
public class RestaurantController {

	private final RestaurantService restaurantService;

	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@PostMapping
	public ResponseEntity<?> registerRestaurant(@Valid @RequestBody RestaurantDto restaurantDto,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
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
			restaurantService.crearRestaurant(restaurantDto.restaurantDtoToRestaurant());
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, status);
		}

		response.put("mensaje", "Restaurante creado correctamente");
		response.put("exito", true);
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

}
