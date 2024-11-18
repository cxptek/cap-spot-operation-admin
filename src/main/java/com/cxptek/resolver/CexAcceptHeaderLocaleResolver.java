package com.cxptek.resolver;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

public class CexAcceptHeaderLocaleResolver extends AcceptHeaderLocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        var defaultLocale = this.getDefaultLocale();
        var acceptLanguageHeaderValue = request.getHeader("Accept-Language");

        if (defaultLocale != null && acceptLanguageHeaderValue == null) {
            return defaultLocale;
        }
        acceptLanguageHeaderValue = StringUtils.replace(acceptLanguageHeaderValue, "\"", "");
        var localRange = Locale.LanguageRange.parse(acceptLanguageHeaderValue);
        var locale = Locale.lookup(localRange, getSupportedLocales());

        return locale != null ? locale : defaultLocale;
    }
}
