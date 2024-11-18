package com.cxptek.service;

import com.cxptek.api.authentication.LoginRequest;
import com.cxptek.api.authentication.UserLoginResponse;
import com.cxptek.exception.BusinessLogicException;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

    UserLoginResponse login(LoginRequest loginRequest) throws BusinessLogicException;

    void logout(HttpServletRequest request);

}
