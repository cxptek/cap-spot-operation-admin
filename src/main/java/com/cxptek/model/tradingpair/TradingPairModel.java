package com.cxptek.model.tradingpair;

import com.cxptek.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class TradingPairModel extends BaseModel {
    private Long id;
    private String partnerId;
    private String symbolCode;
    private Boolean active;
    private BigDecimal customMakerFee;
    private BigDecimal customTakerFee;
    private Date customFeeDateStart;
    private Date customFeeDateEnd;
}
