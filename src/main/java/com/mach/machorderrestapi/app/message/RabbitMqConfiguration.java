package com.mach.machorderrestapi.app.message;

import com.mach.machorderrestapi.core.artifact.order.event.OrderCreatedEventListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {
	@Bean
	public ConnectionFactory paymentQueueConnectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5675);
		connectionFactory.setUsername("myuser");
		connectionFactory.setPassword("secret");
		return connectionFactory;
	}

	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setMessageListener(listenerAdapter);
		container.setQueueNames("payment-queue");
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(OrderCreatedEventListener listener) {
		return new MessageListenerAdapter(listener, "onMessageReceived");
	}

	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
}
