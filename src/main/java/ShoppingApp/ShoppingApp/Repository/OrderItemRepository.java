package ShoppingApp.ShoppingApp.Repository;

import ShoppingApp.ShoppingApp.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
