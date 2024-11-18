package com.cxptek.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class CustomStringRedisSerializer extends StringRedisSerializer {

    private final String appName;
    private final String activeProfile;

    @Override
    public String deserialize(@Nullable byte[] bytes) {
        return (bytes == null ? null : new String(bytes, StandardCharsets.UTF_8));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.redis.serializer.RedisSerializer#serialize(java.lang.Object)
     */
    @Override
    public byte[] serialize(@Nullable String string) {
        var keyCache = String.format("%s_%s_%s", appName, activeProfile, string);
        return (string == null ? null : keyCache.getBytes(StandardCharsets.UTF_8));
    }
}
