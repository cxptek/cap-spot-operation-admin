package com.cxptek.config;

import com.cxptek.exception.AuthException;
import com.cxptek.exception.BusinessLogicException;
import com.cxptek.exception.InternalException;
import com.cxptek.model.error.CommonErrorCode;
import com.cxptek.model.error.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.naming.ServiceUnavailableException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class RestExceptionAdviceConfig {
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerConstraintViolationException(ConstraintViolationException ex) {
        return ErrorResponse.builder()
                .code(CommonErrorCode.PARAMETER_INVALID.name())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage()).build();
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAuthorizationException(Throwable e) {
        return ErrorResponse.builder()
                .code(CommonErrorCode.FORBIDDEN.name())
                .status(HttpStatus.FORBIDDEN.value())
                .message(getRootCauseMessage(e))
                .build();
    }

    @ExceptionHandler({ AuthException.class, BadCredentialsException.class, UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleAuthException(Throwable e) {
        return ErrorResponse.builder()
                .code(CommonErrorCode.UNAUTHORIZED.name())
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(getRootCauseMessage(e))
                .build();
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public ErrorResponse handleServiceUnavailableException(
            ServiceUnavailableException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.GATEWAY_TIMEOUT.value()).message(ex.getMessage()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        return ErrorResponse.builder()
                .code(CommonErrorCode.PARAMETER_INVALID.name())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(details.getFirst()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessLogicException.class)
    public ErrorResponse handleBusinessLogicException(BusinessLogicException exception) {
        return exception.getError();
    }

    @ExceptionHandler(InternalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalException(
            InternalException ex) {
        return ErrorResponse.builder()
                .code(CommonErrorCode.INTERNAL_SERVICE_ERROR.name())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage()).build();
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMediaTypeException(Exception ex) {
        var rootCauseMsg = ExceptionUtils.getRootCause(ex).getMessage();
        return ErrorResponse.builder()
                .code(CommonErrorCode.HEADER_INVALID.name())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(rootCauseMsg).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception ex) {
        var rootCauseMsg = ExceptionUtils.getRootCause(ex).getMessage();
        log.error("handleException err >>>>>> {}", rootCauseMsg);
        return ErrorResponse.builder()
                .code(CommonErrorCode.INTERNAL_SERVICE_ERROR.name())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Internal Server Error").build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        var rootCauseMsg = ExceptionUtils.getRootCause(ex).getMessage();
        return ErrorResponse.builder()
                .code(CommonErrorCode.DATA_DUPLICATED.name())
                .status(HttpStatus.CONFLICT.value())
                .message(rootCauseMsg)
                .build();
    }


}
