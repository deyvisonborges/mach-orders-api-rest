package com.mach.machorderrestapi.app.integrations.catalogapi;

import com.mach.machorderrestapi.core.artifact.order.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CatalogApiClient {
	private final WebClient webClient;

	public CatalogApiClient(WebClient webClient) {
		this.webClient = webClient;
	}
//
//	private Mono<?> handleResponse(ClientResponse response) {
//
//		if (response.statusCode().is2xxSuccessful()) {
//			return response.bodyToMono(null);
//		}
//		else if (response.statusCode().is4xxClientError()) {
//			// Handle client errors (e.g., 404 Not Found)
//			return Mono.error(new EmployeeNotFoundException("Employee not found"));
//		}
//		else if (response.statusCode().is5xxServerError()) {
//			// Handle server errors (e.g., 500 Internal Server Error)
//			return Mono.error(new RuntimeException("Server error"));
//		}
//		else {
//			// Handle other status codes as needed
//			return Mono.error(new RuntimeException("Unexpected error"));
//		}
//	}
}
