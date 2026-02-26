package ShoppingApp.ShoppingApp.Controller;


import ShoppingApp.ShoppingApp.ServiceImp.ProductControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductControllerImp productControllerImp;

    @GetMapping("/getProduct")
    public ResponseEntity<?> getProduct(@RequestParam Long id){
        return ResponseEntity.ok(productControllerImp.getProduct(id));
    }
    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProduct(){
        return ResponseEntity.ok(productControllerImp.getAllProduct());
    }
    @GetMapping("/getProductBetweenPriceRange")
    public ResponseEntity<?> getProductBetweenPriceRange(@RequestParam double start , @RequestParam double end){
        return ResponseEntity.ok(productControllerImp.getProductBetweenPriceRange(start,end));
    }

}
