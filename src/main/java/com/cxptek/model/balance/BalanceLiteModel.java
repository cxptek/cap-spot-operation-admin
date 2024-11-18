package com.cxptek.model.balance;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BalanceLiteModel {
    private Long tokenId;
    private String tokenCode;
    private String tokenName;
    private Long userId;
    private BigDecimal amount;
    private BigDecimal availableAmount;
}
