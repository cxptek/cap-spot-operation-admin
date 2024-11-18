package com.cxptek.exception;

import com.cxptek.model.error.CommonErrorCode;
import com.cxptek.model.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import static com.cxptek.model.error.CommonErrorCode.UNAUTHORIZED;

@Slf4j
public class AuthException extends BusinessLogicException {

    public AuthException(String message) {
        this(UNAUTHORIZED, message);
    }

    public AuthException(CommonErrorCode code, String message) {
        super(message, validationResponseError(code, message));
    }

    private static ErrorResponse validationResponseError(CommonErrorCode code, String message) {
        return ErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .code(String.valueOf(code))
                .message(message)
                .build();
    }

    public static AuthException requiredLogin() {
        return new AuthException(UNAUTHORIZED, "Required Login!");
    }

}
