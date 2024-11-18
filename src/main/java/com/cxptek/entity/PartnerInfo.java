package com.cxptek.entity;

import com.cxptek.enums.partner.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "partner_info")
@EqualsAndHashCode(callSuper = true)
public class PartnerInfo extends BaseEntity {
    @Id
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
    private String email;

    @Column(name = "verify_token_endpoint")
    private String verifyTokenEndpoint;

    @Column(name = "reserve_order_endpoint")
    private String reserveOrderEndpoint;

    @Column(name = "cancel_order_endpoint")
    private String cancelOrderEndpoint;

    @Column(name = "update_order_price_endpoint")
    private String updateOrderPriceEndpoint;

    @Column(name = "update_matching_order_endpoint")
    private String updateMatchingOrderEndpoint;

    @Column(name = "user_balance_endpoint")
    private String userBalanceEndpoint;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Status status;

//    @OneToMany(mappedBy = "partnerInfo")
//    private List<PartnerAdminUser> partnerAdminUsers;
}
