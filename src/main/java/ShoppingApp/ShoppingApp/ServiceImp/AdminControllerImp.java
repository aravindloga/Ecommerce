package ShoppingApp.ShoppingApp.ServiceImp;

import ShoppingApp.ShoppingApp.DTOs.ProductDTO;
import ShoppingApp.ShoppingApp.Entity.Orders;
import ShoppingApp.ShoppingApp.Entity.Product;
import ShoppingApp.ShoppingApp.Entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminControllerImp {

    public void addProduct(ProductDTO productDTO);
    public void deleteProduct(Long id);
    public List<User> getUsers();
    public List<Orders> getTodaySales();
    public List<Orders> getAllOrder();
    public void updateOrderStatus(Long orderId,String status);
    public void updateProductName(Long id,String name);
    public void updateProductPrice(Long id,double price);
    public void updateProductDesc(Long id,String desc);
    public void updateProductImg(Long id,String img);
    public void updateProductStock(Long id,boolean stock);
    public List<Product> getOutOfStockProduct();
}
