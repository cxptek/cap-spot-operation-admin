package com.cxptek.entity;

import com.cxptek.enums.TokenStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "token", schema = "public", indexes = {@Index(columnList = "code,name")})
public class Token extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID externalId;
    private String code;
    private String name;
    private Short scale;
    @Enumerated(EnumType.STRING)
    private TokenStatus status;
    private String imgUrl;
    private Short scaleForDisplay;
}
