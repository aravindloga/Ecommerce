package ShoppingApp.ShoppingApp.Exception;

public class PaymentNotFound extends RuntimeException {
    public PaymentNotFound(String message) {
        super(message);
    }
}
