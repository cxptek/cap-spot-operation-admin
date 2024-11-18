package com.cxptek.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.TimeZone;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TimezoneConfig {
    @Value("${cex-admin.timezone}")
    public String timeZone;

//    @PostConstruct
//    public void setDefaultTimezone() {
//        log.info("setDefaultTimezone");
//        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
//        log.info("now: {}", new Date());
//    }
}
