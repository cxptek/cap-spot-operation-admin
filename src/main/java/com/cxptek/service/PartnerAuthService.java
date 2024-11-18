package com.cxptek.service;

import com.cxptek.api.authentication.LoginRequest;
import com.cxptek.api.authentication.UserLoginResponse;
import com.cxptek.api.partner.auth.PartnerUserLoginRequest;
import com.cxptek.exception.BusinessLogicException;
import jakarta.servlet.http.HttpServletRequest;

public interface PartnerAuthService {

    UserLoginResponse login(PartnerUserLoginRequest loginRequest) throws BusinessLogicException;

    void logout(HttpServletRequest request);

}
