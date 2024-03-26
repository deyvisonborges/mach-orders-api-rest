package com.mach.machorderrestapi.app.message.order;

import com.mach.machorderrestapi.app.message.RabbitMQConstants;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class OrderRmqConfiguration {
	private final RabbitMQConstants constants;

	public OrderRmqConfiguration(RabbitMQConstants constants) {
		this.constants = constants;
	}

	@Bean
	@Primary
	public ConnectionFactory orderConnectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(this.constants.getOrderHost());
		connectionFactory.setPort(this.constants.getOrderPort());
		connectionFactory.setUsername(this.constants.getOrderUsername());
		connectionFactory.setPassword(this.constants.getOrderPassword());
		return connectionFactory;
	}

	@Bean
	public MessageListenerAdapter orderListenerAdapter(OrderListener orderListener) {
		return new MessageListenerAdapter(orderListener, "onMessage");
	}

	@Bean
	public SimpleMessageListenerContainer orderContainer(
		@Qualifier("orderConnectionFactory") ConnectionFactory orderConnectionFactory,
		@Qualifier("orderListenerAdapter") MessageListenerAdapter orderListenerAdapter
	) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(orderConnectionFactory);
		container.setMessageListener(orderListenerAdapter);
		container.setQueueNames(this.constants.getOrderQueue());
		return container;
	}

}
