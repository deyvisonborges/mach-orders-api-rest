package com.mach.machorderrestapi.app.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class RabbitMqService implements Serializable {
	private final AmqpTemplate amqpTemplate;

	public RabbitMqService(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}


	public void sendMessage(String queueName, Object message) {
		String jsonString = null;

		try {
			jsonString = new ObjectMapper().writeValueAsString(message);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		this.amqpTemplate.convertAndSend(queueName, jsonString);
	}
}
