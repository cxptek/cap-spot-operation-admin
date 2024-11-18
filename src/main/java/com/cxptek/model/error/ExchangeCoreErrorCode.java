package com.cxptek.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExchangeCoreErrorCode {
    INSUFFICIENT_BALANCE_ACCOUNT(2001, "INSUFFICIENT_BALANCE_ACCOUNT");

    private final int code;
    private final String message;

}
