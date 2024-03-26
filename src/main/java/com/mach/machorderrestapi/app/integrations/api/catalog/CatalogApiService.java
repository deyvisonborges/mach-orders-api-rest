package com.mach.machorderrestapi.app.integrations.api.catalog;

import com.mach.machorderrestapi.app.integrations.api.catalog.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class CatalogApiService {
	private final CatalogApiClient catalogApiClient;

	public CatalogApiService(CatalogApiClient catalogApiClient) {
		this.catalogApiClient = catalogApiClient;
	}

	public List<ProductDTO> getProducts(Set<String> items) {
		return this.catalogApiClient.getProductsByIds(
			items.stream().toList()
		).block();
	}
}
