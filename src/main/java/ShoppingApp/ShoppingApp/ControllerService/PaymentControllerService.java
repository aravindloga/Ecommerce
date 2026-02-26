package ShoppingApp.ShoppingApp.ControllerService;

import ShoppingApp.ShoppingApp.Entity.Payment;
import ShoppingApp.ShoppingApp.Enums.PaymentStatus;
import ShoppingApp.ShoppingApp.Exception.PaymentNotFound;
import ShoppingApp.ShoppingApp.Repository.PaymentRepository;
import ShoppingApp.ShoppingApp.ServiceImp.PaymentControllerImp;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class PaymentControllerService implements PaymentControllerImp {


    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public String createPaymentOrder(Double amount) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(keyId,keySecret);
            JSONObject object = new JSONObject();
            object.put("amount",amount*100);
            object.put("currency", "INR");
            object.put("receipt", "order_rcpt_" + System.currentTimeMillis());

            Order order = razorpayClient.orders.create(object);
            return order.get("id");

        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean verifyPayment(String razorpayOrderId, String razorpayPaymentId, String razorpaySignature) {
        try {
            String payload = razorpayOrderId + "|" +razorpayPaymentId;
            boolean isValid = Utils.verifySignature(payload,razorpaySignature,keySecret);
            return isValid;
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public boolean refundPayment(String razorpayPaymentId, Double amount) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(keyId, keySecret);
            JSONObject object = new JSONObject();
            object.put("amount",amount*100);
            razorpayClient.payments.refund(razorpayPaymentId,object);
            Payment payment=paymentRepository.findByRazorpayPaymentId(razorpayPaymentId)
                    .orElseThrow(() -> {
                        throw new PaymentNotFound("Payment not found");
                    });
            payment.setStatus(PaymentStatus.REFUNDED);
            return true;
        } catch (RazorpayException e) {
            throw new RuntimeException(e);

    }
}}
