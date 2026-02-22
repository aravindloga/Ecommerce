package ShoppingApp.ShoppingApp.Repository;

import ShoppingApp.ShoppingApp.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByOrderId(Long id);
}
