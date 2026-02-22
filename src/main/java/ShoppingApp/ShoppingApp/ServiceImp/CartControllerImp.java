package ShoppingApp.ShoppingApp.ServiceImp;

import ShoppingApp.ShoppingApp.Entity.Cart;
import ShoppingApp.ShoppingApp.Entity.Product;
import ShoppingApp.ShoppingApp.Entity.User;
import org.springframework.stereotype.Component;

import javax.swing.plaf.SpinnerUI;
import java.util.List;

@Component
public interface CartControllerImp {

    public void addToCart(User user,String guestId,Long id);
    public void removeFromCart(long id);
    public void updateQuantity(Long id,int quantity);


    public Cart getCartItems(User user,String guestId);
    public void clearCart(User user,String guestId);
    public void mergeGuestCartToUserCart(String guestId, User user);
}
