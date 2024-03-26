package com.mach.machorderrestapi.core.artifact.order.event.commands;

import com.mach.machorderrestapi.app.integrations.api.catalog.CatalogApiService;
import com.mach.machorderrestapi.app.integrations.api.identity.IdentityApiClient;
import com.mach.machorderrestapi.app.integrations.api.identity.dto.CustomerDTO;
import com.mach.machorderrestapi.app.message.RabbitMQConstants;
import com.mach.machorderrestapi.app.persistence.order.springjpa.OrderJPAMapper;
import com.mach.machorderrestapi.app.persistence.order.springjpa.OrderJPARepository;
import com.mach.machorderrestapi.core.artifact.order.*;
import com.mach.machorderrestapi.app.message.RabbitmqEventEmitter;
import com.mach.machorderrestapi.core.artifact.order.event.OrderEvent;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderCommandHandler {
	private final OrderJPARepository repository;
	private final IdentityApiClient identityApiClient;
	private final RabbitmqEventEmitter rabbitmqEventEmitter;
	private final CatalogApiService catalogApiService;
	private final RabbitMQConstants rabbitMQConstants;

	public CreateOrderCommandHandler(OrderJPARepository repository, IdentityApiClient identityApiClient, RabbitmqEventEmitter rabbitmqEventEmitter, CatalogApiService catalogApiService, RabbitMQConstants rabbitMQConstants) {
		this.repository = repository;
		this.identityApiClient = identityApiClient;
		this.rabbitmqEventEmitter = rabbitmqEventEmitter;
		this.catalogApiService = catalogApiService;
		this.rabbitMQConstants = rabbitMQConstants;
	}

	public void execute(CreateOrderCommand command) {
		this.rabbitmqEventEmitter.emit(
				this.rabbitMQConstants.getOrderQueue(),
				new OrderEvent(Order.factoryEmptyOrder().getId(), OrderStatus.CREATED
				)
		);
//
//		var customer = this.getCustomerOnApi(command);
//		assert customer != null;
//
//		var order = Order.factoryEmptyOrder();
//		var products = this.catalogApiService.getProducts(command.items());
//
//		products.forEach(product -> {
//			var orderItem = new OrderItem();
//			orderItem.setProductId(product.id());
//			orderItem.setPrice(product.salePrice());
//			orderItem.setQuantity(1);
//			order.addOrderItem(orderItem);
//		});
//
//		order.setStatus(OrderStatus.CREATED);
//		this.repository.save(OrderJPAMapper.toJPAEntity(order));
//
//		this.rabbitmqEventEmitter.emit(
//				this.rabbitMQConstants.getOrderQueue(),
//				new OrderEvent(order.getId(), OrderStatus.CREATED
//			)
//		);
	}

	private CustomerDTO getCustomerOnApi(CreateOrderCommand command) {
		return this.identityApiClient.getCustomerById(command.customerId().toString()).block();
	}
}

