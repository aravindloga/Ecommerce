package ShoppingApp.ShoppingApp.ServiceImp;


import ShoppingApp.ShoppingApp.DTOs.ProductDTO;
import ShoppingApp.ShoppingApp.Entity.Product;

import javax.print.attribute.standard.MediaPrintableArea;
import java.util.List;

public interface UserServiceImp {

//    auth users

    public List<Product> getMyOrders();

    public void updateAddress();

    public void getUserProfile();

    public void changePassword();

}
