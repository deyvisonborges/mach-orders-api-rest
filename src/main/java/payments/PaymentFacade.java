package payments;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class PaymentFacade {
	private final PaymentRequestProducer paymentRequestProducer;

	public PaymentFacade(PaymentRequestProducer paymentRequestProducer) {
		this.paymentRequestProducer = paymentRequestProducer;
	}

	public String requestPayment() {
		try {
			paymentRequestProducer.integrate("Enviei uma mensagem");
		} catch (JsonProcessingException e) {
			return "Ocorreu um erro " + e.getMessage();
		}
		return "Pagamento aguardando confirmação";
	}
}
