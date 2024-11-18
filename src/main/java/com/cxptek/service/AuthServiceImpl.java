package com.cxptek.service;

import com.cxptek.api.authentication.LoginRequest;
import com.cxptek.api.authentication.UserLoginResponse;
import com.cxptek.config.JwtProvider;
import com.cxptek.entity.SysUser;
import com.cxptek.model.auth.SysUserJwtClaim;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserLoginResponse login(LoginRequest loginRequest) {
        var authenticationInput = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        var authentication = authenticationManager.authenticate(authenticationInput);

        // TODO: Need enhance later
        var subject = loginRequest.getUsername();
        var claims = toJwtClaim((SysUser) authentication.getPrincipal());
        var token = jwtProvider.generateJwtAccessToken(subject, claims);

        return UserLoginResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public void logout(HttpServletRequest request) {
    }

    private SysUserJwtClaim toJwtClaim(SysUser user) {
        var claim = new SysUserJwtClaim();
        claim.setUsername(user.getUsername());
        claim.setAuthorities(user
                .getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .collect(toList()));
        claim.setStatus(user.getStatus());
        return claim;
    }
}
