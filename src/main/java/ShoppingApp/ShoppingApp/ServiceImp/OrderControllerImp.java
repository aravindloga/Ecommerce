package ShoppingApp.ShoppingApp.ServiceImp;

import ShoppingApp.ShoppingApp.Entity.Orders;
import ShoppingApp.ShoppingApp.Entity.Product;
import ShoppingApp.ShoppingApp.Entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderControllerImp {

    public void placeOrder(Long cartId,String razorpayOrderId,String razorpayPaymentId);

    public void cancelOrder(long id);
    public List<Orders> getMyOrders(User user);


}
