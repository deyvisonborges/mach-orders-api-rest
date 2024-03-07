package com.mach.machorderrestapi.core.artifact.order.service;

import com.mach.machorderrestapi.app.integrations.catalogapi.CatalogApiClient;
import com.mach.machorderrestapi.core.artifact.order.Order;
import com.mach.machorderrestapi.core.artifact.order.OrderItem;
import com.mach.machorderrestapi.core.artifact.order.OrderStatus;
import com.mach.machorderrestapi.core.artifact.order.repository.OrderInMemoryRepository;
import com.mach.machorderrestapi.core.artifact.order.repository.OrderRepositoryContract;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreateOrderService {
	private final CatalogApiClient catalogApiClient;
	private final OrderRepositoryContract orderRepositoryContract;

	public CreateOrderService(CatalogApiClient catalogApiClient) {
		this.catalogApiClient = catalogApiClient;
		this.orderRepositoryContract = new OrderInMemoryRepository();
	}

	public record CreateOrderServiceInput(
		 OrderStatus status,
		 List<OrderItem> orderItems,
		 BigDecimal total,
		 UUID customerId
	){}

//	public Order execute(CreateOrderServiceInput input) {
	public Order execute() {
		var orderItems = new ArrayList<OrderItem>();
		orderItems.add(new OrderItem(UUID.fromString("f2b6bebe-013f-4522-a653-b2672f732e50"), new BigDecimal(10), 1));
		var order = new Order(OrderStatus.ORDER_PLACED, new BigDecimal("0.254523234"), UUID.randomUUID());
		this.catalogApiClient.getProductsByIds(
					orderItems
					.stream()
					.map(item -> item.getProductId().toString())
					.collect(Collectors.toList())
		).flatMapMany(Flux::fromIterable)
				.flatMap(product -> {
					var orderItem =
						new OrderItem(
							product.id(),
							product.salePrice(),
							1 // TODO -> add in ProductDTO on CatalogAPI
						);
					return Mono.just(orderItem);
				})
				.collectList()
				.subscribe(oi -> {
					oi.forEach(order::addOrderItem);
					order.setStatus(OrderStatus.ORDER_PLACED);
					orderRepositoryContract.save(order);
				});
		orderRepositoryContract.save(order);
		return order;
	}

}

