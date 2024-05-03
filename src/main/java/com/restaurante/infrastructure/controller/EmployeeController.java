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

import com.restaurante.application.service.EmployeeService;
import com.restaurante.application.service.RestaurantService;
import com.restaurante.domain.Employee;
import com.restaurante.domain.Restaurant;
import com.restaurante.infrastructure.dto.EmployeeDto;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

	private final EmployeeService employeeService;

	private final RestaurantService restaurantService;

	public EmployeeController(EmployeeService employeeService, RestaurantService restaurantService) {
		this.employeeService = employeeService;
		this.restaurantService = restaurantService;
	}

	@PostMapping
	public ResponseEntity<?> registerRestaurant(@Valid @RequestBody EmployeeDto employeeDto,
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
			Restaurant restaurant = restaurantService.buscarById(Integer.parseInt(employeeDto.getIdRestaurant()));
			if (restaurant != null) {
				Employee employee = employeeService.crearEmployee(employeeDto.employeeDtoToEmployee());
				if (employee != null) {
					restaurant.getLstEmployees().add(employee);
					restaurantService.crearRestaurant(restaurant);
				}
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

		response.put("mensaje", "Empleado creado correctamente");
		response.put("exito", true);
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@GetMapping(value = "/consultar/{id}")
	public ResponseEntity<?> findAll(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		response.put("exito", false);
		HttpStatus status = HttpStatus.OK;
		try {
			Restaurant restaurant = restaurantService.buscarById(id);
			if (restaurant != null && restaurant.getLstEmployees().size() > 0) {
				response.put("mensaje", "OK");
				response.put("employees", restaurant.getLstEmployees());
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
