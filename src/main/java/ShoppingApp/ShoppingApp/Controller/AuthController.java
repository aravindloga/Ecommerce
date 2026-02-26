package ShoppingApp.ShoppingApp.Controller;


import ShoppingApp.ShoppingApp.ControllerService.AuthControllerService;
import ShoppingApp.ShoppingApp.DTOs.LoginDTO;
import ShoppingApp.ShoppingApp.DTOs.NewPasswordDTO;
import ShoppingApp.ShoppingApp.DTOs.UserDto;
import ShoppingApp.ShoppingApp.Entity.User;
import ShoppingApp.ShoppingApp.Repository.UserRepository;
import ShoppingApp.ShoppingApp.ServiceImp.AuthControllerImp;
import ShoppingApp.ShoppingApp.ServiceImp.CartControllerImp;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthControllerImp authControllerImp;

    @Autowired
    CartControllerImp cartControllerImp;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDto userDto, HttpServletResponse response){
        try{
            authControllerImp.signUp(userDto,response);
            return ResponseEntity.ok("Sign up successful");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Sign up failed");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO,
                                   HttpServletResponse response, HttpServletRequest request){
        try {
            authControllerImp.login(loginDTO,response);
            User user = userRepository.findByUserName(loginDTO.getUserName());
            String guest = null;
            for(Cookie cookie : request.getCookies()){
                if("GUEST_ID".equals(cookie.getName()))
                    guest=cookie.getValue();
            }
            cartControllerImp.mergeGuestCartToUserCart(guest,user);
            return ResponseEntity.ok("Login Successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login failed");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout( HttpServletResponse response){
        try{
            authControllerImp.logout(response);
            return ResponseEntity.ok("Logout ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam NewPasswordDTO newPasswordDTO){
        try{
            authControllerImp.resetPassword(newPasswordDTO);
            return ResponseEntity.ok("password changed ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
