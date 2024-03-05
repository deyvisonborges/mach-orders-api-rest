package com.mach.machorderrestapi.app.api.order;

import com.mach.machorderrestapi.app.integrations.catalogapi.CatalogApiClient;
import com.mach.machorderrestapi.app.integrations.catalogapi.dto.ProductDTO;
import com.mach.machorderrestapi.core.artifact.order.Order;
import com.mach.machorderrestapi.core.artifact.order.service.CreateOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

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
	public Flux<ProductDTO> test() {
		List<UUID> ids = List.of(UUID.randomUUID());
		return catalogApiClient.getProductsByIds(ids);
	}

	@GetMapping
	public ResponseEntity<Order> create(@RequestBody Order.OrderRecord input) {
		return ResponseEntity.status(HttpStatus.CREATED).body(createOrderService.execute(input));
	}
}
