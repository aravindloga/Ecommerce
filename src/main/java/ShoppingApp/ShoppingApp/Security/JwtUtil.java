package ShoppingApp.ShoppingApp.Security;


import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    private final String  key = "ThisIsTheProjectFileWhichWeNeedToWriteCodeForSecurity";
    private final SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    public String generateToken(String userName){
        return io.jsonwebtoken.Jwts.builder().signWith(secretKey,SignatureAlgorithm.HS256)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 100000) )
                .setSubject(userName).compact();
    }
    public String extractUserName(String token){
                return io.jsonwebtoken.Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean isExpired(String token){
        return io.jsonwebtoken.Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }
}
