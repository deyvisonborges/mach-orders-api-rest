package com.mach.machorderrestapi.app.api.order.service;

import com.mach.machorderrestapi.app.integrations.api.catalog.CatalogApiClient;
import com.mach.machorderrestapi.app.integrations.api.identity.IdentityApiClient;
import com.mach.machorderrestapi.app.integrations.api.identity.dto.CustomerDTO;
import com.mach.machorderrestapi.app.persistence.order.springjpa.OrderJPAMapper;
import com.mach.machorderrestapi.app.persistence.order.springjpa.OrderJPARepository;
import com.mach.machorderrestapi.core.artifact.order.Order;
import com.mach.machorderrestapi.core.artifact.order.OrderItem;
import com.mach.machorderrestapi.core.artifact.order.OrderStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
		Set<OrderItem>items,
		UUID customerId,
		Set<String> paymentsIds,
		double subTotal,
		double shippingFee,
		double discount,
		double total
	){}

	public Order execute(CreateOrderServiceInput input) {
		CustomerDTO customer = this.identityApiClient.getCustomerById(input.customerId().toString()).block();
		assert customer != null;

		var order = new Order(
			input.status,
			new HashSet<>(), // items
			customer.id(),
			new HashSet<>(), // paymentsIds
			0.0,
			0.0,
			0.0,
			0.0
		);

		var products = this.catalogApiClient.getProductsByIds(
			input.items
				.stream()
				.map(OrderItem::toString)
				.collect(Collectors.toList())
		).block();

		products.forEach(product -> {
			order.addOrderItem(
				new OrderItem(
					product.id(),
					product.salePrice(),
					1 // TODO -> add in ProductDTO on CatalogAPI
				)
			);
		});
		order.setStatus(OrderStatus.PROCESSING);
		orderJPARepository.save(OrderJPAMapper.toJPAEntity(order));
		return order;
	}
}

