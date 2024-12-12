package product.mangagement.productm.utils;

import io.jsonwebtoken.*;


import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
public class JwtUtil {

    private SecretKeySpec secretKey = new SecretKeySpec("a very long fsfsfsfsfsfsfsfsfsfsfstest".getBytes(), "HmacSHA256");  // Secret key for signing JWT token
    // Method to create a JWT token
    public String generateToken(String username) {
        return Jwts.builder().subject(username).signWith(secretKey, Jwts.SIG.HS256 ).compact();
    }

    // Method to get the username from the token
    public String getUsernameFromToken(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getSubject();
    }

    // 
}
