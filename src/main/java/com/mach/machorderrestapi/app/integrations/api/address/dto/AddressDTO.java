package com.mach.machorderrestapi.app.integrations.api.address.dto;

public record AddressDTO(
	String id,
	String label,
	String address,
	String country,
	String province,
	String city,
	String postalCode
) { }
