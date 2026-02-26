package ShoppingApp.ShoppingApp.Controller;


import ShoppingApp.ShoppingApp.Entity.User;
import ShoppingApp.ShoppingApp.Repository.UserRepository;
import ShoppingApp.ShoppingApp.ServiceImp.CartControllerImp;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private  CartControllerImp cartControllerImp;

    @Autowired
    UserRepository userRepository;

    @PutMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestParam Long id, HttpServletRequest request){
        try{
            User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
            String guestId = null;
            for(Cookie cookie : request.getCookies()){
                if("GUEST_ID".equals(cookie.getName())){
                    guestId = cookie.getValue();
                }
            }

            cartControllerImp.addToCart(user,guestId,id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/removeProduct")
    public ResponseEntity<?> removeFromCart(@RequestParam Long id)
    {
        try{
            cartControllerImp.removeFromCart(id);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/clearCart")
    public ResponseEntity<?> removeCart(HttpServletRequest request)
    {
        try{
            User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
            String guestId = null;
            for(Cookie cookie : request.getCookies()){
                if("GUEST_ID".equals(cookie.getName())){
                    guestId = cookie.getValue();
                }
            }
            cartControllerImp.clearCart(user,guestId);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/updateQuantity")
    public ResponseEntity<?> updateQuantity(@RequestParam Long id,
                                            @RequestParam int quantity){
        try{
            cartControllerImp.updateQuantity(id,quantity);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/getCart")
    public ResponseEntity<?> getCart(HttpServletRequest request){
        try{
            User user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
            String guestId = null;
            for(Cookie cookie : request.getCookies()){
                if("GUEST_ID".equals(cookie.getName())){
                    guestId = cookie.getValue();
                }
            }

            return ResponseEntity.ok(cartControllerImp.getCartItems(user,guestId));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
