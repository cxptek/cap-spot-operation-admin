package com.cxptek.model.error;
/*
 NOTE: Please don't remove break line, I think we need break line to easy reading this enum
*/

public enum CommonErrorCode {
    HEADER_INVALID,
    UNAUTHORIZED,
    FORBIDDEN,
    //
    PASSWORD_SYNTAX_INCORRECT,
    EMAIL_SYNTAX_INCORRECT,
    EMAIL_EXISTS,
    // common
    INTERNAL_SERVICE_ERROR,
    DATA_NOT_FOUND,
    DATA_DUPLICATED,
    PARAMETER_INVALID,
}
