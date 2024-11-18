package com.cxptek.config;

import com.cxptek.utils.CustomStringRedisSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {
    @Value("${spring.profiles.active}")
    String activeProfile;
    @Value("${spring.application.name}")
    String appName;


    @Bean
    public <K, V> RedisTemplate<K, V> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<K, V> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);

        var keySerializer = new CustomStringRedisSerializer(appName, activeProfile);
        template.setHashKeySerializer(keySerializer);
        template.setKeySerializer(keySerializer);
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
