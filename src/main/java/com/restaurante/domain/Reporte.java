package com.restaurante.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Reporte {

	private Integer idRestaurante;
	private String nombreRestaurante;
	private Integer idProducto;
	private String codigoProducto;
	private String nombreProducto;
	private Integer cantidadProductos;
	private BigDecimal valorProducto;
	private Integer totalProducto;
	private BigDecimal totalFactura;
	private Integer idOrden;
}
