package com.mach.machorderrestapi.app.integrations.catalogapi.__test__;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TestClient {
	private final WebClient webClient;

	public TestClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public Mono<Object> employeeMono() {
		var output = webClient.get()
				.uri("/pokemon/ditto")
				.retrieve()
				.bodyToMono(Object.class);
		System.out.println(output);
		return output;
	}
}