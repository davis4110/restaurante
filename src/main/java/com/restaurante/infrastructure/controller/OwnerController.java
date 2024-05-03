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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.restaurante.application.service.OwnerService;
import com.restaurante.application.service.RestaurantService;
import com.restaurante.domain.Owner;
import com.restaurante.domain.Restaurant;
import com.restaurante.infrastructure.dto.OwnerDto;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/owner")
@Slf4j
public class OwnerController {

	private final OwnerService ownerService;

	private final RestaurantService restaurantService;

	public OwnerController(OwnerService ownerService, RestaurantService restaurantService) {
		this.ownerService = ownerService;
		this.restaurantService = restaurantService;
	}

	@PostMapping
	public ResponseEntity<?> registerOwner(@Valid @RequestBody OwnerDto ownerDto, BindingResult bindingResult,
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
			Restaurant restaurant = restaurantService.buscarById(Integer.parseInt(ownerDto.getIdRestaurant()));
			if (restaurant != null) {
				List<Restaurant> lstRestaurant = new ArrayList<>();
				lstRestaurant.add(restaurant);
				ownerDto.setLstRestaurant(lstRestaurant);
				ownerService.crearOwner(ownerDto.ownerDtoToOwner());
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

		response.put("mensaje", "Owner creado correctamente");
		response.put("exito", true);
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@GetMapping(value = "/consultar/{id}")
	public ResponseEntity<?> findAll(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		response.put("exito", false);
		HttpStatus status = HttpStatus.OK;
		Owner owner;
		try {
			owner = ownerService.buscarById(id);
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, status);
		}

		if (owner != null) {
			response.put("mensaje", "OK");
			response.put("owner", owner);
			response.put("exito", true);
		} else {
			response.put("mensaje", "No existen registros");
			response.put("owner", owner);
			response.put("exito", false);
		}

		return new ResponseEntity<Map<String, Object>>(response, status);
	}
}
