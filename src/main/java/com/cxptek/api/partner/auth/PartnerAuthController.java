package com.cxptek.api.partner.auth;

import com.cxptek.api.authentication.UserLoginResponse;
import com.cxptek.service.PartnerAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/partner/auth")
@Tag(name = "Partner Admin Authentication Controller", description = "Authentication For Partner Admin")
public class PartnerAuthController {
    private final PartnerAuthService partnerAuthService;
    @PostMapping("/login")
    public UserLoginResponse login(@Valid @RequestBody PartnerUserLoginRequest request) {
        return partnerAuthService.login(request);
    }
}
