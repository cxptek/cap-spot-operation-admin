package com.cxptek.repository;

import com.cxptek.entity.PartnerAdminUser;
import com.cxptek.entity.QPartnerAdminUser;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Optional;
import java.util.UUID;

public interface PartnerAdminUserRepository extends BaseRepository<PartnerAdminUser, UUID> {
    QPartnerAdminUser qPartnerAdminUser = QPartnerAdminUser.partnerAdminUser;

    Optional<PartnerAdminUser> findByEmailIgnoreCase(String email);

    static BooleanExpression byPartnerId(UUID partnerId) {
        return qPartnerAdminUser.partnerInfo.id.eq(partnerId);
    }
}
