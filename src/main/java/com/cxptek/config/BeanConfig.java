package com.cxptek.config;

import com.cxptek.config.context.CexLocaleHolder;
import com.cxptek.resolver.CexAcceptHeaderLocaleResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;

import static com.cxptek.constant.Constant.LOCALE_EN;
import static com.cxptek.constant.Constant.SUPPORTED_LOCALES;

@Configuration
public class BeanConfig {

    @Bean
    public LocaleResolver localeResolver() {
        var slr = new CexAcceptHeaderLocaleResolver();
        slr.setDefaultLocale(LOCALE_EN);
        slr.setSupportedLocales(SUPPORTED_LOCALES);
        return slr;
    }

    @Bean("messageSource")
    public MessageSource messageSource() {
        var messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CexLocaleHolder localeHolder() {
        return new CexLocaleHolder();
    }
}
