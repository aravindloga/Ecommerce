package ShoppingApp.ShoppingApp.ServiceImp;

import ShoppingApp.ShoppingApp.Entity.Product;

import java.util.List;

public interface ProductControllerImp {

    Product getProduct(Long id);
    List<Product> getAllProduct();
    List<Product> getProductBetweenPriceRange(double start , double end);

}
