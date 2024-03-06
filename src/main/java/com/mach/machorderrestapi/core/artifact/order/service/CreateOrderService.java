package com.mach.machorderrestapi.core.artifact.order.service;

import com.mach.machorderrestapi.app.integrations.catalogapi.CatalogApiClient;
import com.mach.machorderrestapi.common.base.ServiceContract;
import com.mach.machorderrestapi.core.artifact.order.Order;
import com.mach.machorderrestapi.core.artifact.order.OrderItem;
import com.mach.machorderrestapi.core.artifact.order.OrderStatus;
import com.mach.machorderrestapi.core.artifact.order.repository.OrderRepositoryContract;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreateOrderService extends ServiceContract<Order.OrderRecord, Order> {
	private final OrderRepositoryContract orderRepositoryContract;
	private final CatalogApiClient catalogApiClient;

	public CreateOrderService(CatalogApiClient catalogApiClient, OrderRepositoryContract orderRepositoryContract) {
		this.catalogApiClient = catalogApiClient;
		this.orderRepositoryContract = orderRepositoryContract;
	}

	@Override
	public Order execute(Order.OrderRecord input) {
		var order = new Order(input);
		this.catalogApiClient.getProductsByIds(
				input.orderItems()
					.stream()
					.map(item -> item.getProductId().toString())
					.collect(Collectors.toList())
		)
			.flatMapMany(Flux::fromIterable)
				.flatMap(product -> {
					OrderItem.OrderItemBuilder itemBuilder = new OrderItem.OrderItemBuilder()
						.id(UUID.randomUUID())
						.active(true)
						.createdAt(LocalDateTime.now())
						.updatedAt(LocalDateTime.now())
						.productId(product.id())
						.price(product.salePrice())
						.quantity(input.orderItems().toArray().length);

					return Mono.just(itemBuilder.build());
				})
				.collectList()
				.subscribe(orderItems -> {
					orderItems.forEach(order::addOrderItem); // Adiciona os itens de pedido ao pedido
					order.setStatus(OrderStatus.ORDER_PLACED);
					orderRepositoryContract.save(order);
				});
		order.setStatus(OrderStatus.ORDER_PLACED);
		orderRepositoryContract.save(order);
		return order;
	}

}

