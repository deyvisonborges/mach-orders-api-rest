package com.mach.machorderrestapi.app.integrations.identityapi;

import com.mach.machorderrestapi.app.integrations.catalogapi.CustomWebClient;
import com.mach.machorderrestapi.app.integrations.identityapi.dto.CustomerDTO;
import com.mach.machorderrestapi.shared.exception.IntegrationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@Service
public class IdentityApiClient {
	private final WebClient webClient;

	public IdentityApiClient(WebClient.Builder builder) {
		this.webClient = builder.baseUrl("http://localhost:4000/api/identity").build();
	}

	public Mono<CustomerDTO> getCustomerById(String id) {
		try {
			var customer = this.webClient.get()
				.uri("/management/user/" + id)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(CustomerDTO.class);

			System.out.println(customer);
			return customer;
		} catch (WebClientException e) {
			throw new IntegrationException(e.getMessage());
		}
	}
}
