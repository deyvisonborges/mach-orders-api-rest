package com.mach.machorderrestapi.app.message.order;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class OrderListener implements MessageListener {
	@Override
	public void onMessage(Message message) {
		System.out.println("Consuming Message Order - " + new String(message.getBody()));
	}
}
