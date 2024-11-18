package com.cxptek.api.user.request;

import com.cxptek.model.request.BaseRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class FundRequest extends BaseRequest {

    @NotBlank
    private Long userId;
    @NotBlank
    private String tokenCode;
    @NotBlank
    private BigDecimal amount;
}
