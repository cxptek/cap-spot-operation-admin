package com.cxptek.entity;

import com.cxptek.enums.SymbolStatus;
import com.cxptek.enums.SymbolType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name = "symbol", schema = "public", indexes = {@Index(columnList = "code,name")})
public class Symbol extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    @javax.persistence.Convert(converter = SymbolTypeConverter.class)
    private SymbolType type;
    private Long baseTokenId;
    private Long quoteTokenId;

    private BigDecimal tickSize;
    private BigDecimal lotSize;
    private BigDecimal maxSize;
    private BigDecimal minTotal;
    private BigDecimal stepSize;
    @Enumerated(EnumType.STRING)
    private SymbolStatus status;
    private BigDecimal minPrice;
    private BigDecimal initPrice;
    private Short baseTokenScale;
    private Short quoteTokenScale;
}

