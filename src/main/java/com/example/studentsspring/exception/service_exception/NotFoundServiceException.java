package com.example.studentsspring.exception.service_exception;

public class NotFoundServiceException extends ServiceException {
    public NotFoundServiceException() {

    }

    public NotFoundServiceException(String message) {
        super(message);
    }

    public NotFoundServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundServiceException(Throwable cause) {
        super(cause);
    }

    public NotFoundServiceException(String message, Throwable cause, boolean enableSupperssion, boolean writableStackTrace) {
        super(message, cause, enableSupperssion, writableStackTrace);

    }
}
