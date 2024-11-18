package com.cxptek.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@NoArgsConstructor
public enum SymbolType {
    CURRENCY_EXCHANGE_PAIR(0);

    @Setter
    private int code;

    SymbolType(int code) {
        this.code = code;
    }

    public static SymbolType of(int code) {
        return Arrays.stream(values())
                .filter(c -> c.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("unknown SymbolType code: " + code));
    }
}
