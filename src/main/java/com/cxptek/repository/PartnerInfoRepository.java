package com.cxptek.repository;

import com.cxptek.entity.PartnerInfo;
import com.cxptek.entity.QPartnerInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PartnerInfoRepository extends BaseRepository<PartnerInfo, UUID> {
    Page<PartnerInfo> findByNameContainsIgnoreCase(String name, Pageable pageable);
}
