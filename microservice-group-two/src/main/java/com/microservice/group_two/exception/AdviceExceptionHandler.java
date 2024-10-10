package com.microservice.group_two.exception;

import com.microservice.group_two.dto.response.ResponseMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceExceptionHandler {

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ResponseMessageDto> insufficientBalanceException(InsufficientBalanceException err){
        return new ResponseEntity<>(new ResponseMessageDto(err.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(TransactionMinAmountException.class)
    public ResponseEntity<ResponseMessageDto> transactionMinAmountException(){
        return new ResponseEntity<>(new ResponseMessageDto("The amount must be greater than $0"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseMessageDto> resourceNotFoundException(ResourceNotFoundException err){
        return new ResponseEntity<>(new ResponseMessageDto(err.getMessage()), HttpStatus.NOT_FOUND);
    }
}
