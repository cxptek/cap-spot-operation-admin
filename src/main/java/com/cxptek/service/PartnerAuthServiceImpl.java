package com.cxptek.service;

import com.cxptek.api.authentication.UserLoginResponse;
import com.cxptek.api.partner.auth.PartnerUserLoginRequest;
import com.cxptek.config.JwtProvider;
import com.cxptek.entity.PartnerAdminUser;
import com.cxptek.exception.AuthException;
import com.cxptek.model.auth.PartnerAdminUserJwtClaim;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class PartnerAuthServiceImpl implements PartnerAuthService {
    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserLoginResponse login(PartnerUserLoginRequest loginRequest) {
        var userDetails = userService.loadPartnerUserByEmail(loginRequest.getEmail());
        var isValidPassword = passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword());

        if (!isValidPassword) {
            throw new AuthException("Invalid email or password");
        }

        var subject = loginRequest.getEmail();
        var claims = toJwtClaim(userDetails);
        var token = jwtProvider.generateJwtAccessToken(subject, claims);

        return UserLoginResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public void logout(HttpServletRequest request) {
    }

    private PartnerAdminUserJwtClaim toJwtClaim(PartnerAdminUser user) {
        var claim = new PartnerAdminUserJwtClaim();
        claim.setEmail(user.getEmail());
        claim.setPartnerId(user.getPartnerInfo().getId());
        claim.setAuthorities(user
                .getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .collect(toList()));
        claim.setStatus(user.getStatus());
        return claim;
    }
}
