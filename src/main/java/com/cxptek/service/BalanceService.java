package com.cxptek.service;

import com.cxptek.exception.BusinessLogicException;
import com.cxptek.model.balance.BalanceLiteModel;

import java.math.BigDecimal;

public interface BalanceService {
    BalanceLiteModel depositBalance(Long userId, Long tokenId, BigDecimal amount) throws BusinessLogicException;
}
