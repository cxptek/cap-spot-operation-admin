package com.cxptek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CexAdminBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CexAdminBackendApplication.class, args);
    }

}
