package ShoppingApp.ShoppingApp.ControllerService;

import ShoppingApp.ShoppingApp.Entity.Product;
import ShoppingApp.ShoppingApp.Exception.ProductNotFound;
import ShoppingApp.ShoppingApp.Repository.ProductRepository;
import ShoppingApp.ShoppingApp.ServiceImp.ProductControllerImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductControllerService implements ProductControllerImp {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Product getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () ->{
                    throw new ProductNotFound("Product not found");
                }
        );
        return product;

    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public List<Product> getProductBetweenPriceRange(double start, double end) {
        return productRepository.getProductByPrice(start,end);
    }
}
