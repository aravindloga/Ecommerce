package ShoppingApp.ShoppingApp.Repository;

import ShoppingApp.ShoppingApp.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("Select p from Product p  where p.inStock = false")
    public List<Product> getOutOfStockProduct();
}
