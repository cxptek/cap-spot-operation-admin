package com.cxptek.model.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "cex-admin")
public class CexAdminProperties {
    private static CexAdminProperties instance;

    public static CexAdminProperties get() {
        return instance;
    }

    private static void setInstance(CexAdminProperties i) {
        instance = i;
    }
}
