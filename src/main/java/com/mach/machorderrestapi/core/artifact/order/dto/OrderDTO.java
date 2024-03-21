package com.mach.machorderrestapi.core.artifact.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mach.machorderrestapi.app.integrations.api.payments.dto.PaymentDTO;

import java.util.Set;

public record OrderDTO(
	String status,
	Set<OrderItemDTO> items,

	@JsonProperty("customer_id")
	String customerId,
	Set<PaymentDTO> payments,

	@JsonProperty("subtotal")
	double subTotal,

	@JsonProperty("shipping_fee")
	double shippingFee,

	double discount,
	double total
) {
}
