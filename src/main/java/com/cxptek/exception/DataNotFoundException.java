package com.cxptek.exception;

import com.cxptek.model.error.CommonErrorCode;
import com.cxptek.model.error.ErrorResponse;
import org.springframework.http.HttpStatus;

import static com.cxptek.model.error.CommonErrorCode.DATA_NOT_FOUND;
import static com.cxptek.model.error.ErrorType.INVALID_REQUEST_ERROR;


public class DataNotFoundException extends BusinessLogicException {

    public DataNotFoundException(String message) {
        this(DATA_NOT_FOUND, message);
    }

    public DataNotFoundException(CommonErrorCode code, String message) {
        super(message, dataNotFoundResponseError(code, message));
    }


    private static ErrorResponse dataNotFoundResponseError(CommonErrorCode errorCode, String message) {
        return ErrorResponse.builder()
                .type(String.valueOf(INVALID_REQUEST_ERROR))
                .status(HttpStatus.NOT_FOUND.value())
                .code(String.valueOf(errorCode))
                .message(message)
                .build();
    }
}
