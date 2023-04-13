package com.samgyeobsal.exception;

public class FundingInvalidException extends RuntimeException{

    public FundingInvalidException() {
        super();
    }

    public FundingInvalidException(String message) {
        super(message);
    }

    public FundingInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
