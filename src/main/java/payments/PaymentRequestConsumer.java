package payments;

import com.mach.machorderrestapi.app.message.constant.RabbitMQConstants;
import jakarta.persistence.PostLoad;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PaymentRequestConsumer {

	@PostLoad
	@RabbitListener(queues = RabbitMQConstants.PAYMENT_REQUEST_QUEUE)
	public void retrieveMessage(@Payload Message message) {
		System.out.println(message);
	}
}
