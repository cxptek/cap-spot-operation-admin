package com.cxptek.config;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EntityManagerConfig implements ApplicationContextAware {
    private static ApplicationContext context;
    @Getter
    private static EntityManager entityManager;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @PostConstruct
    public void init() {
        entityManager = context.getBean(EntityManager.class);
    }
}
