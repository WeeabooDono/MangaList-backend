package fr.weeab.mangalist.core.exception;

import fr.weeab.mangalist.core.exception.tools.ApplicationException;
import fr.weeab.mangalist.core.exception.tools.ExceptionType;

public class BadRequestException extends ApplicationException {

    private static final long serialVersionUID = -659255425388174159L;

    private static final String BAD_REQUEST_EXCEPTION_PREFIX = "error.server.bad-request.parameter.";

    public enum BadRequestExceptionType implements ExceptionType {
        PARAMETER_BAD_VALUE(BAD_REQUEST_EXCEPTION_PREFIX + "bad-value.title", BAD_REQUEST_EXCEPTION_PREFIX + "bad-value.msg", null),
        PARAMETER_MISSING(BAD_REQUEST_EXCEPTION_PREFIX + "{0}-missing.title", BAD_REQUEST_EXCEPTION_PREFIX + "{0}-missing.msg", null),
        PARAMETER_BAD_TYPE(BAD_REQUEST_EXCEPTION_PREFIX + "{0}-bad-type.title", BAD_REQUEST_EXCEPTION_PREFIX + "{0}-bad-type.msg", null);

        private final String messageKey;
        private final String titleKey;
        private final String messageCause;

        BadRequestExceptionType(String titleKey, String messageKey, String messageCause) {
            this.titleKey = titleKey;
            this.messageKey = messageKey;
            this.messageCause = messageCause;
        }

        @Override
        public String getTitleKey() {
            return this.titleKey;
        }

        @Override
        public String getMessageKey() {
            return this.messageKey;
        }

        @Override
        public String getMessageCause() {
            return this.messageCause;
        }
    }

    public BadRequestException(BadRequestExceptionType type) {
        super(type);
    }

    public BadRequestException(BadRequestExceptionType type, Object[] valueParams, Object... keyParams) {
        super(type, valueParams, keyParams);
    }

    public BadRequestException(BadRequestExceptionType type, Throwable cause, Object[] valueParams, Object... keyParams) {
        super(type, cause, valueParams, keyParams);
    }

    public BadRequestException(BadRequestExceptionType type, Throwable cause) {
        super(type, cause);
    }

    public BadRequestException(BadRequestExceptionType type, String message, Throwable cause, Object... keyParams) {
        super(type, message, cause, keyParams);
    }

    public BadRequestException(BadRequestExceptionType type, Throwable cause, Object... valueParams) {
        super(type, cause, valueParams);
    }

    public BadRequestException(BadRequestExceptionType type, String message) {
        super(type, message);
    }
}
