package payments;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PaymentRequestConsumer {

	@RabbitListener(queues = "payment-request-queue")
	public void retrieveMessage(@Payload Message message) {
		System.out.println(message);
	}
}
