package net.lscrp.ucp.config;

import net.lscrp.ucp.account.AccountEntity;
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
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtAuthenticationService {

    @Value("${security.jwt.expiration-hours}")
    private Long EXPIRING_HOURS;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    public static final String ACCOUNT_ID = "accountId";

    public String generateToken(AccountEntity account) {
        HashMap<String, Object> claims = new HashMap<>() {{
            put(ACCOUNT_ID, account.getId());
        }};

        return Jwts
                .builder()
                .subject(account.getUsername())
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
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
