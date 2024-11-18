package com.cxptek.exception;

import com.cxptek.model.error.CommonErrorCode;
import com.cxptek.model.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import static com.cxptek.model.error.CommonErrorCode.DATA_NOT_FOUND;
import static com.cxptek.model.error.CommonErrorCode.EMAIL_EXISTS;
import static com.cxptek.model.error.CommonErrorCode.EMAIL_SYNTAX_INCORRECT;
import static com.cxptek.model.error.CommonErrorCode.PARAMETER_INVALID;
import static com.cxptek.model.error.CommonErrorCode.PASSWORD_SYNTAX_INCORRECT;

@Slf4j
public class UserException extends BusinessLogicException {

    public UserException(String message) {
        this(PARAMETER_INVALID, message);
    }

    public UserException(CommonErrorCode code, String message) {
        super(message, validationResponseError(code, message));
    }

    private static ErrorResponse validationResponseError(CommonErrorCode code, String message) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(String.valueOf(code))
                .message(message)
                .build();
    }

    public static UserException emailExists(String email) {
        log.error("UserException emailExists {}", email);
        return new UserException(EMAIL_EXISTS, "email exists!");
    }

    public static UserException passwordIsNotSyntacticallyCorrect() {
        return new UserException(PASSWORD_SYNTAX_INCORRECT, "The password is not syntactically correct");
    }

    public static UserException emailIsNotSyntacticallyCorrect(String email) {
        log.error("UserException emailIsNotSyntacticallyCorrect {}", email);
        return new UserException(EMAIL_SYNTAX_INCORRECT, "The email is not syntactically correct");
    }
    public static UserException notFoundUser(Long userId) {
        log.error("UserException notFoundUser {}", userId);
        return new UserException(DATA_NOT_FOUND, String.format("Not found user %s", userId));
    }
}
