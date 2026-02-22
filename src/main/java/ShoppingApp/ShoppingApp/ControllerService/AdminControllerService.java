package ShoppingApp.ShoppingApp.ControllerService;

import ShoppingApp.ShoppingApp.DTOs.ProductDTO;
import ShoppingApp.ShoppingApp.Entity.Orders;
import ShoppingApp.ShoppingApp.Entity.Product;
import ShoppingApp.ShoppingApp.Entity.User;
import ShoppingApp.ShoppingApp.Enums.OrderStatus;
import ShoppingApp.ShoppingApp.Exception.OrderNotFoundexpection;
import ShoppingApp.ShoppingApp.Exception.ProductNotFound;
import ShoppingApp.ShoppingApp.Repository.OrderRepository;
import ShoppingApp.ShoppingApp.Repository.ProductRepository;
import ShoppingApp.ShoppingApp.Repository.UserRepository;
import ShoppingApp.ShoppingApp.ServiceImp.AdminControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.List;

public class AdminControllerService  implements AdminControllerImp {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(product.getProductName());
        product.setPrice(product.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImgUrl(product.getImgUrl());
        productRepository.save(product);


    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->{
            throw new ProductNotFound("Product not found");
        });
        productRepository.delete(product);
    }

    @Override
    public List<User> getUsers() {

        List<User> users = userRepository.findAll();
        return users;


    }

    @Override
    public List<Orders> getTodaySales() {
            List<Orders> orders = orderRepository.findByDate(LocalDate.now());
            return orders;

    }

    @Override
    public List<Orders> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public void updateOrderStatus(Long orderId,String status) {
        Orders orders = orderRepository.findById(orderId).orElseThrow(
                ()->{
                    throw new OrderNotFoundexpection("Order not found");
                }
        );
        if("SHIPPED".equals(status)) orders.setStatus(OrderStatus.SHIPPED);
        if("DELIVERED".equals(status)) orders.setStatus(OrderStatus.DELIVERED);

    }

    @Override
    public void updateProductName(Long id, String productName) {
            Product product = productRepository.findById(id)
                    .orElseThrow(()->{
                        throw new ProductNotFound("Product Not found");
                    });
            product.setProductName(productName);
    }
    @Override
    public void updateProductImg(Long id, String imgUrl) {
            Product product = productRepository.findById(id)
                    .orElseThrow(()->{
                        throw new ProductNotFound("Product Not found");
                    });
            product.setImgUrl(imgUrl);
    }

    @Override
    public List<Product> getOutOfStockProduct() {

        return  productRepository.getOutOfStockProduct();
    }

    @Override
    public void updateProductDesc(Long id, String desc) {
            Product product = productRepository.findById(id)
                    .orElseThrow(()->{
                        throw new ProductNotFound("Product Not found");
                    });
            product.setDescription(desc);
    }
    @Override
    public void updateProductPrice(Long id, double price) {
            Product product = productRepository.findById(id)
                    .orElseThrow(()->{
                        throw new ProductNotFound("Product Not found");
                    });
            product.setPrice(price);
    }
    @Override
    public void updateProductStock(Long id,boolean stock){
        Product product = productRepository.findById(id)
                .orElseThrow(()->{
                    throw new ProductNotFound("Product Not found");
                });
        product.setInStock(stock);
    }



}
