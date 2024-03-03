package com.mach.machorderrestapi.app.api.integrations.dto;

public class ProductDTO {
	private Long id;
	private String name;
	private String description;

	public ProductDTO() {}

	public ProductDTO(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
}
