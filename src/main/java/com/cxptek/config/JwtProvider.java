package com.cxptek.config;

import com.cxptek.model.properties.JwtProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Slf4j
@Getter
@Setter
@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final JsonMapper jsonMapper;
    private final JwtProperties jwtProperties;

    public String generateJwtAccessToken(String subject, Object claims) {
        var expirationDate = jwtProperties.getExpirationFromNow();
        return generateJwtToken(subject, claims, expirationDate);
    }

    public String generateJwtToken(String subject, Object claims, Date expirationDate) {
        var key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
        var toMapClz = new TypeReference<Map<String, Object>>() {};

        var jwtBuilder = Jwts.builder()
                .expiration(expirationDate)
                .signWith(key, Jwts.SIG.HS256)
                .claim("sub", subject);

        jsonMapper
                .convertValue(claims, toMapClz)
                .forEach(jwtBuilder::claim);

        return jwtBuilder.compact();
    }

    public Claims getClaims(String token) {
        var jwtParser = getJwtParser(jwtProperties.getSecret());
        var jwsClaim = jwtParser.parseSignedClaims(token);
        return jwsClaim.getPayload();
    }

    private JwtParser getJwtParser(String jwtSecret) {
        var secretBytes = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        return Jwts.parser().verifyWith(secretBytes).build();
    }

    public boolean validate(String token) {
        try {
            var jwtParser = getJwtParser(jwtProperties.getSecret());
            jwtParser.parseSignedClaims(token);
            return true;
        } catch (SecurityException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
