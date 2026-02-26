package ShoppingApp.ShoppingApp.Controller;


import ShoppingApp.ShoppingApp.DTOs.AddressDTO;
import ShoppingApp.ShoppingApp.DTOs.ReviewDTO;
import ShoppingApp.ShoppingApp.ServiceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;
    @GetMapping("/getMyOrders")
    public ResponseEntity<?> getMyOrders(){
        return ResponseEntity.ok(userServiceImp.getMyOrders());

    }

    @PostMapping("/updateAddress")
    public ResponseEntity<?> updateAddress(@RequestBody AddressDTO address){
        userServiceImp.updateAddress(address);
        return ResponseEntity.ok().build();

    }
    @PostMapping("/addReview")
    public ResponseEntity<?> addReview(@RequestBody ReviewDTO reviewDTO){
        userServiceImp.addReview(reviewDTO);
        return ResponseEntity.ok().build();

    }
    @DeleteMapping("/removeAddress")
    public ResponseEntity<?> removeAddress(@RequestParam Long id){
        userServiceImp.removeAddress(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(){
        return ResponseEntity.ok(userServiceImp.getUserProfile());

    }


}
