package com.cxptek.config;

import com.cxptek.config.context.CexLocaleHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

@Component
@RequiredArgsConstructor
public class LocaleInterceptor implements HandlerInterceptor {
    private final CexLocaleHolder localeHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver == null) {
            throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
        }

        if (localeResolver instanceof AcceptHeaderLocaleResolver) {
            localeHolder.setCurrentLocale(localeResolver.resolveLocale(request));
        } else {
            throw new IllegalStateException("Resolver should be of AcceptHeaderLocaleResolver type");
        }

        return true;
    }
}

