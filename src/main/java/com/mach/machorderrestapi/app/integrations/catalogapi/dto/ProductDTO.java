package com.mach.machorderrestapi.app.integrations.catalogapi.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDTO(
	UUID id,
	String name,
	String description,
	BigDecimal salePrice
) {}
