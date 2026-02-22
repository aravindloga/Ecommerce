package ShoppingApp.ShoppingApp.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.event.SpringApplicationEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPasswordDTO {
    private String userName;
    private String newPassword;
    private String confirmPassword;
}
