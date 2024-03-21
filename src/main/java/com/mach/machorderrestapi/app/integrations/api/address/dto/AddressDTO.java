package com.mach.machorderrestapi.app.integrations.api.address.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressDTO(
	String id,
	String label,
	String address,
	String country,
	String province,
	String city,

	@JsonProperty("postal_code")
	String postalCode
) { }
