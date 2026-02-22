package ShoppingApp.ShoppingApp.ServiceImp;

public interface PaymentControllerImp {

    public String createPaymentOrder(Double amount);
    public boolean verifyPayment(String razorpayOrderId,
                                 String razorpayPaymentId,
                                 String razorpaySignature);
    public void savePaymentDetails(String orderId, String paymentId, Double amount);
    public boolean refundPayment(String razorpayPaymentId, Double amount);
}
