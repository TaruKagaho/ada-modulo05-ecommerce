package ada.santander.coders.mod05.ecommerce02.infra.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
// import java.security.SignatureException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String genereteToken(Authentication authentication) {
        String username = authentication.getName();
        /*LocalDate currentDate = LocalDate.now();
        LocalDate expireDate = curretDate.plus(jwtExpirationInMs, ChronoUnit.MILLIS);*/
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(
                        currentDate
                        /*Date.from(
                                currentDate
                                        .atStartOfDay(
                                                ZoneId.systemDefault()
                                        )
                                        .toInstant()
                        )*/
                )
                .setExpiration(
                        expireDate
                        /*Date.from(
                                expireDate
                                        .atStartOfDay(
                                                ZoneId.systemDefault()
                                        )
                                        .toInstant()
                        )*/
                )
                .signWith(
                        key()
                )
                .compact();
    }

    public String getUsername(String token) {
        Claims claims = Jwts
                // .parser()
                .parserBuilder()
                .setSigningKey(
                        key()
                )
                .build()
                .parseClaimsJws(
                        token
                )
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    // .parser()
                    .parserBuilder()
                    .setSigningKey(
                            key()
                    )
                    .build()
                    .parse(
                            token
                    );
            return true;
        } /*catch (SignatureException ex) {
            throw new RuntimeException("Token JWT inválido!");
        }*/ catch (MalformedJwtException ex) {
            throw new RuntimeException("Token inválido!");
        } catch (ExpiredJwtException ex) {
            throw new RuntimeException("Token expirado!");
        } catch (UnsupportedJwtException ex) {
            throw new RuntimeException("Token não suportado!");
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Claims está vazio!");
        }
    }

    private Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }
}
