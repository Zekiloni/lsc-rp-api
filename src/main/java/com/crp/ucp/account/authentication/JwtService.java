package com.crp.ucp.account.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret-key}")
    private  String SECRET_KEY;

    private static final int EXPIRING_HOURS = 3;

    public String generateToken(String username) {
        LocalDateTime expiringAt = LocalDateTime.now().plusHours(EXPIRING_HOURS);

        return Jwts
                .builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(Date.from(expiringAt.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String token, String username) {
        String tokenUsername = extractUsername(token);
        return (username.equals(tokenUsername)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
