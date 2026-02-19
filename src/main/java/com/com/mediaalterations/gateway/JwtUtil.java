package com.com.mediaalterations.gateway;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {


    /*
        Spring's dependency injection mechanism does not support using the @Value annotation directly on a static field
        because static fields are initialized when the class is loaded, which occurs before Spring's application context
        is fully initialized and dependency injection takes place.
     */
    private static String secretKey;

    @Value("${jwt.secret.key}")
    public void setSecretKey(String secretKey){
        JwtUtil.secretKey=secretKey;
    }

    private static SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public static boolean isValid(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .after(new Date(System.currentTimeMillis()));
    }

    public static String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

    public static String getUserId(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload().get("userId", String.class);
    }
}
