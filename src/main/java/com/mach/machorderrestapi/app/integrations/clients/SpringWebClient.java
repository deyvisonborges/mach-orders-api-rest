package com.mach.machorderrestapi.app.integrations.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SpringWebClient {
	@Bean
	public WebClient webClient() {
		return WebClient.builder()
			.baseUrl("http://localhost:4002")
			.defaultCookie("cookie-name", "cookie-value")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}
}
