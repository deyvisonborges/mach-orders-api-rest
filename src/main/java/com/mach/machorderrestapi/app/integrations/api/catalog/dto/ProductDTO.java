package com.mach.machorderrestapi.app.integrations.api.catalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDTO(
	UUID id,
	String name,
	String description,

	@JsonProperty("sale_price")
	BigDecimal salePrice
) {}
