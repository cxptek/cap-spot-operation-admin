package com.cxptek.exception;

import com.cxptek.model.error.CommonErrorCode;
import com.cxptek.model.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import static com.cxptek.model.error.CommonErrorCode.DATA_DUPLICATED;
import static com.cxptek.model.error.CommonErrorCode.DATA_NOT_FOUND;
import static com.cxptek.model.error.CommonErrorCode.PARAMETER_INVALID;

@Slf4j
public class TokenException extends BusinessLogicException {

    public TokenException(String message) {
        this(PARAMETER_INVALID, message);
    }

    public TokenException(CommonErrorCode code, String message) {
        super(message, validationResponseError(code, message));
    }

    private static ErrorResponse validationResponseError(CommonErrorCode code, String message) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(String.valueOf(code))
                .message(message)
                .build();
    }

    public static TokenException notFoundToken(Long tokenId) {
        log.error("TokenException notFoundToken {}", tokenId);
        return new TokenException(DATA_NOT_FOUND, String.format("Not found token by id %s", tokenId));
    }

    public static TokenException notFoundTokenByCode(String tokenCode) {
        log.error("TokenException notFoundTokenByCode {}", tokenCode);
        return new TokenException(DATA_NOT_FOUND, String.format("Not found token by code %s", tokenCode));
    }
    public static TokenException tokenExisted(String tokenCode) {
        log.error("TokenException tokenExisted {}", tokenCode);
        return new TokenException(DATA_DUPLICATED, String.format("The token %s has already existed.", tokenCode));
    }
}
