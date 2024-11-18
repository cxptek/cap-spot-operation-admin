package com.cxptek.api.tradingpair.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class TradingPairResponse implements Serializable {
    private Long id;
    private String partnerId;
    private String symbolCode;
    private Boolean active;
    private BigDecimal customMakerFee;
    private BigDecimal customTakerFee;
    private Date customFeeDateStart;
    private Date customFeeDateEnd;
}
