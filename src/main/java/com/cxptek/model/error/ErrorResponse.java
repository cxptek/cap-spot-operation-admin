package com.cxptek.model.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
        ignoreUnknown = true)
public class ErrorResponse {
    @JsonProperty(
            index = 1)
    private int status;
    @JsonProperty(
            index = 2)
    private String type;
    @JsonProperty(
            index = 3)
    private String code;
    @JsonProperty(
            index = 4)
    private String message;
    @JsonProperty(
            index = 5)
    private Parameter parameter;

    public static ErrorResponse generalValidationError(String message) {
        return validationResponseError(CommonErrorCode.PARAMETER_INVALID, message);
    }

    public static ErrorResponse generalValidationError(String message, Parameter parameter) {
        return validationResponseError(HttpStatus.BAD_REQUEST, CommonErrorCode.PARAMETER_INVALID, message, parameter);
    }

    public static ErrorResponse validationResponseError(CommonErrorCode errorCode, String message) {
        return validationResponseError(HttpStatus.BAD_REQUEST, errorCode, message, (Parameter) null);
    }

    public static ErrorResponse validationResponseError(CommonErrorCode errorCode, String message, Parameter parameter) {
        return validationResponseError(HttpStatus.BAD_REQUEST, errorCode, message, parameter);
    }

    public static ErrorResponse validationResponseError(HttpStatus status, CommonErrorCode errorCode, String message,
            Parameter parameter) {
        return builder().type(ErrorType.INVALID_REQUEST_ERROR.name()).status(status.value()).code(errorCode.name())
                .message(message).parameter(parameter).build();
    }

    public static ErrorResponse internalExceptionResponseError(CommonErrorCode errorCode, String message) {
        return builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).type(ErrorType.INTERNAL_ERROR.name())
                .code(errorCode.name()).message(message).build();
    }

    ErrorResponse(final int status, final String type, final String code, final String message, final Parameter parameter) {
        this.status = status;
        this.type = type;
        this.code = code;
        this.message = message;
        this.parameter = parameter;
    }

    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }

    @JsonProperty(
            index = 1)
    public void setStatus(final int status) {
        this.status = status;
    }

    @JsonProperty(
            index = 2)
    public void setType(final String type) {
        this.type = type;
    }

    @JsonProperty(
            index = 3)
    public void setCode(final String code) {
        this.code = code;
    }

    @JsonProperty(
            index = 4)
    public void setMessage(final String message) {
        this.message = message;
    }

    @JsonProperty(
            index = 5)
    public void setParameter(final Parameter parameter) {
        this.parameter = parameter;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ErrorResponse)) {
            return false;
        } else {
            ErrorResponse other = (ErrorResponse) o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getStatus() != other.getStatus()) {
                return false;
            } else {
                label61: {
                    Object this$type = this.getType();
                    Object other$type = other.getType();
                    if (this$type == null) {
                        if (other$type == null) {
                            break label61;
                        }
                    } else if (this$type.equals(other$type)) {
                        break label61;
                    }

                    return false;
                }

                label54: {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label54;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label54;
                    }

                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                Object this$parameter = this.getParameter();
                Object other$parameter = other.getParameter();
                if (this$parameter == null) {
                    if (other$parameter != null) {
                        return false;
                    }
                } else if (!this$parameter.equals(other$parameter)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ErrorResponse;
    }

    public int hashCode() {
        int result = 1;
        result = result * 59 + this.getStatus();
        Object $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $parameter = this.getParameter();
        result = result * 59 + ($parameter == null ? 43 : $parameter.hashCode());
        return result;
    }

    public String toString() {
        int var10000 = this.getStatus();
        return "ErrorResponse(status=" + var10000 + ", type=" + this.getType() + ", code=" + this.getCode() + ", message="
                + this.getMessage() + ", parameter=" + String.valueOf(this.getParameter()) + ")";
    }

    public static class ErrorResponseBuilder {
        private int status;
        private String type;
        private String code;
        private String message;
        private Parameter parameter;

        ErrorResponseBuilder() {}

        @JsonProperty(
                index = 1)
        public ErrorResponseBuilder status(final int status) {
            this.status = status;
            return this;
        }

        @JsonProperty(
                index = 2)
        public ErrorResponseBuilder type(final String type) {
            this.type = type;
            return this;
        }

        @JsonProperty(
                index = 3)
        public ErrorResponseBuilder code(final String code) {
            this.code = code;
            return this;
        }

        @JsonProperty(
                index = 4)
        public ErrorResponseBuilder message(final String message) {
            this.message = message;
            return this;
        }

        @JsonProperty(
                index = 5)
        public ErrorResponseBuilder parameter(final Parameter parameter) {
            this.parameter = parameter;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this.status, this.type, this.code, this.message, this.parameter);
        }

        public String toString() {
            int var10000 = this.status;
            return "ErrorResponse.ErrorResponseBuilder(status=" + var10000 + ", type=" + this.type + ", code=" + this.code
                    + ", message=" + this.message + ", parameter=" + String.valueOf(this.parameter) + ")";
        }
    }
}
