package ShoppingApp.ShoppingApp.Repository;

import ShoppingApp.ShoppingApp.Entity.Cart;
import ShoppingApp.ShoppingApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUser(User user);
    Optional<Cart> findByGuestId(String guestId);

}
