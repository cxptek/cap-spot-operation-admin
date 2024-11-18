package com.cxptek.api.partner.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PartnerUserLoginRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
