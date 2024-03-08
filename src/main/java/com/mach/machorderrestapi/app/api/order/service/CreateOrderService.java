package com.mach.machorderrestapi.app.api.order.service;

import com.mach.machorderrestapi.app.integrations.catalogapi.CatalogApiClient;
import com.mach.machorderrestapi.app.integrations.identityapi.IdentityApiClient;
import com.mach.machorderrestapi.app.integrations.identityapi.dto.CustomerDTO;
import com.mach.machorderrestapi.app.persistence.order.springjpa.OrderJPAMapper;
import com.mach.machorderrestapi.app.persistence.order.springjpa.OrderJPARepository;
import com.mach.machorderrestapi.core.artifact.order.Order;
import com.mach.machorderrestapi.core.artifact.order.OrderItem;
import com.mach.machorderrestapi.core.artifact.order.OrderStatus;
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
	private final IdentityApiClient identityApiClient;
	private final OrderJPARepository orderJPARepository;

	public CreateOrderService(CatalogApiClient catalogApiClient, IdentityApiClient identityApiClient, OrderJPARepository orderJPARepository) {
		this.catalogApiClient = catalogApiClient;
		this.identityApiClient = identityApiClient;
		this.orderJPARepository = orderJPARepository;
	}

	public record CreateOrderServiceInput(
		 OrderStatus status,
		 List<String> orderItemsIds,
		 BigDecimal total,
		 UUID customerId
	){}

	public Order execute(CreateOrderServiceInput input) {
		CustomerDTO customer = this.identityApiClient.getCustomerById(input.customerId().toString()).block();
		assert customer != null;
		var order = new Order(input.status, new BigDecimal(String.valueOf(input.total)), customer.id());

		this.catalogApiClient.getProductsByIds(
					input.orderItemsIds
					.stream()
					.map(item -> item.toString())
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
					orderJPARepository.save(OrderJPAMapper.toJPAEntity(order));
				});
		orderJPARepository.save(OrderJPAMapper.toJPAEntity(order));
		return order;
	}
}

