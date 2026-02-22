package ShoppingApp.ShoppingApp.ControllerService;

import ShoppingApp.ShoppingApp.DTOs.LoginDTO;
import ShoppingApp.ShoppingApp.DTOs.NewPasswordDTO;
import ShoppingApp.ShoppingApp.DTOs.UserDto;
import ShoppingApp.ShoppingApp.Entity.User;
import ShoppingApp.ShoppingApp.Repository.UserRepository;
import ShoppingApp.ShoppingApp.Security.JwtUtil;
import ShoppingApp.ShoppingApp.ServiceImp.AuthControllerImp;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import ShoppingApp.ShoppingApp.Exception.UserNameAlreadyRegistered;
import ShoppingApp.ShoppingApp.Exception.UserNamePasswordNotFound;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthControllerService implements AuthControllerImp {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public void signUp(UserDto userDto,HttpServletResponse response) {
        User user = userRepository.findByUserName(userDto.getUserName());
        if(user != null) {
            throw new UserNameAlreadyRegistered("User Name Already registered");
        }
        user = new User();
        user.setUserName(userDto.getUserName());
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getUserName());
        Cookie cookie = new Cookie("token",token);
        cookie.setMaxAge(24000);
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    @Override
    public void login(LoginDTO loginDTO, HttpServletResponse response) {
        User user = userRepository.findByUserName(loginDTO.getUserName());
        if(user == null){
            throw new UserNamePasswordNotFound("User not found or Invalid Password");
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUserName(),loginDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(authentication.getName());
        Cookie cookie = new Cookie("token",token);
        cookie.setMaxAge(24000);
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    @Override
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token","");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

    }
    @Override
    public void resetPassword(NewPasswordDTO newPasswordDTO){
        User user = userRepository.findByUserName(newPasswordDTO.getUserName());
        if(user == null){
            throw new UserNamePasswordNotFound("Username not found");
        }
        if(!newPasswordDTO.getNewPassword().equals(newPasswordDTO.getConfirmPassword())){
            user.setPassword(passwordEncoder.encode(newPasswordDTO.getConfirmPassword()));
        }
        userRepository.save(user);
    }
}
