package com.mach.machorderrestapi.core.artifact.order.event;

import com.mach.machorderrestapi.app.api.order.service.CreateOrderOutput;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedEventListener {
	public void onMessageReceived(CreateOrderOutput event) {
		System.out.println(event);
	}
}
