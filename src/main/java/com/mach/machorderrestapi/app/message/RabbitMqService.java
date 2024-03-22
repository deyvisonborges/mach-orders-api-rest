package com.mach.machorderrestapi.app.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {
	private final AmqpTemplate template;

	public RabbitMqService(AmqpTemplate template) {
		this.template = template;
	}

	public void sendMessage(String queueName, Object message) {
		String jsonString = null;
		try {
			jsonString = new ObjectMapper().writeValueAsString(message);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		this.template.convertAndSend(queueName, jsonString);
	}
}
