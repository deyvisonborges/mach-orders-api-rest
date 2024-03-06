package com.mach.machorderrestapi.app.integrations.catalogapi;

import com.mach.machorderrestapi.app.integrations.catalogapi.dto.ProductDTO;
import com.mach.machorderrestapi.shared.exception.IntegrationException;

import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CatalogApiClient {
	private final HttpGraphQlClient graphQlClient;

	public CatalogApiClient(WebClient.Builder builder) {
		WebClient webClient = builder.baseUrl("http://localhost:3000/graphql").build();
		this.graphQlClient = HttpGraphQlClient.builder(webClient).build();
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