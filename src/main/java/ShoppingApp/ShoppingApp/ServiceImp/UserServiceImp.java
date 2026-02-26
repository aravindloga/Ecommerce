package ShoppingApp.ShoppingApp.ServiceImp;


import ShoppingApp.ShoppingApp.DTOs.AddressDTO;
import ShoppingApp.ShoppingApp.DTOs.ProductDTO;
import ShoppingApp.ShoppingApp.DTOs.ReviewDTO;
import ShoppingApp.ShoppingApp.Entity.Orders;
import ShoppingApp.ShoppingApp.Entity.Product;
import ShoppingApp.ShoppingApp.Entity.User;

import javax.print.attribute.standard.MediaPrintableArea;
import java.util.List;

public interface UserServiceImp {

//    auth users

    public List<Orders> getMyOrders();

    public void updateAddress(AddressDTO addressDTO);
    public void removeAddress(Long id);
    public User getUserProfile();

    public void addReview(ReviewDTO reviewDTO);


}
