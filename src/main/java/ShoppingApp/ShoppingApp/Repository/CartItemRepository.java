package ShoppingApp.ShoppingApp.Repository;

import ShoppingApp.ShoppingApp.Entity.Cart;
import ShoppingApp.ShoppingApp.Entity.CartItem;
import ShoppingApp.ShoppingApp.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
        Optional<CartItem> findByCartAndProduct(Cart cart ,Product product);

}
