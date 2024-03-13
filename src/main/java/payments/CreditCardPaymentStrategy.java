package payments;

public class CreditCardPaymentStrategy implements PaymentStrategy {
	public void processPayment(double amount) {
		System.out.println("Processing credit card payment of amount " + amount);
	}
}
