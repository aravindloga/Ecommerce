package ShoppingApp.ShoppingApp.ControllerService;

import ShoppingApp.ShoppingApp.Entity.*;
import ShoppingApp.ShoppingApp.Enums.OrderStatus;
import ShoppingApp.ShoppingApp.Enums.PaymentStatus;
import ShoppingApp.ShoppingApp.Repository.*;
import ShoppingApp.ShoppingApp.ServiceImp.OrderControllerImp;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class OrderControllerService implements OrderControllerImp {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    PaymentControllerService paymentControllerService ;
    public OrderControllerService(PaymentControllerService paymentControllerService){
        this.paymentControllerService = paymentControllerService;
    }

    @Override
    public void placeOrder(Long cartId, String razorpayOrderId, String razorpayPaymentId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(()->{
           throw  new RuntimeException("Cart not found");
        }
        );
        if (cart.getItems().isEmpty()) throw new RuntimeException("Cart is Empty");
        Orders orders = new Orders();
        orders.setUser(cart.getUser());
        orders.setStatus(OrderStatus.CREATED);
        orders.setCreatedAt(LocalDateTime.now());
        orders = orderRepository.save(orders);
        double price = 0.0;
        for(CartItem items : cart.getItems()){
            Product product = items.getProduct();
            product.setSalesCount(product.getSalesCount() + items.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(orders);
            orderItem.setProduct(product);
            orderItem.setQuantity(items.getQuantity());
            orderItem.setPriceAtPurchase(items.getPrice());
            orderItemRepository.save(orderItem);
            price += items.getPrice();

        }
        Payment payment = new Payment();
        payment.setOrderId(orders.getId());
        payment.setRazorpayOrderId(razorpayOrderId);
        payment.setRazorpayPaymentId(razorpayPaymentId);
        payment.setAmount(price);
        payment.setStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(payment);

    }


    @Override
    public void cancelOrder(long id) {

        Orders orders = orderRepository.findById(id).orElseThrow(
                ()->{
                    throw new RuntimeException("Order not found");
                }
        );
        if(orders.getStatus() !=OrderStatus.CREATED){
            throw new RuntimeException("Only placed orders will be canceled");
        }
        Payment payment = paymentRepository.findByOrderId(orders.getId()).orElseThrow(
                () ->{
                    throw new RuntimeException("Payment not found");
                }
        );
        if(!PaymentStatus.SUCCESS.equals(payment.getStatus())){
            throw new RuntimeException("Payment is not successful");

        }
        boolean refund =  paymentControllerService.refundPayment(payment.getRazorpayPaymentId(),payment.getAmount());
        if (!refund) {
            throw new RuntimeException("Refund failed. Order not cancelled.");
        }
        orders.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(orders);
        payment.setStatus(PaymentStatus.REFUNDED);
    }

    @Override
    public List<Orders> getMyOrders(User user) {
        List<Orders> orders = orderRepository.findByUser(user);
        return orders;
    }
}
