package com.cxptek.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(schema = "public", indexes = {@Index(columnList = "partnerId,symbolCode")})
public class TradingPair extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String partnerId;
    private String symbolCode;
    private Boolean active;
    private BigDecimal customMakerFee = BigDecimal.ZERO;
    private BigDecimal customTakerFee = BigDecimal.ZERO;
    private Date customFeeDateStart;
    private Date customFeeDateEnd;
}
