package com.mach.machorderrestapi.app.message;

import com.mach.machorderrestapi.core.artifact.order.event.OrderCreatedEventReceiver;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderListenersConfig {
//	@Bean
//	public MessageListenerAdapter onMessageReceived(OrderCreatedEventReceiver listener) {
//		return new MessageListenerAdapter(listener, "receiveMessage");
//	}

	/*
	 * Add more listeners here
	 * */
}
