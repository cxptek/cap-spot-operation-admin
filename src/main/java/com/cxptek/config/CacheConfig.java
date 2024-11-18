package com.cxptek.config;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.util.Arrays;

import static com.cxptek.constant.Constant.IN_MEMORY_CACHE_MANAGE;
import static org.apache.commons.lang3.StringUtils.joinWith;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {
    private final Environment environment;

    @Bean
    public RedisCacheManager getRedisCacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
        var prefixCacheName = getPrefixCacheName();

        return RedisCacheManager.RedisCacheManagerBuilder //
                .fromConnectionFactory(lettuceConnectionFactory)
                .cacheDefaults(
                        RedisCacheConfiguration
                                .defaultCacheConfig()
                                .disableCachingNullValues()
                                .serializeValuesWith(RedisSerializationContext.SerializationPair
                                        .fromSerializer(new GenericJackson2JsonRedisSerializer()))
                                .prefixCacheNameWith(prefixCacheName + "_"))
                .build();

    }

    @Bean
    @Primary
    public CacheManager getCacheManager(RedisCacheManager redisCacheManager) {
        return redisCacheManager;
    }

    @Bean(name = IN_MEMORY_CACHE_MANAGE)
    public CacheManager inMemoryCache() {
        return new ConcurrentMapCacheManager();
    }

    @Bean
    public CacheResolver customCacheResolver(CacheManager cacheManager) {
        return new CustomCacheResolver(cacheManager);
    }

    public String getPrefixCacheName() {
        String redisCachePrefix = environment.getProperty("app.redis.cache-prefix");
        var appName =
                StringUtils.isNotEmpty(redisCachePrefix) ? redisCachePrefix : environment.getProperty("spring.application.name");
        var redisNameSpace = joinWith("_", Arrays.stream(environment.getActiveProfiles()).findFirst().orElse(""));
        return joinWith("_", appName, redisNameSpace);
    }
}
