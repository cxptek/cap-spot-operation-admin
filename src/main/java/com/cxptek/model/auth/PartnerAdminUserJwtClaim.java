package com.cxptek.model.auth;

import com.cxptek.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartnerAdminUserJwtClaim extends UserAuthority {
    private String email;
    private UUID partnerId;
    private List<String> authorities;
    private UserStatus status;

    @Override
    public String getIdentifier() {
        return email;
    }
}
