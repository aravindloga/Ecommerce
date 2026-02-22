package ShoppingApp.ShoppingApp.ServiceImp;


import ShoppingApp.ShoppingApp.DTOs.LoginDTO;
import ShoppingApp.ShoppingApp.DTOs.NewPasswordDTO;
import ShoppingApp.ShoppingApp.DTOs.UserDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public interface AuthControllerImp {

    public void signUp(UserDto userDto , HttpServletResponse response);
    public void login(LoginDTO loginDTO, HttpServletResponse response);
    public void logout(HttpServletResponse response);
    public void resetPassword(NewPasswordDTO passwordDTO);


}
