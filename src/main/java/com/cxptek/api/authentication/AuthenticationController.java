package com.cxptek.api.authentication;

import com.cxptek.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@Tag(name = "CEX Admin Authentication Controller", description = "Authentication For System User")
public class AuthenticationController {
//    private final UserMapper userMapper;
    private final AuthService authService;

    @PostMapping("/login")
    public UserLoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

//    @GetMapping(value = "/me")
//    @SecurityRequirement(name = "Bearer Authentication")
//    public UserResponse getMe(Authentication authentication) throws BusinessLogicException {
//        var user = authService.getUserLoginInfo(authentication);
//        return userMapper.toUserResponse(user);
//    }


    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "Bearer Authentication")
    public void logout(HttpServletRequest request) {
        authService.logout(request);
    }
}

