package com.samgyeobsal.exception;

public class UploadFileException extends RuntimeException{

    public UploadFileException() {
        super();
    }

    public UploadFileException(String message) {
        super(message);
    }

    public UploadFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
