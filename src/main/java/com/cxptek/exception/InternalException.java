package com.cxptek.exception;

import com.cxptek.model.error.CommonErrorCode;
import com.cxptek.model.error.ErrorResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@EqualsAndHashCode(callSuper = true)
@Data
public class InternalException extends Exception implements Serializable {

    private ErrorResponse error;

    public InternalException(ErrorResponse error) {
        super(error.getMessage());
        this.error = error;
    }

    public InternalException(String message, ErrorResponse error, Throwable throwable) {
        super(message, throwable);
        this.error = error;
    }

    public static InternalException internalExceptionResponseError(CommonErrorCode errorCode, String message) {
        var error = ErrorResponse.builder()
                .status(INTERNAL_SERVER_ERROR.value())
                .type(INTERNAL_SERVER_ERROR.name())
                .code(errorCode.name())
                .message(message)
                .build();
        return new InternalException(error);
    }
}
