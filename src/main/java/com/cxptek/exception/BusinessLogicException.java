package com.cxptek.exception;

import com.cxptek.model.error.ErrorResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessLogicException extends RuntimeException implements Serializable {

    private ErrorResponse error;

    public BusinessLogicException(ErrorResponse error) {
        super(error.getMessage());
        this.error = error;
    }

    public BusinessLogicException(String message, ErrorResponse error) {
        super(message);
        this.error = error;
    }

    public BusinessLogicException(String message, ErrorResponse error, Throwable throwable) {
        super(message, throwable);
        this.error = error;
    }
}
