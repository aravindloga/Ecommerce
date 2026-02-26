package ShoppingApp.ShoppingApp.ServiceImp;

import ShoppingApp.ShoppingApp.Enums.PaymentStatus;

public interface PaymentControllerImp {

    public String createPaymentOrder(Double amount);
    public boolean verifyPayment(String razorpayOrderId,
                                 String razorpayPaymentId,
                                 String razorpaySignature);
    public boolean refundPayment(String razorpayPaymentId, Double amount);
}
