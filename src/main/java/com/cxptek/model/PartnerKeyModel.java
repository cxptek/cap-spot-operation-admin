package com.cxptek.model;

import com.cxptek.enums.partner.KeyStatus;
import com.cxptek.enums.partner.KeyType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PartnerKeyModel extends BaseModel {
    private Long id;
    private KeyType type;
    private String alias;
    private String refId;
    private KeyStatus status;
    private KeyPair keyPair;
}
