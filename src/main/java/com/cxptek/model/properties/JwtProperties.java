package com.cxptek.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.Period;
import java.util.Date;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperties {
    private String secret;
    private String expiration;
    private int version;

    public Date getExpirationFromNow() {
        var expirationTime = Period.parse(expiration);
        return Date.from(Instant.now().plus(expirationTime));
    }
}
