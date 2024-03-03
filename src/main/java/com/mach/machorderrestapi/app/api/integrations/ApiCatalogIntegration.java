package com.mach.machorderrestapi.app.api.integrations;

import com.mach.machorderrestapi.app.api.integrations.dto.ProductDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;
import java.util.List;
import java.util.UUID;

@Component
public class ApiCatalogIntegration {
	private final RestTemplate restTemplate;
	private final HttpHeaders httpHeaders;

	public ApiCatalogIntegration(RestTemplate restTemplate, HttpHeaders httpHeaders) {
		this.restTemplate = restTemplate;
		this.httpHeaders = httpHeaders;
	}

	public List<ProductDTO> getProductsByIds(List<UUID> ids) {
		try {
			ResponseEntity<List<ProductDTO>> response = restTemplate
				.exchange( "",
						HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductDTO>>() {});
			return response.getBody();
		} catch (RestClientException e) {
			throw new RestClientException(e.getMessage());
		}
	}
}
