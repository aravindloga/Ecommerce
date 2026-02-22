package ShoppingApp.ShoppingApp.Repository;

import ShoppingApp.ShoppingApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
        public User findByUserName(String userName);
}
