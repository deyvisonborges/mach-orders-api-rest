package com.mach.machorderrestapi.app.api.order;

import com.mach.machorderrestapi.app.integrations.catalogapi.__test__.TestClient;
import com.mach.machorderrestapi.app.integrations.catalogapi.__test__.TestConnection;
import com.mach.machorderrestapi.core.artifact.order.Order;
import com.mach.machorderrestapi.core.artifact.order.service.CreateOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private final CreateOrderService createOrderService;

	public OrderController(CreateOrderService createOrderService) {
		this.createOrderService = createOrderService;
	}

	public ResponseEntity<Order> create(@RequestBody Order.OrderRecord input) {
		new TestClient(new TestConnection().webClient()).employeeMono();
		return ResponseEntity.status(HttpStatus.CREATED).body(createOrderService.execute(input));
	}
}
