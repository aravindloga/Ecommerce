package ShoppingApp.ShoppingApp.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    public String name;

    public String userName;   // email


    public String password;
}
