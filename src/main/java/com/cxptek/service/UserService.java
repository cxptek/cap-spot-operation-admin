package com.cxptek.service;

import com.cxptek.entity.PartnerAdminUser;
import com.cxptek.entity.PartnerInfo;
import com.cxptek.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

public interface UserService extends UserDetailsService {
    SysUser loadUserByUsername(String username) throws UsernameNotFoundException;

    PartnerAdminUser loadPartnerUserByEmail(String email) throws UsernameNotFoundException;

    PartnerAdminUser createPartnerAdminUser(String email, String rawPass, PartnerInfo partnerInfo);

    Page<PartnerAdminUser> getPartnerAdminUsers(UUID partnerId, Pageable pageable);
}
