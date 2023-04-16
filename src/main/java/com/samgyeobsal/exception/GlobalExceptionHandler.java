package com.samgyeobsal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DbException.class)
    public ResponseEntity<Object> handleDbException(DbException ex){
        return new ResponseEntity<>("db exception occur : " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = FundingInvalidException.class)
    public ResponseEntity<Object> handleFundingInvalidException(FundingInvalidException ex){
        return new ResponseEntity<>("funding invalid :" + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = KakaoMeessageException.class)
    public ResponseEntity<Object> handleKakaoMessageException(KakaoMeessageException ex){
        return new ResponseEntity<>("kakao message error : " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = LoginException.class)
    public ResponseEntity<Object> handleLoginException(LoginException ex){
        return new ResponseEntity<>("login error : " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundMemberException.class)
    public ResponseEntity<Object> handleNotFoundMemberException(NotFoundMemberException ex){
        return new ResponseEntity<>("no member exists : " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UploadFileException.class)
    public ResponseEntity<Object> handleUploadFileException(UploadFileException ex){
        return new ResponseEntity<>("file upload error :" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex){
        return new ResponseEntity<>("Runtime Exception occur : " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
