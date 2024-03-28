package com.mach.machorderrestapi.app.api.order;

import com.mach.machorderrestapi.core.artifact.order.Order;
import com.mach.machorderrestapi.core.artifact.order.event.OrderQueryListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderQueryController {

	private final OrderQueryListener orderQueryListener;

	public OrderQueryController(OrderQueryListener orderQueryListener) {
		this.orderQueryListener = orderQueryListener;
	}

	@GetMapping("/search")
	public ResponseEntity<List<Order>> searchOrders() {
		List<Order> orders = orderQueryListener.searchOrders();
		return ResponseEntity.ok(orders);
	}
}
