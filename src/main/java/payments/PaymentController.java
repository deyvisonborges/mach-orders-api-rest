package payments;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
	private final PaymentFacade paymentFacade;

	public PaymentController(PaymentFacade paymentFacade) {
		this.paymentFacade = paymentFacade;
	}

	@GetMapping
	public ResponseEntity<String> requestPayment() {
		System.out.println("ok");
		this.paymentFacade.requestPayment();
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
