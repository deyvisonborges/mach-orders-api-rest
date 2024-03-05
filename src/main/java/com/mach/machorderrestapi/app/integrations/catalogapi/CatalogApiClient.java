package com.mach.machorderrestapi.app.integrations.catalogapi;

import com.mach.machorderrestapi.app.integrations.catalogapi.dto.ProductDTO;
import com.mach.machorderrestapi.shared.exception.IntegrationException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@Service
public class CatalogApiClient {
	private final WebClient webClient;

	public CatalogApiClient(WebClient.Builder builder) {
		this.webClient = builder.baseUrl("https://localhost:3000/graphql").build();
	}

	public Flux<ProductDTO> getProductsByIds(List<UUID> ids) {
		try {
			return webClient.post()
					.uri("/")
					.bodyValue("{\"query\":\"{ products { id name price } }\"}")
					.retrieve()
					.bodyToFlux(ProductDTO.class);
		} catch (WebClientException e) {
			throw new IntegrationException(e.getMessage());
		}
	}
}