package com.example.studentsspring.exception.service_exception;

public class ServiceException extends Exception {
    public ServiceException(){

    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }

    public ServiceException(Throwable cause){
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSupperssion,boolean writableStackTrace){
        super(message, cause, enableSupperssion, writableStackTrace);
    }
}
