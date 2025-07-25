package com.qz.userservice.exception;

public class InvalidJwtException extends Exception {
    public InvalidJwtException(String msg) {
        super(msg);
    }

    public InvalidJwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
