package com.crp.ucp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {

    private static final int EXPIRING_HOURS = 3;

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    public static final String ACCOUNT_ID = "accountId";
    public static final String ACCESS_ROLE = "accessRole";

    public String generateToken(String username, Integer accountId, RoleType role) {
        HashMap<String, Object> claims = new HashMap<>() {{
            put(ACCOUNT_ID, accountId);
            put(ACCESS_ROLE, role);
        }};

        return Jwts
                .builder()
                .subject(username)
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(getExpirationDate(LocalDateTime.now().plusHours(EXPIRING_HOURS)))
                .signWith(getSignInKey())
                .compact();
    }

    private Date getExpirationDate(LocalDateTime expiringAt) {
        return Date.from(expiringAt.atZone(ZoneId.systemDefault()).toInstant());
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

    public Integer extractAccountId(String token) {
        return extractClaim(token, claims -> claims.get(ACCOUNT_ID, Integer.class));
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
