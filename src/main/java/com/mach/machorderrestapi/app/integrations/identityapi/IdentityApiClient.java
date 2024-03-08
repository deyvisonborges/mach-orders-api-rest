package com.mach.machorderrestapi.app.integrations.identityapi;

import com.mach.machorderrestapi.app.integrations.identityapi.dto.CustomerDTO;
import com.mach.machorderrestapi.shared.exception.IntegrationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@Service
public class IdentityApiClient {
	private final WebClient webClient;

	@Value("${integration.url.api.identity}")
	private String url;

	@Value("${integration.security.jwt.secret}")
	private String token;

	public IdentityApiClient(WebClient.Builder builder) {
		this.webClient = builder.baseUrl(url)
			.filter(((request, next) -> {
				ClientRequest newRequest = ClientRequest.from(request)
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
					.build();
				return next.exchange(newRequest);
			}))
			.build();
	}

	public Mono<CustomerDTO> getCustomerById(String id) {
		try {
			return this.webClient.get()
				.uri("/management/user/" + id)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(CustomerDTO.class);
		} catch (WebClientException e) {
			throw new IntegrationException(e.getMessage());
		}
	}
}
