package com.cxptek.service;

import com.cxptek.constant.Constant;
import com.cxptek.entity.PartnerAdminUser;
import com.cxptek.entity.PartnerInfo;
import com.cxptek.entity.SysUser;
import com.cxptek.enums.UserStatus;
import com.cxptek.exception.BusinessLogicException;
import com.cxptek.exception.UserException;
import com.cxptek.repository.PartnerAdminUserRepository;
import com.cxptek.repository.RoleRepository;
import com.cxptek.repository.SysUserRepository;
import com.cxptek.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import static com.cxptek.utils.AppUtils.updateAuditInfo;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;
    private final SysUserRepository sysUserRepository;
    private final UserRepository userRepository;
    private final ApplicationContext applicationContext;
    private final PartnerAdminUserRepository partnerAdminUserRepository;

    @Override
//    @Cacheable(value = SYS_USER_CACHE, key = "#username", unless = "#result == null")
    public SysUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return sysUserRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("Invalid user"));
    }

    @Override
//    @Cacheable(value = PARTNER_ADMIN_USER_CACHE, key = "#email", unless = "#result == null")
    public PartnerAdminUser loadPartnerUserByEmail(String email) throws UsernameNotFoundException {
        return partnerAdminUserRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new UsernameNotFoundException("Invalid user"));
    }

    @Override
    public PartnerAdminUser createPartnerAdminUser(String email,
                                                   String rawPass,
                                                   PartnerInfo partnerInfo) {
        validateCreateUserInputData(email, rawPass);

        var partnerRole = roleRepository.findAllByName(Constant.ROLE_PARTNER);
        var passwordEncoder = applicationContext.getBean(PasswordEncoder.class);

        var newUser = new PartnerAdminUser();
        newUser.setId(UUID.randomUUID());
        newUser.setEmail(email);
        newUser.setRoles(Set.of(partnerRole));
        newUser.setPartnerInfo(partnerInfo);
        newUser.setStatus(UserStatus.ACTIVE);
        newUser.setPassword(passwordEncoder.encode(rawPass));

        updateAuditInfo(newUser, true);

        return partnerAdminUserRepository.save(newUser);
    }

    @Override
    public Page<PartnerAdminUser> getPartnerAdminUsers(UUID partnerId, Pageable pageable) {
        return partnerAdminUserRepository.findAll(PartnerAdminUserRepository.byPartnerId(partnerId), pageable);
    }

    private void validateCreateUserInputData(String email, String password) throws BusinessLogicException {
        // validate password
        var patternPassword = Pattern.compile(Constant.PASSWORD_REGEX, Pattern.CASE_INSENSITIVE);
        if (!patternPassword.matcher(password).matches()) {
            throw UserException.passwordIsNotSyntacticallyCorrect();
        }

        // validate if email exists
        var patternEmail = Pattern.compile(Constant.EMAIL_REGEX);
        if (!patternEmail.matcher(email).matches()) {
            throw UserException.emailIsNotSyntacticallyCorrect(email);
        }

        var exists = partnerAdminUserRepository.findByEmailIgnoreCase(email).isPresent();
        if (exists) {
            throw UserException.emailExists(email);
        }
    }
}
