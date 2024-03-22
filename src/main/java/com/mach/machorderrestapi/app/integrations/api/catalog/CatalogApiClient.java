package com.mach.machorderrestapi.app.integrations.api.catalog;

import com.mach.machorderrestapi.app.integrations.api.catalog.dto.ProductDTO;
import com.mach.machorderrestapi.shared.exception.IntegrationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
public class CatalogApiClient {
	private final HttpGraphQlClient graphQlClient;

	@Value("${integration.url.api.catalog}")
	private String url;

	public CatalogApiClient(WebClient.Builder builder) {
		WebClient webClient = builder
			.baseUrl(url)
//			.filter(addJwtHeader())
			.build();
		this.graphQlClient = HttpGraphQlClient.builder(webClient).build();
	}

	private ExchangeFilterFunction addJwtHeader() {
		return (request, next) -> {
//			var jwtToken = Objects.requireNonNull(
//					request.headers().getFirst(HttpHeaders.AUTHORIZATION));
			var jwtToken = request.headers().getFirst(HttpHeaders.AUTHORIZATION);
			if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
				jwtToken = jwtToken.substring(7); // Remove o prefixo "Bearer " para obter somente o token JWT
			}
			final String finalJwtToken = jwtToken;

			request.headers().setBearerAuth("Bearer " + finalJwtToken);
			request.headers().add(HttpHeaders.AUTHORIZATION, "Bearer " + finalJwtToken);
			return next.exchange(request);
		};
	}

	@QueryMapping("getProductsByIds")
	public Mono<List<ProductDTO>> getProductsByIds(List<String> ids) {
		StringBuilder queryBuilder = new StringBuilder("query { findByIds(input: [");
		for (int i = 0; i < ids.size(); i++) {
			queryBuilder.append('"').append(ids.get(i)).append('"');
			if (i < ids.size() - 1) {
				queryBuilder.append(", ");
			}
		}
		queryBuilder.append("]) { ... on SimpleProductOutput { name\n")
				.append(" salePrice\n")
				.append(" description } } }");
		String query = queryBuilder.toString();

		try {
			System.out.println("entrei na consulta");
			return graphQlClient.document(query)
				.retrieve("findByIds")
				.toEntityList(ProductDTO.class);
		} catch (WebClientException e) {
			throw new IntegrationException(e.getMessage());
		}
	}

//	@Bean
//	CommandLineRunner runProductsByIds() {
//		return args -> {
////			Mono<List<ProductDTO>> products = this.getProductsByIds(List.of(UUID.randomUUID().toString()));
//			this.getProductsByIds(List.of(UUID.randomUUID().toString()));
//		};
//	}
}