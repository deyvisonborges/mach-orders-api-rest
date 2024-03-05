package com.mach.machorderrestapi.app.integrations.catalogapi.__test__;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TestConnection {
	@Bean
	public WebClient webClient() {
		return WebClient.builder()
				.baseUrl("https://pokeapi.co/api/v2/")
				.defaultCookie("cookie-name", "cookie-value")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}
}
