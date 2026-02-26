package ShoppingApp.ShoppingApp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {

    private String name;        // Home / Office
    private String fullName;
    private String phone;
    private String pincode;
    private String city;
    private String state;
    private String street;
    private boolean isDefault ;
}
