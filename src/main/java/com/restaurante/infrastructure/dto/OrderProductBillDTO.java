package com.restaurante.infrastructure.dto;

import java.io.Serializable;
import java.util.List;

import com.restaurante.domain.Order;
import com.restaurante.domain.OrderProduct;

import lombok.Data;

@Data
public class OrderProductBillDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<OrderProduct> lstOrderProduct;
	private Order orderBill;
}
