package ShoppingApp.ShoppingApp.Security;


import ShoppingApp.ShoppingApp.CustomPackage.CustomUserDetailsService;
import com.razorpay.Utils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
       if(authHeader !=  null && authHeader.startsWith("Bearer ")) {
           String token = authHeader.substring(7);
           String userName = jwtUtil.extractUserName(token);

           if (userName != null && !jwtUtil.isExpired(token) && SecurityContextHolder.getContext().getAuthentication() == null) {

               UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);

               UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
               SecurityContextHolder.getContext().setAuthentication(authentication);
           }
       }
       if(SecurityContextHolder.getContext().getAuthentication() == null){
           String guestId = getGuestId(request);
           if(guestId == null){
               String id = UUID.randomUUID().toString();
               Cookie cookie = new Cookie("GUEST_ID",id);
               cookie.setHttpOnly(true);
               cookie.setPath("/");
               cookie.setMaxAge(60 * 60 * 24 * 7);
               response.addCookie(cookie);
           }
       }
       filterChain.doFilter(request,response);
    }
    private String getGuestId(HttpServletRequest request){
        if(request.getCookies() == null) return null;
        for (Cookie cookie : request.getCookies()){
            if("GUEST_ID".equals(cookie)){
                return  cookie.getValue();
            }
        }
        return null;
    }
}
