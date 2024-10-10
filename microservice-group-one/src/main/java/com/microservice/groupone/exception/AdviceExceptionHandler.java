package com.microservice.groupone.exception;

import com.microservice.groupone.dto.response.ResponseMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseMessageDto> resourceNotFoundException(ResourceNotFoundException err){
        return new ResponseEntity<>(new ResponseMessageDto(err.getMessage()), HttpStatus.NOT_FOUND);
    }

}
