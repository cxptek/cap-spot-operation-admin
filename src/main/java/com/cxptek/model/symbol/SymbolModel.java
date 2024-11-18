package com.cxptek.model.symbol;

import com.cxptek.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SymbolModel extends BaseModel {
    private Long id;
    private String code;
    private String name;
    private String type;
    private Long baseTokenId;
    private Long quoteTokenId;
    private BigDecimal lotSize;
    private BigDecimal stepSize;
    private BigDecimal tickSize;
    private Short baseTokenScale;
    private Short quoteTokenScale;
    private BigDecimal maxSize;
    private BigDecimal minTotal;
    private BigDecimal minPrice;
    private BigDecimal initPrice;
}
