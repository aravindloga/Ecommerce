package ShoppingApp.ShoppingApp.Controller;

import ShoppingApp.ShoppingApp.ControllerService.AdminControllerService;
import ShoppingApp.ShoppingApp.DTOs.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminControllerService adminControllerService;

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO){
        try{
            adminControllerService.addProduct(productDTO);
            return ResponseEntity.ok("Product added Successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam Long id){
        try{
            adminControllerService.deleteProduct(id);
            return ResponseEntity.ok("Product deleted Successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers(){
        try{
            return ResponseEntity.ok(adminControllerService.getUsers());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getSales")
    public ResponseEntity<?> getTodaySales(){
        try{
            return ResponseEntity.ok(adminControllerService.getTodaySales());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/getAllOrders")
    public ResponseEntity<?> getAllOrders(){
        try{
            return ResponseEntity.ok(adminControllerService.getAllOrder());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PatchMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestParam Long orderId,
                                          @RequestParam String status){
        try{
            adminControllerService.updateOrderStatus(orderId,status);
            return ResponseEntity.ok("Update user Status");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PatchMapping("/updateProductName")
    public ResponseEntity<?> updateProductName(@RequestParam Long id,
                                          @RequestParam String name){
        try{
            adminControllerService.updateProductName(id,name);
            return ResponseEntity.ok("Updated product name");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PatchMapping("/updateProductImg")
    public ResponseEntity<?> updateProductImg(@RequestParam Long Id,
                                          @RequestParam String img){
        try{
            adminControllerService.updateProductImg(Id,img);
            return ResponseEntity.ok("Updated img");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PatchMapping("/updateProductDesc")
    public ResponseEntity<?> updateProductDesc(@RequestParam Long id,
                                          @RequestParam String desc){
        try{
            adminControllerService.updateProductDesc(id,desc);
            return ResponseEntity.ok("Updated desc");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PatchMapping("/updateProductPrice")
    public ResponseEntity<?> updateProductPrice(@RequestParam Long id,
                                          @RequestParam double price){
        try{
            adminControllerService.updateProductPrice(id,price);
            return ResponseEntity.ok("Updated price");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PatchMapping("/updateProductStock")
    public ResponseEntity<?> updateProductStock(@RequestParam Long id,
                                          @RequestParam boolean inStock){
        try{
            adminControllerService.updateProductStock(id,inStock);
            return ResponseEntity.ok("Updated  Stock");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/getOutOfStockProduct")
    public ResponseEntity<?> getOutOfStockProduct(){
        try {
            return ResponseEntity.ok(adminControllerService.getOutOfStockProduct());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
