package com.cxptek.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(indexes = {@Index(columnList = "partnerId,symbolCode")})
public class TradingPair extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String partnerId;
    private String symbolCode;
    private Boolean active;
    private BigDecimal makerFee = BigDecimal.ZERO;
    private BigDecimal takerFee = BigDecimal.ZERO;
}
