package com.mach.machorderrestapi.core.artifact.order.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mach.machorderrestapi.app.message.RabbitMQConstants;
import com.mach.machorderrestapi.app.persistence.order.springjpa.OrderJPARepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderHandlerListener {
	private final ObjectMapper mapper;
	private final OrderJPARepository orderJPARepository;

	public OrderHandlerListener(ObjectMapper mapper, OrderJPARepository orderJPARepository) {
		this.mapper = mapper;
		this.orderJPARepository = orderJPARepository;
	}

	public void handleOrderEvent(byte[] message) {
		System.out.println("Chamei o event listener do Order");
		//		var messageString = new String(message);
//
//		try {
//			var event = mapper.readValue(messageString, OrderEvent.class);
//
//			switch (event.getStatus()) {
//				case CREATED:
//					System.out.println("Create order event received");
//					break;
//				case CANCELLED:
//					this.orderJPARepository.deleteById(event.getId());
//					break;
//				default:
//					throw new IllegalArgumentException("Invalid order event type: " + event.getStatus());
//			}
//
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
	}
}
