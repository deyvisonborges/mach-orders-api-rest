package com.mach.machorderrestapi.core.artifact.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record OrderItemDTO(
		@JsonProperty("product_id")
		String productId,
		BigDecimal price,
		int quantity
) { }
