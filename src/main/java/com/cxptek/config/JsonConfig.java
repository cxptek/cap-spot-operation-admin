package com.cxptek.config;

import com.cxptek.constant.Constant;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
public class JsonConfig {

    @Value("${cex-admin.timezone}")
    private String timeZone;

    @Bean
    public JsonMapper newJsonMapper() {
        var mapper = new JsonMapper();
        mapper.setTimeZone(TimeZone.getTimeZone(timeZone));
        mapper.setDateFormat(new SimpleDateFormat(Constant.PATTERN_DATE_YYYMMDD_HHMMSS));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }
}
