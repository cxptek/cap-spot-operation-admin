package com.cxptek.utils;

import com.cxptek.entity.BaseEntity;
import com.cxptek.model.auth.PartnerUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.UUID;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
public final class AppUtils {
    private AppUtils() {}

    public static long getCurrentPageResponseFE(long page) {
        return page + 1;
    }

    public static String getRequestBearerToken(HttpServletRequest request) {
        var bearerToken = request.getHeader("Authorization");
        var hasToken = hasText(bearerToken) && bearerToken.startsWith("Bearer ");
        return hasToken ? bearerToken.substring(7) : null;
    }

    public static String getLoginUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object userLogin = authentication.getPrincipal();
        return userLogin instanceof UserDetails userDetails ? userDetails.getUsername() : null;
    }

    public static UUID getCurrentUserPartnerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object userLogin = authentication.getPrincipal();
        return userLogin instanceof PartnerUserDetails userDetails ? userDetails.getPartnerId() : null;
    }

    public static <T extends BaseEntity> void updateAuditInfo(T auditableEntity, boolean isNew) {
        var currentUser = AppUtils.getLoginUsername();
        if (isNew) {
            auditableEntity.setCreatedAt(new Date());
            auditableEntity.setCreatedBy(currentUser);
        }

        auditableEntity.setUpdatedAt(new Date());
        auditableEntity.setUpdatedBy(currentUser);
    }
}

