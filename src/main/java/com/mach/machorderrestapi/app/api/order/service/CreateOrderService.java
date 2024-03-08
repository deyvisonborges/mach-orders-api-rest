package com.mach.machorderrestapi.app.api.order.service;

import com.mach.machorderrestapi.app.integrations.catalogapi.CatalogApiClient;
import com.mach.machorderrestapi.app.integrations.identityapi.IdentityApiClient;
import com.mach.machorderrestapi.app.integrations.identityapi.dto.CustomerDTO;
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
//	private final CatalogApiClient catalogApiClient;
	private final IdentityApiClient identityApiClient;
	private final OrderRepositoryContract orderRepositoryContract;

	public CreateOrderService(IdentityApiClient identityApiClient) {
//		this.catalogApiClient = catalogApiClient;
		this.identityApiClient = identityApiClient;
		this.orderRepositoryContract = new OrderInMemoryRepository();
	}

	public record CreateOrderServiceInput(
		 OrderStatus status,
		 List<String> orderItems,
		 BigDecimal total,
		 UUID customerId
	){}

	public Order execute(CreateOrderServiceInput input) {
		System.out.println(input);
		CustomerDTO customer = this.identityApiClient.getCustomerById(input.customerId().toString()).block();
		System.out.println(customer);
		return null;
//
//		assert customer != null;
//		var order = new Order(input.status, new BigDecimal(String.valueOf(input.total)), customer.id());
////		this.catalogApiClient.getProductsByIds(
////					orderItems
////					.stream()
////					.map(item -> item.getProductId().toString())
////					.collect(Collectors.toList())
////		).flatMapMany(Flux::fromIterable)
////				.flatMap(product -> {
////					var orderItem =
////						new OrderItem(
////							product.id(),
////							product.salePrice(),
////							1 // TODO -> add in ProductDTO on CatalogAPI
////						);
////					return Mono.just(orderItem);
////				})
////				.collectList()
////				.subscribe(oi -> {
////					oi.forEach(order::addOrderItem);
////					order.setStatus(OrderStatus.ORDER_PLACED);
////					orderRepositoryContract.save(order);
////				});
//		orderRepositoryContract.save(order);
//		return order;
	}
}

