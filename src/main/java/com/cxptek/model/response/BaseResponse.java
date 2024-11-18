package com.cxptek.model.response;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class BaseResponse<T> {
    private String message;
    private T data;
}
