package ShoppingApp.ShoppingApp.Controller;


import ShoppingApp.ShoppingApp.DTOs.ConfirmOrderDTO;
import ShoppingApp.ShoppingApp.ServiceImp.OrderControllerImp;
import ShoppingApp.ShoppingApp.ServiceImp.PaymentControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderControllerImp orderControllerImp;

    @Autowired
    PaymentControllerImp paymentControllerImp;

    @PostMapping("/confirmOrder")
    public ResponseEntity<?> confirmOrder(@RequestBody ConfirmOrderDTO dto){
        boolean verfied = paymentControllerImp.verifyPayment(dto.getRazorpayOrderId(), dto.getRazorpayPaymentId(), dto.getRazorpaySignature());
        if(!verfied) {
            return ResponseEntity.badRequest().build();
        }
        orderControllerImp.placeOrder(dto.getCartId(), dto.getRazorpayOrderId(), dto.getRazorpayPaymentId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelOrder(@RequestParam Long orderId){
        orderControllerImp.cancelOrder(orderId);
        Map<String, String> res = new HashMap<>();
        res.put("message", "Order cancelled and refund initiated");
        res.put("orderStatus", "CANCELLED");
        res.put("refundStatus", "INITIATED");
       return ResponseEntity.ok(res);

    }
}
