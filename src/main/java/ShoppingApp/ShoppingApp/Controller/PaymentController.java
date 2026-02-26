package ShoppingApp.ShoppingApp.Controller;

import ShoppingApp.ShoppingApp.ServiceImp.PaymentControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentControllerImp paymentControllerImp;

    @PostMapping("/create")
    public ResponseEntity<?> createPaymentOrder(@RequestParam Double amount){
        String orderId = paymentControllerImp.createPaymentOrder(amount);
        Map<String,String> map = new HashMap<>();
        map.put("razorpayOrderId",orderId);
        return ResponseEntity.ok(map);
    }

}
