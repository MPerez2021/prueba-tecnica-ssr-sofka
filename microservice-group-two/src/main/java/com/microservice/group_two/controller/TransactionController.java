package com.microservice.group_two.controller;

import com.microservice.group_two.dto.request.CreateTransactionDto;
import com.microservice.group_two.dto.response.ResponseMessageDto;
import com.microservice.group_two.entity.Transaction;
import com.microservice.group_two.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {


    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<ResponseMessageDto> createTransaction(@RequestBody CreateTransactionDto createTransactionDto) throws Exception {
        transactionService.createTransaction(createTransactionDto);
        return new ResponseEntity<>(new ResponseMessageDto("Transaction completed"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseMessageDto> updateTransaction(@RequestBody Transaction transaction) {
        transactionService.updateTransaction(transaction);
        return new ResponseEntity<>(new ResponseMessageDto("Information updated"), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findTransactionById(@PathVariable String id) {
        return ResponseEntity.ok(transactionService.findTransactionById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessageDto> deleteTransactionById(@PathVariable String id) {
        transactionService.deleteTransactionById(id);
        return new ResponseEntity<>(new ResponseMessageDto("Transaction has been deleted"), HttpStatus.OK);
    }
}
