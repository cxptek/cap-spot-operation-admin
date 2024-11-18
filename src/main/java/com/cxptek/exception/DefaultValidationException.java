package com.cxptek.exception;

import com.cxptek.model.error.CommonErrorCode;
import com.cxptek.model.error.ErrorResponse;
import org.springframework.http.HttpStatus;

import static com.cxptek.model.error.CommonErrorCode.PARAMETER_INVALID;
import static com.cxptek.model.error.ErrorType.INVALID_REQUEST_ERROR;


public class DefaultValidationException extends BusinessLogicException {

    public DefaultValidationException(String message) {
        this(PARAMETER_INVALID, message);
    }

    public DefaultValidationException(CommonErrorCode code, String message) {
        super(message, validationResponseError(code, message));
    }


    private static ErrorResponse validationResponseError(CommonErrorCode errorCode,
            String message) {
        return ErrorResponse.builder()
                .type(String.valueOf(INVALID_REQUEST_ERROR))
                .status(HttpStatus.BAD_REQUEST.value())
                .code(String.valueOf(errorCode))
                .message(message)
                .build();
    }
}
