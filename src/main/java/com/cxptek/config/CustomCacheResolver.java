package com.cxptek.config;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.SimpleCacheResolver;

import java.util.Collection;

@Slf4j
public class CustomCacheResolver extends SimpleCacheResolver {

    public CustomCacheResolver(CacheManager cacheManager) {
        super(cacheManager);
    }

    @Override
    @NonNull
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        Collection<? extends Cache> caches = super.resolveCaches(context);
        context.getOperation().getCacheNames().forEach(cacheName -> {
            log.info("resolveCaches key={}", cacheName);
            getCacheManager().getCache(cacheName).clear();
        });
        return caches;
    }
}
