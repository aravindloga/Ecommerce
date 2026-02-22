package ShoppingApp.ShoppingApp.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private String productName;

    private String imgUrl;

    private String description;

    private double price;
}
