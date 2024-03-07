package com.mach.machorderrestapi.app.integrations.catalogapi;

import com.mach.machorderrestapi.app.integrations.identityapi.dto.CustomerDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class CustomWebClient {
	@Bean
	public WebClient webClient() {
		return WebClient.builder()
				.baseUrl("http://localhost:3000")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	public static <T> Mono<T> handleResponse(ClientResponse response, Class<T> responseType) {
		if (response.statusCode().is2xxSuccessful()) {
			return response.bodyToMono(responseType);
		} else if (response.statusCode().is4xxClientError()) {
			return Mono.error(new RuntimeException("Client error: " + response.statusCode()));
		} else if (response.statusCode().is5xxServerError()) {
			return Mono.error(new RuntimeException("Server error: " + response.statusCode()));
		} else {
			return Mono.error(new RuntimeException("Unexpected error: " + response.statusCode()));
		}
	}
}
