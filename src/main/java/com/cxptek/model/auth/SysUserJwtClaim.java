package com.cxptek.model.auth;

import com.cxptek.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysUserJwtClaim extends UserAuthority {
    private String username;
    private List<String> authorities;
    private UserStatus status;

    @Override
    public String getIdentifier() {
        return username;
    }
}
