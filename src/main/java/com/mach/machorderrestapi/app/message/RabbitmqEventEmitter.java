package com.mach.machorderrestapi.app.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqEventEmitter {
	private final AmqpTemplate template;
	private final ObjectMapper mapper;

	public RabbitmqEventEmitter(AmqpTemplate template, ObjectMapper mapper) {
		this.template = template;
		this.mapper = mapper;
	}

	public void emit(final String queueName, final Object message) {
		var processedMessage = this.handleMessage(message);
		this.template.convertAndSend(queueName, processedMessage);
	}

	private String handleMessage(Object message) {
		try {
			return new String(mapper.writeValueAsString(message));
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Fail to process message");
		}
	}
}
