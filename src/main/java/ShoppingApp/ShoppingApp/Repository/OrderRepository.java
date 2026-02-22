package ShoppingApp.ShoppingApp.Repository;

import ShoppingApp.ShoppingApp.Entity.Orders;
import ShoppingApp.ShoppingApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Long> {
    List<Orders> findByUser(User user);
    List<Orders> findByDate(LocalDate date);
}
