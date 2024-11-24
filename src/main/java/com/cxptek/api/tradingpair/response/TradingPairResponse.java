package com.cxptek.api.tradingpair.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TradingPairResponse implements Serializable {
    private Long id;
    private String partnerId;
    private String symbolCode;
    private Boolean active;
    private BigDecimal makerFee;
    private BigDecimal takerFee;
}
