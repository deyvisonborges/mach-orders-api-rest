package com.mach.machorderrestapi.app.api.order.service;

import com.mach.machorderrestapi.app.integrations.api.catalog.CatalogApiClient;
import com.mach.machorderrestapi.app.integrations.api.catalog.dto.ProductDTO;
import com.mach.machorderrestapi.app.integrations.api.identity.IdentityApiClient;
import com.mach.machorderrestapi.app.integrations.api.identity.dto.CustomerDTO;
import com.mach.machorderrestapi.app.persistence.order.springjpa.OrderJPAMapper;
import com.mach.machorderrestapi.app.persistence.order.springjpa.OrderJPARepository;
import com.mach.machorderrestapi.core.artifact.order.Order;
import com.mach.machorderrestapi.core.artifact.order.OrderItem;
import com.mach.machorderrestapi.core.artifact.order.OrderStatus;
import com.mach.machorderrestapi.core.artifact.order.event.OrderCreatedEventEmitter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CreateOrderService {
	private final CatalogApiClient catalogApiClient;
	private final IdentityApiClient identityApiClient;
	private final OrderJPARepository orderJPARepository;
	private final OrderCreatedEventEmitter orderCreatedEventEmitter;

	public CreateOrderService(CatalogApiClient catalogApiClient, IdentityApiClient identityApiClient, OrderJPARepository orderJPARepository, OrderCreatedEventEmitter orderCreatedEventEmitter) {
		this.catalogApiClient = catalogApiClient;
		this.identityApiClient = identityApiClient;
		this.orderJPARepository = orderJPARepository;
		this.orderCreatedEventEmitter = orderCreatedEventEmitter;
	}

	public record CreateOrderServiceInput(
	  OrderStatus status,
		Set<String>items,
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
				.map(item -> item.toString())
				.collect(Collectors.toList())
		).block();


		products.forEach(product -> {
			var orderItem = new OrderItem();
			orderItem.setProductId(product.id());
			orderItem.setPrice(product.salePrice());
			orderItem.setQuantity(1);
			System.out.println(orderItem);
			order.addOrderItem(orderItem);
		});
		order.setStatus(OrderStatus.ORDER_PLACED);
		orderJPARepository.save(OrderJPAMapper.toJPAEntity(order));
		this.orderCreatedEventEmitter.execute(order);
		return order;
	}
}

