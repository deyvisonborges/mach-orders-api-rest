package com.mach.machorderrestapi.app.api.order.service;

import com.mach.machorderrestapi.app.integrations.api.payments.dto.PaymentDTO;
import com.mach.machorderrestapi.core.artifact.order.dto.OrderItemDTO;

import java.util.Set;

public record CreateOrderOutput(
	String status,
	Set<OrderItemDTO> items,
	String customerId,
	Set<PaymentDTO> payments,
	double subTotal,
	double shippingFee,
	double discount,
	double total
) {
}
