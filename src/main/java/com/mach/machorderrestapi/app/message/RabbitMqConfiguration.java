package com.mach.machorderrestapi.app.message;

import com.mach.machorderrestapi.app.message.order.OrderListener;
import com.mach.machorderrestapi.app.message.order.PaymentListener;
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
	private final RabbitMQConstants rabbitMQConstants;

	public RabbitMqConfiguration(RabbitMQConstants rabbitMQConstants) {
		this.rabbitMQConstants = rabbitMQConstants;
	}

	@Bean
	public ConnectionFactory paymentConnectionFactory() {
		try {
			CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
			connectionFactory.setHost(this.rabbitMQConstants.getPaymentHost());
			connectionFactory.setPort(this.rabbitMQConstants.getPaymentPort());
			connectionFactory.setUsername(this.rabbitMQConstants.getPaymentUsername());
			connectionFactory.setPassword(this.rabbitMQConstants.getPaymentPassword());
			return connectionFactory;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Bean
	public MessageListenerAdapter paymentListenerAdapter(PaymentListener listener) {
		return new MessageListenerAdapter(listener, "onMessage");
	}

	@Bean
	public SimpleMessageListenerContainer paymentContainer(
		@Qualifier("paymentConnectionFactory") ConnectionFactory paymentConnectionFactory,
	@Qualifier("paymentListenerAdapter") MessageListenerAdapter listenerAdapter
	) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(paymentConnectionFactory);
		container.setMessageListener(listenerAdapter);
		container.setQueueNames(this.rabbitMQConstants.getPaymentQueue());
		return container;
	}

	@Bean
	@Primary
	public AmqpTemplate amqpTemplate(
		@Qualifier("orderConnectionFactory") ConnectionFactory connectionFactory
	) {
		return new RabbitTemplate(connectionFactory);
	}

	@Bean
	public AmqpAdmin amqpAdmin(@Qualifier("orderConnectionFactory") ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}
}
