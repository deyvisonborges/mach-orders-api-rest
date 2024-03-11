package payments;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentRequestProducer {
	private final AmqpTemplate amqpTemplate;
	private final ObjectMapper objectMapper;

	public PaymentRequestProducer(AmqpTemplate amqpTemplate, ObjectMapper objectMapper) {
		this.amqpTemplate = amqpTemplate;
		this.objectMapper = objectMapper;
	}

	public void integrate(String payload) throws JsonProcessingException {
		amqpTemplate.convertAndSend(
			"payment-request-exchange",
			"payment-request-queue-route-key",
			objectMapper.writeValueAsString(payload)
		);
	}
}
