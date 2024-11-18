package com.cxptek.model.token;

import com.cxptek.enums.TokenStatus;
import com.cxptek.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TokenModel extends BaseModel {
    private Long id;
    private String code;
    private String name;
    private Short scale;
    private TokenStatus status;
    private String imgUrl;
    private Short scaleForDisplay;
}
