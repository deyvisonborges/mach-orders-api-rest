package com.mach.machorderrestapi.app.api.order;

import com.mach.machorderrestapi.app.integrations.api.catalog.CatalogApiClient;
import com.mach.machorderrestapi.app.integrations.api.catalog.dto.ProductDTO;
import com.mach.machorderrestapi.core.artifact.order.Order;
import com.mach.machorderrestapi.app.api.order.service.CreateOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private final CreateOrderService createOrderService;
	private final CatalogApiClient catalogApiClient;

	public OrderController(CreateOrderService createOrderService, CatalogApiClient catalogApiClient) {
		this.createOrderService = createOrderService;
		this.catalogApiClient = catalogApiClient;
	}

	@GetMapping("/test")
	public Mono<List<ProductDTO>> test() {
		List<String> ids = List.of("f2b6bebe-013f-4522-a653-b2672f732e50");
		return catalogApiClient.getProductsByIds(ids);
	}

	@GetMapping
	public ResponseEntity<Order> create(@RequestBody CreateOrderService.CreateOrderServiceInput input) {
		return ResponseEntity.status(HttpStatus.CREATED).body(createOrderService.execute(input));
	}
}
