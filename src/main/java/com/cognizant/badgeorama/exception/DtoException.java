package com.cognizant.badgeorama.exception;

public class DtoException extends Exception {

    public DtoException() {
    }

    public DtoException(String message) {
        super(message);
    }

    public DtoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DtoException(Throwable cause) {
        super(cause);
    }

}
