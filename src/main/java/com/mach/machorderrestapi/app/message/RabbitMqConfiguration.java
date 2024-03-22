package com.mach.machorderrestapi.app.message;

import com.mach.machorderrestapi.core.artifact.order.event.OrderCreatedEventReceiver;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMqConfiguration {
	@Bean
	@Primary
	public ConnectionFactory orderConnectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setPort(5673);
		connectionFactory.setUsername("myuser");
		connectionFactory.setPassword("secret");
		return connectionFactory;
	}

	@Bean
	public ConnectionFactory paymentConnectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5675);
		connectionFactory.setUsername("myuser");
		connectionFactory.setPassword("secret");
		return connectionFactory;
	}

	@Bean
	public SimpleMessageListenerContainer paymentContainer(@Qualifier("paymentConnectionFactory") ConnectionFactory paymentConnectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(paymentConnectionFactory);
		container.setMessageListener(listenerAdapter);
		container.setQueueNames(RabbitMQConstants.LISTENER_QUEUE_PAYMENT);
		return container;
	}
	@Bean
	@Primary
	public AmqpTemplate amqpTemplate(@Qualifier("orderConnectionFactory") org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
		return new RabbitTemplate(connectionFactory);
	}

	@Bean
	public AmqpAdmin amqpAdmin(@Qualifier("orderConnectionFactory") ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	@Bean
	public MessageListenerAdapter onMessageReceived(OrderCreatedEventReceiver listener) {
		return new MessageListenerAdapter(listener, "receiveMessage");
	}
}
