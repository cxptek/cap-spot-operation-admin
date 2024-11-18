package com.cxptek.model.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

@Getter
public class PartnerUserDetails extends User {
    private UUID partnerId;

    public PartnerUserDetails(String username,
                              String password,
                              Collection<? extends GrantedAuthority> authorities,
                              UUID partnerId) {
        super(username, password, authorities);
        this.partnerId = partnerId;
    }

    public PartnerUserDetails(String username,
                              String password,
                              boolean enabled,
                              boolean accountNonExpired,
                              boolean credentialsNonExpired,
                              boolean accountNonLocked,
                              Collection<? extends GrantedAuthority> authorities,
                              UUID partnerId) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.partnerId = partnerId;
    }
}
