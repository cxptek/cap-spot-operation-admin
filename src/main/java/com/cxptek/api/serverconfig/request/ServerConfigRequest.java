package com.cxptek.api.serverconfig.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServerConfigRequest {
    private String code;
    @NotBlank
    private String name;
    @NotNull
    private Short scale;
    @NotNull
    private Short scaleForDisplay;
    @NotNull
    private BigDecimal initAmount;
    @NotBlank
    private String imgUrl;
}
